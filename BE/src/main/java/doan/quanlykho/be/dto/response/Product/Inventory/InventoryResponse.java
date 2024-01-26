package doan.quanlykho.be.dto.response.Product.Inventory;

import doan.quanlykho.be.entity.Inventory;
import doan.quanlykho.be.dto.request.ProductVariantsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryResponse {
    private Inventory inventory;
    private List<ProductVariantsDTO> productVariants;
    private Integer totalProductVariant;
    private Integer countProductVariant;

}
