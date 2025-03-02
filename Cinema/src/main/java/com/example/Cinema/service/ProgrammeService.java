package com.example.Cinema.service;

import com.example.Cinema.model.CinemaHall;
import com.example.Cinema.model.Dto.ProgrammeDto;
import com.example.Cinema.model.Movie;
import com.example.Cinema.model.Programme;
import com.example.Cinema.repository.CinemaHallRepository;
import com.example.Cinema.repository.MovieRepository;
import com.example.Cinema.repository.ProgrammeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class ProgrammeService {

    private final ProgrammeRepository programmeRepository;
    private final MovieRepository movieRepository;
    private final CinemaHallRepository cinemaHallRepository;

    @Autowired
    public ProgrammeService(ProgrammeRepository programmeRepository, MovieRepository movieRepository, CinemaHallRepository cinemaHallRepository) {
        this.programmeRepository = programmeRepository;
        this.movieRepository = movieRepository;
        this.cinemaHallRepository = cinemaHallRepository;
    }

    public List<Programme> getAllProgrammes() {
        return programmeRepository.findAll();
    }

    public void save(Programme programme) {
        programmeRepository.save(programme);
    }

    public void deleteById(Long id) {
        programmeRepository.deleteById(id);
    }

    public Optional<Programme> getProgrammeById(Long id) {
        return programmeRepository.findById(id);
    }


    public List<Programme> getProgrammes(LocalDate date, String hallName) {
        if(date != null && hallName != null && !hallName.equals("all")) {
            return programmeRepository.findByDateAndCinemaHall_Name(date, hallName);
        }
        else if(date != null){
            return programmeRepository.findByDate(date);
        }
        else if(hallName != null) {
            return programmeRepository.findByCinemaHall_Name(hallName);
        }

        return programmeRepository.findAll();
    }

    public void updateProgramme(ProgrammeDto programmeDto) {

        Programme programme = programmeRepository.findById(programmeDto.getId()).orElse(new Programme());

        Movie movie = movieRepository.findById(programmeDto.getIdmovie()).orElseThrow();
        CinemaHall cinemaHall = cinemaHallRepository.findCinemaHallByName(programmeDto.getCinemaHallName());

        programme.setMovie(movie);
        programme.setCinemaHall(cinemaHall);
        programme.setDate(programmeDto.getDate());
        programme.setTime(programmeDto.getTime());

        save(programme);
    }
}
