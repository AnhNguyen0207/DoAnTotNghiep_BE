package doan.quanlykho.be.repository;

import doan.quanlykho.be.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountsRepo extends JpaRepository<Account,Integer> {


}
