package com.wsy.gwtp.web.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.wsy.gwtp.web.client.core.AboutUSPresenter;
import com.wsy.gwtp.web.client.core.AggregationGridPresenter;
import com.wsy.gwtp.web.client.core.BoostrapPresenter;
import com.wsy.gwtp.web.client.core.CSS3ChartPresenter;
import com.wsy.gwtp.web.client.core.CellRendererPresenter;
import com.wsy.gwtp.web.client.core.CherryBlossomPresenter;
import com.wsy.gwtp.web.client.core.ClockDemoPresenter;
import com.wsy.gwtp.web.client.core.CounterDemoPresenter;
import com.wsy.gwtp.web.client.core.CssPresenter;
import com.wsy.gwtp.web.client.core.FilterGridPresenter;
import com.wsy.gwtp.web.client.core.GWTPresenter;
import com.wsy.gwtp.web.client.core.GXTTabPresenter;
import com.wsy.gwtp.web.client.core.GridSystemPresenter;
import com.wsy.gwtp.web.client.core.HomePresenter;
import com.wsy.gwtp.web.client.core.JsonGridPresenter;
import com.wsy.gwtp.web.client.core.MainPagePresenter;
import com.wsy.gwtp.web.client.core.MickeyPresenter;
import com.wsy.gwtp.web.client.core.RemoteFilterGridPresenter;
import com.wsy.gwtp.web.client.core.RequestFactoryGridPresenter;
import com.wsy.gwtp.web.client.core.RowExpandGridPresenter;
import com.wsy.gwtp.web.client.core.SimpleCSSPresenter;
import com.wsy.gwtp.web.client.core.TooltipChartPresenter;
import com.wsy.gwtp.web.client.place.ClientPlaceManager;
import com.wsy.gwtp.web.client.place.DefaultPlace;
import com.wsy.gwtp.web.client.place.NameTokens;
import com.wsy.gwtp.web.client.view.AboutUSView;
import com.wsy.gwtp.web.client.view.BoostrapView;
import com.wsy.gwtp.web.client.view.CSS3ChartView;
import com.wsy.gwtp.web.client.view.CellRendererView;
import com.wsy.gwtp.web.client.view.CherryBlossomView;
import com.wsy.gwtp.web.client.view.ClockDemoView;
import com.wsy.gwtp.web.client.view.CounterDemoView;
import com.wsy.gwtp.web.client.view.CssView;
import com.wsy.gwtp.web.client.view.FilterGridView;
import com.wsy.gwtp.web.client.view.GWTView;
import com.wsy.gwtp.web.client.view.GXTTabView;
import com.wsy.gwtp.web.client.view.GridSystemView;
import com.wsy.gwtp.web.client.view.HomeView;
import com.wsy.gwtp.web.client.view.IconMenuView;
import com.wsy.gwtp.web.client.view.JsonGridView;
import com.wsy.gwtp.web.client.view.MainPageView;
import com.wsy.gwtp.web.client.view.MickeyView;
import com.wsy.gwtp.web.client.view.RemoteFilterGridView;
import com.wsy.gwtp.web.client.view.RequestFactoryGridView;
import com.wsy.gwtp.web.client.view.RowExpandGridView;
import com.wsy.gwtp.web.client.view.SimpleCSSView;
import com.wsy.gwtp.web.client.view.TooltipChartView;
import com.wsy.gwtp.web.client.core.IconMenuPresenter;
import com.wsy.gwtp.web.client.view.AggregationGridView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));

