package com.learnspringboot.learningspring.util;

import java.util.Date;
import java.util.List;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.learnspringboot.learningspring.business.GuestDTO;
import com.learnspringboot.learningspring.business.ReservationService;
import com.learnspringboot.learningspring.business.RoomReservation;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final ReservationService reservationService;
    private final DateUtils dateUtils;

    public AppStartupEvent(ReservationService reservationService, DateUtils dateUtils) {
        this.reservationService = reservationService;
        this.dateUtils = dateUtils;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Date date = this.dateUtils.createDateFromDateString("2022-01-01");
        List<RoomReservation> reservations = this.reservationService.getRoomReservationsForDate(date);
        reservations.forEach(System.out::println);
        List<GuestDTO> gustesDTOs=this.reservationService.getAllGuestDetails();
        gustesDTOs.forEach(System.out::println);
    }
}
