package cn.lyscolar.ly50.servlet;

import cn.lyscolar.ly50.dao.TaskDao;
import cn.lyscolar.ly50.daoimpl.TaskDaoImpl;
import cn.lyscolar.ly50.javabean.Reg;
import cn.lyscolar.ly50.javabean.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author LeeSun
 */
@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        int user1 = (int) session.getAttribute("id");
        System.out.println("当前用户的id值为："+user1);

        String taskName =request.getParameter("task_name");
//        String taskName=new String(taskName1.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        taskName = new String(taskName.getBytes("ISO-8859-1"), "UTF-8");
        //牢牢
        String taskDescription =request.getParameter("task_description");
//        String taskDescription=new String(taskDescription1.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        taskDescription = new String(taskDescription.getBytes("ISO-8859-1"), "UTF-8");

        String taskLevel = request.getParameter("task_level");
        String taskDue =request.getParameter("task_due");
        System.out.println("值为："+taskName+taskDescription);


        Task task = new Task();
        task.setUserid(user1);
        task.setEnd(taskDue);
        task.setDescription(taskDescription);
        task.setName(taskName);
        task.setLevel(taskLevel);
        System.out.print(Charset.defaultCharset());
        TaskDao taskDao = new TaskDaoImpl();
        int affect = taskDao.inserTask(task);
        if(affect>0){
            System.out.print(Charset.defaultCharset());
            System.out.print("写入成功");
            response.getWriter().print("<script>window.alert('成功！');window.location.href='user.jsp';</script>");


        }else {
            System.out.print("写入失败");
            response.getWriter().print("<script>window.alert('注册失败！');</script>");
        }
    }
}
