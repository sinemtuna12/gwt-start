/**
 * 
 */
package com.wsy.gwtp.web.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.wsy.gwtp.web.client.model.Stock;
import com.wsy.gwtp.web.client.model.TestData;
import com.wsy.gwtp.web.shared.LoadStockAction;
import com.wsy.gwtp.web.shared.LoadStockResult;

/**
 * @author e518417
 * 
 */
public class LoadStockActionHandler implements
		ActionHandler<LoadStockAction, LoadStockResult> {

	private List<Stock> stocks;

	@Override
	public LoadStockResult execute(LoadStockAction action,
			ExecutionContext context) throws ActionException {
		if (stocks == null) {
			stocks = TestData.getStocks();
		}

		ArrayList<Stock> temp = new ArrayList<Stock>();
		ArrayList<Stock> remove = new ArrayList<Stock>();
		for (Stock s : stocks) {
			temp.add(s);
		}

		if (action.getLoadConfig().getSortInfo().size() > 0) {
			SortInfo sort = action.getLoadConfig().getSortInfo().get(0);
			if (sort.getSortField() != null) {
				final String sortField = sort.getSortField();
				if (sortField != null) {
					Collections.sort(
							temp,
							sort.getSortDir().comparator(
									new Comparator<Stock>() {
										public int compare(Stock s1, Stock s2) {
											if (sortField.equals("name")) {
												return s1.getName().compareTo(
														s2.getName());
											} else if (sortField
													.equals("lastTrans")) {
												return s1
														.getLastTrans()
														.compareTo(
																s2.getLastTrans());
											} else if (sortField
													.equals("split")) {
												Boolean b1 = s1.isSplit();
												Boolean b2 = s2.isSplit();
												return b1.compareTo(b2);
											} else if (sortField
													.equals("industry")) {
												return s1
														.getIndustry()
														.compareTo(
																s2.getIndustry());
											}
											return 0;
										}
									}));
				}
			}
		}

		List<FilterConfig> filters = action.getLoadConfig().getFilters();
		for (FilterConfig f : filters) {
			String type = f.getType();
			String test = f.getValue();
			String path = f.getField();
			String comparison = f.getComparison();

			String safeTest = test == null ? "" : test.toString();

			for (Stock s : stocks) {
				String value = getStockValue(s, path);
				String safeValue = value == null ? null : value.toString();

				if (safeTest.length() == 0
						&& (safeValue == null || safeValue.length() == 0)) {
					continue;
				} else if (safeValue == null) {
					remove.add(s);
					continue;
				}

				if ("string".equals(type)) {
					if (safeValue.toLowerCase().indexOf(safeTest.toLowerCase()) == -1) {
						remove.add(s);
					}
				} else if ("date".equals(type)) {
					if (isDateFiltered(safeTest, comparison, safeValue)) {
						remove.add(s);
					}
				} else if ("boolean".equals(type)) {
					if (isBooleanFiltered(safeTest, comparison, safeValue)) {
						remove.add(s);
					}
				} else if ("list".equals(type)) {
					if (isListFiltered(safeTest, safeValue)) {
						remove.add(s);
					}
				} else if ("numeric".equals(type)) {
					if (isNumberFiltered(safeTest, comparison, safeValue)) {
						remove.add(s);
					}
				}
			}
		}

		for (Stock s : remove) {
			temp.remove(s);
		}

		ArrayList<Stock> sublist = new ArrayList<Stock>();
		int start = action.getLoadConfig().getOffset();
		int limit = temp.size();
		if (action.getLoadConfig().getLimit() > 0) {
			limit = Math.min(start + action.getLoadConfig().getLimit(), limit);
		}
		for (int i = action.getLoadConfig().getOffset(); i < limit; i++) {
			sublist.add(temp.get(i));
		}
		return new LoadStockResult(new PagingLoadResultBean<Stock>(sublist, temp.size(), action.getLoadConfig().getOffset()));
	}

	@Override
	public Class<LoadStockAction> getActionType() {
		return LoadStockAction.class;
	}

	@Override
	public void undo(LoadStockAction arg0, LoadStockResult arg1,
			ExecutionContext arg2) throws ActionException {

	}

	private String getStockValue(Stock s, String property) {
		if (property.equals("name")) {
			return s.getName();
		} else if (property.equals("lastTrans")) {
			return String.valueOf(s.getLastTrans().getTime());
		} else if (property.equals("split")) {
			return String.valueOf(s.isSplit());
		} else if (property.equals("last")) {
			return String.valueOf(s.getLast());
		} else if (property.equals("industry")) {
			return s.getIndustry();
		}

		return "";
	}

	private boolean isBooleanFiltered(String test, String comparison,
			String value) {
		if (value == null) {
			return true;
		}
		boolean t = Boolean.valueOf(test);
		boolean v = Boolean.parseBoolean(value);

		return t != v;
	}

	private boolean isDateFiltered(String test, String comparison, String value) {
		Date t = new Date(Long.valueOf(test));
		Date v = new Date(Long.valueOf(value));
		if (value == null) {
			return false;
		}
		if ("after".equals(comparison)) {
			return v.before(t);
		} else if ("before".equals(comparison)) {
			return v.after(t);
		} else if ("on".equals(comparison)) {
			t = new DateWrapper(t).resetTime().asDate();
			v = new DateWrapper(v).resetTime().asDate();
			return !v.equals(t);
		}
		return true;
	}

	private boolean isListFiltered(String test, String value) {
		String[] tests = test.split("::");
		for (int i = 0; i < tests.length; i++) {
			if (tests[i].equals(value)) {
				return false;
			}
		}
		return true;
	}

	private boolean isNumberFiltered(String test, String comparison,
			String value) {
		if (value == null) {
			return false;
		}
		double t = Double.valueOf(test);
		double v = Double.valueOf(value);

		if ("gt".equals(comparison)) {
			return t >= v;
		} else if ("lt".equals(comparison)) {
			return t <= v;
		} else if ("eq".equals(comparison)) {
			return t != v;
		}
		return false;
	}

}
