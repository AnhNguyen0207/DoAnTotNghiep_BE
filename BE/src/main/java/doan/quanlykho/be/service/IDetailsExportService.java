package doan.quanlykho.be.service;

import doan.quanlykho.be.base.IBaseService;
import doan.quanlykho.be.entity.DetailsExport;

import java.util.List;

public interface IDetailsExportService extends IBaseService<DetailsExport> {
    List<DetailsExport> saveAll(Iterable<DetailsExport> entities);

    List<DetailsExport> findByExportId(Integer id);
     void  deleteByExportId(Integer id);
}
