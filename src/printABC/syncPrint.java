package printABC;

public class syncPrint implements Runnable{
    static String stu="A";
    public syncPrint(){

    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (stu){
                System.out.println(Thread.currentThread().getName()+" 线程打印： "+stu);
                if (stu.equals("A")){
                    stu="B";
                }else if (stu.equals("B")){
                    stu="C";
                }else if (stu.equals("C")){
                    stu="A";
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new syncPrint(),"A").start();
        new Thread(new syncPrint(),"B").start();
        new Thread(new syncPrint(),"C").start();
    }
}
