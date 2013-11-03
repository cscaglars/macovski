package com.macovski.managers.implementation;

import java.util.List;

import com.macovski.entities.OldMatch;
import com.macovski.managers.interfaces.IOldMatchManager;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Session Bean implementation class OldMatchManager
 */
@Stateless(mappedName="OldMatchManager")
@LocalBean
public class OldMatchManager extends BaseManager<OldMatch> implements IOldMatchManager {
     
	@PersistenceContext(unitName = "JPADB")
    protected EntityManager em;
	
	private Logger Log = LogManager.getLogger(OldMatchManager.class.getName());
	
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
    
    @SuppressWarnings("unchecked")
	public List<OldMatch> GetAll()
    {
    	System.out.println("Object is " + OldMatch.class.getSimpleName());
    	String queryString = "SELECT g FROM " + OldMatch.class.getSimpleName() +" g";
    	Query query = entityManager.createQuery(queryString);
    	List<OldMatch> resultList = (List<OldMatch>) query.getResultList();
		return resultList;
    }
    
    public OldMatch GetById(Long Id)
    {
    	Log.info("OldMatchManager returned instance with id: " + Id.toString());
    	return (OldMatch)entityManager.find(OldMatch.class, Id);
    }

}
