package com.snu8;

import java.sql.*;

public class L02Select {
    public static void main(String[] args)throws Exception{
        String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
        String username = "scott"; //db는 대소문자 구분이 없는데 jdbc를 사용할때 대소문자 구분을 함
        String password = "tiger";
        String sql = "select * from DEPT";
        Connection conn = null; //데이터소스
        Statement stmt = null; //sql실행객체
        ResultSet rs = null; // table의 데이터 (select 를 실행한 결과)
//        try {
//            conn = DriverManager.getConnection(url, username, password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {//통신 객체는 종료시키지 않으면 통신하는 경로를 계속 열어둔다.
//                if (null != conn) {rs.close();}
//                if (null != conn) {stmt.close();}
//                if (null != conn) {conn.close();}
//            } catch (Exception e) {}
//        } throws로
        conn = DriverManager.getConnection(url, username, password);
        System.out.println(conn);
        //Statement 접속객체가 반환(준다)하는 쿼리 실행객체
        stmt=conn.createStatement();
        rs=stmt.executeQuery(sql); //select, executeUpdate(dml)
        //select 의 실행결과는 테이블로 테이블의 데이터 타입은 ResultSet(이터레이터로 사용) 이다.
        while(rs.next()){ //부서가 4개 있으니 4번 이동
            String id = rs.getString("deptno");
            String name = rs.getString("dname");
            String loc = rs.getString("loc");
            System.out.println(id+" | "+name+" | "+loc);

        }

    }
}
