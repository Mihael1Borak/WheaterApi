package com.Borak.WheaterApi.lokacija;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface  LokacijaSpremiste  extends JpaRepository<Lokacija,Integer>{
	
	@Transactional
	@Modifying
	@Query(value ="DELETE FROM lokacije WHERE id = ?1  && korisnik_id = 1", nativeQuery = true)
    void deleteLokacijeByMirko(Integer id);
	
	@Transactional
	@Modifying
	@Query(value ="DELETE FROM lokacije WHERE id = ?1  && korisnik_id = 2", nativeQuery = true)
    void deleteLokacijeBySlavko(Integer id);
	
	@Query(value ="SELECT * FROM lokacije where drzava =?1", nativeQuery = true)
	List<Lokacija> selectLokacijeByDrzava(String drzava);
}
