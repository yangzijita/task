package cn.lyscolar.ly50.dao;

import cn.lyscolar.ly50.javabean.Reg;

import java.sql.SQLException;

/**
 * @author LeeSun
 */
public interface FindDao {
    public Reg getAccountByEmail(String email) throws SQLException, Exception;
}
