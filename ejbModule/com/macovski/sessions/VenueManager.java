package com.macovski.sessions;

import java.util.List;

import com.macovski.entities.Venue;
import com.macovski.interfaces.IMatchManager;
import com.macovski.interfaces.IVenueManager;
import com.macovski.jndi.JNDILookup;
import com.sun.org.apache.regexp.internal.recompile;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class VenueManager
 */
@Stateless(mappedName="VenueManager")
@EJB(name="VenueManager", beanInterface=IVenueManager.class)
public class VenueManager extends BaseManager<Venue> implements IVenueManager {
      
	@PersistenceContext(unitName = "JPADB")
    protected EntityManager em;
	
    /**
     * @see BaseManager#BaseManager()
     */
    public VenueManager() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void InitializeEM()
    {
    	entityManager = em;
    }
    
    public List<Venue> GetAll()
    {
    	System.out.println("Object is " + Venue.class.getSimpleName());
    	String queryString = "SELECT g FROM " + Venue.class.getSimpleName() +" g";
    	Query query = entityManager.createQuery(queryString);
    	List<Venue> resultList = (List<Venue>) query.getResultList();
		return resultList;
    }

}
