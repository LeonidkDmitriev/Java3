import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerChat { //это будет сервер
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new MyRunnable1());
        executor.execute(new MyRunnable2());
        executor.shutdown(); //остановим потоки
    }

// это  первый пользователь чата
  public static class MyRunnable1 implements Runnable{
     @Override
     public void run() {
         for (int i = 0; i <3 ; i++) {
             System.out.println("Я есть клиент 1, мое сообщение № " + i);
         }

     }
  }
// это  второй пользователь чата
    public static class MyRunnable2 implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i <3 ; i++) {
                System.out.println("Я есть клиент 2, мое сообщение № " + i);
            }
        }
    }
}
