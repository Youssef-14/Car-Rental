package com.carrentalbackend.reclamations;

import com.carrentalbackend.booking.BookACar;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
public class Reclamation {
    // booking  with table BookACar
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "book_a_car_id", nullable = false)
    private BookACar bookACar;

    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private Date reclamationDate;

    public Reclamation() {
    }

    public Reclamation(Long id, String description, BookACar bookACar, Date reclamationDate) {
        this.id = id;
        this.description = description;
        this.bookACar = bookACar;
        this.reclamationDate = reclamationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookACar getBookACar() {
        return bookACar;
    }

    public void setBookACar(BookACar bookACar) {
        this.bookACar = bookACar;
    }

    public Date getReclamationDate() {
        return reclamationDate;
    }

    public void setReclamationDate(Date reclamationDate) {
        this.reclamationDate = reclamationDate;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", bookACar=" + bookACar +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reclamation)) return false;
        Reclamation that = (Reclamation) o;
        return id.equals(that.id) &&
                description.equals(that.description) &&
                bookACar.equals(that.bookACar);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + bookACar.hashCode();
        return result;
    }
}