//		bind(BrowserCheckGatekeeper.class).in(Singleton.class);
		
		bindPresenter(MainPagePresenter.class, MainPagePresenter.MyView.class,
				MainPageView.class, MainPagePresenter.MyProxy.class);

		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.homePage);

		bindPresenter(HomePresenter.class, HomePresenter.MyView.class,
				HomeView.class, HomePresenter.MyProxy.class);

		bindPresenter(AboutUSPresenter.class, AboutUSPresenter.MyView.class,
				AboutUSView.class, AboutUSPresenter.MyProxy.class);

		bindPresenter(JsonGridPresenter.class, JsonGridPresenter.MyView.class,
				JsonGridView.class, JsonGridPresenter.MyProxy.class);

		bindPresenter(CssPresenter.class,
				CssPresenter.MyView.class, CssView.class,
				CssPresenter.MyProxy.class);

		bindPresenter(FilterGridPresenter.class,
				FilterGridPresenter.MyView.class, FilterGridView.class,
				FilterGridPresenter.MyProxy.class);

		bindPresenter(RemoteFilterGridPresenter.class,
				RemoteFilterGridPresenter.MyView.class,
				RemoteFilterGridView.class,
				RemoteFilterGridPresenter.MyProxy.class);

		bindPresenter(RequestFactoryGridPresenter.class,
				RequestFactoryGridPresenter.MyView.class,
				RequestFactoryGridView.class,
				RequestFactoryGridPresenter.MyProxy.class);
		

		bindPresenter(TooltipChartPresenter.class,
				TooltipChartPresenter.MyView.class, TooltipChartView.class,
				TooltipChartPresenter.MyProxy.class);

		bindPresenter(GXTTabPresenter.class, GXTTabPresenter.MyView.class,
				GXTTabView.class, GXTTabPresenter.MyProxy.class);

		bindPresenter(RowExpandGridPresenter.class,
				RowExpandGridPresenter.MyView.class, RowExpandGridView.class,
				RowExpandGridPresenter.MyProxy.class);

		bindPresenter(BoostrapPresenter.class, BoostrapPresenter.MyView.class,
				BoostrapView.class, BoostrapPresenter.MyProxy.class);

		bindPresenter(GridSystemPresenter.class,
				GridSystemPresenter.MyView.class, GridSystemView.class,
				GridSystemPresenter.MyProxy.class);

		bindPresenter(CherryBlossomPresenter.class,
				CherryBlossomPresenter.MyView.class, CherryBlossomView.class,
				CherryBlossomPresenter.MyProxy.class);

		bindPresenter(CSS3ChartPresenter.class,
				CSS3ChartPresenter.MyView.class, CSS3ChartView.class,
				CSS3ChartPresenter.MyProxy.class);

		bindPresenter(SimpleCSSPresenter.class,
				SimpleCSSPresenter.MyView.class, SimpleCSSView.class,
				SimpleCSSPresenter.MyProxy.class);

		bindPresenter(ClockDemoPresenter.class,
				ClockDemoPresenter.MyView.class, ClockDemoView.class,
				ClockDemoPresenter.MyProxy.class);

		bindPresenter(MickeyPresenter.class, MickeyPresenter.MyView.class,
				MickeyView.class, MickeyPresenter.MyProxy.class);

		bindPresenter(GWTPresenter.class, GWTPresenter.MyView.class,
				GWTView.class, GWTPresenter.MyProxy.class);

		bindPresenter(CellRendererPresenter.class,
				CellRendererPresenter.MyView.class, CellRendererView.class,
				CellRendererPresenter.MyProxy.class);
		
		bindPresenter(CounterDemoPresenter.class,
				CounterDemoPresenter.MyView.class, CounterDemoView.class,
				CounterDemoPresenter.MyProxy.class);
		
//		bindPresenter(AnimationClockPresenter.class, 
//				AnimationClockPresenter.MyView.class, AnimationClockView.class,
//				AnimationClockPresenter.MyProxy.class);

		bindPresenter(IconMenuPresenter.class, IconMenuPresenter.MyView.class,
				IconMenuView.class, IconMenuPresenter.MyProxy.class);

		bindPresenter(AggregationGridPresenter.class,
				AggregationGridPresenter.MyView.class,
				AggregationGridView.class,
				AggregationGridPresenter.MyProxy.class);
	}
}
