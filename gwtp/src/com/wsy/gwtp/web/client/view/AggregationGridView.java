package com.wsy.gwtp.web.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.cell.core.client.PropertyDisplayCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.DoublePropertyEditor;
import com.sencha.gxt.widget.core.client.grid.AggregationNumberSummaryRenderer;
import com.sencha.gxt.widget.core.client.grid.AggregationRowConfig;
import com.sencha.gxt.widget.core.client.grid.AggregationSafeHtmlRenderer;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.grid.HeaderGroupConfig;
import com.sencha.gxt.widget.core.client.grid.SummaryType.AvgSummaryType;
import com.sencha.gxt.widget.core.client.grid.SummaryType.MaxSummaryType;
import com.sencha.gxt.widget.core.client.grid.SummaryType.MinSummaryType;
import com.wsy.gwtp.web.client.core.AggregationGridPresenter;
import com.wsy.gwtp.web.client.model.Stock;
import com.wsy.gwtp.web.client.model.StockProperties;
import com.wsy.gwtp.web.client.model.TestData;

public class AggregationGridView extends ViewImpl implements
		AggregationGridPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, AggregationGridView> {
	}
	
	@UiField(provided = true) ListStore<Stock> store;
	@UiField(provided = true) ColumnModel<Stock> cm;
	@UiField Grid<Stock> grid;
	@UiField GridView view;
	@UiField(provided = true) VerticalLayoutData gridData = new VerticalLayoutData(1, 1);

	@Inject
	public AggregationGridView(final Binder binder) {
		beforeBind();
		widget = binder.createAndBindUi(this);
		afterBind();
	}

	private void beforeBind() {
		StockProperties props = GWT.create(StockProperties.class);
	    final NumberFormat numberFormat = NumberFormat.getFormat("0.00");
	    final NumberFormat currency = NumberFormat.getCurrencyFormat();
	 
	    List<ColumnConfig<Stock, ?>> configs = new ArrayList<ColumnConfig<Stock, ?>>();
	 
	    ColumnConfig<Stock, String> nameColumn = new ColumnConfig<Stock, String>(props.name(), 200, "Company");
	    configs.add(nameColumn);
	 
	    ColumnConfig<Stock, String> symbolColumn = new ColumnConfig<Stock, String>(props.symbol(), 100, "Symbol");
	    configs.add(symbolColumn);
	 
	    ColumnConfig<Stock, Double> lastColumn = new ColumnConfig<Stock, Double>(props.last(), 100, "Last");
	 
	    lastColumn.setCell(new PropertyDisplayCell<Double>(new DoublePropertyEditor(currency)));
	    configs.add(lastColumn);
	 
	    ColumnConfig<Stock, Double> changeColumn = new ColumnConfig<Stock, Double>(props.change(), 100, "Change");
	 
	    changeColumn.setCell(new PropertyDisplayCell<Double>(new DoublePropertyEditor(numberFormat)) {
	      @Override
	      public void render(com.google.gwt.cell.client.Cell.Context context, Double value, SafeHtmlBuilder sb) {
	        String style = value < 0 ? "red" : "green";
	        sb.appendHtmlConstant("<span style='color:" + style + "'>");
	        super.render(context, value, sb);
	        sb.appendHtmlConstant("</span>");
	      }
	    });
	    configs.add(changeColumn);
	 
	    ColumnConfig<Stock, Date> dateColumn = new ColumnConfig<Stock, Date>(props.lastTrans(), 100, "Date");
	    dateColumn.setCell(new DateCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)));
	    configs.add(dateColumn);
	 
	    store = new ListStore<Stock>(props.key());
	    store.addAll(TestData.getStocks());
	 
	    cm = new ColumnModel<Stock>(configs);
	 
	    cm.addHeaderGroup(0, 0, new HeaderGroupConfig("Stock Information", 1, 2));
	    cm.addHeaderGroup(0, 2, new HeaderGroupConfig("Stock Performance", 1, 2));
	 
	    AggregationRowConfig<Stock> averages = new AggregationRowConfig<Stock>();
	    averages.setRenderer(nameColumn, new AggregationSafeHtmlRenderer<Stock>("Average"));
	 
	    averages.setRenderer(lastColumn, new AggregationNumberSummaryRenderer<Stock, Number>(currency,
	        new AvgSummaryType<Number>()));
	 
	    averages.setRenderer(changeColumn, new AggregationNumberSummaryRenderer<Stock, Number>(numberFormat,
	        new AvgSummaryType<Number>()));
	    cm.addAggregationRow(averages);
	 
	    AggregationRowConfig<Stock> max = new AggregationRowConfig<Stock>();
	    max.setRenderer(nameColumn, new AggregationSafeHtmlRenderer<Stock>("Maximum"));
	 
	    max.setRenderer(lastColumn, new AggregationNumberSummaryRenderer<Stock, Number>(currency,
	        new MaxSummaryType<Number>()));
	 
	    max.setRenderer(changeColumn, new AggregationNumberSummaryRenderer<Stock, Number>(numberFormat,
	        new MaxSummaryType<Number>()));
	    cm.addAggregationRow(max);
	 
	    AggregationRowConfig<Stock> min = new AggregationRowConfig<Stock>();
	    min.setRenderer(nameColumn, new AggregationSafeHtmlRenderer<Stock>("Minimum"));
	 
	    min.setRenderer(lastColumn, new AggregationNumberSummaryRenderer<Stock, Number>(currency,
	        new MinSummaryType<Number>()));
	 
	    min.setRenderer(changeColumn, new AggregationNumberSummaryRenderer<Stock, Number>(numberFormat,
	        new MinSummaryType<Number>()));
	    cm.addAggregationRow(min);
	}

	private void afterBind() {
		view.setStripeRows(true);
		view.setForceFit(true);
		view.setColumnLines(true);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
