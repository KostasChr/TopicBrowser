package gr.ntua.imu.topics.analyzer;

import gr.ntua.imu.topics.model.Token;

import java.util.PriorityQueue;

/**
 * @author KostasChr
 */
public interface Analyzer {
    void loadTrainSet();

    void estimate();

    PriorityQueue<Token> getWordsForTopic(Integer topicId);

    Integer getSize();

}
