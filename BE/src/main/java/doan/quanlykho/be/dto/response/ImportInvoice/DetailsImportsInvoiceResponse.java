package doan.quanlykho.be.dto.response.ImportInvoice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import doan.quanlykho.be.entity.Import;
import doan.quanlykho.be.entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class DetailsImportsInvoiceResponse {
    private Import anImport;
    private Supplier supplier;
    private String inventoryName;
}
