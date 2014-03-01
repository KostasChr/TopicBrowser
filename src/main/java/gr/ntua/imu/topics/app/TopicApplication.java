package gr.ntua.imu.topics.app;



import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.settings.BootstrapSettings;
import gr.ntua.imu.topics.app.page.SourcePage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.stereotype.Component;

/**
 * @author KostasChr
 */
@Component(value = "topicApplication")
public class TopicApplication extends WebApplication {

    @Override
    public Class<SourcePage> getHomePage() {

        return SourcePage.class; // return default page
    }

    @Override
    protected void init() {
        super.init();
        BootstrapSettings settings = new BootstrapSettings();
        Bootstrap.install(this, settings);
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
       // addComponentInstantiationListener(new SpringComponentInjector(this));
    }

}