package com.wsy.gwtp.web.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Tab;
import com.gwtplatform.mvp.client.TabData;
import com.gwtplatform.mvp.client.ViewImpl;
import com.wsy.gwtp.web.client.core.BoostrapPresenter;
import com.wsy.gwtp.web.client.ui.SimpleTabPanel;

public class BoostrapView extends ViewImpl implements BoostrapPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, BoostrapView> {
	}

	@UiField
	SimpleTabPanel tabPanel;

	@Inject
	public BoostrapView(Binder uiBinder) {
		widget = uiBinder.createAndBindUi(this);
	}

	@Override
	public Tab addTab(TabData tabData, String historyToken) {
		return tabPanel.addTab(tabData, historyToken);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void removeTab(Tab tab) {
		tabPanel.removeTab(tab);
	}

	@Override
	public void removeTabs() {
		tabPanel.removeTabs();
	}

	@Override
	public void setActiveTab(Tab tab) {
		tabPanel.setActiveTab(tab);
	}

	@Override
	public void changeTab(Tab tab, TabData tabData, String historyToken) {
		tabPanel.changeTab(tab, tabData, historyToken);
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == BoostrapPresenter.TYPE_SetTabContent) {
			tabPanel.setPanelContent(content);
		} else {
			super.setInSlot(slot, content);
		}
	}
}
