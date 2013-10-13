package com.macovski.sessions;

import java.util.List;

import com.macovski.interfaces.IBaseManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class BaseManager
 */

//@Stateless(mappedName="BaseManager")
//@EJB(name="BaseManager", beanInterface=IBaseManager.class)
public abstract class BaseManager<TEntity> implements IBaseManager<TEntity> {

	//@PersistenceContext(unitName = "JPADB")
    protected EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
	public  BaseManager() {
        // TODO Auto-generated constructor stub	
    }
	
	public void Insert(TEntity entity)
    {
    	entityManager.persist(entity);
    }

	public void Delete(TEntity entity)
    {
    	entityManager.remove(entity);
    }
    
	public void Update(TEntity entity)
    {
    	entityManager.merge(entity);
    }
    
    public List<TEntity> GetAll()
    {
    	TEntity entity = (TEntity) new Object();
    	System.out.println("Object is " + entity.getClass().getSimpleName());
    	String queryString = "SELECT g FROM " + entity.getClass().getSimpleName() +" g";
    	Query query = entityManager.createQuery(queryString);
    	List<TEntity> resultList = (List<TEntity>) query.getResultList();
		return resultList;
    }
    
    @SuppressWarnings({ "unchecked", "null" })
    public TEntity GetById(Long Id)
    {
    	TEntity entity = null;
    	return (TEntity)entityManager.find(entity.getClass(), Id);
    }
}
