package gr.ntua.imu.topics;

import gr.ntua.imu.topics.analyzer.Analyzer;
import gr.ntua.imu.topics.analyzer.AnalyzerImpl;
import gr.ntua.imu.topics.model.Token;
import gr.ntua.imu.topics.model.TokenImpl;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.PriorityQueue;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @author KostasChr
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config-test.xml")
public class BrowserTest {

    @Autowired
    private BrowserService browserService;

    //Test Variables
    private static HashSet<String> mockWordSet;
    private static PriorityQueue<Token> mockTokenQueue;


    @BeforeClass
    public static void initializeTestVariables() {
        mockWordSet = new HashSet<String>();
        mockWordSet.add("test");
        mockWordSet.add("test2");
        mockTokenQueue = new PriorityQueue<Token>();
        mockTokenQueue.add(new TokenImpl("word1", 0.2390));
        mockTokenQueue.add(new TokenImpl("word2", 0.12350));


        ;
    }

    @Test
    public void testBrowser() {
        Analyzer mockAnalyzer = Mockito.mock(AnalyzerImpl.class);
        when(mockAnalyzer.getSize()).thenReturn(100);
        when(mockAnalyzer.getWordsForTopic(anyInt())).thenReturn(mockTokenQueue);
        browserService.setAnalyzer(mockAnalyzer);
        Assert.assertNotNull(browserService.presentResults());
    }
}
