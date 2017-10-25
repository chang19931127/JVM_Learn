/**
 * @author Administrator
 *	这里就是一些虚拟机性能监控与故障处理工具
 *		
 *	工具建立在JMX的基础上
 *	首先来一些jdk的工具
 *		jps:		虚拟机进程状况工具
 *		jstat:		虚拟机统计信息监视工具
 *		jinfo:		java配置信息工具
 *		jmap:		java内存映像工具
 *		jhat:		虚拟机堆转储快照分析工具
 *		jstack:		java堆栈跟踪工具
 *		JConsole：	java监视与管理控制台
 *		VisualVM:	多合一故障处理工具
 *			VisualVM:	多合一故障处理工具
 *			https://visualvm.github.io/pluginscenters.html 下载插件
 *			在VisualVM中的插件->设置中修改URL为自己版本的，然后就可以下载插件了，然后了解去使用
 *			下载的.nbm文件手动安装
 *	其他工具
 *		HSDIS:		JIT生成代码反编译
 *		MAT:		内存分析工具
 */
package me.czd.jvm.jvm_learn.monitor;