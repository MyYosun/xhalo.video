package net.xhalo.video.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ObjectDao {
	@Autowired
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@SuppressWarnings("rawtypes")
	public Object selectOne(String methodName, List parameterList) {
		Object result = null;
		result = sqlSession.selectOne(methodName, parameterList);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public Object selectOne(String methodName, Map parameterMap) {
		Object result = null;
		result = sqlSession.selectOne(methodName, parameterMap);
		return result;
	}

	public Object selectOne(String methodName) {
		Object result = null;
		result = sqlSession.selectOne(methodName);
		return result;
	}

	public Object selectOne(String methodName, Object parameter) {
		Object result = null;
		result = sqlSession.selectOne(methodName, parameter);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public List selectList(String methodName, List parameterList) {
		List resultList = null;
		resultList = sqlSession.selectList(methodName, parameterList);
		return resultList;
	}

	@SuppressWarnings("rawtypes")
	public List selectList(String methodName, Map parameterMap) {
		List resultList = null;
		resultList = sqlSession.selectList(methodName, parameterMap);
		return resultList;
	}

	@SuppressWarnings("rawtypes")
	public List selectList(String methodName) {
		List resultList = null;
		resultList = sqlSession.selectList(methodName);
		return resultList;
	}

	@SuppressWarnings("rawtypes")
	public List selectList(String methodName, Object parameter) {
		List resultList = null;
		resultList = sqlSession.selectList(methodName, parameter);
		return resultList;
	}

	@SuppressWarnings("rawtypes")
	public Integer update(String methodName, List parameterList) {
		Integer result = -1;
		result = sqlSession.update(methodName, parameterList);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public Integer update(String methodName, Map parameterMap) {
		Integer result = -1;
		result = sqlSession.update(methodName, parameterMap);
		return result;
	}

	public Integer update(String methodName) {
		Integer result = -1;
		result = sqlSession.update(methodName);
		return result;
	}

	public Integer update(String methodName, Object parameter) {
		Integer result = -1;
		result = sqlSession.update(methodName, parameter);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public Integer delete(String methodName, List parameterList) {
		Integer result = -1;
		result = sqlSession.delete(methodName, parameterList);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public Integer delete(String methodName, Map parameterMap) {
		Integer result = -1;
		result = sqlSession.delete(methodName, parameterMap);
		return result;
	}

	public Integer delete(String methodName) {
		Integer result = -1;
		result = sqlSession.delete(methodName);
		return result;
	}

	public Integer delete(String methodName, Object parameter) {
		Integer result = -1;
		result = sqlSession.delete(methodName, parameter);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public Integer insert(String methodName, List parameterList) {
		Integer result = -1;
		result = sqlSession.insert(methodName, parameterList);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public Integer insert(String methodName, Map parameterMap) {
		Integer result = -1;
		result = sqlSession.insert(methodName, parameterMap);
		return result;
	}

	public Integer insert(String methodName) {
		Integer result = -1;
		result = sqlSession.insert(methodName);
		return result;
	}

	public Integer insert(String methodName, Object parameter) {
		Integer result = -1;
		result = sqlSession.insert(methodName, parameter);
		return result;
	}
}
