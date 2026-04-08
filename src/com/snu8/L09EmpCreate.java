package com.snu8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class L09EmpCreate {
    public static void main(String[] args) {
        //자바는 "" 문자열 라인개행을 지원하지 않음
        String sql="INSERT INTO EMP (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES (?,?,?,?,?,?,?,?)";
        try (
                Connection conn= DriverManager.getConnection(Scott.URL, Scott.USERNAME, Scott.PASSWORD);
                PreparedStatement ps=conn.prepareStatement(sql);
                ){
            ps.setInt(1,8888); //ORA-00001: 무결성 제약 조건(SCOTT.PK_EMP)에 위배됩니다 같은거 2번하면
            ps.setString(2,"test"); //ORA-12899: "SCOTT"."EMP"."ENAME" 열에 대한 값이 너무 큼(실제: 52, 최대값: 10)
            ps.setString(3,"직책");
           // ps.setInt(4, null);
            ps.setObject(4,null);
            ps.setString(5,"2026-04-02");
            ps.setDouble(6,7777.77);
            ps.setDouble(7, 77.77);
            ps.setInt(8,40); //ORA-02291: 무결성 제약조건(SCOTT.FK_DEPTNO)이 위배되었습니다- 부모 키가 없습니다
            int create=ps.executeUpdate();
            if(create>0) {
                System.out.println("사원 등록 성공");
            }//등록 실패는 거의 모두 오류가 발생
        } catch (SQLException e) {
            System.out.println("사원 등록 실패");
           e.printStackTrace();
        }

    }
}
