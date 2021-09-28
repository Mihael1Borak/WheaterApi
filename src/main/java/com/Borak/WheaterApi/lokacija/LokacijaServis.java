package com.Borak.WheaterApi.lokacija;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LokacijaServis {
	
	@Autowired
	private LokacijaSpremiste spremiste;
	
	public Lokacija saveLokacija(Lokacija lokacija)
	{
		return spremiste.save(lokacija);
	}
	
	public List<Lokacija> saveLokacije(List<Lokacija> lokacija)
	{
		return spremiste.saveAll(lokacija);
	}
	public List<Lokacija> getLokacijeSortirane()
	{
		
		return spremiste.findAll(Sort.by(Sort.Direction.ASC,"drzava").and(Sort.by(Sort.Direction.ASC,"grad")));
	}
	public List<Lokacija> getLokacije()
	{
		return spremiste.findAll();
	}
	public Lokacija getLokacijaById(int id)
	{
		return spremiste.findById(id).orElse(null);
	}
	
	public String deleteLokacija(int id)
	{
		spremiste.deleteById(id);
		return "lokacija je  uklonjena " +id;
	}
	
	public 	Lokacija updateLokacija(Lokacija lokacija)
	{
		Lokacija existingLokacija = spremiste.findById(lokacija.getId()).orElse(null);
		existingLokacija.setDrzava(lokacija.getDrzava());
		existingLokacija.setGrad(lokacija.getGrad());
		return spremiste.save(existingLokacija);
	}
	public String deleteLokacijaByMirko(int id)
	{
		spremiste.deleteLokacijeByMirko(id);
		return "lokacija je  uklonjena " ;
	}
	public String deleteLokacijaBySlavko(int id)
	{
		spremiste.deleteLokacijeBySlavko(id);
		return "lokacija je  uklonjena " ;
	}
	
	public  List<Lokacija> dohvatiPrognozuZaSvePoDrzaviS(String drzava)
	{
		 return spremiste.selectLokacijeByDrzava(drzava);
	}
	
	
}
