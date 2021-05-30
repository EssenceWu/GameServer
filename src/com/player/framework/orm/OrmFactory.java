package com.player.framework.orm;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public enum OrmFactory {

	INSTANCE;

	private SqlSessionFactory sqlSessionFactory;

	public void initialize(String fileName) throws Exception {
		try {
			System.out.println("Loading mysql service...");
			InputStream inputStream = Resources.getResourceAsStream(fileName);
			this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			System.out.println("Loading mysql service successfully!");
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public SqlSession getSqlSession() {
		return this.sqlSessionFactory.openSession();
	}

}
