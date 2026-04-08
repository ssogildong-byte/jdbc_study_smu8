package com.snu8;

import java.sql.*;

public class L06DeleteDept {
    public static void main(String[] args) {
        String sql="DELETE FROM DEPT WHERE deptno=?";
        //80번 부서 삭제, 30번 부서 삭제(안됨 이유???)
        try (Connection conn= DriverManager.getConnection(Scott.URL,Scott.USERNAME, Scott.PASSWORD);
             PreparedStatement pstmt=conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1,80); //종로, 교육
            int delete= pstmt.executeUpdate();
            if(delete==0){
                System.out.println("존재하지 않는 부서 입니다.");
            }else{
                System.out.println("삭제 성공");
            }
        } catch (SQLException e) {
            System.out.println("삭제 실패");
            e.printStackTrace();
        }
    }
}
