package br.org.springmvc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements AppModel<User, Long>, Serializable {
	 
	private static final long serialVersionUID = -1135164072724462387L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
     
    private String username;
     
    private String address;
     
    private String email;
     
    public User(){
    }
     
    public User(Long id, String username, String address, String email){
        this.id = id;
        this.username = username;
        this.address = address;
        this.email = email;
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", address=" + address
                + ", email=" + email + "]";
    }

	@Override
	public Long getPK() {
		return id;
	}

	@Override
	public void merge(User other) {
		this.username = other.username;
		this.address = other.address;
		this.email = other.email;
	}
     
 
     
}