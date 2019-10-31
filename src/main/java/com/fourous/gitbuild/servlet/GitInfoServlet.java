package main.java.com.fourous.gitbuild.servlet;

import main.java.com.fourous.gitbuild.VO.HttpResultTestVO;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author fourous
 * @date: 2019/10/30
 * @description: 返回Git查询的信息，发送到前端显示
 */
public class GitInfoServlet extends HttpServlet {
    private static Gson gson = new Gson();

    private void doService(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*这里写入主要逻辑发送，封装实体可以通过sendResult更改，其实Spring在这里也是采用注入方式解决这个问题*/

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    private void sendResult(HttpServletResponse response, HttpResultTestVO httpResultTestVO) throws IOException {
        PrintWriter printWriter = null;
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            printWriter = response.getWriter();
            printWriter.write(gson.toJson(httpResultTestVO));
            printWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
