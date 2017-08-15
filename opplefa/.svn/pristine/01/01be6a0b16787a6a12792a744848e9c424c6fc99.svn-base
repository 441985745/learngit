package com.opple.fa.security.webapp.controller;

import com.opple.security.SessionSecurityConstants;
import com.opple.security.entity.User;
import com.opple.security.model.UserSearchModel;
import com.opple.security.service.UserService;
import com.opple.util.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/user")
public class UserControlle {

	@Resource
    private UserService userService;

	@RequestMapping("/getUserList")
	public String getUserList(Model model, HttpServletRequest request,
			Long offset, Long pageSize, User user, String parentWindowId) {
		String userCode = (String) request.getSession().getAttribute(
				SessionSecurityConstants.KEY_USER_NAME);

		Pager<User> userPager = new Pager<User>();
		userPager.setOffset(null == offset ? userPager.getOffset() : offset);
		userPager.setPageSize(null == pageSize ? userPager.getPageSize()
				: pageSize);

		if (null != user) {
			user.setUserCode(null != user.getUserCode() ? user.getUserCode()
					.trim() : null);
			user.setUserName(null != user.getUserName() ? user.getUserName()
					.trim() : null);
		}

		UserSearchModel userSearchModel = new UserSearchModel();
		userSearchModel.setUser(user);
		userSearchModel.setUserCode(userCode);
		userPager = userService.getUserListPager(userSearchModel, userPager);
		userPager.setUri(request.getRequestURI());
		userPager.setParentWindowId(parentWindowId);

		model.addAttribute("userPager", userPager);
		model.addAttribute("parentWindowId", parentWindowId);

		return "assetallocation/user_list";
		
	}

	@RequestMapping("/getUserListInAttachConfig")
	public String getUserListInAttachConfig(Model model, HttpServletRequest request,
							  Long offset, Long pageSize, User user, String parentWindowId, String mark) {

		Pager<User> userPager = new Pager<User>();
		userPager.setOffset(null == offset ? userPager.getOffset() : offset);
		userPager.setPageSize(null == pageSize ? userPager.getPageSize() : pageSize);

		if (null != user) {
			user.setUserCode(null != user.getUserCode() ? user.getUserCode().trim() : null);
			user.setUserName(null != user.getUserName() ? user.getUserName().trim() : null);
		}

		userPager = userService.getAllUserListPager(userPager, user);
		userPager.setUri(request.getRequestURI());
		userPager.setParentWindowId(parentWindowId);

		model.addAttribute("userPager", userPager);
		model.addAttribute("mark", mark);
		model.addAttribute("parentWindowId", parentWindowId);

		return "config/user_list";

	}


}
