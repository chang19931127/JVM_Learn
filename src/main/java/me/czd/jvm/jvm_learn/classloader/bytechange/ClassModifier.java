package me.czd.jvm.jvm_learn.classloader.bytechange;

/**
 * 
 * @author Administrator 字节码修改类！！！
 * 每个信息对应什么，真的很有意思
 *
 */
public class ClassModifier {
	// class文件中常量池个数的起始偏移
	private static final int CONSTANT_POOL_COUNT_INDEX = 8;

	// CONSTANT_UTF8_INFO常量的tag标记
	private static final int CONSTANT_UTF8_INFO = 1;

	// 常量池中11种常量所占的长度 CONSTANT_UTF8_INFO除外，因为他不是定长的
	private static final int[] CONSTANT_ITEM_LENGTH = { -1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5 };

	private static final int u1 = 1;
	private static final int u2 = 2;

	private byte[] classByte;

	public ClassModifier(byte[] classByte) {
		this.classByte = classByte;
	}

	// 返回常量池个数
	private int getConstantsPoolSize() {
		return ByteUtils.bytes2Int(classByte, CONSTANT_POOL_COUNT_INDEX, u2);
	}

	public byte[] modifyUTF8Constant(String oldStr, String newStr) {
		int constantsPoolSize = getConstantsPoolSize();
		int offset = CONSTANT_POOL_COUNT_INDEX + u2;
		// 常量循环
		for (int i = 0; i < constantsPoolSize; i++) {
			int tag = ByteUtils.bytes2Int(classByte, offset, u1);
			if (tag == CONSTANT_UTF8_INFO) {
				int len = ByteUtils.bytes2Int(classByte, offset + u1, u2);
				offset += u1 + u2;
				String str = ByteUtils.bytes2String(classByte, offset, len);
				if (str.equals(oldStr)) {
					byte[] newStrBytes = ByteUtils.String2Bytes(newStr);
					byte[] newStrLenBytes = ByteUtils.int2Bytes(newStr.length(), u2);
					classByte = ByteUtils.bytesReplace(classByte, offset - u2, u2, newStrLenBytes);
					classByte = ByteUtils.bytesReplace(classByte, offset, len, newStrBytes);
					return classByte;
				} else {
					offset += len;
				}
			} else {
				offset += CONSTANT_ITEM_LENGTH[tag];
			}
		}
		return classByte;
	}
}
