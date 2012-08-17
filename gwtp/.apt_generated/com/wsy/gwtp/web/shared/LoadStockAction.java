package com.wsy.gwtp.web.shared;

import com.gwtplatform.dispatch.shared.Action;

public class LoadStockAction implements Action<LoadStockResult> { 

  com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig loadConfig;

  protected LoadStockAction() {
    // Possibly for serialization.
  }

  public LoadStockAction(com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig loadConfig) {
    this.loadConfig = loadConfig;
  }

  @Override
  public String getServiceName() {
    return Action.DEFAULT_SERVICE_NAME + "LoadStock";
  }

  @Override
  public boolean isSecured() {
    return false;
  }

  public com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig getLoadConfig(){
    return loadConfig;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    LoadStockAction other = (LoadStockAction) obj;
    if (loadConfig == null) {
      if (other.loadConfig != null)
        return false;
    } else if (!loadConfig.equals(other.loadConfig))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (loadConfig == null ? 1 : loadConfig.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "LoadStockAction["
                 + loadConfig
    + "]";
  }
}
