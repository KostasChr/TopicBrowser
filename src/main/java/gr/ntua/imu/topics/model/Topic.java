package gr.ntua.imu.topics.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author KostasChr
 */

public interface Topic {
    Integer getId();
    Set<String> getTopWords();
    Double getProbability();

    public void setId(Integer id);

    public void setTopWords(HashSet<String> topWords) ;

    public void setProbability(Double probability) ;


}
