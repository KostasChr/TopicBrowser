package gr.ntua.imu.topics.analyzer;

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

import static org.mockito.Mockito.when;

/**
 * @author KostasChr
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config-test.xml")
public class AnalyzerTest {


    private static HashSet<String> mockDocumentSet;
    private static HashSet<String> mockLabelSet;
    @Autowired
    private AnalyzerImpl analyzer;

    @BeforeClass
    public static void initializeTestVariables() {
        mockDocumentSet = new HashSet<String>();
        mockDocumentSet.add("test test test test test2 ");
        mockDocumentSet.add("test2 sadf test test ");
        mockLabelSet = new HashSet<String>();
        mockLabelSet.add("test");
        mockLabelSet.add("test2");
    }

    @Test
    public void testEstimation() {
        FileSource mockSource = Mockito.mock(FileSource.class);
        when(mockSource.getDocuments()).thenReturn(mockDocumentSet);
        when(mockSource.getLabels()).thenReturn(mockLabelSet);
        analyzer.setFileSource(mockSource);
        analyzer.loadTrainSet();
        analyzer.estimate();
        Assert.assertNotNull(analyzer.getWordsForTopic(0));
    }

}
