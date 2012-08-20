package com.wsy.gwtp.web.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface ImageResources extends ClientBundle {
	@Source("css/one.png")
	ImageResource one();
	@Source("css/two.png")
	ImageResource two();
	@Source("css/three.png")
	ImageResource three();
	@Source("css/four.png")
	ImageResource four();
	@Source("css/five.png")
	ImageResource five();
	@Source("css/six.png")
	ImageResource six();
	@Source("css/seven.png")
	ImageResource seven();
	@Source("css/eight.png")
	ImageResource eight();
	
	@Source("css/images.sprite.css")
	MyCssResource res();
	
	interface MyCssResource extends CssResource {
		String one();
		String two();
		String three();
		String four();
		String five();
		String six();
		String seven();
		String eight();
	}
}
