package com.snu8;

import java.sql.*;

public class L14DeptDetailDto {
    public static void main(String[] args) {
        String sql = "select * from dept where deptno = ?";
        //상세 조회는 무조건 pk or uk 를 사용해야합니다.
        L12DeptDto dept = null; // 조회한 내역이 없을 수 있기 때문에 null
        try (Connection conn = DriverManager.getConnection(Scott.URL, Scott.USERNAME, Scott.PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            int deptno = 60;
            ps.setInt(1, deptno);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { //무조건 단건의 조회결과가 나오기 때문에 while 대신 if
                    dept = new L12DeptDto();
                    dept.setDeptno(rs.getInt("deptno"));
                    dept.setDname(rs.getString("dname"));
                    dept.setLoc(rs.getString("loc"));

                }
            }
            System.out.println(dept);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}