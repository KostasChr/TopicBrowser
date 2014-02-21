package gr.ntua.imu.topics;

import gr.ntua.imu.topics.analyzer.AnalyzerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BrowserTest.class,
        AnalyzerTest.class
})
public class SpringAppTests {
}
