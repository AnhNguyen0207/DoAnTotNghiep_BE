package doan.quanlykho.be.controller;

import doan.quanlykho.be.base.BaseController;
import doan.quanlykho.be.base.IBaseService;
import doan.quanlykho.be.dto.request.Product.ProductVariantDTO;
import doan.quanlykho.be.entity.ProductVariant;
import doan.quanlykho.be.service.IProductVariantService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-variants")
@PreAuthorize("hasAnyAuthority('admin','warehouse')")
@CrossOrigin("*")
public class ProductVariantController extends BaseController<ProductVariant> {
	private final IProductVariantService productVariantService;

	public ProductVariantController(IBaseService<ProductVariant> baseService, IProductVariantService productVariantService) {
		super(baseService);
		this.productVariantService = productVariantService;
	}

	@GetMapping("search")
	public List<ProductVariant> findProductByName(@RequestParam(defaultValue = "") String name) {
		return productVariantService.findProductByName(name);
	}

	@GetMapping("/findProductVariant")
	public List<ProductVariantDTO> findProductVariant(@RequestParam Integer pageNumber, @RequestParam Integer pageSize, @RequestParam String searchValue) {
		return productVariantService.findAllProductVariantDTO(pageNumber, pageSize, searchValue);
	}

	@GetMapping("/findAllProductVariant")
	public List<ProductVariantDTO> findAllProductVariant() {
		return productVariantService.findAllProductVariantDTO();
	}

	@GetMapping("/count-total")
	public Integer count(@RequestParam String searchValue) {
		return productVariantService.countTotalPage(searchValue);
	}
//    @GetMapping("{id}")
//    public Optional<ProductVariant> findProductById(@PathVariable Integer id) {
//        return productVariantService.findProductById(id);
//    }
}
