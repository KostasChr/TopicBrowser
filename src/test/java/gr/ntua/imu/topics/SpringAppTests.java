package gr.ntua.imu.topics;

import gr.ntua.imu.topics.analyzer.AnalyzerTest;
import gr.ntua.imu.topics.data.FileSource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author KostasChr
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BrowserTest.class,
        AnalyzerTest.class,
        FileSource.class
})
public class SpringAppTests {
}
