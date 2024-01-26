package doan.quanlykho.be.service;

import doan.quanlykho.be.dto.request.Inventory.ListIdRequest;
import doan.quanlykho.be.dto.request.ProductVariantsDTO;
import doan.quanlykho.be.dto.response.Product.Inventory.InventoryResponse;
import doan.quanlykho.be.entity.InventoriesProductVariant;
import doan.quanlykho.be.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import java.util.List;

public interface IInventoryService {
    Page<Inventory> findAllBypPage(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String name, String code);

    List<Inventory> findAll();

    List<Inventory> findAllActiveInventory();

    Inventory findById(Integer id);

    Inventory create(Inventory inventory, BindingResult bindingResult);

    Inventory update(Integer id, Inventory inventory, BindingResult bindingResult);

    void delete (Integer id);

    void updateStatusInventory(Integer id);

    InventoryResponse getProductVariantByInventoryId(Integer id, String name);

    void deleteListProductVanriant(ListIdRequest listIdRequest);

    InventoriesProductVariant changeMinQuantity(Integer inventoryId, Integer productVariantId, Integer minQuantity);

    List<ProductVariantsDTO> findInventoriesQuantity(Integer id);

}