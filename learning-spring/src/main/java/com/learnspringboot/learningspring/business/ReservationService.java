package com.learnspringboot.learningspring.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnspringboot.learningspring.data.Guest;
import com.learnspringboot.learningspring.data.GuestRepository;
import com.learnspringboot.learningspring.data.Reservation;
import com.learnspringboot.learningspring.data.ReservationRepository;
import com.learnspringboot.learningspring.data.Room;
import com.learnspringboot.learningspring.data.RoomRepository;

@Service
public class ReservationService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private GuestRepository guestRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	public List<RoomReservation> getRoomReservationsForDate(Date date) {
		Iterable<Room> rooms = this.roomRepository.findAll();
		Map<Long, RoomReservation> roomReservationMap = new HashMap<Long, RoomReservation>();
		rooms.forEach(room -> {
			RoomReservation roomReservation = new RoomReservation();
			roomReservation.setRoomId(room.getId());
			roomReservation.setRoomName(room.getName());
			roomReservation.setRoomNumber(room.getRoomNumber());
			roomReservationMap.put(room.getId(), roomReservation);
		});
		Iterable<Reservation> reservations = this.reservationRepository
				.findReservationByResDate(new java.sql.Date(date.getTime()));
		reservations.forEach(reservation -> {
			RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
			roomReservation.setDate(date);
			Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
			roomReservation.setFirstName(guest.getFirstName());
			roomReservation.setLastName(guest.getLastName());
			roomReservation.setGuestId(guest.getGuestId());
		});
		List<RoomReservation> roomReservations = new ArrayList<>();
		for (Long id : roomReservationMap.keySet()) {
			roomReservations.add(roomReservationMap.get(id));
		}
		roomReservations.sort(new Comparator<RoomReservation>() {
			@Override
			public int compare(RoomReservation o1, RoomReservation o2) {
				if (o1.getRoomName().equals(o2.getRoomName())) {
					return o1.getRoomNumber().compareTo(o2.getRoomNumber());
				}
				return o1.getRoomName().compareTo(o2.getRoomName());
			}
		});
		return roomReservations;
	}

	public List<GuestDTO> getAllGuestDetails() {

		Iterable<Guest> guests = this.guestRepository.findAll();
		Map<Long, GuestDTO> guestMap = new HashMap<Long, GuestDTO>();
		guests.forEach(guest -> {

			GuestDTO guestDTO = new GuestDTO();
			guestDTO.setFirstName(guest.getFirstName());
			guestDTO.setLastName(guest.getLastName());
			guestDTO.setEmail(guest.getEmail());
			guestDTO.setPhoneNumber(guest.getPhoneNumber());
			guestDTO.setCountry(guest.getCountry());
			guestDTO.setState(guest.getState());
			guestDTO.setAddress(guest.getAddress());
			guestMap.put(guest.getGuestId(), guestDTO);
		});

		List<GuestDTO> guestDTOList = new ArrayList<>();
		for (Long id : guestMap.keySet()) {
			guestDTOList.add(guestMap.get(id));
		}
		return guestDTOList;
	}

	public List<RoomDTO> getAllRoomDetails() {

		Iterable<Room> rooms = this.roomRepository.findAll();
		Map<Long, RoomDTO> roomHM = new HashMap<Long, RoomDTO>();
		rooms.forEach(room -> {
			RoomDTO roomDTO = new RoomDTO();
			roomDTO.setBedInfo(room.getBedInfo());
			roomDTO.setName(room.getName());
			roomDTO.setRoomNumber(room.getRoomNumber());
			roomHM.put(room.getId(), roomDTO);
		});

		List<RoomDTO> roomDTOList = new ArrayList<RoomDTO>();

		for (Long id : roomHM.keySet()) {
			roomDTOList.add(roomHM.get(id));
		}
		return roomDTOList;
	}

	public void addGuest(Guest guest) {

		if (null == guest) {
			throw new RuntimeException("Guest cannot be null!!!");
		}
		this.guestRepository.save(guest);
	}

	public void deleteGuest(String fName) {

		Guest guest = this.guestRepository.findGuestByFirstName(fName);
		this.guestRepository.delete(guest);

	}

}
