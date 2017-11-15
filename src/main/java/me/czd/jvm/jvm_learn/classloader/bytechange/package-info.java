/**
 * @author Administrator
 * 我们修改类，然后让ClassLoader重新加载，
 * 也就是一个热部署
 * 
 * 
 * 我们通过文件流加载 class文件，
 * 通过Class文件格式，找到对应的constant_pool_count常量池个数，然后循环
 * 然后通过我们传入的值，和我们循环的constant_pool中的CONSTANT_Utf8_info去比较，然后找到对应的变量位置
 * 然后修改成我们想要修改的样子，然后使用保存下来
 * 然后被类加载器重新加载
 * 然后实例化去获得值
 * 
 * 需要知道Class文件格式
 * 最好使用16进制打开Class文件观察一下就知道字节数怎么拼了，怎么去查看
 */
package me.czd.jvm.jvm_learn.classloader.bytechange;