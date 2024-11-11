package lab4;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Lab4 {
   
	private static CompanyDao compDao = new CompanyDao();
    
	public static void main(String[] args) {		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("부서명을 입력하시오: ");
		String deptName = scanner.next();
        System.out.println();
		
	    Department dept = compDao.findDeptByName(deptName);
        // dept 객체의 필드 값 출력 (주의: dept 객체가 반환되었는지 확인 필요)
	    System.out.println("<부서정보>");
        System.out.println("부서번호: " + dept.getDeptNo());
        System.out.println("부서명: " + deptName);
        System.out.println("관리자사번: " + dept.getMgrNo());
        System.out.println("사원 수: " + dept.getNumOfEmps());
        System.out.println();
        
        
	   
        List<Employee> empList = compDao.getAllEmpsInDept(dept.getDeptNo());
	    
        // empList에 포함된 모든 Employee 객체들의 필드 값을 출력: 
        // Employee 객체들을 하나씩 접근하기 위해 empList로부터 Iterator 를 구해서 활용
        
        Iterator<Employee> iter = empList.iterator();
        while (iter.hasNext()) {     // 커서를 통해 결과 행들을 하나씩 fetch
        	Employee emp = iter.next();
            int empNo = emp.getEmpNo();
            String empName = emp.geteName();
            String job = emp.getJob();
            Date hiredate = emp.getHireDate();
            double sal = emp.getSal();
            double comm = emp.getComm();
            System.out.printf("%d %s %s %f %f\n", empNo, empName, job, sal, comm);
        }
        System.out.println();
        
		System.out.print("새 관리자 사번과 보직수당을 입력하시오: ");
		int managerNo  = scanner.nextInt();
		double commission = scanner.nextDouble();
        System.out.println();
		
        
		// 관리자 교체를 위한 DTO 객체 생성 (부서번호, 새 관리자 번호, 새관리자 수당) 
        Appointment appo = new Appointment(dept.getDeptNo(), managerNo, commission);
	    Employee oldMgr = compDao.replaceManagerOfDept(appo);	 
		  
        System.out.println("<기존 관리자>");     
		// 반환된 oldMgr 객체의 필드 값 출력 
        System.out.println(oldMgr.toString());
        System.out.println();
        
		Employee newMgr = compDao.findEmpInfo(managerNo);
        
        System.out.println("<새 관리자>");         
	    // 반환된 newMgr 객체의 필드 값 출력 
        System.out.println(newMgr.toString());
        
		scanner.close();
	}
}