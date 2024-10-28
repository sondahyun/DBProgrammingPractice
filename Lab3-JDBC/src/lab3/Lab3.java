package lab3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Lab3 {
    public Lab3() {     // 생성자
        // JDBC 드라이버 로딩
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");   
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }   
    }
    
    private static Connection getConnection() {
        String url = "jdbc:oracle:thin:@dblab.dongduk.ac.kr:1521/orclpdb";        
        //String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
        String user = "dbp2024";
        String passwd = "TIGER";

        // DBMS와의 연결 생성
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }    
        return conn;
    }
    
    public static void printDeptInfo(String deptName) throws DeptNotFoundException {
        Connection conn = null;
        
        conn = getConnection();
        
    }
    
    public static void printAllEmpsInDept(String deptName) {
        Connection conn = null;
        
        conn = getConnection();
        
    }
    
    public static int replaceManagerOfDept(String deptName, int newMgrNo, double newMgrComm) 
            throws EmpNotFoundException {
        Connection conn = null;
        int oldMgrNo = 0;

        conn = getConnection();

        // 기존 부서 관리자 사번 조회 (SELECT문 실행)
        
        // 기존 관리자 사원의 이름에서 "(M)" 삭제 (UPDATE문 실행)
        
        // 새 관리자 사원의 이름과 수당 변경 (UPDATE문 실행)
        
        // 부서의 관리자 변경 (UPDATE문 실행)
        
        return oldMgrNo; 
    }
    
    public static void printEmpInfo(int empNo) {           
        Connection conn = null;
        
        conn = getConnection();             

        // 참고: DB 검색 결과 중 DATE 타입의 컬럼 값을 읽어서 String 객체를 생성하는 방법
        /*  
            Date sqlDate = rs.getDate("...");   // import java.sql.Date 필요
            LocalDate localDate = sqlDate.toLocalDate();    // java.sql.Date --> java.time.LocalDate로 변환. import java.time.LocalDate 필요 
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");   // 문자열 형식 지정. import java.time.format.DateTimeFormatter 필요
            String DateStr = localDate.format(formatter);  // 변환 실행
        */  
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   
        
        System.out.print("부서명을 입력하시오: ");
        String deptName = scanner.next();
        System.out.println();
     
        try {
            // 입력된 부서명 이용 printDeptInfo 호출
            
            // 입력된 부서명 이용 printAllEmpsInDept 호출
    
            System.out.print("새 관리자 사번과 보직수당을 입력하시오: ");
            int managerNo  = scanner.nextInt();
            double commission = scanner.nextDouble();
                
            // 입력된 값들을 이용 replaceManagerOfDept 호출 (기존 관리자 사번 반환)
    
            System.out.println("<기존 관리자>");     
            // 기존 관리자 사번에 대해 printEmpInfo 호출      
    
            System.out.println("<새 관리자>");
            // 새 관리자 사번에 대해 printEmpInfo 호출      
            
        } catch (Exception ex) {  // (DeptNotFoundException | EmpNotFoundException ex) {
            ex.printStackTrace();
        }    
        scanner.close();
    }
}
