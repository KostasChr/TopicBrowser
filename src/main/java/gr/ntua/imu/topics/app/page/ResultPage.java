package gr.ntua.imu.topics.app.page;

import gr.ntua.imu.topics.BrowserService;
import gr.ntua.imu.topics.analyzer.Analyzer;
import gr.ntua.imu.topics.model.Topic;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;

public class ResultPage extends WebPage {

    @SpringBean
    private HelloService helloService;

    @SpringBean
    private BrowserService browserService;


    public ResultPage(final PageParameters parameters) {
        browserService.presentResults();
        add(new DataView<Topic>("simple", browserService) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final Item<Topic> item) {
                Topic topic = item.getModelObject();
                topic.getTopWords();
                StringBuilder sb = new StringBuilder();
                for (String word : topic.getTopWords()) {
                    sb.append(word + " ");
                }
                item.add(new Label("id", String.valueOf(topic.getId())));
                item.add(new Label("words", sb.toString()));

            }
        });
    }
}