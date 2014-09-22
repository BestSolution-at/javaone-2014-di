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
package at.bestsolution.di.services.fx;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import at.bestsolution.di.services.LanguageProviderService;

import com.sun.javafx.application.PlatformImpl;

@SuppressWarnings("restriction")
public class FXLanguageProviderService implements LanguageProviderService {
	public FXLanguageProviderService() {
		bootstrapFX();
	}
	
	private void bootstrapFX() {
		PlatformImpl.startup(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
		Platform.setImplicitExit(false);
	}
	
	@Override
	public String getLanguage() {
		CountDownLatch cdl = new CountDownLatch(1);
		AtomicReference<String> lang = new AtomicReference<>();
		Platform.runLater(() -> {
			TextField t = new TextField();
			Scene s = new Scene(new HBox(new Label("Language: "),t),400,400);
			Stage st = new Stage();
			st.setScene(s);
			st.toFront();
			st.showAndWait();	
			lang.set(t.getText());
			cdl.countDown();
			
			System.err.println("done");
		});
		try {
			cdl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println(lang.get());
		return lang.get();
	}

}
