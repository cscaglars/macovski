package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("unused")
@Entity
@Table(name = "Stats")
public class Stats extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5988547713447020264L;
	
	@OneToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name = "PlayerId", referencedColumnName = "Id", insertable = false, updatable = false)
	private Player player;
	
	@Column(name = "PlayerRank", nullable = true)
	private String Rank;
	
	@Column(name = "PlayedMatches", nullable = true)
	private int PlayedMatches;
	
	@Column(name = "NotAttendedMatches", nullable = true)
	private int NotAttendedMatches;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getRank() {
		return Rank;
	}

	public void setRank(String rank) {
		Rank = rank;
	}

	public int getPlayedMatches() {
		return PlayedMatches;
	}

	public void setPlayedMatches(int playedMatches) {
		PlayedMatches = playedMatches;
	}

	public int getNotAttendedMatches() {
		return NotAttendedMatches;
	}

	public void setNotAttendedMatches(int notAttendedMatches) {
		NotAttendedMatches = notAttendedMatches;
	}
}
