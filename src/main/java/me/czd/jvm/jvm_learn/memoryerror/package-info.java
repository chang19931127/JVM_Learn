
/**
 * @author Administrator
 * 这里是各种内存的错误，想要模拟内存的错误，我们就需要修改内存，是内存变小，然后去使内存溢出
 * 
 * -
 * Xss：每个线程的stack大小（栈）
 * Xmx：JAVA HEAP的最大值、默认为物理内存的1/4
 * Xms：JAVA HEAP的初始值，server端最好Xms与Xmx一样
 * Xmn：JAVA HEAP young区的大小
 * XX:PermSize：设定内存的永久保存区域
 * XX:MaxPermSize：设定最大内存的永久保存区域
 *
 * jdk1.8 中取消了PermGen取消了永久区 
 * 也就导致 常量池也放入到了堆中，只有类信息放入到Metaspace区中
 * 取而代之的是Metaspace 所以参数也变了
 * XX:MetaspaceSize
 * XX:MaxMetaspaceSize
 */
package me.czd.jvm.jvm_learn.memoryerror;