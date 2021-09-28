package com.Borak.WheaterApi.lokacija;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.Borak.WheaterApi.korisnik.Korisnik;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "Lokacije")
public class Lokacija {
	
	@Id
	@GeneratedValue
	private int id;
	private String drzava;
	private String grad;
	
	@ManyToOne
	@JoinColumn(name="korisnik_id", insertable=false ,updatable=false)
	private Korisnik korisnik;
	
	private Integer korisnik_id;
	public Lokacija(){
		
	}
	
	public Lokacija(int id, String drzava, String grad, Integer korisnik_id)
	{
		setId(id);
		setDrzava(drzava);
		setGrad(grad);
		setKorisnik_id(korisnik_id);
	}
	
	public Integer getKorisnik_id() {
		return korisnik_id;
	}
	public void setKorisnik_id(Integer korisnik_id) {
		this.korisnik_id = korisnik_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	
}
