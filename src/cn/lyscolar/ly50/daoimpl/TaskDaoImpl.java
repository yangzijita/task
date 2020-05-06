package cn.lyscolar.ly50.daoimpl;

import cn.lyscolar.ly50.dao.TaskDao;
import cn.lyscolar.ly50.javabean.Reg;
import cn.lyscolar.ly50.javabean.Task;
import cn.lyscolar.ly50.utils.DBhelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List selectByid(int id) throws SQLException {
        List<Task> tasklist = new ArrayList<Task>();
        sql = "select * from task where userid=?";

        conn=DBhelper.getConn();
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        rs=pstmt.executeQuery();
        if (rs!=null){
            while (rs.next()){
                Task task1=new Task();
                task1.setId(rs.getInt("id"));
                task1.setUserid(rs.getInt("userid"));
                task1.setName(rs.getString("name"));
                task1.setDescription(rs.getString("description"));
                task1.setLevel(rs.getString("level"));
                task1.setDue(rs.getString("due"));
                task1.setStatus(rs.getInt("status"));
                task1.setEnd(rs.getString("end"));
                tasklist.add(task1);
            }
        }
        return tasklist;
    }
}
