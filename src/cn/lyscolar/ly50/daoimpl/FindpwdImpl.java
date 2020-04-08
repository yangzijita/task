package cn.lyscolar.ly50.daoimpl;

import cn.lyscolar.ly50.dao.FindDao;
import cn.lyscolar.ly50.javabean.Reg;
import cn.lyscolar.ly50.utils.DBhelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author LeeSun
 */
public class FindpwdImpl implements FindDao {
    protected Connection conn;
    protected PreparedStatement pstmt;
    protected ResultSet rs;
    protected String  sql;
    @Override
    public Reg getAccountByEmail(String email) throws SQLException, Exception {
        Reg account = null;
        sql = "select username,id,PASSWORD,realname,email " +
                "from users where " +
                "email=?";
        conn = DBhelper.getConn();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            account = new Reg();
            account.setUsername(rs.getString("username"));
            account.setId(rs.getInt("id"));
            account.setPassword(rs.getString("PASSWORD"));
            account.setRealname(rs.getString("realname"));
            account.setEmail(rs.getString("email"));
            return account;
        }
        return account;
    }
}
