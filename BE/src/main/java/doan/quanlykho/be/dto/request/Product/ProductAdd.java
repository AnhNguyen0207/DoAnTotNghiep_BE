package doan.quanlykho.be.dto.request.Product;

import doan.quanlykho.be.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ProductAdd {


    private Product product;
    private List<OptionAdd> options;


}
