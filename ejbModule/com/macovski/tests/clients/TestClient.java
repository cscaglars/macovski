package com.macovski.tests.clients;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;

import com.macovski.entities.Login;
import com.macovski.entities.Match;
import com.macovski.entities.Player;
import com.macovski.entities.Stats;
import com.macovski.entities.Venue;
import com.macovski.jndi.JNDILookupClass;
import com.macovski.tests.interfaces.IBaseTestManager;

public class TestClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IBaseTestManager BaseManager = doLookup();
		
		CreatePlayer(BaseManager);
		CreateVenue(BaseManager);
		CreateMatch(BaseManager);
		AssingVenue(BaseManager);
		InvitePlayerToMatch(BaseManager);
		PlayerEnrollsToMatch(BaseManager);
		PlayerRejectsMatch(BaseManager);
	}
	
	private static void CreatePlayer(IBaseTestManager BaseManager)
	{		
		for (int i = 0; i < 100; i++) 
		{			
			Player player = new Player();
			player.setPlayerName("User" + i);
			player.setCity("TestLocation"+i);
			player.setPlayerLogin(CreateLogin(i));
			player.setPlayerStats(CreateStatsforPlayer(i));
			BaseManager.Insert(player);
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
	
	private static void CreateMatch(IBaseTestManager BaseManager)
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
				BaseManager.Insert(match);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	private static void CreateVenue(IBaseTestManager BaseManager)
	{
		for(int i = 0; i< 5; i++)
		{			
			Venue venue = new Venue();
			venue.setAddress("VenueAddress_"+ i);
			venue.setCordX(String.valueOf(randBetween(1000000, 9999999)));
			venue.setCordY(String.valueOf(randBetween(1000000, 9999999)));		
			BaseManager.Insert(venue);
		}
	}
	
	private static void AssingVenue(IBaseTestManager BaseManager)
	{
		List<Match> matches = new ArrayList<Match>();
		List<Venue> venues = new ArrayList<Venue>();
		
		for(int j = 0; j < 5; j++)
		{
			Venue venue = new Venue();
			venue = (Venue) BaseManager.GetAll(venue).get(j);
			venues.add(venue);
		}
		
		for(int i = 0; i < 10; i++)
		{
			Match match = new Match();
			match = (Match) BaseManager.GetAll(match).get(i);
			matches.add(match);
		}	
		
		for (Match match : matches) {
			match.setMatchVenue(venues.get(randBetween(0, 4)));
			BaseManager.Update(match);
		}		
		//match.setMatchVenue(venue);
		//BaseManager.Update(match);
	}
	
	private static void InvitePlayerToMatch(IBaseTestManager BaseManager)
	{
		List<Match> matches = new ArrayList<Match>();
		
		for(int i = 0; i < 10; i++)
		{
			Match match = new Match();
			match = (Match) BaseManager.GetAll(match).get(i);
			matches.add(match);
		}	
		
		for (Match match : matches) {
			
			for (int i = 0; i < 100; i++) 
			{
				Player player = new Player();
				player = (Player) BaseManager.GetAll(player).get(i);
				BaseManager.InvitePlayerToMatch(player, match);
			}
		}
	}
	
	private static void PlayerRejectsMatch(IBaseTestManager BaseManager)
	{
		for (int i = 0; i < 100; i++) 
		{
			Match match = new Match();
			Player player = new Player();
			match = (Match) BaseManager.GetAll(match).get(randBetween(0,9));
			player = (Player) BaseManager.GetAll(player).get(i);
			BaseManager.RejectMatchInviteByPlayer(player);
			//BaseManager.RejectMatchInvite(player, match);
		}
	}
	
	private static void PlayerEnrollsToMatch(IBaseTestManager BaseManager)
	{
		for (int i = 0; i < 100; i++) 
		{
			Match match = new Match();
			Player player = new Player();
			match = (Match) BaseManager.GetAll(match).get(randBetween(0,9));
			player = (Player) BaseManager.GetAll(player).get(i);
			BaseManager.AcceptMatchInviteByPlayer(player);
			//BaseManager.AcceptMatchInvite(player, match);
		}
	}
	
	
	
	private static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
	
	private static IBaseTestManager doLookup() {
		Context context = null;
		IBaseTestManager bean = null;
        try {
            // 1. Obtaining Context
            context = JNDILookupClass.getInitialContext();
            // 2. Generate JNDI Lookup name
            String lookupName = "ejb:/macovski//BaseTestManager!com.macovski.tests.interfaces.IBaseTestManager";
            // 3. Lookup and cast
            bean = (IBaseTestManager) context.lookup(lookupName);
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return bean;
	}

}
