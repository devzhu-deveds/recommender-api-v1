package in.projectzhu.freakyapi.domain;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "tbl_products")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double price;

    private String description;

    private LocalDate addedOn;



    //no args constructor
    public Product() {
    }

    //all args constructor
    public Product(Integer id, String name, Double price, String description, LocalDate addedOn) {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setDescription(description);
        this.setAddedOn(addedOn);
    }


    // getters & setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
    }

// to string

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", addedOn=" + addedOn +
                '}';
    }

}
