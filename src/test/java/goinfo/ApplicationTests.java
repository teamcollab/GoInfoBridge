package goinfo;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
// Separate profile for web tests to avoid clashing databases
public class ApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testRepairItemList() throws Exception {

        this.mvc.perform(get("/repairItem/list")).andExpect(status().isOk())
                .andExpect(content().string("[{\"code\":\"A001\",\"name\":\"A001\",\"note\":null}]"));
    }

    @Test
    public void testQuery() throws Exception {

        String jsonStr = "{\n" +
                "    \"username\": \"admin\",\n" +
                "    \"values\": {},\n" +
                "    \"queryname\": \"selectall\",\n" +
                "    \"action\": \"query\",\n" +
                "    \"password\": \"password\"\n" +
                "}";

        this.mvc.perform(post("/rest/query").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("params", jsonStr)).andExpect(status().isOk())
                .andExpect(content().string("{\"data\":[{\"CODE\":\"A001\",\"NAME\":\"A001\",\"NOTE\":null}],\"success\":true}"));
    }

}