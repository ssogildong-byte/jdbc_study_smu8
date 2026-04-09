package com.snu8;

import java.sql.*;
import java.time.LocalDate;
import java.util.Locale;

public class L19EmpDetailRecord {
    public static void main(String[] args) {
        String sql="SELECT * FROM EMP WHERE empno=?";
        int empno=7902;
        L18EmpRecord emp=null;
        try (Connection conn= DriverManager.getConnection(Scott.URL, Scott.USERNAME, Scott.PASSWORD);
             PreparedStatement pst=conn.prepareStatement(sql);
        ){
            pst.setInt(1, empno);
            try (ResultSet rs=pst.executeQuery()){
                if(rs.next()){
                    emp=new L18EmpRecord(
                            rs.getInt("empno"),
                            rs.getString("ename"),
                            rs.getString("job"),
                            rs.getObject("mgr",Integer.class),
                            rs.getObject("hiredate", LocalDate.class) ,
                            rs.getObject("sal", Double.class),
                            rs.getObject("comm", Double.class),
                            rs.getObject("deptno", Integer.class),
                            null

                    );
                }
                System.out.println(emp);
                System.out.println(emp.ename());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
