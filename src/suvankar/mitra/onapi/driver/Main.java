package suvankar.mitra.onapi.driver;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import suvankar.mitra.onapi.models.NewsElement;
import suvankar.mitra.onapi.sources.TimesOfIndia;

public class Main {
	public static void main(String[] args) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ArrayList<NewsElement> toi = TimesOfIndia.getTopStories();
		int i=0;
		System.out.println("[");
		for(NewsElement ne : toi) {
			System.out.println(gson.toJson(ne));
			i++;
			if(i<toi.size()) {
				System.out.println(",");
			}
		}
		System.out.println("]");
	}
}
