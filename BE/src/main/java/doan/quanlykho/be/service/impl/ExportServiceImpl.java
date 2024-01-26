package doan.quanlykho.be.service.impl;

import doan.quanlykho.be.base.BaseService;
import doan.quanlykho.be.base.IBaseRepo;
import doan.quanlykho.be.base.ResponseListDto;
import doan.quanlykho.be.entity.Export;
import doan.quanlykho.be.repository.ExportRepository;
import doan.quanlykho.be.service.IExportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportServiceImpl extends BaseService<Export> implements IExportService {
    private final ExportRepository repository;
    public ExportServiceImpl(IBaseRepo<Export, Integer> baseRepo, ExportRepository repository) {
        super(baseRepo);
        this.repository = repository;
    }

    @Override
    public ResponseListDto<Export> findExportByAll(Integer exportInventory,
                                                   Integer receiveInventory, Integer status, String code, Boolean cancel,
                                                   Integer page, Integer perPage, String sort, String sortBy) {
        Page<Export> pageList;
        List<Export> data;
        Sort sortList = sort.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        pageList = repository.findExportsByAll(exportInventory,
                receiveInventory, status,code, cancel,PageRequest.of(page - 1, perPage,sortList));

        data = pageList.getContent();
        long total = pageList.getTotalElements();
        ResponseListDto<Export> dto = new ResponseListDto<>();
        dto.setData(data);
        dto.setPage(page);
        dto.setPerPage(perPage);
        dto.setTotal(total);
        dto.setNumberPage((total % perPage == 0) ? (total / perPage) : (total / perPage + 1));
        dto.setBegin(page - 2 <= 1 ? 1 : page - 1);
        return dto;
    }
}
