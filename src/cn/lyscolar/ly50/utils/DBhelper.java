package cn.lyscolar.ly50.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 24794 on 2020/2/20.
 */
public class DBhelper {
        private  static  final String DRIVER="com.mysql.jdbc.Driver";
        private static final String URL="jdbc:mysql://localhost:3306/task_db?useUnicode=true&amp;characterEncoding=UTF-8";
        private static final String USERNAME="root";
        private static final String PASSWORD="654123";
    private static Connection conn=null;
    static {
        //注册驱动
        try {
            Class.forName(DRIVER);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
        //单例模式，返回数据库连接对象
        public static Connection getConn() throws SQLException{
            if(conn==null){
                conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);

            }
            return conn;
        }
    public static  void  main(String [] args){
        try {
            Connection conn=DBhelper.getConn();
            if (conn!=null){
                System.out.print("数据库连接成功");
            }else {
                System.out.print("数据库连接失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
