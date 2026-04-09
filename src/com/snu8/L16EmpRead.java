package com.snu8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class L16EmpRead {
    public static void main(String[] args) {

        String sql = "select * from EMP where deptno = ?";
        List<L15EmpDto> empList =null;
        try(Connection conn= DriverManager.getConnection(Scott.URL,Scott.USERNAME,Scott.PASSWORD);
            PreparedStatement ps= conn.prepareStatement(sql);
        ){int deptno=20;
            ps.setInt(1, deptno);
            try(ResultSet rs=ps.executeQuery()){
            empList=new ArrayList<>();
            while (rs.next()){
                L15EmpDto emp=new L15EmpDto();
                emp.setEmpno(rs.getInt("empno"));
                emp.setEname(rs.getString("ename"));
                emp.setJob(rs.getString("job"));
                emp.setHiredate(rs.getObject("hiredate", LocalDate.class));
                emp.setMgr(rs.getObject("mgr",Integer.class));
                emp.setSal(rs.getObject("sal",Double.class));
                emp.setComm(rs.getObject("comm",Double.class));
                emp.setDeptno(rs.getObject("deptno",Integer.class));
                empList.add(emp);


            }
            }
            System.out.println(empList);
        } catch (Exception e) {
            e.printStackTrace();
        //ORA-17006 : 열 이름이 부적합합니다. 필드이름이 잘못되면 뜨는 오류

        }

    }

}
