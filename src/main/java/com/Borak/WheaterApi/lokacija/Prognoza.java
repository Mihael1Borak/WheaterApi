package com.Borak.WheaterApi.lokacija;

public class Prognoza extends Lokacija {
	
	private float temp;
	private float humidity;
	
	public float getTemp() {
		return temp;
	}
	public void setTemp(float temp) {
		this.temp = temp;
	}
	public float getHumidity() {
		return humidity;
	}
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	
	
	public Prognoza(Lokacija lokacija, float tempp , float humidtyy )
	{
		super(lokacija.getId(),lokacija.getDrzava(),lokacija.getGrad(),lokacija.getKorisnik_id());
		setTemp(tempp);
		setHumidity(humidtyy);
		
	}

}
