package doan.quanlykho.be.service;

import doan.quanlykho.be.entity.DetailsImport;

import java.util.List;

public interface IDetailsImportService {

    List<DetailsImport> save(List<DetailsImport> detailsImportList, Integer importId);
}
