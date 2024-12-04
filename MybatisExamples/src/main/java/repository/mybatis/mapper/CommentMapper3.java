// xml, interface 병용 사용 (CommentMapper3과 같지만 annotation 없음) 
package repository.mybatis.mapper;

import java.util.List;
import java.util.Map;

import model.Comment;

public interface CommentMapper3 {
	Comment selectCommentByPrimaryKey(long commentNo);

	List<Comment> selectCommentByCondition(Map<String, Object> condition);

	int insertComment(Comment comment);

	int updateComment(Comment comment);

	int deleteComment(long commentNo);

	int deleteAllComments();
}
