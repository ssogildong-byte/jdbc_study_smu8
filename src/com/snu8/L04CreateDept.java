package com.snu8;

import java.sql.*;

public class L04CreateDept {
    public static void main(String[] args) {
        String url="jdbc:oracle:thin:@//localhost:1521/XEPDB1";
        String username="scott";
        String password="tiger";
    String sql="INSERT INTO DEPT (deptno,dname,loc) VALUES (?,?,?)";
        try (Connection conn= DriverManager.getConnection(url,username,password);
        PreparedStatement pstmt= conn.prepareStatement(sql);
        )
        {
            // conn.setAutoCommit(false);
            pstmt.setInt(1,80);
            pstmt.setString(2,"교육");
            pstmt.setString(3,"종로구");
            int update=pstmt.executeUpdate();
        System.out.println("등록 성공"+update);
        //conn.commit(); auto commit 을 하고 있음
//            if(update>0){
//                System.out.println("등록성공");
//            }else{
//                System.out.println("등록실패");
//            }
        //dml은 실행시 정수가 반환 (몇개 성공) Or 오류
        //실패시 0 보다 오류가 더 많이 뜬다.
//            try(ResultSet rs=pstmt.executeQuery()){
//                while (rs.next())
//            }



    } catch (SQLException e) {
            System.out.println("등록 성공");
            e.printStackTrace();
            //log4j 로그라이브러리 (로그를 파일로 출력 및 보관)
            //무결성 제약 조건 위배 오류
            // 이 열에 대해 지정된 전제 자릿수 보다 오버됩니다.

        }

    }
}
