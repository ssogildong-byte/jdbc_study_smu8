package com.snu8;
//jdbc 표준라이브러리 패키지

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class L01DataSource {
    public static void main(String[] args) {
        //자바로 데이터베이스에 접속 (접속객체, 데이터 소스)
       // System.out.println("안녕 자바");
        String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
        String username = "scott";
        String password = "tiger";
        try {
           // Class.forName("oracle.jdbc.OracleDriver"); //DriverManager 접속 때 찾아서 사용할 드라이버를 지정
            // url의 oracle:thin 을 보고 자동으로 driver 찾아서 사용
            Connection conn=DriverManager.getConnection(url,username,password);
            //DriverManager 드라이버 객체를 나중에 찾아서 사용하는 것 => 동적로딩
            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } //catch (ClassNotFoundException e) {
            //e.printStackTrace(); => 클래스를 직접 찾을때 필요
        }


    }

