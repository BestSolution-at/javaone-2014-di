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



import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import at.bestsolution.di.services.GreetingService;



import com.sun.javafx.application.PlatformImpl;

@SuppressWarnings("restriction")
public class FXGreetingService implements GreetingService {
	
	public FXGreetingService() {
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
	public void greet(String text) {
		CountDownLatch cdl = new CountDownLatch(1); 
		Platform.runLater(() -> {
			Text t = new Text(text);
			Button b = new Button("Close");
			BorderPane p = new BorderPane(t);
			p.setBottom(b);
			Scene s = new Scene(p,400,400);
			Stage st = new Stage();
			st.setScene(s);
			st.show();	
			st.toFront();
			b.setOnAction((e) -> { 
				st.close();
			});
			st.setOnHidden((e) -> cdl.countDown());
		});
		try {
			cdl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
