package doan.quanlykho.be.common;

import doan.quanlykho.be.entity.DetailsExport;
import doan.quanlykho.be.entity.ProductVariant;

import javax.persistence.metamodel.SingularAttribute;

public class DetailExport_ {
    public static volatile SingularAttribute<DetailsExport, Integer> id;
    public static volatile SingularAttribute<DetailsExport, String> code;
    public static volatile SingularAttribute<DetailsExport, Integer> quantity;
    public static volatile SingularAttribute<DetailsExport, ProductVariant> productVariant;
}
