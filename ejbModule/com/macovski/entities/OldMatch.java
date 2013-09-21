package com.macovski.entities;

import java.sql.Date;
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
@Table(name = "OldMatch")
public class OldMatch extends Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1095386917554924580L;
	
	@Column(name = "MatchDate", nullable = true)
	private Date MatchDate;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "PlayedMatches",
	joinColumns = {@JoinColumn(name = "OldMatchId", referencedColumnName = "Id")},
    inverseJoinColumns = {@JoinColumn(name = "PlayerId", referencedColumnName = "Id")})
	private List<Player> PlayedPlayers;
	
	@Column(name = "PrivateMatch", nullable = true)
	private Boolean PrivateMatch;
	
	@JoinColumn(name = "VenueId", referencedColumnName = "Id")
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Venue MatchVenue;

	public Date getMatchDate() {
		return MatchDate;
	}

	public void setMatchDate(Date matchDate) {
		MatchDate = matchDate;
	}

	public List<Player> getPlayedPlayers() {
		return PlayedPlayers;
	}

	public void setPlayedPlayers(List<Player> playedPlayers) {
		PlayedPlayers = playedPlayers;
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
