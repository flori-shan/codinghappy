package cn.nihility.servlet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Gradle Hello Servlet Request Test
 * @author muscari
 * @date 2019-05-29 11:22
 */
public class HelloServletTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doGet() throws IOException, ServletException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        Mockito.when(response.getWriter()).thenReturn(printWriter);

        new HelloServlet().doGet(request, response);

        Assert.assertEquals("Hello, World!", stringWriter.toString());
    }

    @Test
    public void doPostWithoutName() throws ServletException, IOException {
        Mockito.when(request.getRequestDispatcher("servlet-response.jsp"))
                .thenReturn(requestDispatcher);

        new HelloServlet().doPost(request, response);

        Mockito.verify(request).setAttribute("user", "World");
        Mockito.verify(requestDispatcher).forward(request, response);

    }

    @Test
    public void doPostWithName() throws ServletException, IOException {
        Mockito.when(request.getParameter("name"))
                .thenReturn("Dolly");
        Mockito.when(request.getRequestDispatcher("servlet-response.jsp"))
                .thenReturn(requestDispatcher);

        new HelloServlet().doPost(request, response);

        Mockito.verify(request).setAttribute("user", "Dolly");
        Mockito.verify(requestDispatcher).forward(request, response);

    }
}
