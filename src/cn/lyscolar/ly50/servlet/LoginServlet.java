package cn.lyscolar.ly50.servlet;

import cn.lyscolar.ly50.javabean.Reg;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

/**
 * @author LeeSun
 */


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    Connection conn=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    @Override
    public void init(ServletConfig config) throws ServletException {
        String URL="jdbc:mysql://localhost:3306/task_db?useUnicode=true&amp;characterEncoding=UTF-8";
        String mysqlusername="root";
        String mysqlpwd="654123";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(URL,mysqlusername,mysqlpwd);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        String username=request.getParameter("loginname");
        String password=request.getParameter("loginpassword");
        String ymcode=request.getParameter("logincheckcode");

        ymcode = ymcode.toUpperCase();
        if(!ymcode.equals(code)) {
            response.getWriter().println("<script>alert('验证码输入有误！');location.href='index.jsp';</script>");
        }else {
            String sql = "select id,username,PASSWORD,realname from users where username=? and PASSWORD=?";
            try {
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, username);
                this.pstmt.setString(2, password);
                this.rs = this.pstmt.executeQuery();
                if (rs.next()) {
                    int id=rs.getInt(1);
                    // 获取第一个列的值 编号id
                    session.setAttribute("username", username);
                    session.setAttribute("id", id);
                    Reg login = new Reg();
                    login.setUsername(rs.getString("username"));
                    login.setUsername(rs.getString("password"));
//                    session.setAttribute("id", id);
                    String url = (String) session.getAttribute("url");
                    response.getWriter().println("<script>alert('success！');</script>");
                    response.sendRedirect("user.jsp");


                } else {
                    response.getWriter().println("<script>alert('用户名或密码错误！');location.href='index.jsp';</script>");
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
            //3-传参
        }

    }

}
