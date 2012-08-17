/**
 * 
 */
package com.wsy.gwtp.web.client.model;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.loader.JsonReader;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;

/**
 * @author e518417
 * 
 */
public interface Email {

	String getName();

	String getEmail();

	String getPhone();

	String getState();

	String getZip();

	/**
	 * Defines the structure of the root JSON object being returned by the
	 * server. This class is needed as we cannot return a list of objects.
	 * Instead, we return a single object with a single property that contains
	 * the data records.
	 */
	public interface RecordResult {

		List<Email> getRecords();
	}

	public interface EmailAutoBeanFactory extends AutoBeanFactory {

		AutoBean<RecordResult> items();

		AutoBean<ListLoadConfig> loadConfig();

		public static final EmailAutoBeanFactory instance = GWT
				.create(EmailAutoBeanFactory.class);
	}

	public class EmailRecordJsonReader extends
			JsonReader<ListLoadResult<Email>, RecordResult> {
		public EmailRecordJsonReader() {
			super(EmailAutoBeanFactory.instance, RecordResult.class);
		}

		@Override
		protected ListLoadResult<Email> createReturnData(Object loadConfig,
				RecordResult incomingData) {
			return new ListLoadResultBean<Email>(incomingData.getRecords());
		}
	}

	interface EmailProperties extends PropertyAccess<Email> {
		@Path("name")
		ModelKeyProvider<Email> key();

		ValueProvider<Email, String> name();

		ValueProvider<Email, String> email();

		ValueProvider<Email, String> phone();

		ValueProvider<Email, String> state();

		ValueProvider<Email, String> zip();
		
		public static final EmailProperties props = GWT.create(EmailProperties.class);
	}
}
