package net.slipp.user;

import java.sql.SQLException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.google.gson.annotations.Expose;

public class User {
	@Expose
	@NotNull
	@Size(min = 4, max = 12)
	private String userId;
	
	@Expose(serialize = false)
	@NotNull
	@Size(min = 4, max = 12)
	private String password;
	
	@Expose
	@NotNull
	@Size(min = 2, max = 10)
	private String name;
	
	@Expose
	@Email
	private String email;
	
	public User() {
		super();
	}

	public User(String userId, String password, String name, String email) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
	
	public boolean isSameUser(String newUserId) {
		if (this.userId == null) {
			return false;
		}
		return this.userId.equals(newUserId);
	}

	public boolean matchPassword(String newPassword) {
		return password.equals(newPassword);
	}

	public static boolean login(String userId, String password) throws UserNotFoundException, PasswordMismatchException {
		UserDao userDAO = new UserDao();
		User user = null;
		
		try {
			user = userDAO.findById(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (user == null) {
			throw new UserNotFoundException();
		}
		
		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException();
		}
		
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
}
