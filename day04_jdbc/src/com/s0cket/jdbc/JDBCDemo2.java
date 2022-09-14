package com.s0cket.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 增加一条记录
 */
public class JDBCDemo2 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 1.注册驱动
            // mysql5以后可以省略，自动注册,mysql8手动注册驱动如下
            //Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.定义sql
            String sql = "insert into account values(null,'王五',3000)";
            // 3.获取数据库连接Connection对象
            conn = DriverManager.getConnection("jdbc:mysql:///db4", "root", "44334433");
            // 4.获取执行sql的Statement对象
            stmt = conn.createStatement();
            // 5.执行sql
            int count = stmt.executeUpdate(sql);
            // 6.处理结果
            System.out.println(count);
            if(count > 0) {
                System.out.println("执行成功！");
            } else {
                System.out.println("执行失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 7.释放资源
            if(stmt != null ){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
