package doan.quanlykho.be.dto.request.Product;

import doan.quanlykho.be.entity.OptionValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@Data
public class OptionValuesAdd {
    private Integer id;
    private Integer optionId;
    private String name;

    public OptionValue toEntity()
    {
        ModelMapper mapper=new ModelMapper();
        return mapper.map(this,OptionValue.class);
    }



}
