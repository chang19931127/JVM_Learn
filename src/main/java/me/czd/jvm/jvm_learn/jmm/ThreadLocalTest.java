package me.czd.jvm.jvm_learn.jmm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 看看ThreadLocal 如何导致
 * 也就是说ThreadLocal设计成弱引用
 * 这个弱引用当GC的时候仅仅是ThreadLocal.ThreadLocalMap中的entry的key应用没了，key被收集。
 * ThreadLocal 对象被回收，但是ThreadLocal中set的对象不会被回收。最终发生OOM
 * vm args -Xmx10m -XX:+PrintGCDetails
 * @author Administrator
 *
 */
public class ThreadLocalTest {
	private static final int THREAD_LOOP_SIZE = 500;
    private static final int MOCK_DIB_DATA_LOOP_SIZE = 10000;

    private static ThreadLocal<List<User>> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_LOOP_SIZE);

        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
            	// add大对象
                threadLocal.set(new ThreadLocalTest().addBigList());
                System.out.println(Thread.currentThread().getName());
//                threadLocal.remove(); //不取消注释的话就可能出现OOM
            });
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //executorService.shutdown();
    }

    private List<User> addBigList() {
        List<User> params = new ArrayList<>(MOCK_DIB_DATA_LOOP_SIZE);
        for (int i = 0; i < MOCK_DIB_DATA_LOOP_SIZE; i++) {
            params.add(new User("xuliugen", "password" + i, "男", i));
        }
        return params;
    }

    class User {
        private String userName;
        private String password;
        private String sex;
        private int age;

        public User(String userName, String password, String sex, int age) {
            this.userName = userName;
            this.password = password;
            this.sex = sex;
            this.age = age;
        }
    }
}
