package com.Borak.WheaterApi.kontroler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Borak.WheaterApi.korisnik.MojKorisnikDetaljiService;
import com.Borak.WheaterApi.lokacija.Lokacija;
import com.Borak.WheaterApi.lokacija.LokacijaServis;
import com.Borak.WheaterApi.lokacija.Prognoza;
import com.Borak.WheaterApi.lokacija.Weather;
import com.Borak.WheaterApi.korisnik.*;

@RestController
public class Kontroler {
	
	public String content;
	public static int responseCode;
	private Lokacija lokacija;
	public static String key ="94c21c1908ec80d23c2d93a99d6cf160";
	@Autowired	
	private LokacijaServis servis;
	
	@Autowired
	private MojKorisnikDetaljiService korisnikServis;
	
	@GetMapping("/sve")
    public List<Lokacija> findAllUsersM()
    {
		return servis.getLokacijeSortirane();
    }
	
	@GetMapping("/")
	public String home() {
	return("<h1>Dobrodosli</h1>");
	}
	@GetMapping("/mirko")
	public String Mirko() {
	return("<h1>Dobrodošao mirko</h1>");
	}
	@GetMapping("/slavko")
	public String Slavko() {
	return("<h1>Dobrodošao slavko </h1>");
    }
	
	@GetMapping("/mirko/lokacije")
	public List<Lokacija> getLokacijeByMirko()
	{
		return korisnikServis.getLokacijeByMirko();
	}
	
	@GetMapping("/slavko/lokacije")
	public List<Lokacija> getLokacijeBySlavko()
	{
		return korisnikServis.getLokacijeBySlavko();
	}
	
	@PostMapping("slavko/dodaj")
	public void addLokacijaS (@RequestBody Lokacija lokacija)
	{
		lokacija.setKorisnik_id(2);
		servis.saveLokacija(lokacija);
	}
	
	@PostMapping("mirko/dodaj")
	public void addLokacijaM (@RequestBody Lokacija lokacija)
	{
		lokacija.setKorisnik_id(1);
		servis.saveLokacija(lokacija);
	}
	
	
	@DeleteMapping("mirko/delete/{id}")
	public void deleteLokacijaByMirko(@PathVariable  Integer id)
	{
		servis.deleteLokacijaByMirko(id);
	}
	
	@DeleteMapping("slavko/delete/{id}")
	public void deleteLokacijaBySlavko(@PathVariable  Integer id)
	{
		servis.deleteLokacijaBySlavko(id);
	}
	
	@GetMapping("/weather/current")
    @CrossOrigin(origins="http://localhost:8080")
    public Weather current(@RequestParam(value="location")String location) {

            try {
		String inputLine;	
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+location+"&appid="+key+"&units=metric";
        URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		responseCode = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
				}

		in.close();
		content = response.toString();
         
	}
	
	catch (Exception e) {
		System.out.print("ERROR : "+e);
		return new Weather("Not Found","Not Found",0,0);
    	}
	JSONObject root = new JSONObject(content);
	JSONObject main = root.getJSONObject("main");
	//system
            JSONObject sys = root.getJSONObject("sys");
     
	return new Weather(
			sys.getString("country"),
            root.getString("name"),
			main.getInt("temp"),
            main.getInt("humidity"));
    			
  	}

	@GetMapping("/mirko/prognoza")
	public  List<Prognoza> MirkoPrognoza(){
		List<Prognoza> prognoze= new ArrayList<Prognoza>();
		List<Lokacija> lokacije = getLokacijeByMirko();
		for(Lokacija l : lokacije)
		{
			Weather w=current(l.getGrad());
			Prognoza p=new Prognoza(l,w.getTemp(),w.getHumidity());
			prognoze.add(p);
		}
		return prognoze;
	}
	
	@GetMapping("/slavko/prognoza")
	public  List<Prognoza> SlavkoPrognoza(){
		List<Prognoza> prognoze= new ArrayList<Prognoza>();
		List<Lokacija> lokacije = getLokacijeBySlavko();
		for(Lokacija l : lokacije)
		{
			Weather w=current(l.getGrad());
			Prognoza p=new Prognoza(l,w.getTemp(),w.getHumidity());
			prognoze.add(p);
		}
		return prognoze;
	}
	
	
    public List<Lokacija> dohvatiPrognozuZaSvePoDrzavi(String drzava)
    {
		return servis.dohvatiPrognozuZaSvePoDrzaviS(drzava);
    }
	@GetMapping("/prognoza")
	public  List<Prognoza> Prognoza(){
		List<Prognoza> prognoze= new ArrayList<Prognoza>();
		List<Lokacija> lokacije = findAllUsersM();
		for(Lokacija l : lokacije)
		{
			Weather w=current(l.getGrad());
			Prognoza p=new Prognoza(l,w.getTemp(),w.getHumidity());
			prognoze.add(p);
		}
		return prognoze;
	}
	
	@GetMapping("/prognoza/{drzava}")
	public  List<Prognoza> PrognozaSortirana(@PathVariable String drzava){
		List<Prognoza> prognoze= new ArrayList<Prognoza>();
		List<Lokacija> lokacije = dohvatiPrognozuZaSvePoDrzavi(drzava);
		for(Lokacija l : lokacije)
		{
			Weather w=current(l.getGrad());
			Prognoza p=new Prognoza(l,w.getTemp(),w.getHumidity());
			prognoze.add(p);
		}
		return prognoze;
	}
	
}
 

