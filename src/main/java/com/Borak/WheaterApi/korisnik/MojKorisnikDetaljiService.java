package com.Borak.WheaterApi.korisnik;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Borak.WheaterApi.lokacija.Lokacija;
import com.Borak.WheaterApi.lokacija.LokacijaSpremiste;
import com.Borak.WheaterApi.lokacija.Prognoza;

@Service
public class MojKorisnikDetaljiService implements UserDetailsService{
	
	@Autowired
	KorisnikSpremiste korisnikSpremiste;
	
	@Autowired
	LokacijaSpremiste lokacijaSpremiste;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Korisnik> korisnik = korisnikSpremiste.findByUsername(username);
		
		korisnik.orElseThrow(() -> new UsernameNotFoundException("Not found: "+ username));
		
		
		return korisnik.map(MojKorisnikDetalji::new).get();
	}
	
	public List<Korisnik> getUsers()
	{
		return korisnikSpremiste.findAll();
		
	}
	
	public Korisnik getUserById(int id)
	{
		return korisnikSpremiste.findById(id).orElse(null);
	}
	
	public List<Lokacija> getLokacijeByMirko()
	{
		return korisnikSpremiste.getLokacijeByMirko1();
	}
	
	public List<Lokacija> getLokacijeBySlavko()
	{
		return korisnikSpremiste.getLokacijeBySlavko1();
	}
	
	

}
