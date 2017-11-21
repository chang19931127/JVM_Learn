/**
 * @author Administrator
 * Class文件格式
 * 常量池的项目类型
 * Class文件10个部分组成 	数据类型长度不同
 * 	MagicNumber			魔术：4字节
 * 	Version				版本号：4字节
 * 	Constant_pool		常量池，和jmm中的常量池有着关联，常量池数量+常量池数组，常量池数量：2字节，下来就是常量池数组
 * 					常量池数组是由若干个常量池元素，每一个常量池元素由一个标志位和元素内容构成，标志位:1字节，元素内容：根据标志位来确定具体类型
 * 					JVM中定义了11中常量，常量池数量不使用第0个元素
 * 					对于常量池，jvm知道起始位置，不知道结束位置								
 * 	Access_flag			标志位：2字节，类或者接口，是否public，是否final，
 * 	This_class			全限定名：2字节，指向常量池的索引
 * 	Super_class			父类全限定名：2字节，也是指向常量池的索引
 * 	Interfaces			接口:接口数量：2字节，然后就是接口了，接口信息，一个接口也是2字节，指向常量池
 * 	Fields				字段:字段数量：2字节，然后就是字段结构了，长度不定，因为有attribute_info信息
 * 	Methods				方法:方法数量：2字节，然后就是方法结构了，同样长度不定，因为有attribute_info信息
 * 	Attributes			九大属性表(看JVM虚拟机定义吧)
 * 					Code ConstantValue Deprecated Exceptions InnerClasses LineNumberTal
 * 					LocalVariableTable SourceFile Synthetic
 * 
 * 		大端模式(数据的高位存储在低位存储单元上),数据大的地址小。低位存大数就是大端，也就是从大的开始存
 * 		eg:0x01020304			0x005071 - 0x01		0x005072 - 0x02		0x005073 - 0x03
 * 		java输入的字节信息全部是大端模式，但是jvm有不同的平台，也就导致不同的硬件，导致可能大端模式，可能小端模式，要统一如何？
 * 		看源码/src/share/vm/classfile		里面有大小端转换的统一
 * 		JVM中的java对象oop-klass模型(ordinary object pointer)
 * 		看源码/src/share/vm/oops/       里面是对oop的定义
 * 			java的数据结构实现机制是，编译时变成字节码，运行期实现。
 * 		
 * 
 *
 */
package me.czd.jvm.jvm_learn.classcode;