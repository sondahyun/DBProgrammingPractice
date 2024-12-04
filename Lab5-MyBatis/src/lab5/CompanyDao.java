package lab5;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import lab5.mapper.CompanyMapper;

public class CompanyDao {

	private SqlSessionFactory sqlSessionFactory;

	public CompanyDao() { // 생성자
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	public Department findDeptByName(String deptName) {
		/*
		 * 실습 #3의 printDeptInfo()를 변형. 부서정보 검색 후 Department DTO를 생성하고 검색 결과를 저장해서
		 * return함
		 */
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			return sqlSession.getMapper(CompanyMapper.class).selectDeptByName(deptName);
		} finally {
			sqlSession.close();
		}
	}

	public List<Employee> getAllEmpsInDept(int deptNo) {
		/*
		 * 실습 #3의 printAllEmpsInDept()를 변형. ArrayList<Employee> 타입 객체를 생성한 후, 검색된 각 사원
		 * 정보에 대해 Employee DTO를 생성 및 저장하고 List 객체에 추가함. List 객체를 반환함
		 */
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			return sqlSession.getMapper(CompanyMapper.class).selectEmpsByDeptNo(deptNo);
		} finally {
			sqlSession.close();
		}
	}

	public Department findDeptAndEmpsByDeptName(String deptName) {
		/*
		 * 실습 #3의 printDeptInfo()를 변형. 부서 및 사원 정보를 함께 검색한 후 Department와 List<Employee>
		 * 객체를 생성하고 검색 결과를 저장해서 return함 (Department에 List<Employee> 타입 property 추가 필요)
		 */

		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			return sqlSession.getMapper(CompanyMapper.class).selectDepAndEmpsByDeptName(deptName);
		} finally {
			sqlSession.close();
		}
	}

	public Employee replaceManagerOfDept(Appointment appo) {
		/*
		 * 실습 #3의 replaceManagerOfDept()와 유사하나, 매개변수 전달을 위해 Appointment DTO를 이용하고, 실행
		 * 결과로 기존 관리자 사원 정보를 포함하는 Employee DTO를 반환함
		 */
		return null;
	}

	public Employee findEmployee(int empNo) {
		/*
		 * 실습 #3의 printEmpInfo()를 변형 주어진 사번에 해당하는 사원 정보를 검색 후 Employee DTO를 생성 및 저장하고
		 * 반환함
		 */
		return null;
	}
}