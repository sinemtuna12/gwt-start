package com.wsy.gwtp.web.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.filters.DateFilter;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import com.wsy.gwtp.web.client.core.RequestFactoryGridPresenter;
import com.wsy.gwtp.web.shared.PostProxy;

public class RequestFactoryGridView extends ViewImpl implements
		RequestFactoryGridPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, RequestFactoryGridView> {
	}

	interface PostProxyProperties extends PropertyAccess<PostProxy> {
		ModelKeyProvider<PostProxy> id();

		ValueProvider<PostProxy, String> username();

		ValueProvider<PostProxy, String> forum();

		ValueProvider<PostProxy, String> subject();

		ValueProvider<PostProxy, Date> date();
	}

	private final PostProxyProperties props = GWT
			.create(PostProxyProperties.class);

	@UiField(provided = true)
	ListStore<PostProxy> store;
	@UiField(provided = true)
	ColumnModel<PostProxy> cm;
	@UiField
	Grid<PostProxy> grid;
	@UiField(provided = true)
	VerticalLayoutData gridData = new VerticalLayoutData(1, 1);
	@UiField(provided = true)
	VerticalLayoutData toolBarData = new VerticalLayoutData(1, -1);
	@UiField
	PagingToolBar pagingToolBar;

	@Inject
	public RequestFactoryGridView(final Binder binder) {
		beforeBind();
		widget = binder.createAndBindUi(this);
		afterBind();
	}

	private void beforeBind() {
		store = new ListStore<PostProxy>(props.id());
		ColumnConfig<PostProxy, String> forumColumn = new ColumnConfig<PostProxy, String>(
				props.forum(), 150, "Forum");
		ColumnConfig<PostProxy, String> usernameColumn = new ColumnConfig<PostProxy, String>(
				props.username(), 150, "Username");
		ColumnConfig<PostProxy, String> subjectColumn = new ColumnConfig<PostProxy, String>(
				props.subject(), 150, "Subject");
		ColumnConfig<PostProxy, Date> dateColumn = new ColumnConfig<PostProxy, Date>(
				props.date(), 150, "Date");
		dateColumn.setCell(new DateCell(DateTimeFormat
				.getFormat(PredefinedFormat.DATE_SHORT)));

		List<ColumnConfig<PostProxy, ?>> l = new ArrayList<ColumnConfig<PostProxy, ?>>();
		l.add(forumColumn);
		l.add(usernameColumn);
		l.add(subjectColumn);
		l.add(dateColumn);

		cm = new ColumnModel<PostProxy>(l);
	}

	private void afterBind() {
		pagingToolBar.getElement().getStyle()
				.setProperty("borderBottom", "none");

	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setLoader(
			PagingLoader<FilterPagingLoadConfig, PagingLoadResult<PostProxy>> loader) {
		loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, PostProxy, PagingLoadResult<PostProxy>>(
				store));
		grid.setLoader(loader);
		pagingToolBar.bind(loader);

		GridFilters<PostProxy> filters = new GridFilters<PostProxy>(loader);
		filters.initPlugin(grid);
		filters.setLocal(false);// be sure to be remote, or it will affect the
								// local cached data only
		filters.addFilter(new DateFilter<PostProxy>(props.date()));
		filters.addFilter(new StringFilter<PostProxy>(props.subject()));
		filters.addFilter(new StringFilter<PostProxy>(props.forum()));
		filters.addFilter(new StringFilter<PostProxy>(props.username()));
	}
}
