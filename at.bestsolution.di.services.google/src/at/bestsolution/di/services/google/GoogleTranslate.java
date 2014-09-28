/*******************************************************************************
 * Copyright (c) 2014 BestSolution.at and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tom Schindl<tom.schindl@bestsolution.at> - initial API and implementation
 *******************************************************************************/
package at.bestsolution.di.services.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import at.bestsolution.di.services.TranslationService;

public class GoogleTranslate implements TranslationService {
	private String API_KEY = "AIzaSyCYJ8Aqt0oEy0xbkyaJibKSk0akAR46x5I";
	
	// https://developers.google.com/translate/v2/using_rest#language-params
	
	private String getAPIKey() {
		if( API_KEY == null ) {
			System.out.print("Enter google-API key: ");
			try(BufferedReader r = new BufferedReader(new InputStreamReader(System.in));) {
				API_KEY = r.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return API_KEY;
	}
	
	@Override
	public String[] getLocales() {
		try {
			URI url = new URI("https",null,"www.googleapis.com",-1,"/language/translate/v2/languages","key="+getAPIKey(),null);
			String json = callRest(url);
			
			if( json != null ) {
				JSONObject o = new JSONObject(json);
				JSONArray array = o.getJSONObject("data").getJSONArray("languages");
				
				String[] rv = new String[array.length()];
				for( int i = 0; i < array.length(); i++ ) {
					rv[i] = array.getJSONObject(i).getString("language");
				}
				return rv;
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String[0];
	}
	
	private String callRest(URI url) {
		try(InputStream stream = url.toURL().openStream();) {
			BufferedReader r = new BufferedReader(new InputStreamReader(stream));
			String line;
			StringBuilder b = new StringBuilder();
			while( (line = r.readLine()) != null ) {
				b.append(line);
			}
			return b.toString();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String[] translate(String language, String... term) {
		try {
			URI url = new URI("https",null,"www.googleapis.com",-1,"/language/translate/v2","key="+getAPIKey()+"&q="+String.join(",",term)+"&source=en&target="+language, null);
			String json = callRest(url);
			
			if( json != null ) {
				JSONObject o = new JSONObject(json);
				String rv = o.getJSONObject("data").getJSONArray("translations").getJSONObject(0).getString("translatedText");
				return Arrays.stream(rv.split(",")).map((e)->e.trim()).toArray(s -> new String[s]);
			}
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return new String[0];
	}

}
