package doan.quanlykho.be.service.impl;

import doan.quanlykho.be.dto.response.ImportInvoice.ImportStatusResponse;
import doan.quanlykho.be.entity.ImportsStatus;
import doan.quanlykho.be.repository.IImportStatusRepo;
import doan.quanlykho.be.service.IImportsStatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
@AllArgsConstructor
public class ImportStatusService implements IImportsStatusService {

    private final IImportStatusRepo importStatusRepo;

    private final EntityManager entityManager;

    @Override
    public ImportsStatus save(ImportsStatus importsStatus) {
        return importStatusRepo.save(importsStatus);
    }

    @Override
    public ImportsStatus findByImportIdAndStatusId(Integer importId, Integer statusId) {
        return importStatusRepo.findByImportIdAndStatusId(importId, statusId);
    }

    @Override
    public List<ImportStatusResponse> findDetailsImportStatus(Integer importId) {
        Query query = entityManager.createNamedQuery("getFeaturedImportStatusDTO");
        query.setParameter(1,importId);
        return (List<ImportStatusResponse>) query.getResultList();
    }

}
