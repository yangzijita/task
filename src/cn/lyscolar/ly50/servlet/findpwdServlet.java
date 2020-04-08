package cn.lyscolar.ly50.servlet;

import cn.lyscolar.ly50.dao.FindDao;
import cn.lyscolar.ly50.dao.RegDao;
import cn.lyscolar.ly50.daoimpl.FindpwdImpl;
import cn.lyscolar.ly50.daoimpl.RegDaoImpl;
import cn.lyscolar.ly50.javabean.Reg;
import cn.lyscolar.ly50.utils.SendMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author LeeSun
 */
@WebServlet("/findpwdServlet")
public class findpwdServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        String email = request.getParameter("findem");
        String user = request.getParameter("findname");
        String ymcode=request.getParameter("findpwdcheckcode");
        System.out.println("输入的邮箱"+email);
        PrintWriter out = response.getWriter();
        String username1 ;
        ymcode = ymcode.toUpperCase();
        if(!ymcode.equals(code)) {
            response.getWriter().println("<script>alert('验证码输入有误！');location.href='index.jsp';</script>");
        }else {

            FindDao findDao = new FindpwdImpl();
            try {
                //            Reg reg = new Reg();
                Reg dao = findDao.getAccountByEmail(email);


                        //            reg.setUsername(username);
                        //            reg.setEmail(email);
                        //            RegDao regDao = new RegDaoImpl();
                        if (dao != null) {
                            username1 = dao.getUsername();
                            if (!username1.equals(user)) {
                                out.println("<script>alert('账号和邮箱不一致！！！核实后重填');window.location.href='index.jsp'</script>");
                            } else {
                                SendMail sendMail = new SendMail();
                                SendMail.sendMail(email, "666系统提醒，您的密码为：" + dao.getPassword());
                                System.out.println(dao.getPassword());
                                out.println("<script>alert('恭喜，找回密码成功');window.location.href='index.jsp'</script>");
                            }

                        } else {
                            out.println("<script>alert('账号和邮箱不一致！！！核实后重填');window.location.href='index.jsp'</script>");
                        }

                } catch(Exception e){
                    e.printStackTrace();
                }

        }
    }
}
