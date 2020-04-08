package cn.lyscolar.ly50.servlet;

import cn.lyscolar.ly50.dao.RegDao;
import cn.lyscolar.ly50.daoimpl.RegDaoImpl;
import cn.lyscolar.ly50.javabean.Reg;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author LeeSun
 */
@WebServlet("/reg")
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String Realname=request.getParameter("Realname");
        String checkcode = request.getParameter("checkcode");

        checkcode = checkcode.toUpperCase();
        if(!checkcode.equals(code)) {
            response.getWriter().println("<script>alert('验证码输入有误！');location.href='index.jsp';</script>");
        }else {
            Reg reg = new Reg();
            reg.setUsername(username);
            reg.setPassword(password);
            reg.setRealname(Realname);
            RegDao regDao = new RegDaoImpl();
            int affectrow = regDao.inserUsername(reg);
            if(affectrow>0) {
                System.out.print(Charset.defaultCharset());
                System.out.print("注册成功");
                response.getWriter().print("<script>window.alert('注册成功！');window.location.href='index.jsp';</script>");
            }else {
                System.out.print("注册失败");
                response.getWriter().print("<script>window.alert('注册失败！');window.location.href='index.jsp';</script>");
            }
        }



    }
}
