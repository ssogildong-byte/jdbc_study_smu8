package com.snu8;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class L21DBManager {
    //싱글톤으로 구현하는 db 커넥션을 반환하는 DBManager
    //1. 객체를 외부에서 생성하지 못하게 막기 private 생성자
    //2. 내부에서 객체를 만들어서 반환 (캡슐화)
    //3. 참조할 함수를 static이 아니게 생성
    private L21DBManager(){} //1 외부 출입을 봉쇄
    private static L21DBManager INSTANCE=new L21DBManager(); //내부에서 딱 하나만 미리 만들게 : 인스턴스 생성
    public static L21DBManager getInstance(){
        return INSTANCE;
    } //내부에서만 만들어진 객체를 getInstance로 불러와서 쓰겠다. 유일한 통로 이 메서드만 인스턴스 사용가능 (외부에서는 절대 객체를 생성할 수 없음)
    //=>> 결과적으로 메모리를 아끼고, DB연결 통로를 단 하나로 관리할 수 있게 됨 -- 요기 위 까지 싱클톤

    private Connection conn;
    public Connection getConnection () throws SQLException {
        String url="jdbc:oracle:thin:@//localhost:1521/XEPDB1";
        String user="scott";
        String password="tiger";
        return DriverManager.getConnection(url,user,password);
    } //static 없으면 인스턴스로만 불러 올 수 있음 static은 아무데서나 호출가능
}

class Test{
    public static void main(String[] args) {
//        Connection conn = L21DBManager.getConnection();
//        //객세 생성 없이 Connection을 반환받으려고 static을 작성했는데 다른 곳에서 아직 객체 생성이 가능한 상태
//        //-> 객체를 한번만 생성할 수 있게 만드는 것 -> 싱글톤 디자인 패터
//        //디자인 패턴 : 어떤 도구 없이 구조적인 것으로 기능을 구현 (객체를 한번만 만드는 기능) - 일종의 구조와 약속
//
//        Connection conn2 = new L21DBManager().getConnection(); 매번 객체 생성 가능
        //db 접속기가 1000개 생기면 컴퓨터 메모리 못견뎌

        //싱글톤으로 생성한 객체 호출
        L21DBManager dbManager=L21DBManager.getInstance();
        try {
            Connection conn=dbManager.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}