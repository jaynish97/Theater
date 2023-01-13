package com.jpmc.theater.service;

import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Theater;
import com.jpmc.theater.model.dto.ScheduleResponse;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Service
public class TheaterService {

    /*
    Creates Theater schedule to be returned as JSON
     */
    public ScheduleResponse[] getMovieSchedule(){

        ScheduleResponse[] movieSchedule = new ScheduleResponse[Theater.schedule.size()];

        for (Showing showing : Theater.schedule.values()){
            movieSchedule[showing.getSequenceOfTheDay()-1] =
                    ScheduleResponse.builder()
                            .showStartTime(showing.getShowStartTime())
                            .price(showing.getMovie().getTicketPrice())
                            .movieTitle(showing.getMovie().getTitle())
                            .sequence(showing.getSequenceOfTheDay())
                            .runningTime(humanReadableFormat(showing.getMovie().getRunningTime()))
                            .build();

        }

        return movieSchedule;
    }

    /*
    Creates Theater schedule to be returned as Text
     */
    public String getMovieScheduleText(){

        ScheduleResponse[] movieSchedule = getMovieSchedule();

        StringBuilder sb = new StringBuilder();
        sb.append(LocalDate.now() + "\n");
        sb.append("===================================================\n");
        for (ScheduleResponse s : movieSchedule){
            sb.append(s.getSequence() + ": " + s.getShowStartTime() + " " + s.getMovieTitle() + " (" + s.getRunningTime() + ") $" + s.getPrice() + "\n");
        }
        sb.append("===================================================\n");

        return sb.toString();
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("%s hour%s %s minute%s", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }
}
