package com.snu8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class L10EmpUpdate {
    public static void main(String[] args) {
        //일반적으로 수정은 한명의 사원에 대한 수정을 한다. (PK는 절대 바꾸지 않는다.)
        String sql= """
                UPDATE EMP SET 
                ename=?,job=?,
                sal=?,comm=?,
                hiredate=?,mgr=?,deptno=? 
                WHERE empno=?""";
    try (Connection conn= DriverManager.getConnection(Scott.URL, Scott.USERNAME, Scott.PASSWORD);
         PreparedStatement ps=conn.prepareStatement(sql);
    ){
//7777 사원 수정
        ps.setString(1, "수정이");
        ps.setString(2, "tester");
        ps.setDouble(3,9999.99);
        ps.setObject(4, null);
        ps.setString(5,"2025.04.01"); //ORA-01861: 리터럴이 형식 문자열과 일치하지 않음
        ps.setInt(6,7839); //king
        ps.setInt(7,20);
        ps.setInt(8,7777);
        int update=ps.executeUpdate();
        if(update==0){
            System.out.println("존재하지 않는 사원입니다.");
        }else{
            System.out.println("수정 성공");
        }
    } catch (SQLException e) {
        //무결성 오류
        System.out.println("수정 실패");
        e.printStackTrace();
    }
    }
}
