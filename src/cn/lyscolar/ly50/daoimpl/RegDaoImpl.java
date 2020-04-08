package cn.lyscolar.ly50.daoimpl;

import cn.lyscolar.ly50.dao.RegDao;
import cn.lyscolar.ly50.javabean.Reg;
import cn.lyscolar.ly50.utils.DBhelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author LeeSun
 */
public class RegDaoImpl implements RegDao {
    protected Connection conn;
    protected PreparedStatement pstmt;
    protected ResultSet rs;
    protected String  sql;

    @Override
    public int inserUsername(Reg reg) {
        int affectrow = 0;
        String sql="INSERT INTO users (id,username,PASSWORD,realname) VALUES (null,?,?,?)";
        try {
            conn= DBhelper.getConn();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,reg.getUsername());
            pstmt.setString(2,reg.getPassword());
            pstmt.setString(3,reg.getRealname());
            affectrow = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return affectrow;
    }
//检查用户名是否存在 ajax
    @Override
    public int CheckName(String username) {
        int result=1;
        //可以注册
        sql="select username from users where username=?";
//        String sql2 = "select email from account where email=?";
        //2、连接
        try {
            conn= DBhelper.getConn();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,username);
            rs=pstmt.executeQuery();
            if (rs.next()){
                result=0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateUser(Reg reg) {
        int affectrow = 0;
        String sql="update users set realname=?,password=? where username=?";
        try {
            conn= DBhelper.getConn();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,reg.getRealname());
            pstmt.setString(2,reg.getPassword());
            pstmt.setString(3,reg.getUsername());
            affectrow = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return affectrow;
    }




}
