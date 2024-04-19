package doan.quanlykho.be.service.impl;

import doan.quanlykho.be.base.BaseService;
import doan.quanlykho.be.base.IBaseRepo;
import doan.quanlykho.be.dto.request.Product.ProductVariantDTO;
import doan.quanlykho.be.entity.ProductVariant;
import doan.quanlykho.be.repository.ProductVariantsRepository;
import doan.quanlykho.be.service.IProductVariantService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ProductVariantServiceImpl extends BaseService<ProductVariant> implements IProductVariantService {
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;
    private final ProductVariantsRepository productVariantsRepository;

    public ProductVariantServiceImpl(IBaseRepo<ProductVariant, Integer> baseRepo, EntityManager entityManager, ModelMapper modelMapper, ProductVariantsRepository productVariantsRepository) {
        super(baseRepo);
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
        this.productVariantsRepository = productVariantsRepository;
    }

    @Override
    public List<ProductVariant> findProductByName(String name) {
        return productVariantsRepository.findProductVariantByName(name);
    }

    @Override
    public Optional<ProductVariant> findProductById(Integer id) {
        return productVariantsRepository.findById(id);
    }

    @Override
    public List<ProductVariantDTO> findAllProductVariantDTO(Integer pageNumber, Integer pageSize, String searchValue) {
        List<Object[]> productVariantObjects = productVariantsRepository.filterProductVariants(pageNumber, pageSize, searchValue);
        List<ProductVariantDTO> productVariantDTOS = new ArrayList<>();
        if (productVariantObjects != null) {
            productVariantObjects.forEach(productVariant -> {
                productVariantDTOS.add(new ProductVariantDTO(
                        (Integer) productVariant[0],    // id
                        (String) productVariant[1],     // code
                        (String) productVariant[2],     // name
                        ((BigInteger) productVariant[3]).intValue(),  // quantity
                        (BigDecimal) productVariant[4]  // importPrice
                ));
            });
        }
        return productVariantDTOS;
    }

    @Override
    public List<ProductVariantDTO> findAllProductVariantDTO() {
        Query query = entityManager.createNamedQuery("getFeaturedProductDTO");
        return (List<ProductVariantDTO>) query.getResultList();
    }

    @Override
    public Integer countTotalPage(String searchValue) {
        Long countResult = productVariantsRepository.countTotalPage(searchValue) != null ? productVariantsRepository.countTotalPage(searchValue) : 0;
        if (countResult % 7 == 0) {
            return (int) ((countResult / 7));
        }
        return (int) ((countResult / 7) + 1);
    }
}
