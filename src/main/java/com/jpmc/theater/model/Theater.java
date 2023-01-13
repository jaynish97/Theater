package com.jpmc.theater.model;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Getter
public class Theater {


    private static final List<Movie> movieList =
            List.of(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                    new Movie("Turning Red", Duration.ofMinutes(85), 11, 0),
                    new Movie("The Batman", Duration.ofMinutes(95), 9, 0)
            );

    public static final Map<Integer,Showing> schedule =
            Map.ofEntries(
                    Map.entry(1,new Showing(movieList.get(1), 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)))),
                    Map.entry(2,new Showing(movieList.get(0), 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)))),
                    Map.entry(3,new Showing(movieList.get(2), 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 50)))),
                    Map.entry(4,new Showing(movieList.get(1), 4, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30)))),
                    Map.entry(5,new Showing(movieList.get(0), 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 10)))),
                    Map.entry(6,new Showing(movieList.get(2), 6, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 50)))),
                    Map.entry(7,new Showing(movieList.get(1), 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)))),
                    Map.entry(8,new Showing(movieList.get(0), 8, LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 10)))),
                    Map.entry(9,new Showing(movieList.get(2), 9, LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0))))
            );


}
