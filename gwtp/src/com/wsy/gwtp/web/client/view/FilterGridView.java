package com.wsy.gwtp.web.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.filters.BooleanFilter;
import com.sencha.gxt.widget.core.client.grid.filters.DateFilter;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.ListFilter;
import com.sencha.gxt.widget.core.client.grid.filters.NumericFilter;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import com.wsy.gwtp.web.client.core.FilterGridPresenter;
import com.wsy.gwtp.web.client.model.Stock;
import com.wsy.gwtp.web.client.model.StockProperties;
import com.wsy.gwtp.web.client.model.TestData;

public class FilterGridView extends ViewImpl implements
		FilterGridPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, FilterGridView> {
	}
	
	public static StockProperties props = GWT.create(StockProperties.class);
	
	@UiField(provided = true)
	ColumnModel<Stock> cm;
	@UiField(provided = true)
	ListStore<Stock> store;
	@UiField
	Grid<Stock> grid;
	@UiField(provided = true) VerticalLayoutData gridData = new VerticalLayoutData(1, 1);
	
	private ListStore<String> typeStore;

	@Inject
	public FilterGridView(final Binder binder) {
		beforeBind();
		widget = binder.createAndBindUi(this);
		afterBind();
	}

	private void beforeBind() {
		final NumberFormat number = NumberFormat.getFormat("0.00");

		ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(
				props.name(), 200, "Company");
		ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(
				props.symbol(), 100, "Symbol");
		ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(
				props.last(), 75, "Last");

		ColumnConfig<Stock, Double> changeCol = new ColumnConfig<Stock, Double>(
				props.change(), 100, "Change");
		changeCol.setCell(new AbstractCell<Double>() {

			@Override
			public void render(Context context, Double value, SafeHtmlBuilder sb) {
				String style = "style='color: " + (value < 0 ? "red" : "green")
						+ "'";
				String v = number.format(value);
				sb.appendHtmlConstant("<span " + style
						+ " qtitle='Change' qtip='" + v + "'>" + v + "</span>");
			}
		});

		ColumnConfig<Stock, Date> lastTransCol = new ColumnConfig<Stock, Date>(
				props.lastTrans(), 100, "Last Updated");
		lastTransCol.setCell(new DateCell(DateTimeFormat
				.getFormat("MM/dd/yyyy")));

		ColumnConfig<Stock, Boolean> splitCol = new ColumnConfig<Stock, Boolean>(
				props.split(), 75, "Split");
		splitCol.setCell(new AbstractCell<Boolean>() {
			@Override
			public void render(Context context, Boolean value,
					SafeHtmlBuilder sb) {
				sb.appendHtmlConstant(value ? "Yes" : "No");
			}
		});

		ColumnConfig<Stock, String> typeCol = new ColumnConfig<Stock, String>(
				props.industry(), 75, "Type");

		List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
		l.add(nameCol);
		l.add(symbolCol);
		l.add(lastCol);
		l.add(changeCol);
		l.add(lastTransCol);
		l.add(splitCol);
		l.add(typeCol);

		cm = new ColumnModel<Stock>(l);

		store = new ListStore<Stock>(props.key());
		store.addAll(TestData.getStocks());
	}

	private void afterBind() {
		typeStore = new ListStore<String>(new ModelKeyProvider<String>() {
			@Override
			public String getKey(String item) {
				return item;
			}
		});

		typeStore.add("Auto");
		typeStore.add("Media");
		typeStore.add("Medical");
		typeStore.add("Tech");

		NumericFilter<Stock, Double> lastFilter = new NumericFilter<Stock, Double>(
				props.last(),
				new NumberPropertyEditor.DoublePropertyEditor());
		StringFilter<Stock> nameFilter = new StringFilter<Stock>(
				props.name());
		DateFilter<Stock> dateFilter = new DateFilter<Stock>(
				props.lastTrans());
		BooleanFilter<Stock> booleanFilter = new BooleanFilter<Stock>(
				props.split());
		ListFilter<Stock, String> listFilter = new ListFilter<Stock, String>(
				props.industry(), typeStore);

		GridFilters<Stock> filters = new GridFilters<Stock>();
		filters.initPlugin(grid);
		filters.setLocal(true);
		filters.addFilter(lastFilter);
		filters.addFilter(nameFilter);
		filters.addFilter(dateFilter);
		filters.addFilter(booleanFilter);
		filters.addFilter(listFilter);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
