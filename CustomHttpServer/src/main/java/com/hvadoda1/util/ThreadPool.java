package com.hvadoda1.util;

public class ThreadPool {

	private final int maxNumThreads;
	private final boolean infiniteThreads;
	private int availableThreadCount;

	public ThreadPool(int maxNumThreads) {
		this.maxNumThreads = maxNumThreads == 0 ? 1 : maxNumThreads;
		this.infiniteThreads = maxNumThreads < 0;
		this.availableThreadCount = maxNumThreads;
	}

	public int getMaxNumThreads() {
		return this.maxNumThreads;
	}

	public int getAvailableThreadCount() {
		synchronized (this) {
			return infiniteThreads ? -1 : this.getAvailableThreadCount();
		}
	}

	/**
	 * A blocking operation, waits until
	 * 
	 * @param r
	 * @return {@link ServerThread}
	 * @throws InterruptedException
	 */
	public ServerThread get(Runnable r) throws InterruptedException {
		synchronized (this) {
			while (!infiniteThreads && availableThreadCount == 0) {
				wait();
			}
			availableThreadCount--;
			ServerThread t = new ServerThread(maxNumThreads - availableThreadCount, () -> {
				r.run();
				returnToPool(maxNumThreads - availableThreadCount);
			});
			t.start();
			return t;
		}
	}

	protected void returnToPool(int threadId) {
		synchronized (this) {
			if (infiniteThreads || (threadId > 0 && threadId <= maxNumThreads)) {
				this.availableThreadCount++;
				notify();
			}
		}
	}

	public void returnToPool(ServerThread t) {
		returnToPool(t.threadId);
	}

	public static class ServerThread extends Thread {
		public final int threadId;

		public ServerThread(int id, Runnable r) {
			super(r);
			this.threadId = id;
		}
	}
}
