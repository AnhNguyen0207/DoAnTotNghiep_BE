package doan.quanlykho.be.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface IBaseRepo<T, ID extends Serializable> extends JpaRepository<T, ID> {

}
