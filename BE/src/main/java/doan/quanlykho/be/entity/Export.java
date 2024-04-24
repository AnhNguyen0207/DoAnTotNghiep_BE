package doan.quanlykho.be.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "exports")
@Getter
@Setter
public class Export implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "receive_inventory_id", nullable = false)
    private Inventory receiveInventory;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "export_inventory_id", nullable = false)
    private Inventory exportInventory;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "export")
    private Set<DetailsExport> detailsExports;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "export")
    private Set<ExportsStatus> exportsStatuses ;

    @JoinColumn(name = "status_id")
    private Integer statusId;

    @JoinColumn(name = "transport_company_id")
    private Integer transportCompanyId;

    @JoinColumn(name = "account_id")
    private Integer accountId;
}