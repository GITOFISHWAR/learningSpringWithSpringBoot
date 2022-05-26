/**
 * 
 */
package com.learnspringboot.learningspring.web;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnspringboot.learningspring.business.ReservationService;
import com.learnspringboot.learningspring.business.RoomReservation;
import com.learnspringboot.learningspring.util.DateUtils;

/**
 * @author ishwarsingh
 *
 */

@Controller
@RequestMapping("/reservations")
public class RoomReservationController {

	private final DateUtils dateUtils;
	private final ReservationService reservationService;

	public RoomReservationController(DateUtils dateUtils, ReservationService reservationService) {
		super();
		this.dateUtils = dateUtils;
		this.reservationService = reservationService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getReservations(@RequestParam(value = "date", required = false) String dateString, Model model) {

		Date date = this.dateUtils.createDateFromDateString(dateString);
		List<RoomReservation> roomReservations = this.reservationService.getRoomReservationsForDate(date);
		model.addAttribute("roomReservations", roomReservations);
		return "roomres"; // Return web page name
	}

}
