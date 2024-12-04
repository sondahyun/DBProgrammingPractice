package lab3;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Lab3 {
	public Lab3() { // 생성자
		// JDBC 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	private static Connection getConnection() {
		String url = "jdbc:oracle:thin:@dblab.dongduk.ac.kr:1521/orclpdb";
		// String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
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
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		conn = getConnection();

		StringBuffer query = new StringBuffer();
		query.append("SELECT deptno, dname, manager, COUNT(empno) AS numOfEmps ");
		query.append("FROM EMP0979 JOIN DEPT0979 USING (deptno) ");
		query.append("WHERE dname = ? ");
		query.append("GROUP BY deptno, manager ");

		try {
			pStmt = conn.prepareStatement(query.toString());
			pStmt.setString(1, deptName);
			rs = pStmt.executeQuery();

			System.out.println("<부서정보>");
			if (rs.next()) {
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				int manager = rs.getInt("manager");
				int numOfEmps = rs.getInt("numOfEmps");

				System.out.println("부서번호: " + deptno);
				System.out.println("부서명: " + dname);
				System.out.println("관리자 사번: " + manager);
				System.out.println("소속 사원수: " + numOfEmps);
			}
			System.out.println();
			throw new DeptNotFoundException(deptName + "은 존재하지 않는 부서입니다.");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public static void printAllEmpsInDept(String deptName) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		conn = getConnection();

		StringBuffer query = new StringBuffer();
		query.append("SELECT empno, ename, job, sal, comm ");
		query.append("FROM EMP0979 JOIN DEPT0979 USING (deptno) ");
		query.append("WHERE dname = ? ");
		query.append("ORDER BY empno ");

		try {
			pStmt = conn.prepareStatement(query.toString());
			pStmt.setString(1, deptName);
			rs = pStmt.executeQuery();

			System.out.println("empno(사번)   ename(이름)   job(직무)   sal(급여)   comm(수당)");
			System.out.println();

			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				double sal = rs.getDouble("sal");
				double comm = rs.getInt("comm");

				System.out.println(empno + "  " + ename + "  " + job + "  " + sal + "  " + comm);
			}
			System.out.println();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public static int replaceManagerOfDept(String deptName, int newMgrNo, double newMgrComm)
			throws EmpNotFoundException {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		int oldMgrNo = 0;

		conn = getConnection();

		try {
			// 기존 부서 관리자 사번 조회 (SELECT문 실행)
			StringBuffer query = new StringBuffer();
			query.append("SELECT manager ");
			query.append("FROM EMP0979 JOIN DEPT0979 USING (deptno) ");
			query.append("WHERE dname = ? ");

			pStmt = conn.prepareStatement(query.toString());
			pStmt.setString(1, deptName);

			rs = pStmt.executeQuery();

			if (rs.next()) {
				oldMgrNo = rs.getInt("manager");
				// System.out.println("기존 관리자 사번: " + oldMgrNo);
			}

			pStmt.close();

			// 기존 관리자 사원의 이름에서 "(M)" 삭제 (UPDATE문 실행)
			StringBuffer update1 = new StringBuffer();
			update1.append("UPDATE EMP0979 ");
			update1.append("SET ename = REPLACE(ename, '(M)', '') ");
			update1.append("WHERE empno = ? ");

			pStmt = conn.prepareStatement(update1.toString());
			pStmt.setInt(1, oldMgrNo);

			pStmt.executeUpdate();

			pStmt.close();

			// 새 관리자 사원의 이름과 수당 변경 (UPDATE문 실행)
			StringBuffer update2 = new StringBuffer();
			update2.append("UPDATE EMP0979 ");
			update2.append("SET ename = ename || '(M)', comm = NVL(comm, 0) + ? ");
			update2.append("WHERE empno = ? ");

			pStmt = conn.prepareStatement(update2.toString());
			pStmt.setDouble(1, newMgrComm);
			pStmt.setInt(2, newMgrNo);

			pStmt.executeUpdate();

			// 부서의 관리자 변경 (UPDATE문 실행)
			StringBuffer update3 = new StringBuffer();
			update3.append("UPDATE DEPT0979 ");
			update3.append("SET manager = newMgrNo ");
			update3.append("WHERE dname = deptName ");

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return oldMgrNo;
	}

	public static void printEmpInfo(int empNo) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		conn = getConnection();

		StringBuffer query = new StringBuffer();
		query.append("SELECT empno, ename, job, hiredate, sal, comm, deptno ");
		query.append("FROM EMP0979 ");
		query.append("WHERE empNo = ? ");

		try {
			pStmt = conn.prepareStatement(query.toString());
			pStmt.setInt(1, empNo);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");

				// 참고: DB 검색 결과 중 DATE 타입의 컬럼 값을 읽어서 String 객체를 생성하는 방법
				Date sqlDate = rs.getDate("hiredate"); // import java.sql.Date 필요
				LocalDate localDate = sqlDate.toLocalDate(); // java.sql.Date --> java.time.LocalDate로 변환. import
																// java.time.LocalDate 필요
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 문자열 형식 지정. import
																							// java.time.format.DateTimeFormatter
																							// 필요
				String DateStr = localDate.format(formatter); // 변환 실행

				double sal = rs.getInt("sal");
				double comm = rs.getInt("comm");
				int deptno = rs.getInt("deptno");

				System.out.println(
						empno + " " + ename + " " + job + " " + DateStr + " " + sal + " " + comm + " " + deptno);

			}
		} catch (SQLException ex) {

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("부서명을 입력하시오: ");
		String deptName = scanner.next();
		System.out.println();

		try {
			// 입력된 부서명 이용 printDeptInfo 호출
			printDeptInfo(deptName);

			// 입력된 부서명 이용 printAllEmpsInDept 호출
			printAllEmpsInDept(deptName);

			System.out.print("새 관리자 사번과 보직수당을 입력하시오: ");
			int managerNo = scanner.nextInt();
			double commission = scanner.nextDouble();

			// 입력된 값들을 이용 replaceManagerOfDept 호출 (기존 관리자 사번 반환)
			int oldMgrNo = replaceManagerOfDept(deptName, managerNo, commission);

			System.out.println();
			System.out.println("<기존 관리자>");
			// 기존 관리자 사번에 대해 printEmpInfo 호출
			printEmpInfo(oldMgrNo);

			System.out.println("<새 관리자>");
			// 새 관리자 사번에 대해 printEmpInfo 호출
			printEmpInfo(managerNo);

		} catch (Exception ex) { // (DeptNotFoundException | EmpNotFoundException ex) {
			ex.printStackTrace();
		}
		scanner.close();
	}
}
