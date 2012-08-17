package com.wsy.gwtp.web.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Tab;
import com.gwtplatform.mvp.client.TabData;
import com.gwtplatform.mvp.client.ViewImpl;
import com.wsy.gwtp.web.client.core.MainPagePresenter;
import com.wsy.gwtp.web.client.core.MainPagePresenter.MyView;
import com.wsy.gwtp.web.client.ui.RoundTabPanel;

public class MainPageView extends ViewImpl implements MyView {
	/**
	   */
	  public interface Binder extends UiBinder<Widget, MainPageView> { }

	  public final Widget widget;

	  @UiField
	  RoundTabPanel tabPanel;

	  @UiField
	  InlineLabel topMessage;

	  @Inject
	  public MainPageView(Binder uiBinder) {
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
	    if (slot == MainPagePresenter.TYPE_SetTabContent) {
	      tabPanel.setPanelContent(content);
	    } else {
	      super.setInSlot(slot, content);
	    }
	  }

	  @Override
	  public void setTopMessage(String string) {
	    if (string == null || string.length() == 0) {
	      topMessage.setVisible(false);
	      topMessage.setText("");
	    } else {
	      topMessage.setVisible(true);
	      topMessage.setText(string);
	    }
	  }
}