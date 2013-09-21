package com.macovski.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Matches")
public class Match extends Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1756204491206994359L;
	
	@Column(name = "MatchDate", nullable = true)
	private Date MatchDate;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "EnrolledMatches",
	joinColumns = {@JoinColumn(name = "MatchId", referencedColumnName = "Id")},
    inverseJoinColumns = {@JoinColumn(name = "PlayerId", referencedColumnName = "Id")})
	private List<Player> EnrolledPlayers;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "InvitedMatches",
	joinColumns = {@JoinColumn(name = "MatchId", referencedColumnName = "Id")},
    inverseJoinColumns = {@JoinColumn(name = "PlayerId", referencedColumnName = "Id")})
	private List<Player> InvitedPlayers;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "RejectedMatches",
	joinColumns = {@JoinColumn(name = "MatchId", referencedColumnName = "Id")},
    inverseJoinColumns = {@JoinColumn(name = "PlayerId", referencedColumnName = "Id")})
	private List<Player> RejectedPlayers;
	
	@Column(name = "PrivateMatch", nullable = true)
	private Boolean PrivateMatch;
	
	@JoinColumn(name = "VenueId", referencedColumnName = "Id")
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Venue MatchVenue;

	public Date getMatchDate() {
		return MatchDate;
	}

	public void setMatchDate(Date matchDate) {
		MatchDate = matchDate;
	}

	public List<Player> getEnrolledPlayers() {
		return EnrolledPlayers;
	}

	public void setEnrolledPlayers(List<Player> enrolledPlayers) {
		EnrolledPlayers = enrolledPlayers;
	}

	public List<Player> getInvitedPlayers() {
		return InvitedPlayers;
	}

	public void setInvitedPlayers(List<Player> invitedPlayers) {
		InvitedPlayers = invitedPlayers;
	}

	public List<Player> getRejectedPlayers() {
		return RejectedPlayers;
	}

	public void setRejectedPlayers(List<Player> rejectedPlayers) {
		RejectedPlayers = rejectedPlayers;
	}

	public Boolean getPrivateMatch() {
		return PrivateMatch;
	}

	public void setPrivateMatch(Boolean privateMatch) {
		PrivateMatch = privateMatch;
	}

	public Venue getMatchVenue() {
		return MatchVenue;
	}

	public void setMatchVenue(Venue matchVenue) {
		MatchVenue = matchVenue;
	}	
}
