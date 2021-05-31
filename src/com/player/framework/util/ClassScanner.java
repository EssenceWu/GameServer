package com.player.framework.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassScanner {

	private static Predicate<Class<?>> EMPTY_FILTER = clazz -> true;

	public static Set<Class<?>> getDeclaredClass(String packageName) throws Exception {
		return getClass(packageName, EMPTY_FILTER);
	}

	public static Set<Class<?>> getDeclaredSubClass(String packageName, Class<?> parent) throws Exception {
		return getClass(packageName, (clazz) -> {
			return parent.isAssignableFrom(clazz) && !Modifier.isAbstract(clazz.getModifiers());
		});
	}

	public static <A extends Annotation> Set<Class<?>> getDeclaredAnnotations(String packageName) throws Exception {
		return getClass(packageName, (clazz) -> {
			return clazz.isAnnotation();
		});
	}

	public static <A extends Annotation> Set<Class<?>> getDeclaredAnnotationClass(String packageName,
			Class<A> annotation) throws Exception {
		return getClass(packageName, (clazz) -> {
			return clazz.getAnnotation(annotation) != null;
		});
	}

	private static Set<Class<?>> getClass(String packageName, Predicate<Class<?>> filter) throws Exception {
		Set<Class<?>> result = new LinkedHashSet<Class<?>>();
		URL url = Thread.currentThread().getContextClassLoader().getResource(packageName.replace('.', '/'));
		if (url != null) {
			String protocol = url.getProtocol();
			if ("file".equals(protocol)) {
				getDirectoryClass(packageName, filter, url.getPath(), result);
			} else if ("jar".equals(protocol)) {
				result.addAll(getJarClass(packageName, filter, url));
			}
		}
		return result;
	}

	private static void getDirectoryClass(String packageName, Predicate<Class<?>> filter, String dirPath,
			Set<Class<?>> result) throws Exception {
		try {
			File dir = new File(dirPath);
			File[] list = dir.listFiles(new FileFilter() {
				public boolean accept(File file) {
					return file.isDirectory() || file.getName().endsWith(".class") && !file.getName().contains("$");
				}
			});
			for (File file : list) {
				String name = file.getName();
				if (file.isDirectory()) {
					getDirectoryClass(packageName + "." + name, filter, file.getAbsolutePath(), result);
				} else {
					String className = packageName + '.' + name.substring(0, name.length() - 6);
					Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
					if (filter.test(clazz)) {
						result.add(clazz);
					}

				}
			}
		} catch (ClassNotFoundException e) {
			throw new Exception();
		}
	}

	private static Set<Class<?>> getJarClass(String packageName, Predicate<Class<?>> filter, URL url) throws Exception {
		Set<Class<?>> result = new LinkedHashSet<Class<?>>();
		try {
			JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
			Enumeration<JarEntry> entries = jar.entries();
			while (entries.hasMoreElements()) {
				String name = entries.nextElement().getName();
				if (name.startsWith(packageName.replace('.', '/')) && name.endsWith(".class") && !name.contains("$")) {
					String className = name.substring(0, name.length() - 6).replace('/', '.');
					Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
					if (filter.test(clazz)) {
						result.add(clazz);
					}
				}
			}
		} catch (Exception e) {
			throw new IOException();
		}
		return result;
	}

}
