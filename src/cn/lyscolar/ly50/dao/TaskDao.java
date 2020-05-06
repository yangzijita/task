package cn.lyscolar.ly50.dao;

import cn.lyscolar.ly50.javabean.Reg;
import cn.lyscolar.ly50.javabean.Task;

import java.sql.SQLException;
import java.util.List;

/**
 * @author LeeSun
 */
public interface TaskDao {
    public int inserTask(Task task);
    List<Task> selectByid(int id) throws SQLException;
}
