package com.snu8;
class Dept{
 //private final int deptno;
 private int deptno;
 // private final String dname;
 private String dname;
 // private final String loc;
 private String loc;
 public Dept(int deptno, String dname, String loc) {
     this.deptno=deptno;
     this.dname=dname;
     this.loc=loc;// 초기값이 없는 상수는 생성자 호출시 변경 가능
 }

    public int getDeptno() {
        return deptno;
    }

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
        return "Dept{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
//상수는 생성 이후에는 변경 불가 final 이면 변경불가
//public void setDeptno(int deptno){
//    this.deptno=deptno;
//}
}

public record L17DeptRecord (
      int deptno, // 매개 변수를 선언하면 레코드 필드로 private final 로 생성됨
      String dname,
      String loc
){ //아무것도 안슴
}

class TestApp{ //record 와 dto 생성의 차이를 비교하는 테스트 앱
    public static void main(String[] args) {
        //77번 부서, bigdata, 서울
        L17DeptRecord deptRecord = new L17DeptRecord(77,"bigdata","서울");
        System.out.println(deptRecord); //레코드가 자동으로 생성하는 get 함수 아무것도 안썼는데 재정의 됨
        System.out.println(deptRecord.dname());
        System.out.println(deptRecord.loc());
        //deptRecord.dname()="ddd";
        //레코드는 파이널로만 가능해서 값을 중간에 변경 불가 (final이기 때문,)
        //dto는 보통 필드를 final로 정의하지 않기 때문에 set 함수로 변경

        Dept deptDto = new Dept(77,"bigdata", "서울");
        System.out.println(deptDto); //com.snu8.Dept@306a30c7 주소 재정의
//        System.out.println(deptRecord.dname()); 안됨 Private 해
//        System.out.println(deptRecord.loc());
        System.out.println(deptDto.getDname());
        deptDto.setDname("빅데이터");
        System.out.println(deptDto.getDname());

    }
}