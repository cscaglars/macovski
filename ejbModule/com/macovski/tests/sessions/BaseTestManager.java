package com.macovski.tests.sessions;

import java.util.List;

import com.macovski.tests.interfaces.IBaseTestManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

}
