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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author LeeSun
 */
@WebServlet("/looktaskServlet")
public class looktaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dosth=request.getParameter("do");
        System.out.println("执行参数："+dosth);
        if (dosth.equals("search")){
            try {
                search(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        Reg admin= (Reg) request.getSession().getAttribute("admin");
        int id= (int) request.getSession().getAttribute("id");

        System.out.println("用户id条件查询："+request.getSession().getAttribute("id"));
        Task task=new Task();
        TaskDao taskdao=new TaskDaoImpl();
        List<Task> tasklist = taskdao.selectByid(id);
        if (tasklist.size()>0){
            request.setAttribute("tasklist",tasklist);
            request.setAttribute("task",task);
            request.getRequestDispatcher("task-one.jsp").forward(request,response);
        }
    }
}
