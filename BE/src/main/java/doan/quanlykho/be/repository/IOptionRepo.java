package doan.quanlykho.be.repository;

import doan.quanlykho.be.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOptionRepo extends JpaRepository<Option,Integer> {
}
