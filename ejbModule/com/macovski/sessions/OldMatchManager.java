package com.macovski.sessions;

import java.util.List;

import com.macovski.entities.OldMatch;
import com.macovski.interfaces.IOldMatchManager;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class OldMatchManager
 */
@Stateless
@LocalBean
public class OldMatchManager extends BaseManager<OldMatch> implements IOldMatchManager {
     
	@PersistenceContext(unitName = "JPADB")
    protected EntityManager em;
	
    /**
     * @see BaseManager#BaseManager()
     */
    public OldMatchManager() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void InitializeEM()
    {
    	entityManager = em;
    }
    
    public List<OldMatch> GetAll()
    {
    	System.out.println("Object is " + OldMatch.class.getSimpleName());
    	String queryString = "SELECT g FROM " + OldMatch.class.getSimpleName() +" g";
    	Query query = entityManager.createQuery(queryString);
    	List<OldMatch> resultList = (List<OldMatch>) query.getResultList();
		return resultList;
    }

}
