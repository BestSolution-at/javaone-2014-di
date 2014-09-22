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
package at.bestsolution.di.app;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;

import at.bestsolution.di.services.LanguageProviderService;

public class LanguageActor {
	private final LanguageProviderService languageService;
	
	@Inject
	public LanguageActor(LanguageProviderService languageService) {
		this.languageService = languageService;
	}
	
	@Execute
	public String requestLanguage() {
		return languageService.getLanguage();
	}
}
