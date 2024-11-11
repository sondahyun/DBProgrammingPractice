package lab4;

public class Appointment {
	private int deptNo;
	private int newManagerNo;
	private double newComm;
	
	public Appointment() {}		// 기본 생성자

	public Appointment(int deptNo, int newManagerNo, double newComm) {
		super();
		this.deptNo = deptNo;
		this.newManagerNo = newManagerNo;
		this.newComm = newComm;
	}

	@Override
	public String toString() {
		return "Appointment [deptNo=" + deptNo + ", newManagerNo=" + newManagerNo + ", newComm=" + newComm + "]";
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public int getNewManagerNo() {
		return newManagerNo;
	}

	public void setNewManagerNo(int newManagerNo) {
		this.newManagerNo = newManagerNo;
	}

	public double getNewComm() {
		return newComm;
	}

	public void setNewComm(double newComm) {
		this.newComm = newComm;
	}
	
	
}
