package cn.lyscolar.ly50.servlet;

import cn.lyscolar.ly50.dao.RegDao;
import cn.lyscolar.ly50.daoimpl.RegDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckNameServlet")
public class CheckNameServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        int result=0;
        RegDao regDao = new RegDaoImpl();
        result = regDao.CheckName(username);
        response.getWriter().print(result);
        response.getWriter().flush();
        response.getWriter().close();

    }
}
