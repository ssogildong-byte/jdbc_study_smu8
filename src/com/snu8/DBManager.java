package com.snu8;

import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private DBManager() {}; //외부에서 객체생성을 막는다.
    private static DBManager INSTANCE = new DBManager();
    public static DBManager getInstance() {
        return INSTANCE;
    } //결과적으로 static 필요 쓰려면

    private Connection conn;
    public Connection getConnection() throws SQLException {
        conn = java.sql.DriverManager.getConnection(Scott.URL, Scott.USERNAME, Scott.PASSWORD);
        return conn;
    }
}
class A{}
//테스트만 하는 어플(지워도 상관없음)
class DBManagerTestApp{
    public static void main(String[] args) {

        // DBManager dbManager=new DBManager(); 안되는거 확인~
        A a=new A();
        A a2=new A();
        System.out.println(a); // com.snu8.A@52cc8049
        System.out.println(a2); // com.snu8.A@5b6f7412
        System.out.println(a==a2); //false 두개 다른객체

        DBManager db1=DBManager.getInstance();
        DBManager db2=DBManager.getInstance();
        System.out.println(db1); //com.snu8.DBManager@312b1dae
        System.out.println(db2); //com.snu8.DBManager@312b1dae
        System.out.println(db1==db2); //true
    }
}
