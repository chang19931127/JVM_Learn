/** 
 * @author Administrator
 * GC 垃圾回收
 * 首先要明白什么垃圾可以被回收，在jvm中，java堆和方法区和程序计数器，虚拟机栈，本地方法栈不同
 * 可以进行回收，因为这些内存不固定，只有在程序运行的时候才知道这些内存的大小，所以GC也就关注这些区域
 * 
 * 如何判断对象是否死亡
 * 1，引用计数算法
 * 2，可达性分析算法
 * 
 * 
 * GC日志的看法
 * GC日志中 通常有[GC 和[Full GC 通常 带有FullGC的是发生了Stop-The-World 的GC，但是所有GC都有STW
 * 		如果是调用了System.gc就显示[FullGC(System)
 *  几个区域 Tenured老年代 Perm新生代，遇到其他收集器后名字就会改变		
 * 		[DefNew	使用Serial收集器中新生代名为"Default New Generation"
 * 		[ParNew 使用的是ParNew收集器 名称为"Parallel New Generation"
 * 		[PSYoungGen 使用的是Parallel Scavenge 高吞吐率收集器
 * 	对象优先在Eden分配，Eden没有空间分配JVM就会发生一次MinorGC
 * 	与MinorGC相对的就是FullGC(MajorGC)
 * 	重要的一个概念，就是新生代一个GC老年代一个GC 有一种组合关系的，那种配那种，然后显示的GC日志也不同，效果也不同
 * 	使用不同的垃圾收集器组合也许会得到不同的效果，使用不同的参数也会得到不同的效果
 * 
 * 	注意一些常用的VM参数
 * 
 * 
 * 	这里来记录下引用把
 * 	强引用，就是我们new出来的，只要强引用再GC就没办法
 * 	软引用，描述一些还有用但是并非必须的对象，对于软引用关联着的对象，在系统将要发生内存溢出异常之前
 * 		就会把这些对象列入到回收范围内进行第二次回收，这里会涉及到finalize对象
 * 	弱引用，描述非需要对象，被弱引用关联的对象只能生存到下一次垃圾收集发生之前，无论当前内存是否足够
 * 	虚引用，最弱的引用，无法通过虚引用来获得对象实例，通常设置虚引用的目的就是在这个对象被垃圾回收时收到一个系统通知
 * 	引用还是需要搞明白的，之后补上例子
 */
package me.czd.jvm.jvm_learn.gc;