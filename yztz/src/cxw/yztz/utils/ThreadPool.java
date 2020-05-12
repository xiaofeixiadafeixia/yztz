package cxw.yztz.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

	private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
	private ThreadPool() {
		// TODO Auto-generated constructor stub
	}
	public static ExecutorService get() {
		return fixedThreadPool;
	}
}
