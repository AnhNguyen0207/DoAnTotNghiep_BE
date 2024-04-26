package doan.quanlykho.be.controller;

import doan.quanlykho.be.entity.ReturnImport;
import doan.quanlykho.be.service.IReturnImportService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/return_import")
@CrossOrigin("*")
@PreAuthorize("hasAnyAuthority('admin')")
public class ReturnImportController {

    private final IReturnImportService returnImportService;

    @PostMapping("/{inventoryId}")
    public void save(@RequestBody ReturnImport returnImport, @PathVariable Integer inventoryId) {

        ReturnImport returnImport1 = returnImportService.save(returnImport);
        returnImportService.saveAllDetails(returnImport.getDetailsReturnImports(), inventoryId, returnImport1.getId());
    }
}
