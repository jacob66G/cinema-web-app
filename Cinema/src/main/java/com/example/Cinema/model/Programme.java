package com.example.Cinema.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Programme {
    @Id
    @GeneratedValue
    private Long idprogramme;

    @ManyToOne
    @JoinColumn(name = "idmovie")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "idcinemahall")
    private CinemaHall cinemaHall;

    private LocalDate date;
    private LocalTime time;

    public Programme(Movie movie, LocalDate date, LocalTime time, CinemaHall cinemaHall) {
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.cinemaHall = cinemaHall;
    }
}