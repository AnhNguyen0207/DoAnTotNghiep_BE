package doan.quanlykho.be.controller;

import doan.quanlykho.be.dto.request.Inventory.ListIdRequest;
import doan.quanlykho.be.dto.request.ProductVariantsDTO;
import doan.quanlykho.be.dto.response.Product.Inventory.InventoryResponse;
import doan.quanlykho.be.entity.Inventory;
import doan.quanlykho.be.service.IInventoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/inventories")
@CrossOrigin("*")
@PreAuthorize("hasAnyAuthority('admin','staff')")
public class InventoryController {
    private final IInventoryService iInventoryService;

    @GetMapping("/pagination")
    public ResponseEntity<Object> getPagination(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                                @RequestParam(value = "sortDir", required = false, defaultValue = "desc") String sortDir,
                                                @RequestParam(value = "name", required = false) String name,
                                                @RequestParam(value = "code", required = false) String code) {
        Page<Inventory> inventory = iInventoryService.findAllBypPage(pageNumber, pageSize, sortBy, sortDir, name, code);
        List<InventoryResponse> inventories = new ArrayList<>();
        inventory.getContent().forEach((i -> {
            InventoryResponse e = new InventoryResponse();
            e.setInventory(i);
            e.setTotalProductVariant(iInventoryService.getProductVariantByInventoryId(i.getId(), "").getTotalProductVariant());
            inventories.add(e);
        }));
        Map<String, Object> results = new HashMap<>();
        results.put("data", inventories);
        results.put("total", inventory.getTotalElements());
        results.put("from", inventory.getSize() * inventory.getNumber() + 1);
        results.put("to", inventory.getSize() * inventory.getNumber() + inventory.getNumberOfElements());

        return ResponseEntity.ok(results);
    }

    @GetMapping("")
    public List<Inventory> getAll() {
        return iInventoryService.findAll();
    }


    @GetMapping("/active")
    public List<Inventory> getAllActiveInventory() {
        return iInventoryService.findAllActiveInventory();
    }

    @PostMapping("")
    public Inventory createInventory(@RequestBody @Valid Inventory inventory, BindingResult bindingResult) {
        return iInventoryService.create(inventory, bindingResult);
    }


    @GetMapping("/{id}")
    public Inventory getById(@PathVariable(value = "id") Integer id) {
        return iInventoryService.findById(id);
    }

    @PutMapping("/{id}")

    public Inventory update(@RequestBody @Valid Inventory inventory, BindingResult bindingResult,
                            @PathVariable(value = "id") Integer id) {
        return iInventoryService.update(id, inventory, bindingResult);
    }

    @PutMapping("/delete/{id}")
    public void deleteInventory(@PathVariable(value = "id") Integer id) {
        iInventoryService.delete(id);
    }


    @PutMapping("/status/{id}")
    public void updateStatusInventory(@PathVariable(value = "id") Integer id) {
        iInventoryService.updateStatusInventory(id);
    }

    @GetMapping("/productvariant/{id}")
    public InventoryResponse getAll(@PathVariable(value = "id") Integer id, @RequestParam(required = false, value = "name") String name) {
        return iInventoryService.getProductVariantByInventoryId(id, name);
    }

    @PostMapping("/delete")
    public void deleteProductVariant(@RequestBody ListIdRequest listIdRequest) {
        iInventoryService.deleteListProductVanriant(listIdRequest);
    }

    @PutMapping("/change/minquantity")
    public ResponseEntity<Object> changeMinQuantity(@RequestParam(value = "inventoryId", required = false) Integer inventoryId,
                                                    @RequestParam(value = "productVariantId", required = false) Integer productVariantId,
                                                    @RequestParam(value = "minQuantity", required = false) Integer minQuantity) {
        return ResponseEntity.ok(iInventoryService.changeMinQuantity(inventoryId, productVariantId, minQuantity));
    }

    @GetMapping("/quantity")
    public List<ProductVariantsDTO> getInventoriesQuantity(@RequestParam(value = "id") Integer id) {
        return iInventoryService.findInventoriesQuantity(id);
    }

}