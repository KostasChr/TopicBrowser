package gr.ntua.imu.topics.model;


/**
 * @author KostasChr
 */
public interface Token {

    Double getProbability();

    String getToken();

    void setProbability(Double probability);

    void setToken(String token);

}
