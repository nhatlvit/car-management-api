package shopping.car.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

	@NotNull(message = "Username is required")
	//@Max(value = 10, message = "Username must be less than 10 characters")
    private String username;

	@JsonProperty( value = "password", access = JsonProperty.Access.WRITE_ONLY)
	@NotNull(message = "Password is required")
	//@Min(value = 8, message = "Password must be more than 8 characters")
    private String password;

	@Email(message = "Email is invalid")
    private String email;
	
	//@Max(value = 50, message = "Firstname must be less than 50 characters")
    private String firstName;

	//@Max(value = 50, message = "Lastname must be less than 50 characters")
    private String lastName;

	//@Max(value = 150, message = "Address must be less than 150 characters")
    private String address;

	@NotNull(message = "Phone number is required")
	//@Min(value = 11, message = "Phone number must be 11 characters")
	//@Max(value = 11, message = "Phone number must be 11 characters")
	private String phoneNumber;
}