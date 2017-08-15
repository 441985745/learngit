package com.opple.fa.config.webapp.controller;

import com.opple.fa.config.entity.CostCenterPlatform;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.service.CostCenterPlatformService;
import com.opple.fa.config.service.UserInfoService;
import com.opple.sap.SAPExchageRate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private CostCenterPlatformService costCenterPlatformService;

    /**
     * 根据部门树查询所属公司和成本中心
     *
     * @param ckscode
     * @return
     */
    @RequestMapping(value = "/api/cksDept")
    @ResponseBody
    public UserInfo cksDept(String ckscode) {

        UserInfo treeObj = userInfoService.treeDept(ckscode);
        return treeObj;
    }

    /**
     * 根据部门查询公司
     *
     * @param ckscode
     * @return
     */
    @RequestMapping(value = "/api/getCompany")
    @ResponseBody
    public List<UserInfo> getCompany(String ckscode) {

        List<UserInfo> treeObj = userInfoService.getCompany(ckscode);
        return treeObj;
    }

    @RequestMapping(value = "/api/getCostCenter")
    @ResponseBody
    public UserInfo getCostCenter(UserInfo userInfo) {
        return userInfoService.getCostCenter(userInfo);
    }

    /**
     * 根据公司查询 资产类型
     *
     * @param companyCode
     * @return
     */
    @RequestMapping(value = "/api/comAsset")
    @ResponseBody
    public List<UserInfo> comAsset(String companyCode) {
        UserInfo userInfo = new UserInfo();
        userInfo.setCcompanycode(companyCode);
        List<UserInfo> assetlist = userInfoService.searchAssetsTypeInfo(userInfo);
        return assetlist;
    }

    /**
     * 根据资产类型 查询 车间类型
     *
     * @param depcoms = [companyCode, departType]
     * @return
     */
    @RequestMapping(value = "/api/assetWork")
    @ResponseBody
    public List<UserInfo> assetWork(String[] depcoms) {

        if ("机器设备".equals(depcoms[1])) {
            UserInfo userInfo = new UserInfo();
            userInfo.setCcompanycode(depcoms[0]);
            userInfo.setAssetsType(depcoms[1]);
            List<UserInfo> worklist = userInfoService.searchDepartInfo(userInfo);
            return worklist;
        }
        return null;
    }

    /**
     * 根据预算部门编号和公司查询 平台
     *
     * @param comdata
     * @return
     */
    /*@RequestMapping(value = "/api/comPlatform")
    @ResponseBody
	public List<UserInfo> comPlatform(String companyCode) {
		UserInfo userInfo = new UserInfo();
		userInfo.setCcompanycode(companyCode);
		List<UserInfo> platformlist = userInfoService.searchPlatformInfo(userInfo);
		return platformlist;
	}*/

    /**
     * 根据成本中心查询 平台
     *
     * @param costCenterCode
     * @return
     */
    @RequestMapping(value = "/api/comPlatform")
    @ResponseBody
    public CostCenterPlatform comPlatform(String costCenterCode) {
        CostCenterPlatform searchPlatformByCostCenterCode = costCenterPlatformService.searchPlatformByCostCenterCode(costCenterCode);
        return searchPlatformByCostCenterCode;
    }

    /**
     * 根据平台 查询 区域
     *
     * @param cplatformdata
     * @return
     */
    @RequestMapping(value = "/api/platformRegion")
    @ResponseBody
    public List<UserInfo> platformRegion(String cplatformdata) {
        UserInfo userInfo = new UserInfo();
        if (cplatformdata.indexOf("家居") != -1 && cplatformdata.indexOf("区域") != -1) {
            userInfo.setCplatform("家居");
            List<UserInfo> arealist = userInfoService.searchAreasInfo(userInfo);
            return arealist;
        } else if (cplatformdata.indexOf("商用") != -1 && cplatformdata.indexOf("区域") != -1) {
            userInfo.setCplatform("商用");
            List<UserInfo> arealist = userInfoService.searchAreasInfo(userInfo);
            return arealist;
        }
        return null;
    }

    /**
     * 根据申请人 查询 所属部门
     *
     * @param employeename
     * @return
     */
    @RequestMapping(value = "/api/empNameDept")
    @ResponseBody
    public List<UserInfo> empNameDept(String employeename) {
        UserInfo userInfo = new UserInfo();
        userInfo.setCemployeename(employeename);
        List<UserInfo> empdeptlist = userInfoService.searchOpdeptInfo(userInfo);
        return empdeptlist;
    }

    /**
     * 根据办公地点和公司 查询采购员
     *
     * @param addresscom = [companyCode, address];
     * @return
     */
    @RequestMapping(value = "/api/addressPurchase")
    @ResponseBody
    public List<UserInfo> addressPurchase(String[] addresscom) {
        UserInfo userInfo = new UserInfo();
//		注意：这里办公地点code没有值，目前用caddressName查询
        userInfo.setCaddressName(addresscom[1]);
        userInfo.setCcompanycode(addresscom[0]);
        List<UserInfo> addresslist = userInfoService.searchPurchaseInfo(userInfo);
        return addresslist;
    }

    /**
     * 根据币种(相对于人民币（CNY）) 查询汇率
     *
     * @param currency
     * @return
     */
    @RequestMapping(value = "/api/currencyRate")
    @ResponseBody
    public UserInfo currencyRate(String currency) {
        String iexchangerate = SAPExchageRate.getExchangeRateFromSAP(currency, "CNY");
        UserInfo userInfo = new UserInfo();
        userInfo.setIexchangerate(iexchangerate);
        return userInfo;
    }

    /**
     * 根据调拨责人编码查询调拨后负责人部门，成本中心和归属公司
     */
    @RequestMapping("/api/searchRelevance")
    @ResponseBody
    public UserInfo searchRelevance(String code) {
        UserInfo userInfo = new UserInfo();
        userInfo.setCemployeecode(code);
        userInfo = userInfoService.searchDepartment(userInfo);
        return userInfo;
    }


    /**
     * 根据办公地点查询调拨后存放位置
     *
     * @return
     */
    @RequestMapping("/api/getCpsition")
    @ResponseBody
    public List<UserInfo> getCpsition(String workAddress) {

        UserInfo userInfo = new UserInfo();
        userInfo.setWorkAddress(workAddress);
        List<UserInfo> listUser = userInfoService.getCpsition(userInfo);
        return listUser;
    }

    /**
     * 离职授权，根据当前登录人判断是否有权限删除修改
     *
     * @param loginUserCode
     * @param applyUserCode
     * @return
     */
    @RequestMapping(value = "/api/isAuthority")
    @ResponseBody
    public Boolean isAuthority(String loginUserCode, String applyUserCode) {
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginUserCode(loginUserCode);
        List<UserInfo> userInfos = userInfoService.searchCauthorizerCode(userInfo);
        Boolean flag = false;
        for (UserInfo userInfo2 : userInfos) {
            if (applyUserCode.equals(userInfo2.getCauthorizerCode())) {
                flag = true;
                break;
            }
        }
        if (loginUserCode.equals(applyUserCode)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 根据部门查询是否是末级部门(0不是，大于等于1是)
     *
     * @param ckscode
     * @return
     */
    @RequestMapping(value = "/api/searchLowDeptCount")
    @ResponseBody
    public Long searchLowDeptCount(String ckscode) {

        return userInfoService.searchLowDeptCount(ckscode);

    }

    /**
     * 根据成本中心编码和公司代码查询内部订单号
     *
     * @param ccompanycode
     * @param ccostcentercode
     * @return
     */
    @RequestMapping(value = "/api/getInternalOrderCode")
    @ResponseBody
    public UserInfo getInternalOrderCode(String ccompanycode, String ccostcentercode) {
        UserInfo userInfo = new UserInfo();
        userInfo.setCcompanycode(ccompanycode);
        userInfo.setCcostcentercode(ccostcentercode);
        return userInfoService.getInternalOrderCode(userInfo);

    }
}
