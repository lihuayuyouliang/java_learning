package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class callable {

    // ceshi
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread=new MyThread();
        FutureTask futureTask=new FutureTask(myThread);
        new Thread(futureTask,"A").start();
        Integer o=(Integer) futureTask.get();
        System.out.println(o);
    }
}
class MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("call");
        return 1024;
    }
}
