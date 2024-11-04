package lab4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;

public class CompanyDao {
	private JDBCUtil jdbcUtil = null;	// JDBCUtil 필드 선언
		
	public CompanyDao() {				// 생성자 
		jdbcUtil = new JDBCUtil();		// JDBCUtil 객체 생성
		
/*		// JDBCUtil 이용 시 불필요: DBCP 활용		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
*/
	}

/*	// JDBCUtil 이용 시 불필요: 내부적으로 connection 생성 및 사용 
	private Connection getConnection() {
		String url = "jdbc:oracle:thin:@dblab.dongduk.ac.kr:1521/orclpdb";		
		String user = "scott2";
		String passwd = "TIGER";

		// DBMS와의 연결 획득
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}	 
		return conn;
	}
*/
	
    public Department findDeptByName(String deptName) {
    	/* 실습 #3의 printDeptInfo()를 변형. 
    	 * 부서정보 검색 후 Department DTO를 생성하고 검색 결과를 저장해서 return함
    	 * (검색 결과가 없을 경우 null을 반환) 
    	 */
        StringBuffer query = new StringBuffer();
        
    	jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{deptName});	// JDBCUtil에 질의문과 파라미터 설정	
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			
			if (rs.next()) {		// 검색 결과 존재
				Department dept = new Department();
				
				// dept.setDeptNo(rs.getInt("deptno"));
				// dept.setMgrNo(rs.getInt("manager"));
				// dept.setNumOfEmps(rs.getInt("numOfEmps"));
				return dept;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 등 해제
		}
    	return null;
    } 

    public List<Employee> getAllEmpsInDept(int deptNo) {
    	/* 실습 #3의 printAllEmpsInDept()를 변형.
    	 * ArrayList<Employee> 타입 객체를 생성한 후, 검색된 각 사원 정보에 대해
    	 * Employee DTO를 생성 및 저장하고 List 객체에 추가함. List 객체를 반환함
    	 */
    	List<Employee> empList = new ArrayList<Employee>();    	
    	
    	return empList;
    }

    public Employee replaceManagerOfDept(Appointment appo) {
    	/* 실습 #3의 replaceManagerOfDept()와 유사하나, 매개변수 전달을 위해 Appointment
    	 * DTO를 이용하고, 실행 결과로 기존 관리자 사원 정보를 포함하는 Employee DTO를 반환함
    	 */    	
        try {
        	Employee oldMgr = new Employee();
        	
        	return oldMgr;
        } catch (Exception ex) {
        	jdbcUtil.rollback();	// 트랜잭션 rollback 실행
        	ex.printStackTrace();
        } finally {
            jdbcUtil.commit();		// 트랜잭션 commit 실행
            jdbcUtil.close();
        }
    	return null;
    }
    
    public Employee findEmpInfo(int empNo) {
    	/* 실습 #3의 printEmpInfo()를 변형
    	 * 주어진 사번에 해당하는 사원 정보를 검색 후 Employee DTO를 생성 및 저장하고 반환함
         * (검색 결과가 없을 경우 null을 반환) 
         */ 
    	
    	return null;        
    }
} 

