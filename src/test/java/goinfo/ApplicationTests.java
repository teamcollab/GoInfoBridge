package goinfo;


import goinfo.service.ConvertService;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ApplicationTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ConvertService convertService;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testQuery() throws Exception {
        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("password", "password");
        params.put("action", "query");
        params.put("queryname", "selectall");
        params.put("connectname", "major");


        String jsonStr = convertService.mapToJsonString(params);

        MvcResult mvcResult = this.mvc.perform(post("/rest/api")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("params", jsonStr))
                .andExpect(status().isOk()).andReturn();

        Map result = convertService.stringToMap(mvcResult.getResponse().getContentAsString());

        assert result.get("success").equals(true);
    }
    @Test
    public void testQueryFail() throws Exception {
        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("password", "password");
        params.put("action", "query");
        params.put("queryname", "selecterror");
        params.put("connectname", "major");

        String jsonStr = convertService.mapToJsonString(params);

        MvcResult mvcResult = this.mvc.perform(post("/rest/api")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("params", jsonStr))
                .andExpect(status().isOk())
                .andReturn();

        Map result = convertService.stringToMap(mvcResult.getResponse().getContentAsString());

        assert result.get("success").equals(false);

        assert result.get("errorMessage").toString().contains("bad SQL grammar [select * from table]");



    }
    @Test
    public void testUpdate() throws Exception {

        Map values = new HashMap();
        values.put("Code","A001");
        values.put("Name","A001_update");
        values.put("Note","update_note");


        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("password", "password");
        params.put("action", "update");
        params.put("queryname", "updateone");
        params.put("values", values);
        params.put("connectname", "major");

        String jsonStr = convertService.mapToJsonString(params);

        MvcResult mvcResult = this.mvc.perform(post("/rest/api")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("params", jsonStr))
                .andExpect(status().isOk())
                .andReturn();

        Map result = convertService.stringToMap(mvcResult.getResponse().getContentAsString());

        assert result.get("success").equals(true);

        System.out.println(result);
    }

    @Test
    public void testUpdateFail() throws Exception {




        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("password", "password");
        params.put("action", "update");
        params.put("queryname", "updateone");


        String jsonStr = convertService.mapToJsonString(params);

        MvcResult mvcResult = this.mvc.perform(post("/rest/api")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("params", jsonStr))
                .andExpect(status().isOk())
                .andReturn();

        Map result = convertService.stringToMap(mvcResult.getResponse().getContentAsString());

        assert result.get("success").equals(false);
        assert !result.get("errorMessage").equals("");
    }
    @Test
    public void testUDelete() throws Exception {

        Map values = new HashMap();
        values.put("Code","A001");


        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("password", "password");
        params.put("action", "delete");
        params.put("queryname", "deleteone");
        params.put("values", values);
        params.put("connectname", "major");

        String jsonStr = convertService.mapToJsonString(params);

        MvcResult mvcResult = this.mvc.perform(post("/rest/api")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("params", jsonStr))
                .andExpect(status().isOk())
                .andReturn();

        Map result = convertService.stringToMap(mvcResult.getResponse().getContentAsString());

        assert result.get("success").equals(true);

        System.out.println(result);
    }
    @Test
    public void testUCreate() throws Exception {
        Map values = new HashMap();
        values.put("Code","A001_CREATE");
        values.put("Name","A001_CREATE");
        values.put("Note","note_CRETAE");


        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("password", "password");
        params.put("action", "create");
        params.put("queryname", "createone");
        params.put("values", values);
        params.put("connectname", "major");

        String jsonStr = convertService.mapToJsonString(params);

        MvcResult mvcResult = this.mvc.perform(post("/rest/api")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("params", jsonStr))
                .andExpect(status().isOk())
                .andReturn();

        Map result = convertService.stringToMap(mvcResult.getResponse().getContentAsString());

        assert result.get("success").equals(true);

        System.out.println(result);
    }


}