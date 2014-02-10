package gr.ntua.imu.topics;

import gr.ntua.imu.topics.analyzer.Analyzer;
import gr.ntua.imu.topics.analyzer.AnalyzerImpl;
import gr.ntua.imu.topics.data.FileSource;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config-test.xml")
public class SpringAppTests {
    @Autowired
    private BrowserService browserService;

    @Autowired
    private AnalyzerImpl analyzer;

    //Test Variables
    private static HashSet<String> mockWordSet;
    private static HashSet<String> mockDocumentSet;
    private static HashSet<String> mockLabelSet;

    //TODO: split tests per method

    @BeforeClass
    public static void initializeTestVariables(){
        mockWordSet = new HashSet<String>();
        mockWordSet.add("test");
        mockWordSet.add("test2");
        mockDocumentSet = new HashSet<String>();
        mockDocumentSet.add("test test test test test2 ");
        mockDocumentSet.add("test2 sadf test test ");
        mockLabelSet = new HashSet<String>();
        mockLabelSet.add("test");
        mockLabelSet.add("test2");
    }

    @Test
    public void testBrowser() {
        Analyzer mockAnalyzer = Mockito.mock(AnalyzerImpl.class)  ;
        when(mockAnalyzer.getSize()).thenReturn(100);
        when(mockAnalyzer.getWordsForTopic(anyInt())).thenReturn(mockWordSet);
        browserService.setAnalyzer(mockAnalyzer);
        Assert.assertEquals("Presented", browserService.presentResults());
    }

    @Test
    public void testAnalyzer(){
        FileSource mockSource = Mockito.mock(FileSource.class);
        when(mockSource.getDocuments()) .thenReturn(mockDocumentSet);
        when(mockSource.getLabels()).thenReturn(mockLabelSet);
        analyzer.setSource(mockSource);
        analyzer.loadTrainSet();
        analyzer.estimate();
        Assert.assertNotNull(analyzer.getWordsForTopic(0));

    }

    //TODO: add a test for source
}
