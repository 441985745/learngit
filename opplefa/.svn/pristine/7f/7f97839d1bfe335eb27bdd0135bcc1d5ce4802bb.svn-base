package com.opple.fa.webapp.taglib;

import com.opple.fa.interceptor.Authentication;
import com.opple.security.SessionSecurityConstants;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Phoenix Z Sun on 9/25/2016.
 */
public class AuthTaglib extends SimpleTagSupport {
    private StringWriter sw = new StringWriter();
    private Set<String> authCodeSet;

    public void setCode(String code) {
        String[] codes = code.split(",");
        authCodeSet = new HashSet<String>(Arrays.asList(codes));
    }

    @Override
	public void doTag() throws JspException, IOException {
		Authentication authentication = (Authentication) getJspContext()
				.getAttribute(SessionSecurityConstants.KEY_AUTHENTICATION, PageContext.SESSION_SCOPE);

		getJspBody().invoke(sw);
		JspWriter out = getJspContext().getOut();

		if (hasAuth(authentication)) {
			out.println(sw.toString());
		} else {
			out.println("");
		}

	}

	private boolean hasAuth(Authentication authentication) {
		for (String auth : authCodeSet) {
			if (authentication.getAuthButtonCodes().contains(auth)) {
				return true;
			}
		}
		return false;
	}
}
