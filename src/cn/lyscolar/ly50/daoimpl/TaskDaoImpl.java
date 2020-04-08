package cn.lyscolar.ly50.daoimpl;

import cn.lyscolar.ly50.dao.TaskDao;
import cn.lyscolar.ly50.javabean.Task;
import cn.lyscolar.ly50.utils.DBhelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author LeeSun
 */
public class TaskDaoImpl implements TaskDao {
    protected Connection conn;
    protected PreparedStatement pstmt;
    protected ResultSet rs;
    protected String  sql;
    @Override
    public int inserTask(Task task) {
        int affectrow = 0;
        String sql="INSERT INTO task (name,userid,description,level,end) VALUES (?,?,?,?,?)";
        try {
            conn= DBhelper.getConn();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,task.getName());
            pstmt.setInt(2,task.getUserid());
            pstmt.setString(3,task.getDescription());
            pstmt.setString(4,task.getLevel());
            pstmt.setString(5,task.getEnd());

            affectrow = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return affectrow;
    }
}
