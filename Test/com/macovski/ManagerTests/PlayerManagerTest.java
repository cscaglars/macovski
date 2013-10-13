package com.macovski.ManagerTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.macovski.BaseTest.BaseTest;
import com.macovski.entities.Login;
import com.macovski.entities.Player;
import com.macovski.entities.Stats;

public class PlayerManagerTest extends BaseTest {
	
	@Test
	public void testInsertPlayer_fails_if_cannot_fetch_from_DB() {
		
	    boolean playerFound = false;
		
		Player player = new Player();
		player.setPlayerName("Ertan");
		player.setPlayerLastName("Ergun");
		player.setCity("Milano");
		
		Login login = new Login();
		login.setUsername("ertanergun");
		login.setPassword("12345");		
		player.setPlayerLogin(login);
		
		Stats stats = new Stats();
		stats.setNotAttendedMatches(randBetween(1, 20));
		stats.setPlayedMatches(randBetween(1, 20));
		stats.setRank("King");		
		player.setPlayerStats(stats);
		
		playerManager.Insert(player);
		
		List<Player> players = playerManager.GetAll();
		
		for (Player plyr : players) {
			
			if(plyr.getPlayerName().equals("Ertan"))
			{
				assertEquals("Ertan", plyr.getPlayerName());
				playerFound = true;
				
			}
		}
		assertTrue(playerFound);
	}
	
	private static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

}
