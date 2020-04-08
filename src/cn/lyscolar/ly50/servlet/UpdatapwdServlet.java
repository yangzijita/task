package cn.lyscolar.ly50.servlet;

import cn.lyscolar.ly50.dao.RegDao;
import cn.lyscolar.ly50.daoimpl.RegDaoImpl;
import cn.lyscolar.ly50.javabean.Reg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author LeeSun
 */
@WebServlet("/UpdatapwdServlet")
public class UpdatapwdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String realname=request.getParameter("up_realname");
        String password=request.getParameter("up_password");
        String password2=request.getParameter("up_password2");
        HttpSession session = request.getSession();
        String username1 = (String) session.getAttribute("username");
//        System.out.println(realname+password+username1);

        if (!password.equals(password2)){
            response.getWriter().print("<script>window.alert('2次密码不一致！');</script>");
        }else {
            Reg updatapwd = new Reg();
            updatapwd.setRealname(realname);
            updatapwd.setPassword(password);
            updatapwd.setUsername(username1);

            RegDao updatapwdDao = new RegDaoImpl();
            int affectrow = updatapwdDao.updateUser(updatapwd);

            if (affectrow > 0){
                response.getWriter().print("<script>window.alert('密码修改成功！即将返回登录界面');window.location.href='index.jsp';</script>");
                session.removeAttribute("username");
            }else {
                response.getWriter().print("<script>window.alert('密码修改失败！');window.location.href='user.jsp';</script>");
            }

        }
    }
}
