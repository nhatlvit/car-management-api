package shopping.car.models;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "cars")
public class Car implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer carId;

	@NotNull(message = "CarName is required")
	//@Max(value = 30, message ="CarName must be less then 30 characters")
    private String carName;

	//@Max(value = 200, message ="Car description must be less then 200 characters")
    private String carDescription;

	//@Max(value = 30, message ="Car category must be less then 30 characters")
    private String carCategory;

    private Double carPrice;
}