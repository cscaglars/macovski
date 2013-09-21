package com.macovski.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Player")
public class Player extends Base {

	/**
	 * 
	 */

	private static final long serialVersionUID = 653418962396240420L;
	
	@Column(name = "PlayerName", nullable = true)
	private String PlayerName;
	
	@Column(name = "PlayerCity", nullable = true)
	private String City;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	//@JoinColumn(name = "LoginId", referencedColumnName = "Id", insertable = false, updatable = false)
	private Login PlayerLogin;
	
	//we can reach player from stats thats why it creates foreign key Ids inside both tables
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Stats PlayerStats;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "EnrolledMatches",
	joinColumns = {@JoinColumn(name = "PlayerId", referencedColumnName = "Id")},
    inverseJoinColumns = {@JoinColumn(name = "MatchId", referencedColumnName = "Id")})
	private List<Match> EnrolledMatches;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "InvitedMatches",
	joinColumns = {@JoinColumn(name = "PlayerId", referencedColumnName = "Id")},
    inverseJoinColumns = {@JoinColumn(name = "MatchId", referencedColumnName = "Id")})
	private List<Match> InvitedMatches;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "RejectedMatches",
	joinColumns = {@JoinColumn(name = "PlayerId", referencedColumnName = "Id")},
    inverseJoinColumns = {@JoinColumn(name = "MatchId", referencedColumnName = "Id")})
	private List<Match> RejectedMatches;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "PlayedMatches",
	joinColumns = {@JoinColumn(name = "PlayerId", referencedColumnName = "Id")},
    inverseJoinColumns = {@JoinColumn(name = "OldMatchId", referencedColumnName = "Id")})
	private List<OldMatch> PlayedMatches;

	public String getPlayerName() {
		return PlayerName;
	}

	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public Login getPlayerLogin() {
		return PlayerLogin;
	}

	public void setPlayerLogin(Login playerLogin) {
		PlayerLogin = playerLogin;
	}

	public Stats getPlayerStats() {
		return PlayerStats;
	}

	public void setPlayerStats(Stats playerStats) {
		PlayerStats = playerStats;
	}

	public List<Match> getEnrolledMatches() {
		return EnrolledMatches;
	}

	public void setEnrolledMatches(List<Match> enrolledMatches) {
		EnrolledMatches = enrolledMatches;
	}

	public List<Match> getInvitedMatches() {
		return InvitedMatches;
	}

	public void setInvitedMatches(List<Match> invitedMatches) {
		InvitedMatches = invitedMatches;
	}

	public List<Match> getRejectedMatches() {
		return RejectedMatches;
	}

	public void setRejectedMatches(List<Match> rejectedMatches) {
		RejectedMatches = rejectedMatches;
	}

	public List<OldMatch> getPlayedMatches() {
		return PlayedMatches;
	}

	public void setPlayedMatches(List<OldMatch> playedMatches) {
		PlayedMatches = playedMatches;
	}
}
