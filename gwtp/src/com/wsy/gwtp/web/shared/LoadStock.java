/**
 * 
 */
package com.wsy.gwtp.web.shared;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.wsy.gwtp.web.client.model.Stock;

/**
 * @author e518417
 *
 */
@GenDispatch(isSecure = false)
public class LoadStock {

	@In(1) FilterPagingLoadConfig loadConfig;
	
	@Out(1) PagingLoadResult<Stock> result;
}
