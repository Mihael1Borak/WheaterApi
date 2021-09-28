package com.Borak.WheaterApi;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.Borak.WheaterApi.lokacija.Lokacija;
import com.Borak.WheaterApi.lokacija.LokacijaSpremiste;

@SpringBootTest
class WheaterApiApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	LokacijaSpremiste lokacijaSpr;
	
	@Test
	public void testDodaj()
	{
		Lokacija l = new Lokacija();
		l.setId(30);
		l.setDrzava("Njemacka");
		l.setGrad("Berlin");
		l.setKorisnik_id(1);
		lokacijaSpr.save(l);
		assertNotNull(lokacijaSpr.findById(30).get());
	}
	
	@Test
	public void testAll()
	{
		List<Lokacija> list = lokacijaSpr.findAll();
		Assert.notEmpty(list);
	}

}
