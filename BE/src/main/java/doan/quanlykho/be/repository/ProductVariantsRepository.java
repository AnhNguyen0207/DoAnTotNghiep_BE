package doan.quanlykho.be.repository;

import doan.quanlykho.be.base.IBaseRepo;
import doan.quanlykho.be.entity.ProductVariant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface ProductVariantsRepository extends IBaseRepo<ProductVariant, Integer> {
    @Query(value = "SELECT * FROM product_variants WHERE NAME LIKE %?%", nativeQuery = true)
    List<ProductVariant> findProductVariantByName(String name);

    @Transactional
    @Query(value = "call get_productvariant_byname(?1,?2)", nativeQuery = true)
    List<ProductVariant> listProductVariantByName(Integer id, String name);

    @Transactional
    @Query(value = "call filter_product_variant(?1, ?2, ?3)", nativeQuery = true)
    List<Object[]> filterProductVariants (Integer pageNumber, Integer pageSize, String searchValue);

    @Query(value = "SELECT COUNT(*) FROM ProductVariant pv INNER JOIN Product p ON pv.productId = p.id WHERE p.isDelete = false AND pv.name LIKE CONCAT('%',?1,'%')")
    Long countTotalPage(String searchValue);
}
