package gr.ntua.imu.topics.model;

import gr.ntua.imu.topics.BrowserService;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author KostasChr
 */

public class DetachableTopicModel extends LoadableDetachableModel<Topic> {

    private final int id;

    @SpringBean
    private BrowserService browserService;


    public DetachableTopicModel(Topic c) {

        this(c.getId().intValue());
    }

    /**
     * @param id
     */
    public DetachableTopicModel(int id) {

        InjectorHolder.getInjector().inject(this);
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }

    @Override
    protected Topic load() {
        // loads contact from the database
        return browserService.getTopicById(id);
    }

}
