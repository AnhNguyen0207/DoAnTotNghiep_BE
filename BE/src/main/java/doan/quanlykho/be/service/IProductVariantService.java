package doan.quanlykho.be.service;

import doan.quanlykho.be.base.IBaseService;
import doan.quanlykho.be.dto.request.Product.ProductVariantDTO;
import doan.quanlykho.be.entity.ProductVariant;

import java.util.List;
import java.util.Optional;

public interface IProductVariantService extends IBaseService<ProductVariant> {
    List<ProductVariant> findProductByName(String name);

    Optional<ProductVariant> findProductById(Integer id);

    List<ProductVariantDTO> findAllProductVariantDTO(Integer pageNumber, Integer pageSize, String searchValue);

    List<ProductVariantDTO> findAllProductVariantDTO();

    Integer countTotalPage(String searchValue);
}
