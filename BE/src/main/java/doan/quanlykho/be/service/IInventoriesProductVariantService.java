package doan.quanlykho.be.service;

import doan.quanlykho.be.entity.DetailsExport;
import doan.quanlykho.be.entity.DetailsImport;
import doan.quanlykho.be.entity.InventoriesProductVariant;

import java.util.List;

public interface IInventoriesProductVariantService {

   InventoriesProductVariant findByInventoryIdAndProductVariantId(Integer inventoryId, Integer productVariantId);

   void importProductVariantToInventory(List<DetailsImport> list, Integer inventoryId);

   void exportProductVariantToInventory(List<DetailsExport> detailsExports, Integer inventoryId);
   void importQuantityProductVariantToInventory(List<DetailsExport> detailsExports, Integer inventoryId);

}
