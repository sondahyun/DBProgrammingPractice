// xml, interface 병용 사용 (CommentMapper3과 같지만 annotation 없음) 
package lab5.mapper;

import java.util.List;

import lab5.Department;
import lab5.Employee;

public interface CompanyMapper {
	Department selectDeptByName(String deptName);

	List<Employee> selectEmpsByDeptNo(int deptNo);

	Department selectDepAndEmpsByDeptName(String deptName);
}
