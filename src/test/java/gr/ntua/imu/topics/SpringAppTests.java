package gr.ntua.imu.topics;

import gr.ntua.imu.topics.analyzer.Analyzer;
import gr.ntua.imu.topics.analyzer.AnalyzerImpl;
import gr.ntua.imu.topics.analyzer.AnalyzerTest;
import gr.ntua.imu.topics.data.FileSource;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BrowserTest.class,
        AnalyzerTest.class
        })
public class SpringAppTests {
}
