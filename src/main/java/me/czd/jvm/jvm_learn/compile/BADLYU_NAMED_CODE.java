package me.czd.jvm.jvm_learn.compile;

/**
 * NameCheck
 * 
 * @author Administrator
 *
 */
public class BADLYU_NAMED_CODE {
	enum colors {
		red, blue, green;
	}

	static final int _FORTY_TWO = 42;

	public static int NOT_A_CONSTANT = _FORTY_TWO;

	protected void BADLY_NAMED_CODE() {
		return;
	}

	public void NOTcamelCASEmenthodNAME() {
		return;
	}
}
