package com.macovski.tests.clients;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	}
	
	private static void CreatePlayer(IBaseTestManager BaseManager)
	{
		List<Match> matches = new ArrayList<Match>();
		Player player = new Player();
		player.setPlayerName("Ertan");
		player.setCity("Milano");
		player.setInvitedMatches(matches);
		player.setEnrolledMatches(matches);
		player.setRejectedMatches(matches);
		player.setPlayerLogin(CreateLogin());
		player.setPlayerStats(CreateStatsforPlayer());
		BaseManager.Insert(player);
	}
	
	private static Login CreateLogin()
	{
		Login login = new Login();
		login.setUsername("ertan");
		login.setPassword("1234");
		return login;
	}
	
	private static Stats CreateStatsforPlayer()
	{
		Stats stats = new Stats();
		stats.setNotAttendedMatches(1);
		stats.setPlayedMatches(2);
		stats.setRank("King");
		return stats;
	}
	
	private static void CreateMatch(IBaseTestManager BaseManager)
	{
		Match match = new Match();
		
		try {
			String date = "03/07/2013 21:54";
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			match.setMatchDate(dateFormat.parse(date));
			match.setPrivateMatch(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BaseManager.Insert(match);
	}
	
	private static void CreateVenue(IBaseTestManager BaseManager)
	{
		Venue venue = new Venue();
		venue.setAddress("Via Anotnio Bazzini 13, 20131, Milano");
		venue.setCordX("1010101001010");
		venue.setCordY("2347239472");		
		BaseManager.Insert(venue);
	}
	
	private static void AssingVenue(IBaseTestManager BaseManager)
	{
		Venue venue = new Venue();
		Match match = new Match();
		venue = (Venue) BaseManager.GetAll(venue).get(0);
		match = (Match) BaseManager.GetAll(match).get(0);
		match.setMatchVenue(venue);
		BaseManager.Update(match);
	}
	
	private static void InvitePlayerToMatch(IBaseTestManager BaseManager)
	{
		Match match = new Match();
		Player player = new Player();
		List<Match> matches = new ArrayList<Match>();
		match = (Match) BaseManager.GetAll(match).get(0);
		player = (Player) BaseManager.GetAll(player).get(0);
		matches = player.getInvitedMatches();
		matches.add(match);
		player.setInvitedMatches(matches);
		BaseManager.Update(player);
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
