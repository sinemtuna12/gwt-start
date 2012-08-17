/**
 * 
 */
package com.wsy.gwtp.web.client.resources;

import com.github.gwtbootstrap.client.ui.config.Configurator;
import com.github.gwtbootstrap.client.ui.resources.Resources;
import com.google.gwt.core.client.GWT;

/**
 * @author e518417
 *
 */
public class CustomConfigurator implements Configurator {

	private CustomResources res;
	/* (non-Javadoc)
	 * @see com.github.gwtbootstrap.client.ui.config.Configurator#getResources()
	 */
	@Override
	public Resources getResources() {
		if(res == null) {
			res = GWT.create(CustomResources.class);
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see com.github.gwtbootstrap.client.ui.config.Configurator#hasResponsiveDesign()
	 */
	@Override
	public boolean hasResponsiveDesign() {
		// TODO Auto-generated method stub
		return false;
	}

}
