package springboot.springboot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

    private MockMvc mvc;
    private RequestBuilder request;

    @Before
    public void setup() throws Exception{
        mvc = MockMvcBuilders.standaloneSetup(new employeeController()).build();
        RequestBuilder request = null;
    }

    @Test
    public void testShowList_should_return_empty() throws Exception{

        request = get("/employees/showlist");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void testAddPerson_should_return_employee() throws Exception{

        request = post("/employees/add")
                .param("id", "1")
                .param("name", "谢嵩")
                .param("age", "20")
                .param("gender","男");
        mvc.perform(request).andExpect(content().string(equalTo("Add succsee")));
    }

    @Test
    public void testDeletePerson_should_return_success() throws Exception{

        request = delete("/employees/delete/1");
        mvc.perform(request).andExpect(content().string(equalTo("delete success!")));
    }

    @Test
    public void testFindPerson_should_return_person() throws Exception{
        request = post("/employees/add")
                .param("id","1")
                .param("name","谢嵩")
                .param("age","20")
                .param("gender","男");
        mvc.perform(request);

        request = post("/employees/findperson")
                .param("id","1");
        mvc.perform(request).andExpect(content().string(equalTo("{\"id\":1,\"name\":\"谢嵩\",\"age\":20,\"gender\":\"男\"}")));

    }

    @Test
    public void testChangePerson_should_return_another_person() throws Exception{

        request = post("/employees/add")
                .param("id","1")
                .param("name","谢嵩")
                .param("age","20")
                .param("gender","男");
        mvc.perform(request);

        request = post("/employees/add")
                .param("id","1")
                .param("name","张三")
                .param("age","22")
                .param("gender","女");
        mvc.perform(request);

        request = post("/employees/findperson")
                .param("id","1");
        mvc.perform(request).andExpect(content().string(equalTo("{\"id\":1,\"name\":\"张三\",\"age\":22,\"gender\":\"女\"}")));
    }
}
