package com.opple.fa.security.webapp.controller;

import com.opple.fa.config.service.UserInfoService;
import com.opple.fa.security.entity.USERTYPE;
import com.opple.fa.utils.LdapUtil;
import com.opple.security.SessionSecurityConstants;
import com.opple.security.entity.User;
import com.opple.security.service.UserService;
import com.opple.util.Env;
import com.opple.util.ExecuteResult;
import com.opple.util.LoginResult;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Created by phoenix on 2016/9/21.
 */
@Controller
@RequestMapping("/")
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private UserInfoService userInfoService;

    private static final String SOURCE = "PRODUCTION";

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject login(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) throws Exception {

/* 2017/6/16 注释 修改登录验证
       ExecuteResult<User> result = new ExecuteResult<User>();
        LoginResult loginResult = new LoginResult();

        String userCode = user.getUserCode();
        if (!userCode.equals(Env.getProperty("admin.name"))) {
            userCode = userCode.toUpperCase();
        }
        String password = user.getPwd();

        if (StringUtils.isBlank(userCode) || StringUtils.isBlank(password)) {
            result.addErrorMessage("用户名或密码不可为空");
            loginResult.setResult("false");
            return JSONObject.fromObject(loginResult);
        }

        //result = userService.loginUser(user);
        WorkFlowStub stub = new WorkFlowStub();

        WorkFlowStub.UserLogin userLogin = new WorkFlowStub.UserLogin();
        userLogin.setUserCode(userCode);
        userLogin.setPwd(password);
        WorkFlowStub.UserLoginResponse userLoginResponse = stub.userLogin(userLogin);


        if (StringUtils.isNotBlank(userLoginResponse.getUserLoginResult())) {
            result.addErrorMessage(userLoginResponse.getUserLoginResult());
            loginResult.setMsg(result.getErrorMessages().size() > 0 ? result.getErrorMessages().get(0) : "用户名或密码错误");
        } else {
            UserInfo userInfo = new UserInfo();
            userInfo.setLoginUserCode(userCode);
            userInfo = userInfoService.searchUserNameAndType(userInfo);

            request.getSession().setAttribute(SessionSecurityConstants.KEY_USER_NAME, userCode.trim());
            // 其他一些session的信息在这里设置
            //request.getSession().setAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME, user.getUserName().trim());
            request.getSession().setAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME, userInfo.getCusername());
            Object targetUrl = request.getSession().getAttribute(SessionSecurityConstants.KEY_LAST_VISIT_URL);
            //request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
            loginResult.setResult("true");
            loginResult.setMsg("登录认证成功");

//			System.out.println(request.getSession().getMaxInactiveInterval());
            request.getSession().setMaxInactiveInterval(60 * 60 * 2);

            //如果是登录初始页或者是点退出后过来的页面，就跳到Env.APP_URL默认首页。·
            loginResult.setTargetUrl(null == targetUrl || "/exit".equals(targetUrl) || targetUrl.toString().startsWith("/download") || targetUrl.toString().startsWith("/excelModel") ? Env.getProperty(Env.APP_URL) : (String) targetUrl);
        }

        return JSONObject.fromObject(loginResult);*/

        ExecuteResult<User> result = new ExecuteResult<User>();

        String userCode = user.getUserCode();
        String password = user.getPwd();

        LoginResult loginResult = new LoginResult();

        if (StringUtils.isBlank(userCode) || StringUtils.isBlank(password)) {
            result.addErrorMessage("用户名或密码不可为空");
            loginResult.setResult("false");
            return JSONObject.fromObject(loginResult);
        }

        user.setUserCode(userCode);
        if (!userCode.equals(Env.getProperty("admin.name"))) {
            userCode = userCode.toUpperCase();
        }
        user.setPwd(password);

        User user1 = userService.searchUser(user);
        if (null == user1) {
            result.addErrorMessage("用户名错误");
        }
        if (SOURCE.equals(Env.getProperty("env.type")) && result.isSuccess()
                && USERTYPE.EMPLOYEE.getUserType().equals(user1.getUserType())) {
            if (!LdapUtil.LdapAuth(user)) {
                result.addErrorMessage("用户名或密码错误");
            }
            user = user1;
        } else {
            result = userService.loginUser(user);
        }

        if (!result.isSuccess()) {
            loginResult.setResult("false");
            loginResult.setMsg(result.getErrorMessages().size() > 0 ? result.getErrorMessages().get(0) : "用户名或密码错误");
        } else {
            request.getSession().setAttribute(SessionSecurityConstants.KEY_USER_NAME, userCode.trim());
            // 其他一些session的信息在这里设置
            request.getSession().setAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME, user.getUserName().trim());
            Object targetUrl = request.getSession().getAttribute(SessionSecurityConstants.KEY_LAST_VISIT_URL);
            //request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
            loginResult.setResult("true");
            loginResult.setMsg("登录认证成功");
//			System.out.println(request.getSession().getMaxInactiveInterval());
            request.getSession().setMaxInactiveInterval(60 * 60 * 2);

            //如果是登录初始页或者是点退出后过来的页面，就跳到Env.APP_URL默认首页。·
            loginResult.setTargetUrl(null == targetUrl || "/exit".equals(targetUrl) || targetUrl.toString().startsWith("/download") || targetUrl.toString().startsWith("/excelModel") ? Env.getProperty(Env.APP_URL) : (String) targetUrl);
        }

        return JSONObject.fromObject(loginResult);

    }

    @RequestMapping("/loginInit")
    public String loginInit(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping("/exit")
    public String exitLogin(HttpServletRequest request) {
        Enumeration em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        return "login";
    }
}
