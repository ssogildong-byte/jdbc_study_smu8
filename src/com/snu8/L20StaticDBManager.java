package com.snu8;

import java.sql.*;


public class L20StaticDBManager {
    //접속 객체를 반환하는 함수 생성 (static, 객체를 처음에 만들고 공유함)
    int a=10; //객체일때만 데이터
    static int b=20; //객체가 아니어도 데이터로 존재함

    private static String url="jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static String user="scott";
    private static String pw="tiger";
//  접속시 오류를 동반 -> try-catch 를 강제함(필드에서 불가)
   // public static Connection conn=DriverManager.getConnection(url,user,pw);

   //일반적인 객체(10,true,dto...)는 static으로 사용해도 상관 없지만
    //접속객체는 상태가 계속 변하기 때문에 static으로 적합하지 않다!!
    //접속 객체는 private 으로 막고 getConnection() 함수를 만들어서 새로 접속을 맺어서 반환 (캡슐화)
    public static Connection conn;

    static { // 생성자 처럼 jvm 실행시 static(클래스멤버)를 처리하는 블럭
        try {
            conn = DriverManager.getConnection(url,user,pw);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //쓰다보면 문제가 있음

    /// /////////////////////////사용권장하지 않는 코드////////////////////
    /// /////////////////////////사용권장하는 코드 아래  ///////////////////////


    private static Connection connection;
    //호출할때마다 새로 커넥션을 만들어서 반환!
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,pw);
    }

    // 더 좋은 구조가 있음. 싱글톤


    public static void main(String[] args) {
        //System.out.println(a); // static 이 아닌 필드는 아직 데이터가 아님
        System.out.println(b);//jvm이 시작할때 데이터로 만들어서 사용가능
        System.out.println(new L20StaticDBManager().a);

        try(Connection conn=L20StaticDBManager.getConnection();
        Statement st=conn.createStatement();
        ){ //오토 클로즈 없이 내부로 넣어서 실행 가능 but 이 정보를 세션에 남기기 때문에  무조건 close하고 넘어가야 정보가 안남음
            int update=st.executeUpdate("UPDATE EMP SET COMM=8888 WHERE EMPNO=7902");
            if(update>0){
                System.out.println("포드씨 커미션 888 성공");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 다 쓰고 나면 close 하게 되어 잇는데

        // 다른곳에서 다시 conn 호출 후 사용 -> 닫혀있는 상태 // ORA-17008: 접속을 해제했습니다.
        try(Connection conn=L20StaticDBManager.getConnection();
        Statement st=conn.createStatement()){ //오류

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
