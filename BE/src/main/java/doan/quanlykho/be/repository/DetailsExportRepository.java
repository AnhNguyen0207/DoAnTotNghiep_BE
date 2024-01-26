package doan.quanlykho.be.repository;

import doan.quanlykho.be.base.IBaseRepo;
import doan.quanlykho.be.entity.DetailsExport;

import java.util.List;

public interface DetailsExportRepository extends IBaseRepo<DetailsExport, Integer> {

    List<DetailsExport> findDetailsExportByExport(Integer id);
}
