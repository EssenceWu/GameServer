package com.player.framework.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public abstract class CacheAdapter<K, V> {

	private LoadingCache<K, V> cache;

	public CacheAdapter() {
		this.cache = CacheBuilder.newBuilder().initialCapacity(CacheOption.initialCapacity)
				.maximumSize(CacheOption.maximumSize)
				.expireAfterAccess(CacheOption.expireAfterAccessSeconds, TimeUnit.SECONDS)
				.expireAfterWrite(CacheOption.expireAfterWriteSeconds, TimeUnit.SECONDS).build(new Loader());
	}

	public V getCache(K k) {
		try {
			return this.cache.get(k);
		} catch (Exception e) {
			return null;
		}
	}

	private class Loader extends CacheLoader<K, V> {
		public V load(K k) throws Exception {
			return loadCache(k);
		}
	}

	public abstract V loadCache(K k);

}
