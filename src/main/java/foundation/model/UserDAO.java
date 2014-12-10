package foundation.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import foundation.dao.entities.User;

@Transactional
public interface UserDAO extends CrudRepository<User, Long>
{
	public User findByUsername(String username);
}