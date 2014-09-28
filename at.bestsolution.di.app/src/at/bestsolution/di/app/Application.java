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

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	public Object start(IApplicationContext context) throws Exception {
		// Create the root context bound to the OSGi-Service registry
		
		// Store 2 Name and Language value
		
		// Create a language actor
		
		// Create a greeting actor on root context
		
		// Create a child context and a actor bound to it
		
		// Let the actors greet the people
		
		// Flip the language on the root context
		
		// Let the actors greet the people
		
		// Flip the language on the root context
		
		// Let the actors greet the people
		
		return IApplication.EXIT_OK;
	}

	public void stop() {
		// nothing to do
	}
}
