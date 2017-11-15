package me.czd.jvm.jvm_learn.classloader.bytechange;

/**
 * Bytes数组处理工具
 * 
 * @author Administrator
 *
 */
public final class ByteUtils {

	public static int bytes2Int(byte[] bytes, int start, int len) {
		int sum = 0;
		int end = start + len;
		for (int i = start; i < end; i++) {
			// 感觉这里(int)和 &0xff相同的作用
			int n = ((int) bytes[i]) & 0xff;
			n = n << (--len) * 8;
			sum = sum + n;
		}
		return sum;
	}

	public static byte[] int2Bytes(int value, int len) {
		byte[] b = new byte[len];
		for (int i = 0; i < b.length; i++) {
			b[len - i - 1] = (byte) ((value >> 8 * i) & 0xff);
		}
		return b;
	}

	public static String bytes2String(byte[] bytes, int start, int len) {
		return new String(bytes, start, len);
	}

	public static byte[] String2Bytes(String str) {
		return str.getBytes();
	}

	/**
	 * originalBytes 从offset 开始 将replaceBytes  的内容复制过来，复制len个
	 * @param originalBytes
	 * @param offset
	 * @param len
	 * @param replaceBytes
	 * @return
	 */
	public static byte[] bytesReplace(byte[] originalBytes, int offset, int len, byte[] replaceBytes) {
		byte[] newBytes = new byte[originalBytes.length + (replaceBytes.length - len)];
		System.arraycopy(originalBytes, 0, newBytes, 0, offset);
		System.arraycopy(replaceBytes, 0, newBytes, offset, replaceBytes.length);
		System.arraycopy(originalBytes, offset + len, newBytes, offset + replaceBytes.length,
		        originalBytes.length - offset - len);
		return newBytes;
	}
}
