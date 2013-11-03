package com.macovski.managers.implementation;

import java.util.List;

import com.macovski.entities.Match;
import com.macovski.entities.Player;
import com.macovski.managers.interfaces.IPlayerManager;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;


/**
 * Session Bean implementation class PlayerManager
 */
@Stateless(mappedName="PlayerManager")
@LocalBean
public class PlayerManager extends BaseManager<Player> implements IPlayerManager {
     
	@PersistenceContext(unitName = "JPADB")
    protected EntityManager em;
	
	private Logger Log = LogManager.getLogger(PlayerManager.class.getName());
	
    /**
     * @see BaseManager#BaseManager()
     */
    public  PlayerManager() {
    	 super();
        // TODO Auto-generated constructor stub  	 
    }
    
    public void InitializeEM()
    {
    	entityManager = em;
    }
    
    public void InvitePlayerToMatch(Player player, Match match)
    {
    	Player invitedPlayer = entityManager.find(Player.class, player.getId());
    	Match invitedMatch = entityManager.find(Match.class, match.getId());
    	invitedPlayer.getInvitedMatches().add(invitedMatch);
    	Update(invitedPlayer);
    }
    
    public void AcceptMatchInvite(Player player, Match match)
    {
    	Player invitedPlayer = entityManager.find(Player.class, player.getId());
    	Match invitedMatch = entityManager.find(Match.class, match.getId());
    	if(invitedPlayer.getEnrolledMatches().contains(invitedMatch))
    	{
    		invitedPlayer.getEnrolledMatches().add(invitedMatch);
        	invitedPlayer.getInvitedMatches().remove(invitedMatch);
        	Update(invitedPlayer);
    	}   	
    }
    
    public void RejectMatchInvite(Player player, Match match)
    {
    	Player invitedPlayer = entityManager.find(Player.class, player.getId());
    	Match invitedMatch = entityManager.find(Match.class, match.getId());
    	if(invitedPlayer.getInvitedMatches().contains(invitedMatch))
    	{
    		invitedPlayer.getInvitedMatches().remove(invitedMatch);
        	invitedPlayer.getRejectedMatches().add(invitedMatch);
        	Update(invitedPlayer);
    	}    	
    }
    
    @SuppressWarnings("unchecked")
	public List<Player> GetAll()
    {
    	System.out.println("Object is " + Player.class.getSimpleName());
    	String queryString = "SELECT g FROM " + Player.class.getSimpleName() +" g";
    	Query query = entityManager.createQuery(queryString);
    	List<Player> resultList = (List<Player>) query.getResultList();
		return resultList;
    }
    
    public Player GetById(Long Id)
    {
    	Log.info("PlayerManager returned instance with id: " + Id.toString());
    	return (Player)entityManager.find(Player.class, Id);
    }
    
    
    /**
     * Testing Purpose Only
     */
    public void AcceptMatchInviteByPlayer(Player player)
    {
    	Player invitedPlayer = entityManager.find(Player.class, player.getId());
    	int invitedMatchCount = invitedPlayer.getInvitedMatches().size();
    	for(int i = 0; i<invitedMatchCount/2; i++)
    	{
    		System.out.println("AcceptMatchInviteByPlayer if true , count: " + invitedMatchCount);
    		
    		Match match = invitedPlayer.getInvitedMatches().get(randBetween(1,invitedMatchCount/2));
    		invitedPlayer.getInvitedMatches().remove(match);
    		invitedPlayer.getEnrolledMatches().add(match);
    		Update(invitedPlayer);
    	}
    }
    
    public void RejectMatchInviteByPlayer(Player player)
    {
    	Player invitedPlayer = entityManager.find(Player.class, player.getId());
    	int invitedMatchCount = invitedPlayer.getInvitedMatches().size();
    	for(int i = 0; i<invitedMatchCount/2; i++)
    	{
    		System.out.println("RejectMatchInviteByPlayer if true , count: " + invitedMatchCount);
    		
    		Match match = invitedPlayer.getInvitedMatches().get(randBetween(1,invitedMatchCount/2));
    		invitedPlayer.getInvitedMatches().remove(match);
    		invitedPlayer.getRejectedMatches().add(match);
    		Update(invitedPlayer);
    	}
    }
    
    private static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
    
}
