package com.snu8;

import java.sql.*;

public class L03PreparedStatement {
    public static void main(String[] args) {
        //10번 부서의 사원 조회 (사번, 이름, 직책)
        String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
       String user="scott";
        String pw="tiger";
        String sql="SELECT EMPNO 사번,ENAME 이름,JOB 직책 FROM EMP WHERE deptno=?";
        //db 접속 객체는 연결성이 있기 때문에 항상 다사용하면(**) 종료해야한다!
        try(
                Connection conn = DriverManager.getConnection(url,user,pw);
//                Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
             PreparedStatement pstmt = conn.prepareStatement(sql);

        ){
            pstmt.setInt(1,30);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                    int empno = rs.getInt("사번");
                    String ename = rs.getString("이름");
                    String job = rs.getString("직책");
            System.out.printf("%d | %s | %s\n", empno, ename, job);
        }
        }catch(SQLException e){
           e.printStackTrace();
        }




    }
}
