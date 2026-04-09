package com.snu8;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class L13DeptReadDto {
    public static void main(String[] args) {
        String sql = "SELECT * FROM DEPT";
        List<L12DeptDto> deptList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Scott.URL, Scott.USERNAME, Scott.PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                L12DeptDto deptDto = new L12DeptDto(); //한줄이 있다는 것은 deptDto 객체가 한개 있다는 의미
                deptDto.setDeptno(rs.getInt("deptno"));
                deptDto.setDname(rs.getString("dname"));
                deptDto.setLoc(rs.getString("loc"));
                deptList.add(deptDto);

            }
            System.out.println(deptList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}