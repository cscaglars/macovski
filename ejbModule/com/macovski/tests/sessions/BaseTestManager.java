package com.macovski.tests.sessions;

import java.util.List;

import com.macovski.entities.Match;
import com.macovski.entities.Player;
import com.macovski.tests.interfaces.IBaseTestManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.metamodel.EntityType;

/**
 * Session Bean implementation class BaseTestManager
 */
@Stateless(mappedName="BaseTestManager")
@EJB(name="TestManager", beanInterface=IBaseTestManager.class)
public class BaseTestManager implements IBaseTestManager {

	@PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
	
	//EntityTransaction tx = entityManager.getTransaction();
	
    /**
     * Default constructor. 
     */
    public BaseTestManager() {
        // TODO Auto-generated constructor stub
    }
    
    public void Insert(Object entity)
    {
    	entityManager.persist(entity);
    }

    public void Delete(Object entity)
    {
    	entityManager.remove(entity);
    }
    
    public void Update(Object entity)
    {
    	entityManager.merge(entity);
    }
    
    public List<Object> GetAll(Object object)
    {
    	System.out.println("Object is " + object.getClass().getSimpleName());
    	String queryString = "SELECT g FROM " + object.getClass().getSimpleName() +" g";
    	Query query = entityManager.createQuery(queryString);
    	return (List<Object>) query.getResultList();
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
