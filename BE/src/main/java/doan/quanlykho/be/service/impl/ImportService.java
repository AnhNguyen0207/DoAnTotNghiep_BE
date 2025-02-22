package doan.quanlykho.be.service.impl;

import doan.quanlykho.be.dto.response.ImportInvoice.*;
import doan.quanlykho.be.entity.*;
import doan.quanlykho.be.repository.*;
import doan.quanlykho.be.service.IDetailsImportService;
import doan.quanlykho.be.service.IImportService;
import doan.quanlykho.be.service.IInventoriesProductVariantService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ImportService implements IImportService {

    private final IImportRepo importRepo;

    private final ImportStatusService importStatusService;

    private final IStatusRepo statusRepo;
    private final ISupplierRepo supplierRepo;

    private final IAccountsRepo accountsRepo;
    private final InventoryRepository inventoryRepository;
    private final EntityManager entityManager;

    private EmployeeRepository employeeRepository;

    private final IDetailsImportService detailsImportService;

    private final IReturnImportRepo returnImportRepo;

    private final JdbcTemplate jdbcTemplate;

    private final ModelMapper modelMapper;
    private final IInventoriesProductVariantService inventoriesProductVariantService;

    @Override
    public List<Import> findAll() {
        return importRepo.findAll();
    }

    @Override
    public List<ImportResponse> findAllImportDTO(String searchValue) {
        List<Object[]> findAllImport = importRepo.getImportFilter(searchValue);
        List<ImportResponse> importResponses = new ArrayList<>();
        findAllImport.forEach(object -> {
            importResponses.add(new ImportResponse(
                    (String) object[0],
                    (String) object[1],
                    (String) object[2],
                    (BigDecimal) object[3],
                    (Boolean) object[4],
                    (Boolean) object[5],
                    (Boolean) object[6],
                    (Boolean) object[7],
                    (String) object[8],
                    (String) object[9]
            ));
        });
        return importResponses;
    }

    @Override
    public Import save(Import importField) {
        importField.setCode(getNewCode());
        Import anImport = importRepo.save(importField);
        updateStatus(anImport.getId(), "IMPORT01", importField.getAccountId());
        return anImport;
    }

    public boolean checkStatusExitsInImport(Integer importId, Integer statusId) {
        if (statusId == 5) {
            return true;
        }
        var statusInImport = importStatusService.findByImportIdAndStatusId(importId, statusId);
        return statusInImport == null;
    }

    public void updateStatus(Integer importId, String code, Integer accountId) {
        ImportsStatus importsStatus = new ImportsStatus();
        Integer statusId = statusRepo.findByCode(code).getId();
        if (checkStatusExitsInImport(importId, statusId)) {
            importsStatus.setImportId(importId);
            importsStatus.setStatusId(statusId);
            importsStatus.setAccount_id(accountId);
            importsStatus.setCreateAt(Timestamp.from(Instant.now()));
            importStatusService.save(importsStatus);
        }
    }


    @Override
    public void updateStatusImport(Integer importId, String chooses, Integer accountId) {
        Import anImport = importRepo.findById(importId).orElseThrow(() -> new IllegalArgumentException(("id not found: " + importId)));
        switch (chooses) {
            case "paidPayment": {
                updateStatus(importId, "IMPORT02", accountId);
                anImport.setIsPaid(true);
                if (anImport.getIsImport() && anImport.getIsPaid()) {
                    anImport.setIsDone(true);
                }
                anImport.setIsImport(false);
                break;
            }
            case "importWarehouse": {
                updateStatus(importId, "IMPORT03", accountId);
                anImport.setIsImport(true);
                break;
            }

            case "paidPaymentAndImportWarehouse": {
                updateStatus(importId, "IMPORT02", accountId);
                anImport.setIsImport(true);
                updateStatus(importId, "IMPORT03", accountId);
                anImport.setIsPaid(true);
                break;
            }
        }
        if (anImport.getIsImport()) {
            ;
            inventoriesProductVariantService.importProductVariantToInventory(anImport.getDetailsImports(), anImport.getInventoryId());
        }
        if (anImport.getIsDone()) {
            anImport.setIsImport(true);
        }
        if (anImport.getIsImport() && anImport.getIsPaid()) {
            anImport.setIsDone(true);
        }

        importRepo.save(anImport);
    }

    @Override
    public void updateStatusImportReturn(Integer importId, String chooses, Integer accountId) {
        Import anImport = importRepo.findById(importId).orElseThrow(() -> new IllegalArgumentException(("id not found: " + importId)));
        updateStatus(importId, "IMPORT04", accountId);
        anImport.setIsReturn(true);
        importRepo.save(anImport);

    }


    @Override
    public DetailsImportsInvoiceResponse getDetailInvoiceByCode(String code) {
        DetailsImportsInvoiceResponse dImportResponse = new DetailsImportsInvoiceResponse();
        var im = importRepo.findByCode(code).orElseThrow(() -> new IllegalArgumentException(("code not found: " + code)));
        dImportResponse.setAnImport(im);
        dImportResponse.setSupplier(supplierRepo.findById(im.getSupplierId()).get());
        dImportResponse.setInventoryName(inventoryRepository.findById(im.getInventoryId()).get().getName());
        return dImportResponse;
    }

    @Override
    public List<DetailsReturnImportResponse> getAllReturnImport(String code) {
        Query query = entityManager.createNamedQuery("getImportReturnDTO");
        query.setParameter(1, code);
        return (List<DetailsReturnImportResponse>) query.getResultList();
    }

    @Override
    public List<ReturnImportInvoiceResponse> getDetailsReturnImport(String code) {
        Import anImport = importRepo.findByCode(code).orElseThrow(() -> new IllegalArgumentException(("code not found: " + code)));
        Type listType = new TypeToken<List<ReturnImportInvoiceResponse>>() {
        }.getType();
        List<ReturnImportInvoiceResponse> invoiceResponseList = modelMapper.map(returnImportRepo.findByImportId(anImport.getId()), listType);
        for (ReturnImportInvoiceResponse response : invoiceResponseList) {
            Query query = entityManager.createNamedQuery("getImportReturnDTOResponse");
            query.setParameter(1, response.getId());
            List<DetailsReturnImportResponse> list = (List<DetailsReturnImportResponse>) query.getResultList();
            BigDecimal total = BigDecimal.ZERO;
            for (DetailsReturnImportResponse detailsImportsInvoiceResponse : list) {
                total = total.add(detailsImportsInvoiceResponse.getTotalPrice());
            }
            Account account = accountsRepo.findById(response.getAccountId()).orElseThrow(() -> new IllegalArgumentException("wrong id"));
            Employee employee = employeeRepository.findById(account.getId()).orElseThrow(() -> new IllegalArgumentException("wrong id"));
            response.setFullName(employee.getFullName());
            response.setPhoneNumber(employee.getPhone());
            response.setTotalPrice(total);
            response.setDetailsReturnImportResponseList(list);
        }
        return invoiceResponseList;
    }

    @Override
    public List<ImportInvoiceBySupplier> getImportInvoiceBySupplier(Integer id) {
        String query = "call filter_import_by_supplier(?)";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper(ImportInvoiceBySupplier.class), id);
    }

    public String getNewCode() {
        String newCode = "IMP";
        Import importTop = importRepo.getTop();
        if (importTop == null) return "IMP1";
        newCode = newCode + (importTop.getId() + 1);
        return newCode;
    }
}
