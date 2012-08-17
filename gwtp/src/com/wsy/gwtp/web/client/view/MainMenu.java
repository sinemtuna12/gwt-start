/**
 * 
 */
package com.wsy.gwtp.web.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author e518417
 *
 */
public class MainMenu extends Composite {
	  public interface Binder extends UiBinder<Widget, MainMenu> {}
	  
	  @Inject
	  public MainMenu(final Binder binder) {
	    initWidget(binder.createAndBindUi(this));
	  }
}