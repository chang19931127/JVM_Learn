/**
 * @author Administrator
 *	java编译器，分两种
 *		第一种是，类似于javac的 Java -> class的编译器
 *		第二种，就是 class转化成本机代码的 JIT编译器
 *
 *		javac编译器做了很多东西，就是将我们的java代码转化成符合虚拟机语法的class文件
 *	我们java提供的语法糖在这个地方也就被融化了，哈哈
 *		javac编译器在jdk1.6中被添加的一组插入式注解处理器的API映像，可读取，修改抽象语法树中的任何元素
 *		处理器注解参考这篇文章
 *			http://www.importnew.com/15246.html
 *
 *	虚拟机运行的时候，有两种模式解释模式和编译模式 -Xint解释模式，-Xcomp编译模式
 *		编译模式在细分就是下面说的,当然只有在一定条件下发生
 *		JIT编译器：虚拟机将会把这些代码编译成与本地平台相关的机器码，并且进行各种优化
 *			有两种，	一种Client：
 *					一种Server
 *
 *		
 */
package me.czd.jvm.jvm_learn.compile;