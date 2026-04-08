package com.snu8;

import java.sql.*;

public class L06DeleteDept {
    public static void main(String[] args) {
        String sql="DELETE FROM DEPT WHERE deptno=?";
        //80번 부서 삭제, 30번 부서 삭제(안됨 이유???)
        try (Connection conn= DriverManager.getConnection(Scott.URL,Scott.USERNAME, Scott.PASSWORD);
             PreparedStatement pstmt=conn.prepareStatement(sql);
        ) {
            //pstmt.setInt(1,80); //종로, 교육
            pstmt.setInt(1,30); //종로, 교육
            int delete= pstmt.executeUpdate();
            if(delete==0){
                System.out.println("존재하지 않는 부서 입니다.");
            }else{
                System.out.println("삭제 성공");
            }
        } catch (SQLException e) {
            System.out.println("삭제 실패");
            // ORA-02292: 무결성 제약조건(SCOTT.FK_DEPTNO)이 위배되었습니다- 자식 레코드가 발견되었습니다(on delete set null / cascade 옵션을 줘야지 지워짐.)
            //부서를 참조하는 자식의 부서번호를 Null로 바꾸거나 자식을 모두 삭제하지 않으면 부서도 삭제될 수 없다. (참조의 무결성)
            e.printStackTrace();
        }
    }
}
