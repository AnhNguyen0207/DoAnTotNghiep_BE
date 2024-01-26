package doan.quanlykho.be.service;

import doan.quanlykho.be.entity.DetailsReturnImport;
import doan.quanlykho.be.entity.ReturnImport;

import java.util.List;

public interface IReturnImportService {
    ReturnImport save(ReturnImport returnImport);

    void saveAllDetails(List<DetailsReturnImport> returnImport, Integer inventoryId, Integer importReturnId);

}
