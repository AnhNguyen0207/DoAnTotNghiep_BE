package doan.quanlykho.be.controller;

import doan.quanlykho.be.dto.request.Product.OptionAdd;
import doan.quanlykho.be.dto.request.Product.ProductAddDTO;
import doan.quanlykho.be.dto.request.Product.ProductFilter;
import doan.quanlykho.be.service.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("api/products")
@PreAuthorize("hasAnyAuthority('admin','staff')")
public class ProductController {
	private final IProductService productService;

	public ProductController(IProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/count")
	public ResponseEntity<?> countProductByFilter(@RequestBody @Valid ProductFilter filter, BindingResult bindingResult) {

		return ResponseEntity.ok(productService.countProductByFilter(filter, bindingResult));

	}

	@PostMapping("/filter")
	public ResponseEntity<?> filterProducts(@RequestBody @Valid ProductFilter filter, BindingResult bindingResult) {
		return ResponseEntity.ok(productService.productFilter(filter, bindingResult));

	}

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody @Valid ProductAddDTO request, BindingResult bindingResult) {
		return ResponseEntity.ok(productService.save(request, bindingResult));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id") Integer id) {
		return ResponseEntity.ok(productService.findById(id));
	}


	@DeleteMapping("/{id}")
	public void delete(@PathVariable(value = "id") Integer id) {
		productService.deleteById(id);
	}

	@DeleteMapping()
	public void deleteProducts(@RequestBody Integer[] listId) {
		productService.deleteProductsById(listId);
	}


//update

	@PutMapping
	public ResponseEntity<Object> update(@RequestBody @Valid ProductAddDTO entity, BindingResult bindingResult) {
		return ResponseEntity.ok(productService.update(entity, bindingResult));
	}

	@DeleteMapping("/options/{id}")
	public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody OptionAdd[] options) {
		return ResponseEntity.ok(options);
	}

	@DeleteMapping("/variants/{id}")
	public void deleteVariant(@PathVariable Integer id) {
		productService.deleteVariantById(id);
	}

	@DeleteMapping("/variants")
	public void deleteVariant(@RequestBody Integer[] listId) {
		productService.deleteVariantsById(listId);
	}

}
