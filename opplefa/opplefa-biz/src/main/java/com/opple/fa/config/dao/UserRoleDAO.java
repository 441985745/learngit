package com.opple.fa.config.dao;

import com.opple.fa.config.entity.UserRole;



public interface UserRoleDAO {
	/**
	 * 根据用户编码 和 多个角色编码 查询角色数量
	 * @param userRole
	 * @return
	 */
	Long searchUserRole(UserRole userRole);
}
