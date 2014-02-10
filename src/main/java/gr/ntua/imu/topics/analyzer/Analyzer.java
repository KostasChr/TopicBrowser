package gr.ntua.imu.topics.analyzer;

import java.util.Set;

/**
 * User: Kostas Christidis
 */
public interface Analyzer {
    void loadTrainSet();

    void estimate();

    Set<String> getWordsForTopic(Integer topicId);

    Integer getSize();

}
