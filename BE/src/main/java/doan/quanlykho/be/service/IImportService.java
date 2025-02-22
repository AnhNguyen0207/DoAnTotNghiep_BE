package doan.quanlykho.be.service;

import doan.quanlykho.be.dto.response.ImportInvoice.*;
import doan.quanlykho.be.entity.Import;

import java.util.List;

public interface IImportService {

    List<Import> findAll();

    List<ImportResponse> findAllImportDTO(String searchValue);

    Import save(Import importField);

    void updateStatusImport(Integer importId, String chooses, Integer accountId);

    void updateStatusImportReturn(Integer importId, String chooses, Integer accountId);

    DetailsImportsInvoiceResponse getDetailInvoiceByCode(String code);

    List<DetailsReturnImportResponse> getAllReturnImport(String code);

    List<ReturnImportInvoiceResponse> getDetailsReturnImport(String code);

    List<ImportInvoiceBySupplier> getImportInvoiceBySupplier(Integer id);
}
