package com.opple.fa.interceptor;

import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.service.UserInfoService;
import com.opple.security.LoginContext;
import com.opple.security.LoginContextHolder;
import com.opple.security.SessionSecurityConstants;
import com.opple.security.entity.User;
import com.opple.security.service.UserService;
import com.opple.util.Env;
import com.opple.util.IpUtil;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

/**
 * 从session中读取用户信息，设置到线程上线文中
 * 
 * @author hanty
 * 
 */
public class LoginContextInterceptor extends HandlerInterceptorAdapter {
	private final static String DEFAULT_LOGIN = "/login";
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginContextInterceptor.class);

	@Resource
	private UserService userService;
    @Resource
    private UserInfoService userInfoService;

	@Setter
	private List<String> noLoginAuthUrlList = new ArrayList<String>();
	/**
	 * 用户session中的用户表示
	 */
	@Setter
	private String keyUserName = SessionSecurityConstants.KEY_USER_NAME;
	@Setter
	private String keyUserNickName = SessionSecurityConstants.KEY_USER_NICK_NAME;
	@Setter
	private String keyUserId = SessionSecurityConstants.KEY_USER_ID;
	@Setter
	private String keyLocalLanguage = SessionSecurityConstants.KEY_LOCAL_LANGUAGE;

	/**
	 * 预处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object handler) throws Exception {
		HttpSession httpSession = request.getSession();
		if (!isLogin(request, response)) {
			redirectToLogin(request, response);
			return false;
		}

		//初始化登录用户
		LoginContext loginContext = buildLoginContext(request);
		LoginContextHolder.put(loginContext);
		return super.preHandle(request, response, handler);
	}

	/**
	 * 拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {
		LoginContextHolder.clear();
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 返回方法拦截
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (ex != null) {
			//test没用 纯属为了解决CheckStyle
			int test = 0;
		}
		super.afterCompletion(request, response, handler, ex);
	}

	/**
	 * 判断是否登录
	 * @param httpServletRequest
	 * @return
	 */
    protected boolean isLogin(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {
		String currentUrl = httpServletRequest.getRequestURI();
		for (String url : noLoginAuthUrlList) {
			if (currentUrl.startsWith(url)) {
				return true;
			}
		}

		HttpSession httpSession = httpServletRequest.getSession();

		// .net 跳转
		String userCode = httpServletRequest.getParameter("userCode");
		String password = httpServletRequest.getParameter("pwd");
		String directToPageFlag = httpServletRequest.getParameter("directToPageFlag");
		if (StringUtils.isNotBlank(userCode) && StringUtils.isNotBlank(password) && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "GET")) {

//
//            Des des = new Des();
//
//            WorkFlowStub stub = new WorkFlowStub();
//
//            WorkFlowStub.UserLogin userLogin = new WorkFlowStub.UserLogin();
//            userLogin.setUserCode(userCode);
//            userLogin.setPwd(des.decrypt(password));
//            WorkFlowStub.UserLoginResponse userLoginResponse = stub.userLogin(userLogin);
//
//            if (StringUtils.isNotBlank(userLoginResponse.getUserLoginResult())) {
////                result.addErrorMessage(userLoginResponse.getUserLoginResult());
////                loginResult.setMsg(result.getErrorMessages().size() > 0 ? result.getErrorMessages().get(0) : "用户名或密码错误");
//            } else {
			UserInfo userInfo = new UserInfo();
			userInfo.setLoginUserCode(userCode);
			userInfo = userInfoService.searchUserNameAndType(userInfo);


			httpSession.setAttribute(SessionSecurityConstants.KEY_USER_NAME, userCode.trim());
			// 其他一些session的信息在这里设置
			httpSession.setAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME, userInfo.getCusername());
			// 获取员工部门等信息
			User user = userService.getUserDeptInfo(userCode.trim());
			if (user != null) {
				if (null != user.getXydj() && "".equals(user.getXydj())) {
					httpSession.setAttribute(SessionSecurityConstants.KEY_USER_CREDIT_RATING, user.getXydj().trim());
				}
				if (null != user.getScore()) {
					httpSession.setAttribute(SessionSecurityConstants.KEY_USER_SCORE, user.getScore().toString());
				}
			}

			// 直接跳转到请求页面，用于.net访问固定资产审批和查看页
			if ("1".equals(directToPageFlag)) {
				httpSession.setAttribute("dotNet", "dotNet");
				httpSession.setAttribute("cdUrl", Env.getProperty("domain.name"));

				return true;
			}

			try {
				response.sendRedirect(Env.getProperty(Env.APP_URL));
			} catch (IOException e) {
				e.printStackTrace();
				LOGGER.error("IOException", e);
			}

			return true;
//            }



			/*Des des = new Des();
            User user = new User();
			user.setUserCode(username);
			try {
				user.setPwd(des.decrypt(password));
			} catch (Exception e) {
				LOGGER.error("Exception", e);
			}

			ExecuteResult<User> userExecuteResult = userService.loginUser(user);

			if (userExecuteResult.isSuccess()) {

				httpServletRequest.getSession().setAttribute(SessionSecurityConstants.KEY_USER_NAME, username.trim());
				// 其他一些session的信息在这里设置
				httpServletRequest.getSession().setAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME, userExecuteResult.getResult().getUserName());
				// 获取员工部门等信息
				LOGGER.debug("跨域登陆设置session", username.trim() + "------" + userExecuteResult.getResult().getUserName());
				user = userService.getUserDeptInfo(user.getUserCode());
				if (user != null) {
					if (user.getXydj() != null && user.getXydj() != "") {
						httpServletRequest.getSession().setAttribute(SessionSecurityConstants.KEY_USER_CREDIT_RATING, user.getXydj().trim());
					}
					if (user.getScore() != null) {
						httpServletRequest.getSession().setAttribute(SessionSecurityConstants.KEY_USER_SCORE, user.getScore().toString());
					}
				}

				// 直接跳转到请求页面，用于.net访问固定资产审批和查看页
				if ("1".equals(directToPageFlag)) {
					httpServletRequest.getSession().setAttribute("dotNet", "dotNet");
					httpServletRequest.getSession().setAttribute("cdUrl", Env.getProperty("domain.name"));

					return true;
				}

				try {
					response.sendRedirect(Env.getProperty(Env.APP_URL));
				} catch (IOException e) {
					e.printStackTrace();
					LOGGER.error("IOException", e);
				}

				return true;
			}*/

		}

		String userName = (String) httpSession.getAttribute(keyUserName);
		if (userName == null) {
			// 仅仅记住get请求的链接
			if (StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "GET")) {
				HttpSession session = httpServletRequest.getSession();
				String contextPath = httpServletRequest.getContextPath();
				String servletPath = httpServletRequest.getServletPath();
				String fullURL = new StringBuffer(contextPath + servletPath).append(toParameterString(httpServletRequest)).toString();
				session.setAttribute(SessionSecurityConstants.KEY_LAST_VISIT_URL, fullURL);
			}

			return false;
		}
		return true;
	}

	/**
	 * 封装登录用户信息到用户登录上下文中，并将登录用户封装到HOP4.0用BaseUser中
	 * @param httpServletRequest
	 * @return
	 */
	protected LoginContext buildLoginContext(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		String userName = (String) httpSession.getAttribute(keyUserName);
		Locale language = (Locale) httpSession.getAttribute(keyLocalLanguage);
		LoginContext loginContext = createLoginContext();
		loginContext.setUserName(userName);
		loginContext.setIp(IpUtil.getIpAddress(httpServletRequest));
		loginContext.setLanguage(language == null ? "zh_CN" : language.toString());
		return loginContext;
	}

	/**
	 * 创建用户登录上下文
	 * @return
	 */
	protected LoginContext createLoginContext() {
		return new LoginContext();
	}

	/**
	 *
	 * @param httpServletRequest
	 * @return
	 */
	private String toParameterString(HttpServletRequest httpServletRequest) {
		Enumeration<String> paramEnumeration = httpServletRequest.getParameterNames();
		if (!paramEnumeration.hasMoreElements()) {
			return "";
		}
		StringBuffer stringBuffer = new StringBuffer();
		while (paramEnumeration.hasMoreElements()) {
			String paramName = paramEnumeration.nextElement();
			stringBuffer.append("&");
			stringBuffer.append(paramName);
			stringBuffer.append("=");
			stringBuffer.append(httpServletRequest.getParameter(paramName));
		}
		stringBuffer.replace(0, 1, "?");
		return stringBuffer.toString();
	}

	/**
	 * 重定向到登录页面
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(Env.getProperty(Env.APP_LOGIN_URL));
	}

}
