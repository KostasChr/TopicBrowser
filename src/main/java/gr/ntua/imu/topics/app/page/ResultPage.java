package gr.ntua.imu.topics.app.page;

import de.agilecoders.wicket.markup.html.bootstrap.heading.Heading;
import gr.ntua.imu.topics.BrowserService;
import gr.ntua.imu.topics.model.Token;
import gr.ntua.imu.topics.model.Topic;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.PriorityQueue;

/**
 * @author KostasChr
 */
public class ResultPage extends WebPage {

    @SpringBean
    private BrowserService browserService;

    private Integer numberOfWords = 10;

    public ResultPage() {
        browserService.presentResults();
        Heading heading = new Heading("wicket-markup-id", Model.of("Topic Browser"));
        Heading heading2 = new Heading("h3", Model.of("Step 3: Results"));
        add(heading);
        add(heading2);
        add(new DataView<Topic>("simple", browserService) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final Item<Topic> item) {
                Topic topic = item.getModelObject();
                PriorityQueue<Token> queue = topic.getTokens();
                StringBuilder sb = new StringBuilder();
                Integer counter = numberOfWords;
                while (!queue.isEmpty() && counter > 0) {
                    counter--;
                    Token token = queue.poll();
                    sb.append(token.getToken() + " (" + token.getProbability() + ") , ");
                }
                item.add(new Label("id", String.valueOf(topic.getId())));
                item.add(new Label("words", sb.toString()));

            }
        });
    }
}