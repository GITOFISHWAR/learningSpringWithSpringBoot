package com.learnspringboot.learningspring.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.learnspringboot.learningspring.business.GuestDTO;
import com.learnspringboot.learningspring.business.ReservationService;

@Controller
@RequestMapping("/guests")
public class GuestController {

	private ReservationService reservationService;

	public GuestController(ReservationService reservationService) {
		super();
		this.reservationService = reservationService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllGuestDetails(Model model){
		
		List<GuestDTO> guestDTOs = this.reservationService.getAllGuestDetails();
		model.addAttribute("guestDTOs", guestDTOs);
		//guestDTOs.forEach(System.out::println);
		return "guests";
	}
	
}
