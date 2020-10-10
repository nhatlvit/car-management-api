package shopping.car.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import shopping.car.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Query("select u from User u where u.username = ?1 and u.password = ?2")
	public User findUserByLoginInfo(String username, String password);

}