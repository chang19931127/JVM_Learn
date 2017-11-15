package me.czd.jvm.jvm_learn.compile;

import java.util.EnumSet;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner8;
import javax.tools.Diagnostic.Kind;

/**
 * 程序命名规范的编译器插件 这里使用的就是注解处理器API
 * 
 * @author Administrator
 *
 */
public class NameChecker {
	private final Messager messager;
	NamecheckScanner nameCheckScanner = new NamecheckScanner();

	public NameChecker(ProcessingEnvironment processingEnv) {
		super();
		this.messager = processingEnv.getMessager();
	}

	public void checkNames(Element element) {
	}

	/**
	 * 实现类，以Visitor模式访问抽吸nag语法树中的元素
	 * 
	 * @author Administrator
	 *
	 */
	private class NamecheckScanner extends ElementScanner8<Void, Void> {


		/**
		 * 检查java类
		 */
		@Override
		public Void visitType(TypeElement e, Void p) {
			scan(e.getTypeParameters(), p);
			checkCamelCase(e, true);
			super.visitType(e, p);
			return null;
		}
		
		/**
		 * 检查方法命名是否合规
		 */
		@Override
		public Void visitExecutable(ExecutableElement e, Void p) {
			if (e.getKind() == ElementKind.METHOD) {
				Name name = e.getSimpleName();
				if (name.contentEquals(e.getEnclosingElement().getSimpleName())) {
					messager.printMessage(Kind.WARNING, "一个普通方法" + name + "不应当和类名相同，避免和构造方法产生混淆", e);
				}
				checkCamelCase(e, false);
			}
			super.visitExecutable(e, p);
			return null;
		}
		
		/**
		 * 检查变量命名是否合法
		 */
		@Override
		public Void visitVariable(VariableElement e, Void p) {
			if(e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e)){
				checkALLCaps(e);
			}else{
				checkCamelCase(e, false);
			}
			return null;
		}
		
		/**
		 * 判断一个变量是否是常量
		 * @param e
		 * @return
		 */
		private boolean heuristicallyConstant(VariableElement e){
			if(e.getEnclosingElement().getKind() == ElementKind.INTERFACE){
				return true;
			}else if(e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(Modifier.PUBLIC,Modifier.STATIC,Modifier.FINAL))){
				return true;
			}else{
				return false;
			}
		}
		
		/**
		 * 检查驼峰命名
		 * @param e
		 * @param initialCaps
		 */
		private void checkCamelCase(Element e, boolean initialCaps) {
			String name = e.getSimpleName().toString();
			boolean previousUpper = false;
			boolean conventional = true;
			int firstCodePoint = name.codePointAt(0);
			if (Character.isUpperCase(firstCodePoint)) {
				previousUpper = true;
				if (!initialCaps) {
					messager.printMessage(Kind.WARNING, "名称" + name + "应该以小写字母开头", e);
					return;
				}
			} else if (Character.isLowerCase(firstCodePoint)) {
				if (initialCaps) {
					messager.printMessage(Kind.WARNING, "名称" + name + "应该以大写字母开头");
				}
			} else {
				conventional = false;
			}
			if (conventional) {
				int cp = firstCodePoint;
				for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
					cp = name.codePointAt(i);
					if (Character.isUpperCase(cp)) {
						if (previousUpper) {
							conventional = false;
							break;
						}
						previousUpper = true;
					} else {
						previousUpper = false;
					}
				}
			}
			if (!conventional) {
				messager.printMessage(Kind.WARNING, "名称" + name + "应当符合驼峰式明明发(Camel Case Names)", e);
			}
		}

		/**
		 * 大写命名检查，第一个字母必须是大写的英文字母，其余部分可以使下划线和或大写字母
		 * 
		 * @param element
		 */
		private void checkALLCaps(Element e) {
			String name = e.getSimpleName().toString();
			boolean conventional = true;
			int firstCodePoint = name.codePointAt(0);

			if (!Character.isUpperCase(firstCodePoint)) {
				conventional = false;
			} else {
				boolean previousUnderscore = false;
				int cp = firstCodePoint;
				for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
					cp = name.codePointAt(i);
					if (cp == (int) '_') {
						if (previousUnderscore) {
							conventional = false;
							break;
						}
						previousUnderscore = true;
					} else {
						previousUnderscore = false;
						if (!Character.isUpperCase(cp) && !Character.isDigit(cp)) {
							conventional = false;
							break;
						}
					}
				}
			}
			if (!conventional) {
				messager.printMessage(Kind.WARNING, "常量" + name + "应该全部以大写字母或下划线明明，并且以字母开头", e);
			}
		}

	}

}
