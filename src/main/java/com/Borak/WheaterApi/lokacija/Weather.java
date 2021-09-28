package com.Borak.WheaterApi.lokacija;

public class Weather{
	private float temp;
	private String country;
	private String city;
	private float humidity;


	public Weather(String country, String city,float temp, float humidity){
		this.country=country;
		this.city=city;
		this.temp=temp;
		this.humidity = humidity;
	}

	public String getCountry() {
       return country;
        }

   public  String getCity() {
        return city;
        }
	public float getTemp() {
		return temp;
		}
	public float getHumidity() {
		return humidity;
		}
}
		
