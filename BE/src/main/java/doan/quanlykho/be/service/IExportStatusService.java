package doan.quanlykho.be.service;

import java.util.List;

import doan.quanlykho.be.base.IBaseService;
import doan.quanlykho.be.entity.ExportsStatus;

public interface IExportStatusService extends IBaseService<ExportsStatus> {
    ExportsStatus findByExport(Integer export);
    List<ExportsStatus> findByParentId(Integer parentId);

    ExportsStatus updateExportsStatus(ExportsStatus exportsStatus, Integer id);
}
