package doan.quanlykho.be.service.impl;

import doan.quanlykho.be.entity.Supplier;
import doan.quanlykho.be.exception.AlreadyExistsException;
import doan.quanlykho.be.helper.ExcelHelper;
import doan.quanlykho.be.repository.ISupplierRepo;
import doan.quanlykho.be.security.jwt.util.Utils;
import doan.quanlykho.be.service.ISupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService implements ISupplierService {

    private final ISupplierRepo supplierRepo;

    private final Utils utils;

    @Override
    public Page<Supplier> findAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        if (sortDir != null) {
            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
            return supplierRepo.findAll(PageRequest.of(pageNumber - 1, pageSize, sort));
        }
        return supplierRepo.findAll(PageRequest.of(pageNumber - 1, pageSize));
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepo.findAllByIsDelete();
    }

    @Override
    public Supplier create(Supplier request, BindingResult bindingResult) {
        supplierRepo.findByPhone(request.getPhone()).ifPresent(e -> {
            throw new AlreadyExistsException("Số điện thoại đã tồn tại");
        });
        supplierRepo.findByEmail(request.getEmail()).ifPresent(e -> {
            throw new AlreadyExistsException("Email đã tồn tại");
        });
        supplierRepo.findByCode(request.getCode()).ifPresent(e -> {
            throw new AlreadyExistsException("Mã nhà cũng cấp đã tồn tại");
        });
        if (bindingResult.hasErrors()) {
            throw utils.invalidInputException(bindingResult);
        }
        request.setCode(getNewCode());
        return supplierRepo.save(request);

    }

    @Override
    public void save(MultipartFile file, Integer AccountId) {
        try {
            List<Supplier> suppliers = ExcelHelper.excelToSuppliers(file.getInputStream());
            suppliers.forEach(supplier -> supplier.setAccountId(AccountId));
            supplierRepo.saveAll(suppliers);
        } catch (Exception e) {
            throw new AlreadyExistsException("fail to store excel data");
        }
    }

    @Override
    public ByteArrayInputStream loadExcel() {
        List<Supplier> tutorials = supplierRepo.findAll();
        ByteArrayInputStream in = ExcelHelper.supplierToExcel(tutorials);
        return in;
    }

    @Override
    public Supplier findById(int id) {
        return supplierRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found: " + id));
    }

    @Override
    public Supplier update(Supplier request, BindingResult bindingResult) {
        var t = supplierRepo.findById(request.getId()).orElseThrow(() -> new IllegalArgumentException(("id not found: " + request.getId())));
        if (!t.getPhone().equals(request.getPhone())) {
            supplierRepo.findByPhone(request.getPhone()).ifPresent(e -> {
                throw new AlreadyExistsException("phone number has already exits");
            });
        } else if (!t.getEmail().equals(request.getEmail())) {
            supplierRepo.findByEmail(request.getEmail()).ifPresent(e -> {
                throw new AlreadyExistsException("email has already exits");
            });
        }
        BindingResult result = utils.getListResult(bindingResult, request);
        if (result.hasErrors()) {
            throw utils.invalidInputException(result);
        } else {
            request.setCreatedAt(t.getCreatedAt());
            return supplierRepo.save(request);
        }
    }

    @Override
    public void softDeleteAllIds(List<Integer> listId) {
        listId.forEach(id -> supplierRepo.findById(id).orElseThrow(() -> new IllegalArgumentException(("id not found: " + id))));
        supplierRepo.softDeleteAllIds(listId);
    }

    @Override
    public void updateStatusFalseTransaction(List<Integer> listId) {
        listId.forEach(id -> supplierRepo.findById(id).orElseThrow(() -> new IllegalArgumentException(("id not found: " + id))));
        supplierRepo.updateStatusFalseTransaction(listId);
    }

    @Override
    public void updateStatusTrueTransaction(List<Integer> listId) {
        listId.forEach(id -> supplierRepo.findById(id).orElseThrow(() -> new IllegalArgumentException(("id not found: " + id))));
        supplierRepo.updateStatusTrueTransaction(listId);
    }

    public String getNewCode() {
        String newCode = "SUP";
        Supplier supplier = supplierRepo.getTop();
        if (supplier == null) return "SUP1";
        newCode = newCode + (supplier.getId() + 1);
        return newCode;
    }

}