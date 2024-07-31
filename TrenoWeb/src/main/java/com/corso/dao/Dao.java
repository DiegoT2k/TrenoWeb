package com.corso.dao;

import java.util.List;


public interface Dao {
	List<?> all(Class c);
	Object find(Class c, Integer id);
}