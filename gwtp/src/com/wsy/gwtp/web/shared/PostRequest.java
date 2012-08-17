/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.wsy.gwtp.web.shared;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.wsy.gwtp.web.client.model.Post;
import com.wsy.gwtp.web.server.PostService;
import com.wsy.gwtp.web.server.PostServiceLocator;

@Service(value = PostService.class, locator = PostServiceLocator.class)
public interface PostRequest extends RequestContext {
  @ProxyFor(PostPagingLoadResultBean.class)
  public interface PostPagingLoadResultProxy extends ValueProxy, PagingLoadResult<PostProxy> {
    @Override
    public List<PostProxy> getData();
  }
  
  public static class PostPagingLoadResultBean extends PagingLoadResultBean<Post> {
    protected PostPagingLoadResultBean() {
      
    }
    public PostPagingLoadResultBean(List<Post> list, int totalLength, int offset) {
      super(list, totalLength, offset);
    }
  }

  Request<PostPagingLoadResultProxy> getPosts(int offset, int limit, List<? extends SortInfo> sortInfo, List<? extends FilterConfig> filterConfig);
}
