package doan.quanlykho.be.controller;

import doan.quanlykho.be.base.BaseController;
import doan.quanlykho.be.base.IBaseService;
import doan.quanlykho.be.entity.ExportsStatus;
import doan.quanlykho.be.service.IExportStatusService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/exportsStatus")
@CrossOrigin("*")
@PreAuthorize("hasAnyAuthority('admin','staff')")
public class ExportStatusController extends BaseController<ExportsStatus> {
    public ExportStatusController(IBaseService<ExportsStatus> baseService, IExportStatusService service) {
        super(baseService);
        this.service = service;
    }

    private final IExportStatusService service;

    @GetMapping("getByExport/{id}")
    public ExportsStatus findByExport(@PathVariable Integer id) {
        return service.findByExport(id);
    }
    @GetMapping("getByParentId/{id}")
    public List<ExportsStatus> findByParentId(@PathVariable Integer id) {
        return service.findByParentId(id);
    }
    @PutMapping("/{id}")
    public ExportsStatus updateExportsStatus(@RequestBody @Valid ExportsStatus request,
                                             @PathVariable(value = "id") Integer id) {
        return service.updateExportsStatus(request, id);
    }

}
