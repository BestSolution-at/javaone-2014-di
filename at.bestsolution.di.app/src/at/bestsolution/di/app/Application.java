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

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	public Object start(IApplicationContext context) throws Exception {
		IEclipseContext rootContext = EclipseContextFactory.getServiceContext(Activator.getContext());
		rootContext.set("Name", "Tom Schindl");
		
		LanguageActor languageActor = ContextInjectionFactory.make(LanguageActor.class, rootContext);
		String language = (String) ContextInjectionFactory.invoke(languageActor, Execute.class, rootContext);
		rootContext.set("Language", language);
		
		GreetingActor greetingActor = ContextInjectionFactory.make(GreetingActor.class, rootContext);
		ContextInjectionFactory.invoke(greetingActor, Execute.class, rootContext);
		
		language = (String) ContextInjectionFactory.invoke(languageActor, Execute.class, rootContext);
		rootContext.set("Language", language);
		ContextInjectionFactory.invoke(greetingActor, Execute.class, rootContext);
		
		return IApplication.EXIT_OK;
	}

	public void stop() {
		// nothing to do
	}
}
