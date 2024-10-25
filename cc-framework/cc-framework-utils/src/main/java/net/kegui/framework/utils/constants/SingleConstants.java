package net.kegui.framework.utils.constants;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 一些常用的单例对象
 */
public class SingleConstants {

	/**
	 * RANDOM
	 */
	public final static Random RANDOM = new Random();

	/**
	 * SECURE_RANDOM
	 */
	public final static SecureRandom SECURE_RANDOM = new SecureRandom();

}
