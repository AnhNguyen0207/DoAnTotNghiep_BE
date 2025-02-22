package doan.quanlykho.be.service.impl;

import doan.quanlykho.be.entity.DetailsImport;
import doan.quanlykho.be.repository.IDetailImportRepo;
import doan.quanlykho.be.service.IDetailsImportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DetailsImportService implements IDetailsImportService {

    private final IDetailImportRepo detailImportRepo;

    @Override
    public List<DetailsImport> save(List<DetailsImport> detailsImportList, Integer importId) {
        for (DetailsImport detailsImport : detailsImportList) {
            detailsImport.setImport_id(importId);
        }
        return detailImportRepo.saveAll(detailsImportList);
    }
}
