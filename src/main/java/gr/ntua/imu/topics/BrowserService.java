package gr.ntua.imu.topics;

import gr.ntua.imu.topics.analyzer.Analyzer;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BrowserService {

    @Autowired
    private Analyzer analyzer;

    public String presentResults() {
        try {
            analyzer.loadTrainSet();
            analyzer.estimate();
            int numberOfTopics = analyzer.getSize();
            Set<String> wordsForTopic;
            for (int i = 0; i < numberOfTopics; i++) {
                wordsForTopic = analyzer.getWordsForTopic(i);
                System.out.println();
                for (String s : wordsForTopic) {
                    System.out.print(s + " ");
                }
            }

        } catch (Exception e) {
            return "not presented";
        }
        return "Presented";
    }

    public Analyzer getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }
}