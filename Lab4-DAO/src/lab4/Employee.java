package lab4;

import java.sql.Date;

public class Employee {
    // 필드: 사번, 이름, 직무, 입사일, 월급, 수당, 소속부서명 
    // default 생성자와, 위 필드들에 대한 getter & setter method 정의
    // 위 필드들을 초기화하는 생성자를 정의해서 활용 가능 
    // 모든 필드 값들을 출력하기 위한 toString() 정의 (Source > Generate toString() 메뉴 이용)
	private int empNo;
	private String eName;
	private String job;
	private Date hireDate;
	private double sal;
	private double comm;
	private int deptNo;
	
	public Employee() {}		// 기본 생성자

	public Employee(int empNo, String eName, String job, Date hireDate, double sal, double comm, int deptNo) {
		super();
		this.empNo = empNo;
		this.eName = eName;
		this.job = job;
		this.hireDate = hireDate;
		this.sal = sal;
		this.comm = comm;
		this.deptNo = deptNo;
	}
	

	@Override
	public String toString() {
		String rslt = "Employee [empNo=" + empNo + ", eName=" + eName + ", job=" + job + ", hireDate=" + hireDate + ", sal="
				+ sal + ", comm=" + comm + ", deptNo=" + deptNo + "]";
		return rslt;
	}

	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}
	public double getComm() {
		return comm;
	}
	public void setComm(double comm) {
		this.comm = comm;
	}
	public int getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	
	
	
	
}
