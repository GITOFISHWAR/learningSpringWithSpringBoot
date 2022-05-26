package com.learnspringboot.learningspring.business;

public class RoomDTO {

	private long id;
	private String name;
	private String roomNumber;
	private String bedInfo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getBedInfo() {
		return bedInfo;
	}

	public void setBedInfo(String bedInfo) {
		this.bedInfo = bedInfo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomDTO [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", roomNumber=");
		builder.append(roomNumber);
		builder.append(", bedInfo=");
		builder.append(bedInfo);
		builder.append("]");
		return builder.toString();
	}

}
