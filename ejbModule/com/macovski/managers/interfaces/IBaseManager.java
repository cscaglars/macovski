package com.macovski.managers.interfaces;

import java.util.List;

public interface IBaseManager<T> {

	void Insert(T entity);
    void Delete(T entity);
    void Update(T entity);
    List<T> GetAll();
    T GetById(Long Id);
}
