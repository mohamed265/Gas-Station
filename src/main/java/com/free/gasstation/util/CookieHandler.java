package com.free.gasstation.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieHandler {

	public static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
		for (Cookie c : request.getCookies()) {
			if (c.getName().equals(cookieName)) {
				return c;
			}
		}
		return null;
	}
}
