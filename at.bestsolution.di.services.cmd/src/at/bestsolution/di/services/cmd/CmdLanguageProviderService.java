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
package at.bestsolution.di.services.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import at.bestsolution.di.services.LanguageProviderService;

public class CmdLanguageProviderService implements LanguageProviderService {

	@Override
	public String getLanguage() {
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter language: ");
			String lang = r.readLine();
			return lang;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Locale.getDefault().getLanguage();
	}

}
