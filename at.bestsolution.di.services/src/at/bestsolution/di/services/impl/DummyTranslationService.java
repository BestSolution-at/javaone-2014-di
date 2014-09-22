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
package at.bestsolution.di.services.impl;

import at.bestsolution.di.services.TranslationService;

public class DummyTranslationService implements TranslationService {

	@Override
	public String translate(String language, String term) {
		if( "en".equals(language) ) {
			return term;
		}
		
		if( "Hello".equals(term) ) {
			if( "de".equals(language) ) {
				return "Hallo";
			} else if( "fr".equals(language) ) {
				return "Bonjour";
			}
		}
		return term + "(lang="+language+")";
	}

}
