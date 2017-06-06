package com.springframework.cinema.domain.room;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Patryk on 2017-05-27.
 */
@Entity
@Table(name = "roomSeats")
public class RoomSeat implements Serializable {
	
	private static final long serialVersionUID = 6385739183421877208L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "room_fk")
	Room room;

	public RoomSeat(){}

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }
	
}