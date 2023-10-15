package thread;

import java.util.concurrent.*;
// 线程池创建线程
public class ThreadCreate {
    public static void main(String[] args) {
        ExecutorService threadPool= new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 1; i <= 8; i++) {
            int finalI = i;
            threadPool.execute(()->{
                System.out.println(Thread.currentThread().getName()+"正在执行任务"+ finalI);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        threadPool.shutdown();
    }
}

