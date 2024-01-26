package doan.quanlykho.be.service;

import doan.quanlykho.be.dto.request.Product.ProductAddDTO;
import doan.quanlykho.be.dto.request.Product.ProductFilter;
import doan.quanlykho.be.dto.response.Product.ProductFilterResponse;
import doan.quanlykho.be.dto.response.Product.ProductReponse;
import doan.quanlykho.be.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

public interface IProductService {
    List<Product> findAll();


    ProductReponse findById(Integer integer);


    void deleteById(Integer integer);

    Page<Product> findAll(Integer pageNumber, Integer pageSize);

//    Product save(ProductAdd request, BindingResult bindingResult);
ProductAddDTO save(ProductAddDTO request, BindingResult bindingResult);


    List<Product> findAllVariant(Integer pageNumber, Integer pageSize, String name);

    List<ProductFilterResponse> productFilter(ProductFilter filter, BindingResult bindingResult);

    Integer countProductByFilter(ProductFilter filter, BindingResult bindingResult);

    void deleteVariantById(Integer id);

    void deleteVariantsById(Integer[] listId);

    void deleteProductsById(Integer[] listId);

    @Transactional(rollbackOn = SQLException.class)
    ProductReponse update(ProductAddDTO request, BindingResult bindingResult);
}
