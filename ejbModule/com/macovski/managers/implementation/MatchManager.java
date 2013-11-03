package com.macovski.managers.implementation;

import java.util.List;

import com.macovski.entities.Match;
import com.macovski.managers.interfaces.IMatchManager;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Session Bean implementation class MatchManager
 */

@Stateless(mappedName="MatchManager")
@LocalBean
public class MatchManager extends BaseManager<Match> implements IMatchManager {
      	
	@PersistenceContext(unitName = "JPADB")
    protected EntityManager em;
	
	private Logger Log = LogManager.getLogger(MatchManager.class.getName());
	
    /**
     * @see BaseManager#BaseManager()
     */
    public  MatchManager() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void InitializeEM()
    {
    	entityManager = em;
    }
    
    @SuppressWarnings("unchecked")
	public List<Match> GetAll()
    {
    	System.out.println("Object is " + Match.class.getSimpleName());
    	String queryString = "SELECT g FROM " + Match.class.getSimpleName() +" g";
    	Query query = entityManager.createQuery(queryString);
    	List<Match> resultList = (List<Match>) query.getResultList();
		return resultList;
    }
    
    public Match GetById(Long Id)
    {
    	Log.info("MatchManager returned instance with id: " + Id.toString());
    	return (Match)entityManager.find(Match.class, Id);
    }
}
