package doan.quanlykho.be.repository;

import doan.quanlykho.be.entity.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IImportRepo extends JpaRepository<Import, Long> {
    Optional<Import> findByCode(String code);

    Optional<Import> findById(Integer id);

    @Transactional
    @Query(value = "call filter_import_invoice(?1)", nativeQuery = true)
    List<Object[]> getImportFilter(String searchValue);

    @Query(value = "select * from imports order by id DESC limit 1",nativeQuery = true)
    Import getTop();
}
