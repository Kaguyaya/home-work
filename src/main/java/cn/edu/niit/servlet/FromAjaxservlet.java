package cn.edu.niit.servlet;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FromAjaxservlet")
public class FromAjaxservlet extends HttpServlet {


  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setCharacterEncoding("utf-8");
    try {
      //创建默认连接
      CloseableHttpClient httpClient = HttpClients.createDefault();
      //创建HttpGet对象,处理get请求,转发到A站点
      HttpGet httpGet =
          new HttpGet("https://www.layui.com/demo/table/user/?page=1&limit=30");
      //执行
      CloseableHttpResponse response = httpClient.execute(httpGet);
      int code = response.getStatusLine().getStatusCode();
      //获取状态
      System.out.println("http请求结果为:" + code);
      if (code == 200) {
        //获取A站点返回的结果
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        //把结果返回给B站点
        resp.getWriter().print(result);
      }
      response.close();
      httpClient.close();
    } catch (Exception e) {
    }
  }
}
