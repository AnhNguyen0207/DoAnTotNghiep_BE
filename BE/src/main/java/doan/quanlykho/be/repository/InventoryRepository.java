package doan.quanlykho.be.repository;

import doan.quanlykho.be.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    Page<Inventory> findByNameContaining(String name,Pageable pageable);
    Page<Inventory> findByCodeContaining(String code,Pageable pageable);

    @Query(value = "select quantity from inventories_product_variant where inventory_id = ?1 and product_variant_id = ?2",nativeQuery = true)
    Integer Quantity(Integer inventoryId, Integer productvariantId);

    @Query(value = "select min_quantity from inventories_product_variant where inventory_id = ?1 and product_variant_id = ?2",nativeQuery = true)
    Integer minQuantity(Integer inventoryId, Integer productvariantId);

    @Query(value = "select * from inventories  where is_delete = 0", nativeQuery = true)
    List<Inventory> findAllActiveInventory();

    @Query(value = "call select_create_at(?1)",nativeQuery = true)
    Timestamp createAt(Integer id);

    @Query(value = "select product_variant_id from inventories_product_variant where inventory_id = ?1 and  quantity <= min_quantity", nativeQuery = true)
    List<Integer> findInventoriesQuantity(Integer id);

    @Query(value = "select * from inventories order by id DESC limit 1",nativeQuery = true)
    Inventory getTop();
}