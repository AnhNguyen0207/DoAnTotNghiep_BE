package doan.quanlykho.be.repository;

import doan.quanlykho.be.entity.DetailsReturnImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetailImportReturnRepo extends JpaRepository<DetailsReturnImport, Integer> {
}
