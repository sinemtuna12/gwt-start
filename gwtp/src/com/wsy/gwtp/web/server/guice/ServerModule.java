package com.wsy.gwtp.web.server.guice;

import com.gwtplatform.dispatch.server.guice.HandlerModule;
import com.wsy.gwtp.web.server.LoadStockActionHandler;
import com.wsy.gwtp.web.server.SendTextToServerActionHandler;
import com.wsy.gwtp.web.shared.LoadStockAction;
import com.wsy.gwtp.web.shared.SendTextToServer;

public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {

		bindHandler(SendTextToServer.class, SendTextToServerActionHandler.class);
		bindHandler(LoadStockAction.class, LoadStockActionHandler.class);
	}
}
