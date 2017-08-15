package com.opple.fa.config.service.impl;

import com.opple.fa.config.dao.UserInfoDAO;
import com.opple.fa.config.entity.AssetsBudgetFromBUD;
import com.opple.fa.config.entity.BudgetAdjustmentDetail;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.service.UserInfoService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *  * @Description:
 *  * @author lipengju
 *  * @date 2016年10月27日 上午10:13:57
 */
public class UserInfoServiceImpl implements UserInfoService {
    @Getter
    @Setter
    private UserInfoDAO userInfoDAO;

    @Override
    public UserInfo treeDept(String ckscode) {

        return userInfoDAO.treeDept(ckscode);
    }

    @Override
    public List<UserInfo> getCompany(String ckscode) {

        return userInfoDAO.getCompany(ckscode);
    }

    @Override
    public UserInfo getCostCenter(UserInfo userInfo) {
        return userInfoDAO.getCostCenter(userInfo);
    }

    @Override
    public List<UserInfo> searchAssetsTypeInfo(UserInfo userInfo) {
        return userInfoDAO.searchAssetsTypeInfo(userInfo);
    }

    @Override
    public List<UserInfo> searchAddressInfo() {
        return userInfoDAO.searchAddressInfo();
    }

    @Override
    public List<UserInfo> searchPurchaseInfo(UserInfo userInfo) {
        return userInfoDAO.searchPurchaseInfo(userInfo);
    }

    @Override
    public List<UserInfo> searchPlatformInfo(UserInfo userInfo) {
        return userInfoDAO.searchPlatformInfo(userInfo);
    }

    @Override
    public List<UserInfo> searchCurrencyInfo() {
        return userInfoDAO.searchCurrencyInfo();
    }


    @Override
    public List<UserInfo> searchAreasInfo(UserInfo userInfo) {
        return userInfoDAO.searchAreasInfo(userInfo);
    }

    @Override
    public List<UserInfo> searchDepartInfo(UserInfo userInfo) {
        return userInfoDAO.searchDepartInfo(userInfo);
    }

    @Override
    public List<UserInfo> searchOpdeptInfo(UserInfo userInfo) {
        return userInfoDAO.searchOpdeptInfo(userInfo);
    }

    @Override
    public List<UserInfo> searchDeptAllInfo(UserInfo userInfo) {
        return userInfoDAO.searchDeptAllInfo(userInfo);
    }

    @Override
    public UserInfo searchDepartment(UserInfo userInfo) {
        return userInfoDAO.searchDepartment(userInfo);
    }


    @Override
    public List<UserInfo> getCpsition(UserInfo userInfo) {
        if (!"上海".equals(userInfo.getWorkAddress()) && !"吴江".equals(userInfo.getWorkAddress()) && !"中山".equals(userInfo.getWorkAddress()) && !"驻外".equals(userInfo.getWorkAddress())) {
            userInfo.setWorkAddress("其他");
        }
        return userInfoDAO.getCpsition(userInfo);
    }


    @Override
    public UserInfo searchCostcenterName(AssetsBudgetFromBUD assetsBudgetFromBUD) {
        return userInfoDAO.searchCostcenterName(assetsBudgetFromBUD);
    }

    @Override
    public UserInfo searchDepInfoByCostCenter(BudgetAdjustmentDetail budgetAdjustmentDetail) {
        return userInfoDAO.searchDepInfoByCostCenter(budgetAdjustmentDetail);
    }


    @Override
    public UserInfo searchSupplierCode(UserInfo userInfo) {
        return userInfoDAO.searchSupplierCode(userInfo);
    }


    @Override
    public List<UserInfo> searchAllCompany() {
        return userInfoDAO.searchAllCompany();
    }

    @Override
    public List<UserInfo> searchCauthorizerCode(UserInfo userInfo) {
        return userInfoDAO.searchCauthorizerCode(userInfo);
    }

    @Override
    public Long searchLowDeptCount(String ckscode) {
        return userInfoDAO.searchLowDeptCount(ckscode);
    }

    @Override
    public UserInfo searchUserNameAndType(UserInfo userInfo) {
        return userInfoDAO.searchUserNameAndType(userInfo);
    }

    @Override
    public Integer countUserByCodeAndName(String userCode, String userName) {
        return userInfoDAO.countUserByCodeAndName(userCode, userName);
    }

    @Override
    public UserInfo getInternalOrderCode(UserInfo userInfo) {
        return userInfoDAO.getInternalOrderCode(userInfo);
    }
}
