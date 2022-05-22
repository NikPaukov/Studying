package com.example.demo;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address")

public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String city;

    @Column
    private String street;

    @Column(name = "home_number")
    private String homeNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adress adress = (Adress) o;
        return Objects.equals(id, adress.id) && Objects.equals(city, adress.city) && Objects.equals(street, adress.street) && Objects.equals(homeNumber, adress.homeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, homeNumber);
    }

    @Override
    public String toString() {
        return "Adress{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", homeNumber='" + homeNumber + '\'' +
                '}';
    }

    public Adress() {
    }

    public Adress(String city, String street, String homeNumber) {
        this.city = city;
        this.street = street;
        this.homeNumber = homeNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

//getters and setters
}
