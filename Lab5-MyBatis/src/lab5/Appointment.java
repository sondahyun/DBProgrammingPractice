package lab5;

import java.io.Serializable;

public class Appointment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int deptNo;
	private int mgrNo;
	private double comm;

	public Appointment() {

	}

	public Appointment(int deptNo, int mgrNo, double comm) {
		super();
		this.deptNo = deptNo;
		this.mgrNo = mgrNo;
		this.comm = comm;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public int getMgrNo() {
		return mgrNo;
	}

	public void setMgrNo(int mgrNo) {
		this.mgrNo = mgrNo;
	}

	public double getComm() {
		return comm;
	}

	public void setComm(double comm) {
		this.comm = comm;
	}
}
