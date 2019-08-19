package com.eros.demo.common.support;

import java.util.Iterator;
import java.util.List;

/**
 * 分页List
 * 
 * @author guanheng
 *
 * @param <T>
 */
public class PagedList<T> implements Iterable<T> {

	private List<T> rows;
	private long total;

	public PagedList(List<T> rows, long total) {
		this.rows = rows;
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public T get(int index) {
		return rows.get(index);
	}

	public int size() {
		return rows.size();
	}

	public long getTotal() {
		return total;
	}

	@Override
	public Iterator<T> iterator() {
		return rows.iterator();
	}

}
