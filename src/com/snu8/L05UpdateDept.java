package com.snu8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class L05UpdateDept {
    public static void main(String[] args) {
        String url="jdbc:oracle:thin:@//localhost:1521/XEPDB1";
        String username="scott";
        String password="tiger";
        //pk는 수정을 권장하지 않음
        String sql="UPDATE DEPT SET dname=?, loc=? WHERE deptno=?";
        //90번 부서를 (가산동, 빅데이터) => 강남, Bigdata 로 수정

        try(Connection conn= DriverManager.getConnection(url, username, password);
            PreparedStatement pstmt=conn.prepareStatement(sql);
        ) {
            pstmt.setString(1,"BigData"); // 작은따옴표로 나옴
            pstmt.setString(2,"강남");
            pstmt.setInt(3,90);
            int update=pstmt.executeUpdate();
            if(update>0) {
                System.out.println("수정성공 :" + update);
            }else{
                System.out.println("존재하지 않는 부서 입니다.");
            }

        } catch (SQLException e) {
            System.out.println("수정실패");
            e.printStackTrace();

            //ORA-12899 : 열에 대한 값이 너무 큼 (실제 : 58, 최대값 : 14)
        }


    }
}
