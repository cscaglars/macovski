package com.macovski.sessions;

import java.util.List;

import com.macovski.entities.Match;
import com.macovski.interfaces.IMatchManager;
import com.macovski.jndi.JNDILookup;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class MatchManager
 */

@Stateless(mappedName="MatchManager")
@EJB(name="MatchManager", beanInterface=IMatchManager.class)
public class MatchManager extends BaseManager<Match> implements IMatchManager {
      	
	@PersistenceContext(unitName = "JPADB")
    protected EntityManager em;
	
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
    
    public List<Match> GetAll()
    {
    	System.out.println("Object is " + Match.class.getSimpleName());
    	String queryString = "SELECT g FROM " + Match.class.getSimpleName() +" g";
    	Query query = entityManager.createQuery(queryString);
    	List<Match> resultList = (List<Match>) query.getResultList();
		return resultList;
    }
}
