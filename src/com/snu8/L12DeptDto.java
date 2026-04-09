package com.snu8;
//Java Beans > DTO 하위
//POJO (PLAIN OLD JAVA OBJECT)
public class L12DeptDto {

        //DATa Transfer Object => DTO 대신 record 사용가능 더 편한데 개발자 안씀
        // dto : 데이터를 저장(캡슐화, Beans)하고 전송하는 객체
        //DEPTNO NUBER(2) PK(not null)
        //Dname Varchar(14)
        //LOC Varchar(13)
        private int deptno;
        private String dname;
        private String loc;
        //예) DEPT_NAME -> private String deptName;
    //자바는 대소문자를 구분하고 변수나 필드명을 카멜표기법을 사용한다.
    //대부분의 데이터 베이스는 필드명을 대소문자 구분 없이 사용하기 때문에 상수 표기법

    public int getDeptno(){
        return deptno;
    }
    //set을 호출할때는 대부분 저장 수정 삭제 시 사용
    //유효성 검사 (데이터가 유효한지 검사, 무결성 검사) -> 캡슐화를 해서 가능(Beans 클래스에서 사용)
    /*
    private void setDeptno(int deptno){
       // if(deptno>99) return;
        if(deptno>99) throw new IllegalArgumentException("부서 번호는 100미만입니다.");
        this.deptno = deptno;
    }
        */

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                "}\n";
    }
}

