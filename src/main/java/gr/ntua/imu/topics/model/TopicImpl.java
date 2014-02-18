package gr.ntua.imu.topics.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author KostasChr
 */

public class TopicImpl implements Topic,Serializable{

    private Integer id;
    private HashSet<String> topWords;
    private Double probability;

    @Override
    public Integer getId() {
        return id;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<String> getTopWords() {
        return topWords;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Double getProbability() {
        return probability;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTopWords(HashSet<String> topWords) {
        this.topWords = topWords;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }
}
