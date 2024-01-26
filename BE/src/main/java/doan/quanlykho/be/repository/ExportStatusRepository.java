package doan.quanlykho.be.repository;

import java.util.List;

import doan.quanlykho.be.base.IBaseRepo;
import doan.quanlykho.be.entity.ExportsStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportStatusRepository extends IBaseRepo<ExportsStatus, Integer> {
    ExportsStatus findByExport(Integer export);
    List<ExportsStatus> findByParentId(Integer parentId);

}
