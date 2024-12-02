package lab5;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;

import util.JDBCUtil;

public class CompanyDao {

    private JDBCUtil jdbcUtil = null;   // JDBCUtil 필드 선언
        
    public CompanyDao() {               // 생성자 
        jdbcUtil = new JDBCUtil();      // JDBCUtil 객체 생성
    }
    
    public Department findDeptByName(String deptName) {
        /* 실습 #3의 printDeptInfo()를 변형. 
         * 부서정보 검색 후 Department DTO를 생성하고 검색 결과를 저장해서 return함 
         */
        StringBuffer query = new StringBuffer();
        query.append("SELECT deptno, manager, COUNT(empno) AS numOfEmps ");
        query.append("FROM EMP0000 JOIN DEPT0000 USING (deptno) ");
        query.append("WHERE dname = ? ");
        query.append("GROUP BY deptno, manager");
        
        return null;
    } 
    
    public List<Employee> getAllEmpsInDept(int deptNo) {
        /* 실습 #3의 printAllEmpsInDept()를 변형.
         * ArrayList<Employee> 타입 객체를 생성한 후, 검색된 각 사원 정보에 대해
         * Employee DTO를 생성 및 저장하고 List 객체에 추가함. List 객체를 반환함
         */    
        StringBuffer query = new StringBuffer();
        query.append("SELECT empno, ename, job, sal, comm ");
        query.append("FROM EMP0000 WHERE deptno = ?");                
        
        return null;
    }

    public Department findDeptAndEmpsByDeptName(String deptName) {
        /* 실습 #3의 printDeptInfo()를 변형. 
         * 부서 및 사원 정보를 함께 검색한 후 Department와 List<Employee> 객체를 생성하고 검색 결과를 저장해서 return함 
         * (Department에 List<Employee> 타입 property 추가 필요)
         */
        return null;       
    } 
    
    public Employee replaceManagerOfDept(Appointment appo) {
        /* 실습 #3의 replaceManagerOfDept()와 유사하나, 매개변수 전달을 위해 Appointment
         * DTO를 이용하고, 실행 결과로 기존 관리자 사원 정보를 포함하는 Employee DTO를 반환함
         */             
        return null;
    }
    
    public Employee findEmployee(int empNo) {
        /* 실습 #3의 printEmpInfo()를 변형
         * 주어진 사번에 해당하는 사원 정보를 검색 후 Employee DTO를 생성 및 저장하고 반환함
         */ 
        return null;        
    }
} 