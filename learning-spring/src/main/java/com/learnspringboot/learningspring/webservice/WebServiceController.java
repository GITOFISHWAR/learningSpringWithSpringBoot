package com.learnspringboot.learningspring.webservice;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learnspringboot.learningspring.business.GuestDTO;
import com.learnspringboot.learningspring.business.ReservationService;
import com.learnspringboot.learningspring.business.RoomDTO;
import com.learnspringboot.learningspring.business.RoomReservation;
import com.learnspringboot.learningspring.data.Guest;
import com.learnspringboot.learningspring.util.DateUtils;

@RestController
@RequestMapping("/api")
public class WebServiceController {

	private final DateUtils dateUtils;
	private final ReservationService reservationService;

	public WebServiceController(DateUtils dateUtils, ReservationService reservationService) {

		this.dateUtils = dateUtils;
		this.reservationService = reservationService;
	}

	@RequestMapping(path = "/reservations", method = RequestMethod.GET)
	public List<RoomReservation> getReservations(@RequestParam(name = "date", required = false) String dateString) {

		Date date = this.dateUtils.createDateFromDateString(dateString);
		return this.reservationService.getRoomReservationsForDate(date);
	}

	@GetMapping(path = "/rooms")
	public List<RoomDTO> getAllRoomDetails() {

		List<RoomDTO> roomDTOList = this.reservationService.getAllRoomDetails();
		return roomDTOList;
	}

	@GetMapping(path = "/guests")
	public List<GuestDTO> getAllGuestDetails() {

		List<GuestDTO> guestDTOList = this.reservationService.getAllGuestDetails();
		return guestDTOList;
	}

	@PostMapping("/guests")
	@ResponseStatus(HttpStatus.CREATED)
	public void addGuest(@RequestBody Guest guest) {
		System.out.println(guest.toString());
		this.reservationService.addGuest(guest);
	}

	@DeleteMapping("/guests")
	@ResponseStatus(HttpStatus.OK)
	public void deleteGuest(@RequestParam(name = "id", required = true) String fName) {
		
		this.reservationService.deleteGuest(fName);

	}

}
