package com.snu8;

import oracle.jdbc.proxy.annotation.Pre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class L07DeleteDeptSetNullEmp {
    public static void main(String[] args) {
        //자식이 참조할 때 ON Delete 옵션을 주면 부모를 삭제할때 문제가 없지만
        //옵션을 주지 않으면 자식의 참조키를 null 또는 삭제해야합니다.
        String empSql="UPDATE EMP SET deptno=NULL WHERE deptno=?"; //? 무엇인것~
        //사원이 해당부서를 참조하지 않게 바꾸는 쿼리
        String deptSql="DELETE FROM DEPT WHERE deptno=?";
        int deptno=30;
        try(Connection conn= DriverManager.getConnection(Scott.URL, Scott.USERNAME, Scott.PASSWORD);
            PreparedStatement psEmp=conn.prepareStatement(empSql);
            PreparedStatement psDept=conn.prepareStatement(deptSql);
        ) {
            conn.setAutoCommit(false);
            conn.commit();

            psEmp.setInt(1, deptno);
            int emptUpdate=psEmp.executeUpdate(); // 자식이 참조하는 부서를 모두 null로 변경
            System.out.println("자식 변경 수 : "+emptUpdate);

            psDept.setInt(1, deptno);
            int deptUpdate=psDept.executeUpdate();
            System.out.println("부서 삭제 수 :"+deptUpdate);


            conn.rollback(); //  삭제 및 변경 취소
        } catch (SQLException e) {
            //ORA-02292: 무결성 제약조건(SCOTT.FK_EMP_PAY)이 위배되었습니다- 자식 레코드가 발견되었습니다
          e.printStackTrace();
        }

    }
}
