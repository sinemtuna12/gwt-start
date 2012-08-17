package com.wsy.gwtp.web.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.wsy.gwtp.web.client.core.JsonGridPresenter;
import com.wsy.gwtp.web.client.model.Email;

public class JsonGridView extends ViewImpl implements JsonGridPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, JsonGridView> {
	}
	
	@UiField(provided = true) ListStore<Email> store;
	@UiField(provided = true) ColumnModel<Email> cm;
	@UiField Grid<Email> grid;
	@UiField(provided = true) VerticalLayoutData gridData = new VerticalLayoutData(1, 1);
	@UiField TextButton loadBtn;
	
	private JsonGridPresenter presenter;
	
	@Inject
	public JsonGridView(final Binder binder) {
		beforeBind();
		widget = binder.createAndBindUi(this);
		afterBind();
	}

	private void beforeBind() {
		store = new ListStore<Email>(Email.EmailProperties.props.key());
		ColumnConfig<Email, String> cc1 = new ColumnConfig<Email, String>(Email.EmailProperties.props.name(), 100, "Sender");
	    ColumnConfig<Email, String> cc2 = new ColumnConfig<Email, String>(Email.EmailProperties.props.email(), 165, "Email");
	    ColumnConfig<Email, String> cc3 = new ColumnConfig<Email, String>(Email.EmailProperties.props.phone(), 100, "Phone");
	    ColumnConfig<Email, String> cc4 = new ColumnConfig<Email, String>(Email.EmailProperties.props.state(), 50, "State");
	    ColumnConfig<Email, String> cc5 = new ColumnConfig<Email, String>(Email.EmailProperties.props.zip(), 65, "Zip Code");
	     
	    List<ColumnConfig<Email, ?>> l = new ArrayList<ColumnConfig<Email, ?>>();
	    l.add(cc1);
	    l.add(cc2);
	    l.add(cc3);
	    l.add(cc4);
	    l.add(cc5);
	    cm = new ColumnModel<Email>(l);
	}
	

	private void afterBind() {
		
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setPresenter(JsonGridPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setLoader(
			ListLoader<ListLoadConfig, ListLoadResult<Email>> loader) {
		loader.addLoadHandler(new LoadResultListStoreBinding<ListLoadConfig, Email, ListLoadResult<Email>>(store));
		grid.setLoader(loader);
	}
	
	
	@UiHandler("loadBtn") void onLoadBtnClicked(SelectEvent event) {
		this.presenter.loadJson();
	}
}
