package com.player.framework.orm;

import org.apache.ibatis.session.SqlSession;

public class Mapper {

	private Object object;

	private SqlSession sqlSession;

	public Mapper(Object object, SqlSession sqlSession) {
		this.object = object;
		this.sqlSession = sqlSession;
	}

	public Object getObject() {
		return this.object;
	}

	public void commit() {
		this.sqlSession.commit();
		this.sqlSession.close();
	}

	public void close() {
		this.sqlSession.close();
	}

}
