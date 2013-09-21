package com.macovski.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Venues")
public class Venue extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1818598096587342666L;
	
	@Column(name = "Address", nullable = true)
	private String Address;
	
	@Column(name = "CordX", nullable = false)
	private String CordX;
	
	@Column(name = "CordY", nullable = false)
	private String CordY;
	
	@OneToMany(mappedBy = "MatchVenue" ,cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Match> UpcomingMatches;
	
	@OneToMany(mappedBy = "MatchVenue" ,cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<OldMatch> PlayedMatches;

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCordX() {
		return CordX;
	}

	public void setCordX(String cordX) {
		CordX = cordX;
	}

	public String getCordY() {
		return CordY;
	}

	public void setCordY(String cordY) {
		CordY = cordY;
	}

	public List<Match> getUpcomingMatches() {
		return UpcomingMatches;
	}

	public void setUpcomingMatches(List<Match> upcomingMatches) {
		UpcomingMatches = upcomingMatches;
	}

	public List<OldMatch> getPlayedMatches() {
		return PlayedMatches;
	}

	public void setPlayedMatches(List<OldMatch> playedMatches) {
		PlayedMatches = playedMatches;
	}

//	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	@JoinTable(name = "PassedMatches",
//	joinColumns = {@JoinColumn(name = "VenueId", referencedColumnName = "Id")},
//    inverseJoinColumns = {@JoinColumn(name = "MatchId", referencedColumnName = "Id")})
//	private List<OldMatch> PassedMatches;
		
}
