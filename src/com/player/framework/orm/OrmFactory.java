package com.player.framework.orm;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.player.framework.util.ClassScanner;

public enum OrmFactory {

	INSTANCE;

	private Map<Class<?>, SqlSessionFactory> sqlSessionFactory = new HashMap<>();

	public void initialize(String fileName, String packageName) throws Exception {
		try {
			System.out.println("Loading mysql service...");
			Set<Class<?>> result = ClassScanner.getDeclaredAnnotations(packageName);
			for (Class<?> clazz : result) {
				InputStream inputStream = Resources.getResourceAsStream(fileName);
				SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,
						clazz.getSimpleName());
				this.sqlSessionFactory.put(clazz, sqlSessionFactory);
			}
			System.out.println("Loading mysql service[" + sqlSessionFactory.size() + "] successfully!");
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public Mapper getMapper(Class<?> clazz) {
		Annotation[] annotations = clazz.getDeclaredAnnotations();
		if (annotations.length == 0) {
			return null;
		}
		SqlSessionFactory sqlSessionFactory = this.sqlSessionFactory.get(annotations[0].annotationType());
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return new Mapper(sqlSession.getMapper(clazz), sqlSession);
	}

}
