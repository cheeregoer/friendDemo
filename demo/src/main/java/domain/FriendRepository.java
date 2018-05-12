package domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FriendRepository extends CrudRepository<Friend, Long> {
	
	List<Friend> findAll();
	Friend getById(Long id);
	Friend getByEmail(String email);
}
