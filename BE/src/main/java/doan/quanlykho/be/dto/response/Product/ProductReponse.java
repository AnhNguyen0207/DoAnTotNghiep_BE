package doan.quanlykho.be.dto.response.Product;

import doan.quanlykho.be.entity.Category;
import doan.quanlykho.be.entity.Product;
import doan.quanlykho.be.entity.ProductVariant;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class ProductReponse {
    private Product product;
    private List<ProductVariant> variants;
    private List<Category> categories;
}