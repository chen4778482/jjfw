package com.jxgis.ffireserver.core.util;

import java.util.Random;

/**
 * Created by Administrator on 2017/4/19.
 */
public class RandomUtil {

	public static final String ALLCHAR = "123456789abcdefghijklmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
	public static final String LETTERCHAR = "abcdefghijkllmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
	public static final String NUMBERCHAR = "123456789";
	public static final int[] INS = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	/**
	 * 返回一个定长的随机字符串(只包含大小写字母、数字)
	 *
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯字母字符串(只包含大小写字母)
	 *
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateMixString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
	 *
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateLowerString(int length) {
		return generateMixString(length).toLowerCase();
	}

	/**
	 * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
	 *
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateUpperString(int length) {
		return generateMixString(length).toUpperCase();
	}

	/**
	 * 生成一个定长的纯0字符串
	 *
	 * @param length
	 *            字符串长度
	 * @return 纯0字符串
	 */
	public static String generateZeroString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}

	/**
	 * 根据数字生成一个定长的字符串，长度不够前面补0
	 *
	 * @param num
	 *            数字
	 * @param fixdlenth
	 *            字符串长度
	 * @return 定长的字符串
	 */
	public static String toFixdLengthString(long num, int fixdlenth) {
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if (fixdlenth - strNum.length() >= 0) {
			sb.append(generateZeroString(fixdlenth - strNum.length()));
		} else {
			throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth
					+ "的字符串发生异常！");
		}
		sb.append(strNum);
		return sb.toString();
	}

	/**
	 * 每次生成的len位数都不相同
	 *
	 * @param param
	 * @return 定长的数字
	 */
	public static int getNotSimple(int[] param, int len) {
		Random rand = new Random();
		for (int i = param.length; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = param[index];
			param[index] = param[i - 1];
			param[i - 1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < len; i++) {
			result = result * 10 + param[i];
		}

		//len位数不够时，递归调用
		if(String.valueOf(result).length() < len){
			return getNotSimple(param, len);
		}
		return result;
	}

	/**
	 * 每次生成的len位数都不相同
	 *
	 * @param len 是生成的随机数长度
	 * @return 定长的数字
	 */
	public static int getNotSimple(int len) {

		return getNotSimple(INS, len );
	}

	public static void main(String[] args) {
		System.out.println("返回一个定长的随机字符串(只包含大小写字母、数字):" + generateString(10));
		System.out
				.println("返回一个定长的随机纯字母字符串(只包含大小写字母):" + generateMixString(10));
		System.out.println("返回一个定长的随机纯大写字母字符串(只包含大小写字母):"
				+ generateLowerString(10));
		System.out.println("返回一个定长的随机纯小写字母字符串(只包含大小写字母):"
				+ generateUpperString(10));
		System.out.println("生成一个定长的纯0字符串:" + generateZeroString(10));
		System.out.println("根据数字生成一个定长的字符串，长度不够前面补0:"
				+ toFixdLengthString(123, 10));

		System.out.println("每次生成的len位数都不相同:" + getNotSimple(INS, 4));
		System.out.println("-- 每次生成的len位数都不相同:" + getNotSimple(4));



	}
}
