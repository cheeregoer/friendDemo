package repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import domain.FriendConnection;

public interface FriendConnectionRepository extends CrudRepository<FriendConnection, Long> {
	
	@Query("select q from FriendConnection q where q.associationNature = :associationNature and (q.friendEmail = :friendEmail or q.associatedFriendEmail = :friendEmail)")
	List<FriendConnection> getFriendConnectionByAssociationNatureAndFriendEmail(@Param("associationNature")String associationNature, @Param("friendEmail")String friendEmail);
	
	List<FriendConnection> findByFriendEmailAndAssociationNature(@Param("email")String friendEmail, @Param("associationNature")String associationNature);
	
	List<FriendConnection> findByAssociatedFriendEmailAndAssociationNature(@Param("email")String associatedFriendEmail, @Param("associationNature")String associationNature);
	
	@Query("select case when (count(q)>0) then true else false end from FriendConnection q where q.friendEmail = :friendEmail and q.associatedFriendEmail = :associatedFriendEmail and q.associationNature = :associationNature")
	boolean existByFriendEmailAndAssociatedEmailAndAssociationNature(@Param("friendEmail")String friendEmail, @Param("associatedFriendEmail")String associatedFriendEmail, @Param("associationNature")String associationNature);
	
}
