package gr.ntua.imu.topics.data;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;

/**
 * @author KostasChr
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config-test.xml")
public class FileSourceTest {
    private static HashSet<String> mockDocumentSet;
    private static HashSet<String> mockLabelSet;
    @Autowired
    private FileSource fileSource;

    @Test
    public void testEstimation() {
        //TODO: implement this
        Assert.assertNull(null);
    }

}
