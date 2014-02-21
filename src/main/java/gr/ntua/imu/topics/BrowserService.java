package gr.ntua.imu.topics;

import gr.ntua.imu.topics.analyzer.Analyzer;
import gr.ntua.imu.topics.model.DetachableTopicModel;
import gr.ntua.imu.topics.model.Token;
import gr.ntua.imu.topics.model.Topic;
import gr.ntua.imu.topics.model.TopicImpl;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

@Component
public class BrowserService implements IDataProvider<Topic>, Serializable {


    @Qualifier("analyzer")
    @Autowired
    private Analyzer analyzer;

    private HashSet<Topic> topics;

    public String presentResults() {
        StringBuilder stringBuilder = new StringBuilder();
        try {

            analyzer.loadTrainSet();
            analyzer.estimate();
            topics = new HashSet<Topic>();
            for (int i = 0; i < analyzer.getSize(); i++) {
                Topic topic = new TopicImpl();
                topic.setId(i);
                topic.setTokens((PriorityQueue<Token>) analyzer.getWordsForTopic(i));
                topic.setProbability(0.0);
                topics.add(topic);
            }


        } catch (Exception e) {
            return "not presented";
        }
        return stringBuilder.toString();
    }

    private void getTopics() {


    }

    public Analyzer getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }


    public Topic getTopicById(int i) {
        for (Topic topic : topics) {
            if (topic.getId().equals(i)) return topic;
        }
        return null;
    }


    @Override
    public Iterator<? extends Topic> iterator(int l, int l2) {
        return topics.iterator();
    }

    @Override
    public int size() {
        return topics.size();  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public IModel<Topic> model(Topic topic) {
        return new DetachableTopicModel(topic);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void detach() {
    }
}
