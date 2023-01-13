package com.jpmc.theater.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.dto.ReservationRequest;
import com.jpmc.theater.service.ReservationService;
import com.jpmc.theater.service.TheaterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TheaterController.class)
public class TheaterControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    TheaterService theaterService;

    @MockBean
    ReservationService reservationService;

    @Test
    void getScheduleAsTextTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/theater/schedule/text"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getScheduleAsJsonTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/theater/schedule/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void createReservationTest() throws Exception {
        Customer customer = Customer.builder()
                .name("User")
                .id("1")
                .build();

        ReservationRequest reservationRequest = ReservationRequest.builder()
                .customer(customer)
                .sequence(1)
                .numberOfTickets(1)
                .build();

        mvc.perform(MockMvcRequestBuilders
                .post("/theater/reserve")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(reservationRequest)))
                .andExpect(status().isOk());

    }

    @Test
    void createReservationTest_InvalidNumberTicketsBadRequest() throws Exception {
        Customer customer = Customer.builder()
                .name("User")
                .id("1")
                .build();

        ReservationRequest reservationRequest = ReservationRequest.builder()
                .customer(customer)
                .sequence(1)
                .numberOfTickets(0)
                .build();

        mvc.perform(MockMvcRequestBuilders
                        .post("/theater/reserve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getMapper().writeValueAsString(reservationRequest)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void createReservationTest_InvalidSequenceBadRequest() throws Exception {
        Customer customer = Customer.builder()
                .name("User")
                .id("1")
                .build();

        ReservationRequest reservationRequest = ReservationRequest.builder()
                .customer(customer)
                .sequence(0)
                .numberOfTickets(4)
                .build();

        mvc.perform(MockMvcRequestBuilders
                        .post("/theater/reserve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getMapper().writeValueAsString(reservationRequest)))
                .andExpect(status().isBadRequest());

    }

    private ObjectMapper getMapper(){
        return new ObjectMapper();
    }


}
