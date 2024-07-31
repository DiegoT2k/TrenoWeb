package com.corso.dao;

import org.springframework.transaction.annotation.Transactional;
import com.corso.model.Utente;
	
    @Transactional
	public interface UtenteDao {
		
 
    	public Utente add(Utente utente);
    	/**
    	public void update(Categoria categoria);

    	public Categoria find(Integer id);

    	public List<Categoria> all();

    	public List<Categoria> filterCategoriaByName(String name);

    	public void delete(int id);

    	public void delete(Categoria categoria);
    	
    	public void deleteDetached(Categoria categoria);

    	public Categoria provaTutti(Integer id); 
    	
    	public List<Object[]> countDescrizioneUgualeCategoria();
    	public List<Object[]> countDescrizioneUgualeCategoriaNQ();
    	public List<Object[]> countDescrizioneUgualeCategoriaSQL();
    	public List<Object[]> countDescrizioneUgualeCategoriaNQSQL(); 
    	**/
	}

