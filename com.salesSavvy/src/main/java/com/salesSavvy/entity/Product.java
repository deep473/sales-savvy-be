package com.salesSavvy.entity;

import java.util.List;
import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;
    String description;
    int    price;
    String photo;
    String category;
    List<String> reviews;

    /* ---------- constructors ---------- */
    public Product() {}

    public Product(Long id, String name, String description, int price,
                   String photo, String category, List<String> reviews) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.photo = photo;
        this.category = category;
        this.reviews = reviews;
    }

    /* ---------- getters / setters ---------- */
    public Long getId()                  { return id; }
    public void setId(Long id)           { this.id = id; }

    public String getName()              { return name; }
    public void setName(String name)     { this.name = name; }

    public String getDescription()       { return description; }
    public void setDescription(String d) { this.description = d; }

    public int getPrice()                { return price; }
    public void setPrice(int price)      { this.price = price; }

    public String getPhoto()             { return photo; }
    public void setPhoto(String photo)   { this.photo = photo; }

    public String getCategory()          { return category; }
    public void setCategory(String c)    { this.category = c; }

    public List<String> getReviews()     { return reviews; }
    public void setReviews(List<String> r){ this.reviews = r; }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description +
               ", price=" + price + ", photo=" + photo + ", category=" + category +
               ", reviews=" + reviews + "]";
    }
}
