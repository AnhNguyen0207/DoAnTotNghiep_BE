package doan.quanlykho.be.service;

import doan.quanlykho.be.dto.response.ImportInvoice.ImportStatusResponse;
import doan.quanlykho.be.entity.ImportsStatus;

import java.util.List;

public interface IImportsStatusService {

    ImportsStatus save(ImportsStatus importsStatus);


    ImportsStatus findByImportIdAndStatusId(Integer importId, Integer statusId);

    List<ImportStatusResponse> findDetailsImportStatus(Integer importId);
}
