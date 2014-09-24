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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.json.JSONObject;

import at.bestsolution.di.services.TranslationService;

public class GoogleTranslate implements TranslationService {
	private String API_KEY;
	
	// https://developers.google.com/translate/v2/using_rest#language-params
	
	@Override
	public String[] translate(String language, String... term) {
		if( API_KEY == null ) {
			BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter google-API key: ");
			try {
				API_KEY = r.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			URL url = new URL("https://www.googleapis.com/language/translate/v2?key="+API_KEY+"&q="+String.join(",",term)+"&source=en&target="+language);
			InputStream stream = url.openStream();
			BufferedReader r = new BufferedReader(new InputStreamReader(stream));
			String line;
			StringBuilder b = new StringBuilder();
			while( (line = r.readLine()) != null ) {
				b.append(line);
			}
			stream.close();
			
			JSONObject o = new JSONObject(b.toString());
			String rv = o.getJSONObject("data").getJSONArray("translations").getJSONObject(0).getString("translatedText");
			return Arrays.stream(rv.split(",")).map((e)->e.trim()).toArray(s -> new String[s]);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String[0];
	}

}
