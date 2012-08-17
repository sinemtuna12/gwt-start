package com.wsy.gwtp.web.client.gin;

import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.wsy.gwtp.web.client.core.AboutUSPresenter;
import com.wsy.gwtp.web.client.core.CssPresenter;
import com.wsy.gwtp.web.client.core.FilterGridPresenter;
import com.wsy.gwtp.web.client.core.GWTPresenter;
import com.wsy.gwtp.web.client.core.GXTTabPresenter;
import com.wsy.gwtp.web.client.core.HomePresenter;
import com.wsy.gwtp.web.client.core.JsonGridPresenter;
import com.wsy.gwtp.web.client.core.MainPagePresenter;
import com.wsy.gwtp.web.client.core.RemoteFilterGridPresenter;
import com.wsy.gwtp.web.client.core.RequestFactoryGridPresenter;
import com.wsy.gwtp.web.client.core.TooltipChartPresenter;
import com.wsy.gwtp.web.client.ui.RoundTabPanel;
import com.wsy.gwtp.web.client.ui.SimpleTabPanel;
import com.wsy.gwtp.web.client.view.MainMenu;
import com.wsy.gwtp.web.client.core.RowExpandGridPresenter;
import com.wsy.gwtp.web.client.core.BoostrapPresenter;
import com.wsy.gwtp.web.client.core.GridSystemPresenter;
import com.wsy.gwtp.web.client.core.CherryBlossomPresenter;
import com.wsy.gwtp.web.client.core.CSS3ChartPresenter;
import com.wsy.gwtp.web.client.core.SimpleCSSPresenter;
import com.wsy.gwtp.web.client.core.ClockDemoPresenter;
import com.wsy.gwtp.web.client.core.MickeyPresenter;
import com.wsy.gwtp.web.client.core.CellRendererPresenter;

@GinModules({ DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	PlaceManager getPlaceManager();

	Provider<MainPagePresenter> getMainPagePresenter();

	AsyncProvider<HomePresenter> getHomePresenter();

	AsyncProvider<AboutUSPresenter> getAboutUSPresenter();

	AsyncProvider<JsonGridPresenter> getJsonGridPresenter();

	AsyncProvider<CssPresenter> getCssRegionPresenter();

	AsyncProvider<FilterGridPresenter> getFilterGridPresenter();

	AsyncProvider<RemoteFilterGridPresenter> getRemoteFilterGridPresenter();

	AsyncProvider<RequestFactoryGridPresenter> getRequestFactoryGridPresenter();

	RoundTabPanel getRoundTabPanel();
	
	MainMenu getMainMenu();

	SimpleTabPanel getSimpleTabePanel();
	
	AsyncProvider<TooltipChartPresenter> getTooltipChartPresenter();

	AsyncProvider<GXTTabPresenter> getGXTTabPresenter();

	AsyncProvider<RowExpandGridPresenter> getRowExpandGridPresenter();

	AsyncProvider<BoostrapPresenter> getBoostrapPresenter();

	AsyncProvider<GridSystemPresenter> getGridSystemPresenter();

	AsyncProvider<CherryBlossomPresenter> getCherryBlossomPresenter();

	AsyncProvider<CSS3ChartPresenter> getCSS3ChartPresenter();

	AsyncProvider<SimpleCSSPresenter> getSimpleCSSPresenter();

	AsyncProvider<ClockDemoPresenter> getClockDemoPresenter();

	AsyncProvider<MickeyPresenter> getMickeyPresenter();

	AsyncProvider<GWTPresenter> getGWTPresenter();

	AsyncProvider<CellRendererPresenter> getCellRendererPresenter();
}
