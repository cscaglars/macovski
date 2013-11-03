package com.macovski.sessions;

import java.util.List;

import com.macovski.entities.Venue;
import com.macovski.interfaces.IVenueManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Session Bean implementation class VenueManager
 */
@Stateless(mappedName="VenueManager")
@EJB(name="VenueManager", beanInterface=IVenueManager.class)
public class VenueManager extends BaseManager<Venue> implements IVenueManager {
      
	@PersistenceContext(unitName = "JPADB")
    protected EntityManager em;
	
	private Logger Log = LogManager.getLogger(VenueManager.class.getName());
	
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
    	Log.info("VenueManager has been initialized");
    }
    
    @SuppressWarnings("unchecked")
	public List<Venue> GetAll()
    {
    	System.out.println("Object is " + Venue.class.getSimpleName());
    	String queryString = "SELECT g FROM " + Venue.class.getSimpleName() +" g";
    	Query query = entityManager.createQuery(queryString);
    	List<Venue> resultList = (List<Venue>) query.getResultList();
		return resultList;
    }
    
    public Venue GetById(Long Id)
    {
    	Log.info("VenueManager returned instance with id: " + Id.toString());
    	return (Venue)entityManager.find(Venue.class, Id);
    }

}
