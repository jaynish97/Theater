package com.jpmc.theater.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class Showing {

    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

}
