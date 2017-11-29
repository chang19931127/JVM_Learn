# java虚拟机

## java执行引擎

C语言中的函数指针----十分重要

通过函数指针C语言可以将一个变量直接指向一个函数的首地址。C语言被编译时，C函数将被直接编译成机器指令，而这个函数指令将直接指向这段机器指令的首地址。然后去调用

```c
const unsigned char code[] = "\xe9\x07....";//省略机器指令

int main(){
    int result;
    int (*fun)(int);//声明 fun 函数指针，这里要区别指针函数
    fun = (void*) code;//初始化fun 函数指针，指向code的内存位置
    result = fun(7);//调用函数
    printf("result=%d/r/n",result);
}
```

利用这个特性，在JVM内部也有一个函数指针，就是call_stub指针函数,JVM在调用这个函数之前，主要执行C程序(其实还是C程序编译后的机器指令)，而JVM通过这个函数指针调用目标函数后，就直接进入了机器指令的领域。执行java主函数，类加载都用到了

思考下上句话，可否理解成，jvm翻译字节码形成字节码，然后写到code中，嘿嘿嘿 未证实

JVM要实现直接由C语言调用机器指令

查看/src/share/vm/runtime/stubRoutines.hpp下的内容  再要知道 c h cpp hpp 的关系 头，哈哈

