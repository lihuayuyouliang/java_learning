package printABC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class conditionPrint {
    ReentrantLock lock=new ReentrantLock();
    Condition ac=lock.newCondition();
    Condition bc=lock.newCondition();
    Condition cc=lock.newCondition();
    class A extends Thread{
        @Override
        public void run(){
            while (true){
                lock.lock();
                System.out.println("A,");
                bc.signal();
                try {
                    ac.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                lock.unlock();
            }
        }
    }
    class B extends Thread{
        @Override
        public void run(){
            while (true){
                lock.lock();
                System.out.println("B,");
                cc.signal();
                try {
                    bc.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                lock.unlock();
            }
        }
    }
    class C extends Thread{
        @Override
        public void run(){
            while (true){
                lock.lock();
                System.out.println("C,");
                ac.signal();
                try {
                    cc.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        conditionPrint cp=new conditionPrint();
        A a=cp.new A();
        B b=cp.new B();
        C c=cp.new C();
        a.start();
        b.start();
        c.start();
    }
}
