package doan.quanlykho.be.service.impl;

import doan.quanlykho.be.base.BaseService;
import doan.quanlykho.be.base.IBaseRepo;
import doan.quanlykho.be.entity.DetailsExport;
import doan.quanlykho.be.repository.DetailsExportRepository;
import doan.quanlykho.be.service.IDetailsExportService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DetailsExportServiceImpl extends BaseService<DetailsExport> implements IDetailsExportService {
    private final DetailsExportRepository repository;

    public DetailsExportServiceImpl(IBaseRepo<DetailsExport, Integer> baseRepo, DetailsExportRepository repository) {
        super(baseRepo);
        this.repository = repository;
    }

    @Override
    public List<DetailsExport> saveAll(Iterable<DetailsExport> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public List<DetailsExport> findByExportId(Integer id) {
        return repository.findDetailsExportByExport(id);
    }

    @Override
    public void deleteByExportId(Integer id) {
        List<DetailsExport> list = findByExportId(id);
        for (DetailsExport detailsExport: list) {
            repository.deleteById(detailsExport.getId());
        }
    }

}
