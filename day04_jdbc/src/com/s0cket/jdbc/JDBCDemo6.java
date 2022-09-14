package com.s0cket.jdbc;

import com.s0cket.util.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 练习：
 *      需求：1、通过键盘输入用户名和密码
 *           2、判断用户是否登录成功
 */
public class JDBCDemo6 {

    public static void main(String[] args) {
        // 键盘录入
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();
        // 调用login方法
        boolean flag = new JDBCDemo6().login(username, password);
        // 判断结果
        if(flag) {
            System.out.println("登录成功！");
        } else {
            System.out.println("用户名或密码错误！");
        }
    }
    /**
     * 登录方法
     */
    public boolean login(String username, String password) {
        if(username == null || password == null){
            return false;
        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        // 连接数据库判断是否成功
        try {
            conn = JDBCUtils.getConnection();
            // 定义sql
            String sql = "select * from user where username = '" + username + "' and password = '" + password +"'";
            // 获取执行sql的对象
            stmt = conn.createStatement();
            // 执行查询
            rs = stmt.executeQuery(sql);
            // 判断，如果有下一行则返回true
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stmt, conn);
        }
        return false;
    }
}
