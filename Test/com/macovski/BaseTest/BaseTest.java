package com.macovski.BaseTest;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.macovski.entities.Login;
import com.macovski.entities.Match;
import com.macovski.entities.Player;
import com.macovski.entities.Stats;
import com.macovski.entities.Venue;
import com.macovski.interfaces.IMatchManager;
import com.macovski.interfaces.IPlayerManager;
import com.macovski.interfaces.IVenueManager;
import com.macovski.jndi.JNDILookup;

public class BaseTest {

	public static IPlayerManager playerManager = JNDILookup.getPlayerManagerLookup();
	public static IMatchManager matchManager = JNDILookup.getMatchManagerLookup();
	public static IVenueManager venueManager = JNDILookup.getVenueManagerLookup();
	public boolean createDB = true;
	
	
	public static String name;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		CreateDummyData();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	public static void CreateDummyData()
	{
		CreatePlayer();
		CreateVenue();
		CreateMatch();
		AssingVenue();
		InvitePlayerToMatch();
		PlayerEnrollsToMatch();
		PlayerRejectsMatch();
	}
	
	private static void CreatePlayer()
	{		
		for (int i = 0; i < 100; i++) 
		{			
			Player player = new Player();
			player.setPlayerName("User" + i);
			player.setPlayerLastName("UserLastname" + i);
			player.setCity("TestLocation"+i);
			player.setPlayerLogin(CreateLogin(i));
			player.setPlayerStats(CreateStatsforPlayer(i));
			playerManager.Insert(player);
		}
	}
	
	private static Login CreateLogin(int i)
	{
		Login login = new Login();
		login.setUsername("username_"+i);
		login.setPassword(String.valueOf(randBetween(1000, 4000)));
		return login;
	}
	
	private static Stats CreateStatsforPlayer(int i)
	{		
		Stats stats = new Stats();
		stats.setNotAttendedMatches(randBetween(1, 20));
		stats.setPlayedMatches(randBetween(1, 20));
		stats.setRank("Rank"+i);
		return stats;
	}
	
	private static void CreateMatch()
	{
		for(int i = 0; i< 10; i++)
		{
			try {
				Match match = new Match();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");			
				int year = 2013; //randBetween(2013, 2013);// Here you can set Range of years you need
		        int month = randBetween(10, 12);
		        int hour = randBetween(0, 23); //Hours will be displayed in between 9 to 22
		        int min = randBetween(0, 59);
		        
		        GregorianCalendar gc = new GregorianCalendar(year, month, 1);
		        int day = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_MONTH));
		        //gc.set(year, month, day, hour, min);
		        
		        //String date = "03/07/2013 21:54";
		        String date = String.format("%d/%d/%d %d:%d", day,month,year, hour,min);
		        
				match.setMatchDate(dateFormat.parse(date));
				int pvt = randBetween(0, 1);
				match.setPrivateMatch(pvt == 1?true:false);
				matchManager.Insert(match);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	private static void CreateVenue()
	{
		for(int i = 0; i< 5; i++)
		{			
			Venue venue = new Venue();
			venue.setAddress("VenueAddress_"+ i);
			venue.setCordX(String.valueOf(randBetween(1000000, 9999999)));
			venue.setCordY(String.valueOf(randBetween(1000000, 9999999)));		
			venueManager.Insert(venue);
		}
	}
	
	private static void AssingVenue()
	{
		List<Match> matches = new ArrayList<Match>();
		List<Venue> venues = new ArrayList<Venue>();
		
		for(int j = 0; j < 5; j++)
		{
			Venue venue = new Venue();
			venue = (Venue) venueManager.GetAll().get(j);
			venues.add(venue);
		}
		
		for(int i = 0; i < 10; i++)
		{
			Match match = new Match();
			match = (Match) matchManager.GetAll().get(i);
			matches.add(match);
		}	
		
		for (Match match : matches) {
			match.setMatchVenue(venues.get(randBetween(0, 4)));
			matchManager.Update(match);
		}		
	}
	
	private static void InvitePlayerToMatch()
	{
		List<Match> matches = new ArrayList<Match>();
		
		for(int i = 0; i < 10; i++)
		{
			Match match = new Match();
			match = (Match) matchManager.GetAll().get(i);
			matches.add(match);
		}	
		
		for (Match match : matches) {
			
			for (int i = 0; i < 100; i++) 
			{
				Player player = new Player();
				player = (Player) playerManager.GetAll().get(i);
				playerManager.InvitePlayerToMatch(player, match);
			}
		}
	}
	
	private static void PlayerRejectsMatch()
	{
		for (int i = 0; i < 100; i++) 
		{
			Match match = new Match();
			Player player = new Player();
			match = (Match) matchManager.GetAll().get(randBetween(0,9));
			player = (Player) playerManager.GetAll().get(i);
			playerManager.RejectMatchInviteByPlayer(player);
		}
	}
	
	private static void PlayerEnrollsToMatch()
	{
		for (int i = 0; i < 100; i++) 
		{
			Match match = new Match();
			Player player = new Player();
			match = (Match) matchManager.GetAll().get(randBetween(0,9));
			player = (Player) playerManager.GetAll().get(i);
			playerManager.AcceptMatchInviteByPlayer(player);
		}
	}
	
	private static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

}
