package net.kegui.framework.log.enums;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 操作状态
 */
public enum BusinessStatus {

	/**
	 * 成功
	 */
	SUCCESS(1, "SUCCESS"),

	/**
	 * 失败
	 */
	FAIL(0, "FAIL");

	private final int value;

	private final String reasonPhrase;

	BusinessStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getReasonPhrase() {
		return this.reasonPhrase;
	}

}
