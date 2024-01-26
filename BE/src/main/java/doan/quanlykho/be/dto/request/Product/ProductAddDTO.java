package doan.quanlykho.be.dto.request.Product;

import doan.quanlykho.be.entity.Category;
import doan.quanlykho.be.entity.Product;
import doan.quanlykho.be.entity.ProductVariant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductAddDTO {
    private Product product;
    private List<ProductVariant> variants;
    private List<Category> categories;

}