# ThreadLocal为什么会内存泄漏

1. ThreadLocalMap内部Entry中key使用的是对ThreadLocal对象的弱引用，这为避免内存泄露是一个进步，因为如果是强引用，
那么即使其他地方没有对ThreadLocal对象的引用，ThreadLocalMap中的ThreadLocal对象还是不会被回收，而如果是弱引
用则这时候ThreadLocal引用是会被回收掉的，虽然对于的value还是不能被回收，这时候ThreadLocalMap里面就会存在key
为null但是value不为null的entry项，虽然ThreadLocalMap提供了set,get,remove方法在一些时机下会对这些Entry项
进行清理，但是这是不及时的，也不是每次都会执行的，所以一些情况下还是会发生内存泄露，**所以在使用完毕后即使调用
remove方法才是解决内存泄露的王道**
2. **线程池里面设置了ThreadLocal变量一定要记得及时清理，因为线程池里面的核心线程是一直存在的，如果不清理，
那么线程池的核心线程的threadLocals变量一直会持有ThreadLocal变量**

```
public class ThreadPoolTest {

    static class LocalVariable {
        private Long[] a = new Long[1024*1024];
    }

    // (1)
    final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>());
    // (2)
    final static ThreadLocal<LocalVariable> localVariable = new ThreadLocal<LocalVariable>();

    public static void main(String[] args) throws InterruptedException {
        // (3)
        for (int i = 0; i < 50; ++i) {
            poolExecutor.execute(new Runnable() {
                public void run() {
                    // (4)
                    localVariable.set(new LocalVariable());
                    // (5)
                    System.out.println("use local varaible");
                    //localVariable.remove(); 如果不执行这句就有可能发生内存泄漏

                }
            });

            Thread.sleep(1000);
        }
        // (6)
        System.out.println("pool execute over");
    }
```