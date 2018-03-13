package com.mgmtp.blog.service;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class SessionIdGenerator {

	private static final int LENGTH = 32;
	private static SecureRandom random = new SecureRandom();

	public static String getSessionId() {

		BigInteger bigInteger = new BigInteger(130, random);

		String sessionId = String.valueOf(bigInteger.toString(LENGTH));

		return sessionId.toUpperCase();
	}
}