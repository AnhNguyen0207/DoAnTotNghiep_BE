package doan.quanlykho.be.repository;

import doan.quanlykho.be.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findRoleByName(String name);

	@Query(value = "SELECT max(id) FROM roles", nativeQuery = true)
	Integer maxId();
}
