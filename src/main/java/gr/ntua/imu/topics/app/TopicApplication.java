package gr.ntua.imu.topics.app;

import gr.ntua.imu.topics.app.page.SimplePage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.stereotype.Component;

@Component( value= "topicApplication")
public class TopicApplication extends WebApplication {

	@Override
	public Class<SimplePage> getHomePage() {

		return SimplePage.class; // return default page
	}
	
	@Override
	protected void init() {
		super.init();
		addComponentInstantiationListener(new SpringComponentInjector(this));
	}

}