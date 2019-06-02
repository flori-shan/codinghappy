package cn.nihility.spring.controller;

import cn.nihility.utils.LogbackUtil;
import cn.nihility.utils.LoggerLevelEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author muscari
 * @date 2019-06-02 23:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mvc.xml"})
@WebAppConfiguration
public class DataBoundTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    /*
    * 注意：
    * (1)@WebAppConfiguration：测试环境使用，用来表示测试环境使用的 ApplicationContext 将是 WebApplicationContext 类型的； value 指定 web 应用的根；
    * (2)通过 @Autowired WebApplicationContext wac：注入web环境的 ApplicationContext 容器；
    * (3)然后通过 MockMvcBuilders.webAppContextSetup(wac).build() 创建一个 MockMvc 进行测试；
    * */
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /*
    * perform：执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
    * andExpect：添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确；
    * andDo：添加ResultHandler结果处理器，比如调试时打印结果到控制台；
    * andReturn：最后返回相应的MvcResult；然后进行自定义验证/进行下一步的异步处理；
    * */

    @Test
    public void testHelloController() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.parseMediaType("application/json;charset=UTF-8")).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().json("{\"hello\":\"Hello First Controller\"}"))
                .andReturn();
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Request Result [{}]", result.getResponse().getContentAsString());
        Assert.assertEquals("{\"hello\":\"Hello First Controller\"}", result.getResponse().getContentAsString());
    }

    @Test
    public void testParamBasicTypeIsNotNull() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/basic-type?basicAge=20"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("param age[20]"))
                .andReturn();
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "result [{}]", mvcResult.getResponse().getContentAsString());

    }

}
