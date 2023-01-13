package com.jpmc.theater.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class ScheduleResponse {

    private int sequence;
    private LocalDateTime showStartTime;
    private String movieTitle;
    private String runningTime;
    private double price;

}
