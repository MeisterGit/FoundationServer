package foundation.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import foundation.dao.entities.User;

public interface UserDAO extends CrudRepository<User, Long>
{
	/**
	 * Get a User from the database by username.
	 * <br/>  
	 * This function makes use of Hibernate's automatic query generation using function signatures.
	 * <br/>
	 * User findByUsername tells Hibernate:
	 * <p>
	 * 1. Use a SELECT statement
	 * <p>
	 * 2. Select FROM the table User maps to.
	 * <p>
	 * 3. Select WHERE username = [the passed variable]
	 *  
	 * @param username The username to search by.
	 * @return A User which matches the above query.
	 */
	public User findByUsername(String username);
}