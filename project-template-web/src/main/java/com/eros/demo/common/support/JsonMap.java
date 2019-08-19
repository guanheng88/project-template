package com.eros.demo.common.support;

import java.util.Map;

public class JsonMap<K, V> {

	private Map<K, V> map;

	public JsonMap(Map<K, V> map) {
		this.map = map;
	}

	public Map<K, V> getMap() {
		return map;
	}

	public void setMap(Map<K, V> map) {
		this.map = map;
	}

	public JsonMap<K, V> put(K key, V value) {
		map.put(key, value);
		return this;
	}

	public V get(K key) {
		return map.get(key);
	}
}
