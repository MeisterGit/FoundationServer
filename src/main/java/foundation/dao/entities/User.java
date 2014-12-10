package foundation.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
    private Long id;
    private String username;
    private int age;
    
    public User() { }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "username", nullable = false, length = 36)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the users real age.
	 */
	@Column(name = "age")
	public int getAge() {
		return age;
	}

	/**
	 * @param age the users real age.
	 */
	public void setAge(int age) {
		this.age = age;
	}
}