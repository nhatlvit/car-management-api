package shopping.car.models;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer carId;

	@NotNull(message = "CarName is required")
    private String carName;

    private String carDescription;

    private String carCategory;

    private Double carPrice;

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarDescription() {
		return carDescription;
	}

	public void setCarDescription(String carDescription) {
		this.carDescription = carDescription;
	}

	public String getCarCategory() {
		return carCategory;
	}

	public void setCarCategory(String carCategory) {
		this.carCategory = carCategory;
	}

	public Double getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(Double carPrice) {
		this.carPrice = carPrice;
	}
}