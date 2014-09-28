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

import at.bestsolution.di.services.GreetingService;

public class GreetingActor {
	private String personName;
	
	private GreetingService service;
	
	private String language;
	
	// Use constructor DI for the name

	// Use @PostConstruct for the GreetingService
	
	// Use method injection for the language

	// Use @Execute for the greeting-method
}
