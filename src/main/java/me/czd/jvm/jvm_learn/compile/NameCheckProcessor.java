package me.czd.jvm.jvm_learn.compile;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 *	注解处理器， 
 *@SupportedAnnotationTypes支持的注解类型
 *@SupportedSourceVersion支持的版本
 * @author Administrator
 *
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameCheckProcessor extends AbstractProcessor {

	
	
	/**
	 * processingEnv是AbstractProcessor中的一个protected变量，代表了注解处理器框架提供的一个上下文环境
	 * 要创建新的代码，像编译器输出信息，获取其他工具类都需要这个变量
	 */
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
	}

	/**
	 * roundEnv访问当前Round中的语法树节点，在这里语法树节点代表的是javax.lang.model包中的Element
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		return false;
	}

}
