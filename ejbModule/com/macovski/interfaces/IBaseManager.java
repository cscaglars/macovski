package com.macovski.interfaces;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface IBaseManager<TEntity> {

	void Insert(TEntity entity);
	void Delete(TEntity entity);
	void Update(TEntity entity);
	List<TEntity> GetAll();
	TEntity GetById(Long Id);
}
