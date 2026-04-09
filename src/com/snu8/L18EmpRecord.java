package com.snu8;

import java.time.LocalDate;
//생성자 초기화 + toString + equals+get함수 자동생성

public record L18EmpRecord (
int empno,
    String ename,
    String job,
    Integer mgr,
LocalDate hiredate,
Double sal,
Double comm,
Integer deptno,
L17DeptRecord dept // n:1 일때 사원이 부서를 포함함
    ){
    @Override
    public String toString() {
        return "{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                ", dept=" + dept +
                "}\n";
    }
}

