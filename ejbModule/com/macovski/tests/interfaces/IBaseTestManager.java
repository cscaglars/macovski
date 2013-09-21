package com.macovski.tests.interfaces;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface IBaseTestManager {

	void Insert(Object entity);
	void Delete(Object entity);
	void Update(Object entity);
	List<Object> GetAll(Object object);
}
