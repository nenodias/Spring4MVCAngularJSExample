package br.org.springmvc.dao;

import java.util.List;

public interface GenericDao<T, PK> {
    
    T findById(PK id);
     
    void save(T entity);
     
    void update(T entity);
     
    void delete(PK id);
 
    List<T> findAll(); 
     
    void deleteAll();
    
    T initialize(T entity);
}