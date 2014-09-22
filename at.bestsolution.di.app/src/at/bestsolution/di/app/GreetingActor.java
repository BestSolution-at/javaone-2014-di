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

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;

import at.bestsolution.di.services.GreetingService;
import at.bestsolution.di.services.TranslationService;

public class GreetingActor {
	private final String personName;
	
	private GreetingService service;
	
	private String language;
	
	@Inject
	public GreetingActor(@Named("Name") String personName) {
		this.personName = personName;
	}
	
	@PostConstruct
	private void init(GreetingService service) {
		this.service = service;
	}
	
	@Inject
	public void setLanguage(@Named("Language") String language) {
		this.language = language;
	}
	
	@Execute
	public void greet(TranslationService t) {
		this.service.greet(t.translate(language,"Hello") + " '" + this.personName + "'!");
	}
}
