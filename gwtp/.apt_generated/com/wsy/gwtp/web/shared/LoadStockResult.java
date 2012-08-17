package com.wsy.gwtp.web.shared;



import com.gwtplatform.dispatch.shared.Result;

public class LoadStockResult implements Result { 

  com.sencha.gxt.data.shared.loader.PagingLoadResult<com.wsy.gwtp.web.client.model.Stock> result;

  protected LoadStockResult() {
    // Possibly for serialization.
  }

  public LoadStockResult(com.sencha.gxt.data.shared.loader.PagingLoadResult<com.wsy.gwtp.web.client.model.Stock> result) {
    this.result = result;
  }

  public com.sencha.gxt.data.shared.loader.PagingLoadResult<com.wsy.gwtp.web.client.model.Stock> getResult(){
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    LoadStockResult other = (LoadStockResult) obj;
    if (result == null) {
      if (other.result != null)
        return false;
    } else if (!result.equals(other.result))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (result == null ? 1 : result.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "LoadStockResult["
                 + result
    + "]";
  }
}
