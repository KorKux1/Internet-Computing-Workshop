package co.edu.icesi.internetcomputing.workshop.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.internetcomputing.workshop.model.TsscAdmin;

@Repository
public interface TsscAdminRepository extends CrudRepository<TsscAdmin, Long>{
	
	List<TsscAdmin> findByUsername(String name);
}
