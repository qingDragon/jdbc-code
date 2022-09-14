package com.s0cket.jdbc;

import com.s0cket.domain.Emp;
import com.s0cket.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义一个方法，查询emp表的数据，并将其封装为对象，然后装载集合，返回。
 */
public class JDBCDemo5 {
    public static void main(String[] args) {
        List<Emp> list = new JDBCDemo5().findAll2();
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

    /**
     * 查询所有emp对象
     * @return
     */
    public List<Emp> findAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Emp> list = null;
        try {
            // 1.注册驱动
            //Class.forName("com.mysql.jdbc.Driver");
            // 2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql:///db3","root","44334433");
            // 3.定义sql
            String sql = "select * from emp";
            // 4.获取执行sql的对象
            stmt = conn.createStatement();
            // 5.执行sql
            rs = stmt.executeQuery(sql);
            // 6.遍历结果集，封装对象，装载集合
            Emp emp = null;
            list = new ArrayList<>();
            while (rs.next()){
                // 获取数据
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");// 这个Date是emp类中Date类的子类，父类引用可以接受子类对象
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");
                // 创建emp对象
                emp = new Emp();
                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);

                // 装载集合
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    /**
     * 使用JDBCUtils，查询所有emp对象
     * @return
     */
    public List<Emp> findAll2() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Emp> list = null;
        try {
            // 1.注册驱动
            //Class.forName("com.mysql.jdbc.Driver");
            // 2.获取连接
            //conn = DriverManager.getConnection("jdbc:mysql:///db3","root","44334433");
            conn = JDBCUtils.getConnection();
            // 3.定义sql
            String sql = "select * from emp";
            // 4.获取执行sql的对象
            stmt = conn.createStatement();
            // 5.执行sql
            rs = stmt.executeQuery(sql);
            // 6.遍历结果集，封装对象，装载集合
            Emp emp = null;
            list = new ArrayList<>();
            while (rs.next()){
                // 获取数据
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");// 这个Date是emp类中Date类的子类，父类引用可以接受子类对象
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");
                // 创建emp对象
                emp = new Emp();
                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);

                // 装载集合
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs,stmt,conn);
        }
        return list;
    }
}
