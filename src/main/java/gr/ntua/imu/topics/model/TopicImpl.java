package gr.ntua.imu.topics.model;

import java.io.Serializable;
import java.util.PriorityQueue;

/**
 * @author KostasChr
 */

public class TopicImpl implements Comparable<Topic>,Topic, Serializable {

    private Integer id;
    private PriorityQueue<Token> tokens;
    private Double probability;

    @Override
    public Integer getId() {
        return id;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PriorityQueue<Token> getTokens() {
        return tokens;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Double getProbability() {
        return probability;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTokens(PriorityQueue<Token> tokens) {
        this.tokens = tokens;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    @Override
    public int compareTo(Topic topic) {
        return Double.compare(topic.getProbability(),this.getProbability());  //To change body of implemented methods use File | Settings | File Templates.
    }
}
