package doan.quanlykho.be.repository;

import doan.quanlykho.be.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	Optional<Account> findAccountByUsername(String username);
	List<Account> findAllByIsDelete(boolean delete);

	@Query("select a from Account a where a.isDelete = ?1")
	Page<Account> findAllByIsDeletePage(boolean delete, Pageable pageable);
}
