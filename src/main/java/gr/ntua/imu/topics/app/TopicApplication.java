package gr.ntua.imu.topics.app;

import gr.ntua.imu.topics.app.page.SelectSourcePage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.stereotype.Component;

@Component(value = "topicApplication")
public class TopicApplication extends WebApplication {

    @Override
    public Class<SelectSourcePage> getHomePage() {

        return SelectSourcePage.class; // return default page
    }

    @Override
    protected void init() {
        super.init();
        addComponentInstantiationListener(new SpringComponentInjector(this));
    }

}