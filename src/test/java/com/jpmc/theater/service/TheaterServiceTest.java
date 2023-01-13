package com.jpmc.theater.service;

import com.jpmc.theater.model.dto.ScheduleResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TheaterServiceTest {

    @Autowired
    TheaterService theaterService;


    @Test
    void getMovieScheduleTest() {
        ScheduleResponse[] schedule = theaterService.getMovieSchedule();
        int index = 0;
        for (int i = 0; i < schedule.length;i++){
            assertEquals(i+1,schedule[i].getSequence());
        }
    }

    @Test
    void humanReadableFormatTest(){
        assertEquals("2 hours 0 minutes", theaterService.humanReadableFormat(Duration.ofMinutes(120L)));
        assertEquals("0 hours 0 minutes", theaterService.humanReadableFormat(Duration.ofMinutes(0L)));
        assertEquals("1 hour 30 minutes", theaterService.humanReadableFormat(Duration.ofMinutes(90L)));

    }

}
