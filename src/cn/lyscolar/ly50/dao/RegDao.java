package cn.lyscolar.ly50.dao;

import cn.lyscolar.ly50.javabean.Reg;

import java.sql.SQLException;

/**
 * @author LeeSun
 */
public interface RegDao {
    public int inserUsername(Reg reg);
    public int CheckName(String username);
    public int updateUser(Reg reg);



//    void findeamil(Reg dao);
}
