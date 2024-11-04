package lab4;

import java.util.Scanner;

public class Lab4 {
   
	private static CompanyDao compDao = new CompanyDao();
    
	public static void main(String[] args) {		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("부서명을 입력하시오: ");
		String deptName = scanner.next();
        System.out.println();
		
	    // Department dept = compDao.findDeptByName(deptName);
        // dept 객체의 필드 값 출력 (주의: dept 객체가 반환되었는지 확인 필요)
		
	    // List<Employee> empList = compDao.getAllEmpsInDept( /*부서번호*/ );
        // empList에 포함된 모든 Employee 객체들의 필드 값을 출력: 
        //   Employee 객체들을 하나씩 접근하기 위해 empList로부터 Iterator를 구해서 활용

		System.out.print("새 관리자 사번과 보직수당을 입력하시오: ");
		int managerNo  = scanner.nextInt();
		double commission = scanner.nextDouble();
        System.out.println();
		
		// 관리자 교체를 위한 DTO 객체 생성
	    // Employee oldMgr = compDao.replaceManagerOfDept( /* DTO 객체 전달 */ );	 
		      
        System.out.println("<기존 관리자>");     
		// 반환된 oldMgr 객체의 필드 값 출력 
        
		// Employee newMgr = compDao.findEmployee( /*새 관리자 사번*/ );
        
        System.out.println("<새 관리자>");         
	    // 반환된 newMgr 객체의 필드 값 출력 

		scanner.close();
	}
}