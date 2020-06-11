package com.liangyuelong.test;

import com.github.kevinsawicki.http.HttpRequest;
import com.liangyuelong.test.elapsedtime.ConcurrentRun;
import com.liangyuelong.test.elapsedtime.Executor;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;


public class HttpTest {

    // 请求地址
    private String host = "http://inner-elb-1067244909.us-east-1.elb.amazonaws.com";

    // seed 长度，模拟 input 密钥
    private int seedSize = 50;

    // 并发线程数量
    private int count = 200;

    private int[] seed;

    @Before
    public void init() {
        seed = new int[seedSize];
        for (int i = 0; i < seedSize; i++) {
            seed[i] = i;
        }
    }

    @Test
    public void test() throws InterruptedException {
        ConcurrentRun.run(count, () -> {
            long time = System.currentTimeMillis();
            // input 数据
            Object input = seed[RandomUtils.nextInt(0, seedSize)];
            // 返回数据
            String body = HttpRequest.get(host, true, "input", input).body();
            // 打印信息
            time = System.currentTimeMillis() - time;
            System.out.println("input: " + input);
            System.out.println("返回: " + body);
            System.out.println("用时: " + time + " ms");
        });
    }

}
