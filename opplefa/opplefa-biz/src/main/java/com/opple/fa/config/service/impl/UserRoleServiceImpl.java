package com.opple.fa.config.service.impl;


import com.opple.fa.config.dao.UserInfoDAO;
import com.opple.fa.config.dao.UserRoleDAO;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.entity.UserRole;
import com.opple.fa.config.service.UserRoleService;
import com.opple.fa.purchase.service.impl.DemandOrderServiceImpl;
import com.opple.workflow.WorkFlowStub;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

/**
 *  * @Description:
 *  * @author lipengju
 *  * @date 2016年10月27日 上午10:13:57
 */
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemandOrderServiceImpl.class);

    @Getter
    @Setter
    private UserRoleDAO userRoleDAO;
    @Getter
    @Setter
    private UserInfoDAO userInfoDAO;

    @Override
    public Long searchUserRole(UserRole userRole, String billCode, String typeCode) {
        Long result = 0L;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.GetNowStepRoleCode getNowStepRoleCode = new WorkFlowStub.GetNowStepRoleCode();
            WorkFlowStub.AgreeFlowParam param = new WorkFlowStub.AgreeFlowParam();
            param.setBillCode(billCode);
            param.setCUserCode(userRole.getCusercode());
            param.setTypeCode(typeCode);
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date());
            param.setCreateDate(instance);

            UserInfo userInfo = new UserInfo();
            userInfo.setLoginUserCode(userRole.getCusercode());
            UserInfo userDeptInfo = userInfoDAO.getUserDeptInfo(userInfo);
            param.setUserDepCode(userDeptInfo.getCkscode());

            getNowStepRoleCode.setParam(param);

            WorkFlowStub.GetNowStepRoleCodeResponse nowStepRoleCode = stub.getNowStepRoleCode(getNowStepRoleCode);
            String getNowStepRoleCodeResult = nowStepRoleCode.getGetNowStepRoleCodeResult();
            if (StringUtils.isNotBlank(getNowStepRoleCodeResult) && userRole.getCrolecode().indexOf(getNowStepRoleCodeResult) != -1) {
                result = 1L;
            }
            return result;
        } catch (Exception e) {
            LOGGER.error("Exception", e);
            return result;
        }

    }

    @Override
    public Long searchUserRole(UserRole userRole) {
        return userRoleDAO.searchUserRole(userRole);
    }


}
