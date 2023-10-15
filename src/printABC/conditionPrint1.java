package printABC;
// 狂神
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class conditionPrint1 {
    public static void main(String[] args) {
        Data data=new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        },"C").start();

    }
}
class Data{
    private Lock lock=new ReentrantLock();
    private Condition condition1=lock.newCondition();
    private Condition condition2=lock.newCondition();
    private Condition condition3=lock.newCondition();
    private int number=1;
    public void printA(){
        lock.lock();
        try {
            while (number!=1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+" 线程打印： "+"A");
            number=2;
            condition2.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        lock.unlock();
    }
    public void printB(){
        lock.lock();
        try {
            while (number!=2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+" 线程打印： "+"B");
            number=3;
            condition3.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        lock.unlock();
    }
    public void printC(){
        lock.lock();
        try {
            while (number!=3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+" 线程打印： "+"C");
            number=1;
            condition1.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        lock.unlock();
    }
}
