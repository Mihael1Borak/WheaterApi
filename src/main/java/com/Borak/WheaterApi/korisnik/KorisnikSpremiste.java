package com.Borak.WheaterApi.korisnik;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Borak.WheaterApi.lokacija.Lokacija;

public interface KorisnikSpremiste extends JpaRepository<Korisnik,Integer>{
	Optional<Korisnik> findByUsername(String username);

	@Query("select lokacije from Korisnik k where k.id = 1 order by drzava , grad")
    List<Lokacija> getLokacijeByMirko1();
	
	@Query("select lokacije from Korisnik k where k.id = 2 order by drzava , grad")
    List<Lokacija> getLokacijeBySlavko1();
	
	

}
