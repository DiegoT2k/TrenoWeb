package com.corso.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.corso.base.BaseTest;

public class ConfigTest extends BaseTest{

	public static void main(String[] args) {
		testDataSource();
	}
	
	public static void testDataSource() {
		   stampa("1-testDataSource");
		   
		   AnnotationConfigApplicationContext factory = new AnnotationConfigApplicationContext(Beans.class); 
		   
		   DataSource ds = factory.getBean("dataSource", DataSource.class); 
		   
		   Connection connection = null;
		   try {
			   connection = (Connection)ds.getConnection();
		   } catch (SQLException e) {
				e.printStackTrace();
		   }
		   System.out.println(connection);
		   factory.close();    
   }
	
	

	  
	  



		   
}
