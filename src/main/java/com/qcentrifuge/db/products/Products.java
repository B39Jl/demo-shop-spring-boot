package com.qcentrifuge.db.products;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table(name = "products")
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="id", updatable=false, nullable=false)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Double price;

    @NotBlank
    private String description;


    private String imageName;


    public Products(){}

    public Products(String name, Double price, String description, String imgname) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageName = imgname;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
