package com.bazl.dna.test;

import java.time.Instant;

import com.google.common.util.concurrent.RateLimiter;

public class TestMain {

	public static void main(String[] args) {
		// 每秒产生 10 个令牌（每 100 ms 产生一个）
		RateLimiter rt = RateLimiter.create(10);
		for (int i = 0; i < 11; i++) {
			new Thread(() -> {
				// 获取 1 个令牌
				double d = rt.acquire();
				System.out.println("正常执行方法，ts:" + Instant.now() + " -- " + d);
			}).start();
		}
	}

}
