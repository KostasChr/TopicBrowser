package gr.ntua.imu.topics;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author KostasChr
 */
public class TopicBrowserApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        BrowserService browserService = context.getBean(BrowserService.class);
        System.out.println(browserService.presentResults());
    }
}
