package goinfo.service;

import goinfo.Application;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.OutputCapture;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by Spooky on 2013/12/20.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("test")
public class SingletonsServiceTests {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void testDynamicPropertiesLoad() throws Exception {

        System.out.println(PropertiesHoldService.getQueriesProperties().getProperty("selectall"));

        String output = this.outputCapture.toString();

        assertTrue(output, output.startsWith("select"));
    }

}
