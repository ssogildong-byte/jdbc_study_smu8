package com.snu8;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDate;

public class L08EmpRead {
    public static void main(String[] args) {
        String sql = "select * from emp";
        try(Connection conn= DriverManager.getConnection(Scott.URL, Scott.USERNAME, Scott.PASSWORD);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql)
            ){
            StringBuilder sb=new StringBuilder(); //a다음 b삽입시 a, b, ab 3가지 지만 버퍼 사용시 ab 1개만 생성
            while(rs.next()){
                //잘못된 방식 (타입) -왜? / 복수의 방식
                // 기본형은 null 불가기 때문에 Null 데이터를 0으로 받는다.

             int empno=rs.getInt("empno");
             //int mgr=rs.getInt("mgr");
                Integer mgr=rs.getObject("mgr", Integer.class);
             //Object deptno=rs.getObject("deptno");
             Integer deptno=rs.getObject("deptno", Integer.class);
             //double sal=rs.getDouble("sal");
             Double sal=rs.getObject("sal", Double.class);
             Object comm=rs.getObject("comm"); //기존 int / double에서 object 변환시 null로 반환 변환하는 방법도 가능 벗 복잡
             String ename=rs.getString("ename");
             String job=rs.getString("job");
             Date hiredate=rs.getDate("hiredate");
        //   LocalDate hireday=rs.getObject("hireday", LocalDate.class);
             String row=empno+"|"+ename+"|"+job+"|"+hiredate+"|"+comm+"|"+sal+"|"+mgr+"|"+deptno+"\n"; //과 상사가 0 null 인 상태에서
             sb.append(row);


            }
            System.out.println(sb.toString());

        } //소괄호 오토 클로즈
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
