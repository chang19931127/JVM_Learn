/**
 * @author Administrator
 * 这个包下面就是我们对java 类加载机制的学习以及一些关于类加载机制的代码练习
 * 
 * JVM 通过类加载器 来加载class文件 字节码，加载的时候，肯定要判断
 * 语法是否规范，语义是否完整，等等
 * 
 * 概括：虚拟机把描述类的数据从Class文件加载到内存，并对数据进行校验，转换解析和初始化
 * 最终形成可以被虚拟机直接使用的类型，这就是虚拟机的类加载机制
 * Class文件，抽象完毕就是一串二进制的字节流
 * 
 * 大概分这么几个阶段 加载-(验证-准备-解析)-初始化-使用-卸载
 * 
 * 初始化阶段要好好了解以下，和被动引用不同，初始化是主动引用，那么我认为class就被加载成功并且随时可用
 * new， 反射， 子类初始化的父类，执行了main函数的类，MethodHandle实例对应的句柄触发初始化
 * 
 * 加载，就是类加载过程的一个阶段，只要是二进制流都可以被jvm加载
 * 	大概
 * 	1，通过一个类的全限定名来获取定义此类的二进制字节流，可以是任何渠道的二进制流（ZIP包，网络，动态产生，其他文件生成，数据库读取。。。。）
 * 	2，将这个字节流所代表的静态存储结构转化为方法去的运行时数据结构
 * 	3，在内存中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口。
 * 	一点须知，数组类是java虚拟机直接创建，但是数组类型和类加载器有关
 * 
 * 验证，保证class文件的合法性，文件格式，元数据验证，字节码验证，符号引用验证
 * 
 * 准备，最重要的就是数据类型的0值，属性的初始化，0 \u0000 false null
 * 
 * 解析
 * 	1，类或接口解析
 * 	2，字段解析
 * 	3，类方法解析
 * 	4，接口方法解析
 * 		<clinit>方法被JVM加载后，只会执行一次
 * 		多线程中只有某一个线程可以执行<clinit>方法，就会导致其他线程阻塞一段时间
 * 
 * 类加载器负责加载二进制字节流
 * 			类加载器几大领域，类层次划分，OSGI，热部署，代码加密
 * 		对于任意一个类，都需要它的类加载器和这个类本身一同确立其在java虚拟机中的唯一性
 * 		BootStrap ClassLoader		C++实现
 * 			这个类负责将JAVA_HOME\lib目录中进行加载或者-Xbootclasspath制定路径，并且是虚拟机识别的(如rt.jar)
 * 		Extension ClassLoader		java实现
 * 			这个类负责将lib\ext目录进行加载，或者是java.ext.dirs系统变来那个或指定的类库，开发者可以直接使用
 * 		Application ClassLoader		java实现
 * 			这个类负责加载用户类路径上所制定的类库	也是ClassLoader.getSystemClassLoader() 直接获取的
 * 		双亲委托模型代码在 ClassLoader 的loadClass()方法中
 * 	破坏双亲委托模型
 * 	Thread.setContextClassLoader() 这个加载器就是俗称的上下文类加载器，JNDI，JDBC，JCE，JAXB等都是这么使用的，fastJSON也可以看到
 * 
 */
package me.czd.jvm.jvm_learn.classloader;