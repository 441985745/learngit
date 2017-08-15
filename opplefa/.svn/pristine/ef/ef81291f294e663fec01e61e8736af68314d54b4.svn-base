package com.opple.fa.purchase.webapp.controller;

import com.opple.fa.config.entity.AttchConfig;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.AttchConfigService;
import com.opple.fa.config.service.UserInfoService;
import com.opple.fa.purchase.entity.PurchaseCommon;
import com.opple.fa.purchase.entity.ReceiveGoods;
import com.opple.fa.purchase.entity.ReceiveGoodsDetail;
import com.opple.fa.purchase.entity.ReceiveGoodsMapping;
import com.opple.fa.purchase.model.ReceiveGoodsModel;
import com.opple.fa.purchase.model.ReceiveGoodsWrapper;
import com.opple.fa.purchase.service.CheckAcceptMappingService;
import com.opple.fa.purchase.service.PurchaseCommonService;
import com.opple.fa.purchase.service.ReceiveGoodsDetailService;
import com.opple.fa.purchase.service.ReceiveGoodsMappingService;
import com.opple.fa.purchase.service.ReceiveGoodsService;
import com.opple.security.SessionSecurityConstants;
import com.opple.util.Env;
import com.opple.util.ExecuteResult;
import com.opple.util.ExportExcle;
import com.opple.util.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 采购收货
 */
@Controller
@RequestMapping("/receiveGoods")
public class ReceiveGoodsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemandController.class);

    @Resource
    private ReceiveGoodsMappingService receiveGoodsMappingService;
    @Resource
    private ReceiveGoodsService receiveGoodsService;
    @Resource
    private ReceiveGoodsDetailService receiveGoodsDetailService;
    @Resource
    private PurchaseCommonService purchaseCommonService;
    @Resource
    private AttchConfigService attchConfigService;
    @Resource
    private CheckAcceptMappingService checkAcceptMappingService;
    @Resource
    private UserInfoService userInfoService;

    /**
     * 存放位置弹框
     *
     * @param storageLocation
     * @return
     */
    @RequestMapping("/api/searchStorageLocationByOfficeLocation")
    @ResponseBody
    public List<ReceiveGoodsMapping> searchStorageLocationByOfficeLocation(String storageLocation, String officeLocation) {
        ReceiveGoodsMapping rgm = new ReceiveGoodsMapping();
        rgm.setOfficeLocation(officeLocation);
        rgm.setStorageLocation(storageLocation);
        List<ReceiveGoodsMapping> storageLocationList = receiveGoodsMappingService.searchStorageLocationByOfficeLocation(rgm);
        return storageLocationList;
    }


    /**
     * 保存收货单（同时需要保存资产卡片信息）
     *
     * @param receiveGoodsWrapper
     * @return
     */
    @RequestMapping(value = "/api/saveReceiveGoods")
    @ResponseBody
    public ExecuteResult<ReceiveGoods> saveReceiveGoods(@RequestBody ReceiveGoodsWrapper receiveGoodsWrapper, HttpServletRequest req) {

        ExecuteResult<ReceiveGoods> executeResult = new ExecuteResult<ReceiveGoods>();
        try {
            //头
            ReceiveGoods receiveGoods = receiveGoodsWrapper.getReceiveGoods();
            //行
            List<ReceiveGoodsDetail> receiveGoodsDetails = receiveGoodsWrapper.getReceiveGoodsDetails();
//		 行对应的需求明细
            List<ReceiveGoodsMapping> receiveGoodsMappings = receiveGoodsWrapper.getReceiveGoodsMappings();
//		 行对应的sap资产明细
            List<PurchaseCommon> purchaseCommons = receiveGoodsWrapper.getPurchaseCommons();
            //判断是否为草稿，若是草稿，判断订单是否有已关闭的，若有，不能提交
            if (STATUS.YES.getStatus().equals(receiveGoods.getDraft())) {
                boolean flag = false;
                for (PurchaseCommon purchaseCommon : purchaseCommons) {
                    String orderStatus = receiveGoodsService.searchPurchaseOrderStatus(purchaseCommon.getPurchaseId());
                    if ("关闭".equals(orderStatus)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    executeResult.addErrorMessage("资产明细中有已关闭单据，不能保存！");
                    return executeResult;
                }
            }
            String username = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
            receiveGoods.setCuserName(username);
            String usernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
            receiveGoods.setCuserCode(usernameCode);
            /**
             * 根据资产类型和所属成本中心，去配置表TB_FA_ATTACH_CONFIG中查询归口部门管理员（工程师）
             */
            AttchConfig attchConfig = new AttchConfig();
            attchConfig.setCostcenterCode(receiveGoods.getCostCenterCode());
            attchConfig.setAssetType(receiveGoods.getAssetsType());
            AttchConfig attchConfig1 = attchConfigService.searchByCostcenterCodeAndAssetType(attchConfig);
            receiveGoods.setAttachDepartAdminCode(attchConfig1.getAttachDepartAdminCode());
            receiveGoods.setAttachDepartAdminName(attchConfig1.getAttachDepartAdminName());
//		    先保存收货单的头 saveReceiveGoods方法已经移植到save方法中
//          receiveGoodsService.saveReceiveGoods(receiveGoods); //收货主表中保存基本信息
            //purchase_common表、收货详情表、tb_fa_purchase_apply_mapping 采购订单明细和采购申请明细关联表进行保存
            receiveGoodsMappingService.save(receiveGoods, receiveGoodsDetails, receiveGoodsMappings, purchaseCommons);

        } catch (Exception e) {
            LOGGER.error("Exception", e);
            executeResult.addErrorMessage("收货失败");
        }

        executeResult.setSuccessMessage("收货成功");
        return executeResult;
    }


    /**
     * 修改页面
     *
     * @param receiveGoodsId
     * @return
     */
    @RequestMapping("/updateReceiveGoods")
    public String updateReceiveGoods(String receiveGoodsId, Model model) {
        //		查询收货头
        ReceiveGoods receiveGoods = receiveGoodsService.searchReceiveGoodsByReceiveGoodsId(receiveGoodsId);
//		查询收货行
        List<ReceiveGoodsDetail> receiveGoodsDetail = receiveGoodsDetailService.searchReceiveGoodsDetailByReceiveGoodsId(receiveGoodsId);
//		查询sap资产明细
        List<PurchaseCommon> purchaseCommon = purchaseCommonService.searchPurchaseCommonByReceiveGoodsId(receiveGoodsId);
        /**
         * 获取审批流流程的历史记录
         */
        model.addAttribute("receiveGoods", receiveGoods);
        model.addAttribute("receiveGoodsDetail", receiveGoodsDetail);
        model.addAttribute("purchaseCommon", purchaseCommon);


        return "purchase/receive_goods_update1";
    }

    /**
     * 修改 保存收货单
     *
     * @param receiveGoodsWrapper
     * @return
     */
    @RequestMapping(value = "/api/updateSaveReceiveGoods1")
    @ResponseBody
    public ExecuteResult<ReceiveGoods> updateSaveReceiveGoods(@RequestBody ReceiveGoodsWrapper receiveGoodsWrapper, HttpServletRequest req) {
        ExecuteResult<ReceiveGoods> executeResult = new ExecuteResult<ReceiveGoods>();
        //头
        ReceiveGoods receiveGoods = receiveGoodsWrapper.getReceiveGoods();
        //行
        List<ReceiveGoodsDetail> receiveGoodsDetails = receiveGoodsWrapper.getReceiveGoodsDetails();
        //行对应的需求明细
//        List<ReceiveGoodsMapping> receiveGoodsMappings = receiveGoodsWrapper.getReceiveGoodsMappings();
        List<ReceiveGoodsMapping> purchaseApplyMappingList = receiveGoodsMappingService.searchPurchaseApplyMappingByPurchaseId(receiveGoods.getPurchaseId());
        //行对应的sap资产明细
        List<PurchaseCommon> purchaseCommons = receiveGoodsWrapper.getPurchaseCommons();
        //Long isSave = receiveGoodsMappingService.updateSaveReceiveGoods1(receiveGoods, receiveGoodsDetails, purchaseCommons);


        try {
            receiveGoodsMappingService.updateSaveReceiveGoods1(receiveGoods, receiveGoodsDetails, purchaseCommons, purchaseApplyMappingList);
        } catch (Exception e) {
            LOGGER.error("e", e);
            executeResult.addErrorMessage("修改失败");
        }
        return executeResult;
    }


    /**
     * 查看
     *
     * @param receiveGoodsId
     * @param model
     * @return
     */
    @RequestMapping("/showReceiveGoods")
    public String showReceiveGoods(Model model, HttpServletRequest req, Long offset, Long pageSize, String receiveGoodsId, String typeId) {
//		查询收货头
        ReceiveGoods receiveGoods = receiveGoodsService.searchReceiveGoodsByReceiveGoodsId(receiveGoodsId);
//		查询收货头
        List<ReceiveGoodsDetail> receiveGoodsDetail = receiveGoodsDetailService.searchReceiveGoodsDetailByReceiveGoodsId(receiveGoodsId);
//		查询sap资产明细
        List<PurchaseCommon> purchaseCommon = purchaseCommonService.searchPurchaseCommonByReceiveGoodsId(receiveGoodsId);

        /**
         * 获取审批流流程的历史记录
         */
        /*Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workflowService.showApproveHistory(receiveGoodsId, typeId, pager);
        appHistoryPager.setUri(req.getRequestURI());
        model.addAttribute("appHistoryPager", appHistoryPager);*/

        model.addAttribute("receiveGoods", receiveGoods);
        model.addAttribute("receiveGoodsDetail", receiveGoodsDetail);
        model.addAttribute("purchaseCommon", purchaseCommon);

        return "purchase/receive_goods_show";
    }


    /**
     * zhangshulong代码
     */
    /**
     * 分页查询 --- 查询按钮
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @param receiveGoodsMapping
     * @return
     */
    @RequestMapping("/searchReceiveGoodsMapping")
    public String searchReceiveGoodsMapping1(Model model, HttpServletRequest req, Long offset, Long pageSize, ReceiveGoodsMapping receiveGoodsMapping) {
        if ("".equals(receiveGoodsMapping.getDoProcess()) || (null == (receiveGoodsMapping.getDoProcess()))) { //待收货
            receiveGoodsMapping.setDoProcess("Y");
        }
        receiveGoodsMapping.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));
        String loginUserName = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        // receiveGoodsMapping.setBuyerName(loginUserName);
        String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginUserCode(loginUserCode);
        List<UserInfo> searchCauthorizerCode = userInfoService.searchCauthorizerCode(userInfo);

        //判断当前登陆人是采购员还是经理，如果是经理，则能查看所有人的
        AttchConfig attchConfig = attchConfigService.searchByPurchaseManagerCode(loginUserCode);
        if (null == attchConfig) { //采购员
            //receiveGoodsMapping.setBuyerCode(loginUserCode);
            receiveGoodsMapping.setLoginUserName(loginUserName);
            receiveGoodsMapping.setLoginUserCode(loginUserCode);
            Pager<ReceiveGoodsMapping> pager = new Pager<ReceiveGoodsMapping>();
            pager.setUri(req.getRequestURI());
            pager.setOffset(null == offset ? pager.getOffset() : offset);
            pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
            Pager<ReceiveGoodsMapping> receiveGoodsMappingPager = null;
            if ("Y".equals(receiveGoodsMapping.getDoProcess()) || "ALL".equals(receiveGoodsMapping.getDoProcess())) { //请选择和待收货显示同样信息
                receiveGoodsMappingPager = receiveGoodsMappingService.searchReceiveGoodsMappingForReceive(pager, receiveGoodsMapping);
            } else { //已收货（直接从收货详情表中查）
                receiveGoodsMappingPager = receiveGoodsMappingService.searchReceiveGoodsMapping(pager, receiveGoodsMapping);
            }
            for (ReceiveGoodsMapping rgm : receiveGoodsMappingPager.getRecords()) {
                if (loginUserCode.equals(rgm.getBuyerCode())) {
                    rgm.setIsAuthorize("Y");
                    continue;
                }
                for (UserInfo userInfo2 : searchCauthorizerCode) {
                    if (rgm.getBuyerCode().equals(userInfo2.getCauthorizerCode())) {
                        rgm.setIsAuthorize("Y");
                    }
                }
            }
            //Pager<ReceiveGoodsMapping> receiveGoodsMappingPager = receiveGoodsMappingService.searchReceiveGoodsMapping(pager, receiveGoodsMapping);
            receiveGoodsMappingPager.setUri(req.getRequestURI());
            model.addAttribute("receiveGoodsMappingPager", receiveGoodsMappingPager);
            model.addAttribute("isPurchaseManager", "N"); //不是采购经理
        } else {
            String ids = "";
            ids = "'" + attchConfig.getBuyerCodea() + "','" + attchConfig.getBuyerCodeb() + "','" + attchConfig.getBuyerCodec() + "','" + attchConfig.getBuyerCoded() + "'";
            //当查询条件采购员为空时，根据采购经理下级人员查数据，否则根据buyerName查数据
            if ("".equals(receiveGoodsMapping.getBuyerName() == null ? "" : receiveGoodsMapping.getBuyerName().trim())) {
                receiveGoodsMapping.setBuyerCode(ids);
            }
            /*receiveGoodsMapping.setLoginUserName(loginUserName);
            receiveGoodsMapping.setLoginUserCode(loginUserCode);*/
            Pager<ReceiveGoodsMapping> pager = new Pager<ReceiveGoodsMapping>();
            pager.setUri(req.getRequestURI());
            pager.setOffset(null == offset ? pager.getOffset() : offset);
            pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
            Pager<ReceiveGoodsMapping> receiveGoodsMappingPager = null;
            if ("Y".equals(receiveGoodsMapping.getDoProcess()) || "ALL".equals(receiveGoodsMapping.getDoProcess())) { //请选择和待收货显示同样信息
                receiveGoodsMappingPager = receiveGoodsMappingService.searchReceiveGoodsMappingForReceiveAll(pager, receiveGoodsMapping);
            } else {
                receiveGoodsMappingPager = receiveGoodsMappingService.searchAllReceiveGoodsMapping(pager, receiveGoodsMapping);
            }
            for (ReceiveGoodsMapping rgm : receiveGoodsMappingPager.getRecords()) {
                if (loginUserCode.equals(rgm.getBuyerCode())) {
                    rgm.setIsAuthorize("Y");
                    continue;
                }
                for (UserInfo userInfo2 : searchCauthorizerCode) {
                    if (rgm.getBuyerCode().equals(userInfo2.getCauthorizerCode())) {
                        rgm.setIsAuthorize("Y");
                    }
                }
            }
            //Pager<ReceiveGoodsMapping> receiveGoodsMappingPager = receiveGoodsMappingService.searchAllReceiveGoodsMapping(pager, receiveGoodsMapping);
            receiveGoodsMappingPager.setUri(req.getRequestURI());
            model.addAttribute("receiveGoodsMappingPager", receiveGoodsMappingPager);
            model.addAttribute("isPurchaseManager", "Y"); //是采购经理
        }
        List<UserInfo> companys = userInfoService.searchAllCompany();
        model.addAttribute("companys", companys);
        return "purchase/receive_goods_list";
    }

    @RequestMapping("/api/searchHavingDraft")
    @ResponseBody
    public String searchHavingDraft(String purchaseId) {
        /**
         * 查询此订单下对应的所有收货单
         * 如果有草稿，则不能进行收货，需要对草稿进行处理
         */
        List<ReceiveGoods> receiveGoodsList = null;
        try {
            receiveGoodsList = receiveGoodsService.searchReceiveGoodsByPurchaseId(purchaseId);
        } catch (Exception e) {
            LOGGER.error("e", e);
        }
        String flag = "0";
        for (ReceiveGoods receiveGoods : receiveGoodsList) {
            if (STATUS.YES.getStatus().equals(receiveGoods.getDraft())) { //如果是草稿
                flag = "1";
                break;
            }
        }
        return flag;
    }

    /**
     * 收货按钮
     * 收货单 头 行（全部的行，待选择）
     *
     * @param model
     * @param purchaseId
     * @return
     */
    @RequestMapping("/searchPurchaseOrderMappingByPurchaseId")
    public String searchPurchaseOrderMappingByPurchaseId1(Model model, String purchaseId) {
        /**
         * 订单主表、详情表，收货主表、详情表关联查询，根据详情表对应的主表主键查询主表中信息。
         * receiveGoodsMapping当中存放的是头信息，包括点击收货按钮后弹出的页面page1，包括点击page1中确定按钮后弹出的窗口page3，它们
         * 当中的头信息，都存放在这个对象中。
         */
        ReceiveGoodsMapping receiveGoodsMapping = receiveGoodsMappingService.searchPurchaseOrderMappingByPurchaseId(purchaseId);
        /**
         * 订单详情表和收货详情表关联查询
         * receiveGoodsMappingList当中存放的是点击收货按钮后弹出的页面page1中的行明细信息，
         * 以及各种单据号，存放page3在序号后的隐藏域中。包括：申请明细编码，订单明细编码
         */
        List<ReceiveGoodsMapping> receiveGoodsMappingList = receiveGoodsMappingService.searchPurchaseOrderDetailMappingByPurchaseId(purchaseId);

        // 生产收货编号
        String str = "CGSH";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        str += sdf.format(date);
        Long searchNextSequence = receiveGoodsService.searchNextSequence();
        // 0 代表前面补充0 4 代表长度为4 d 代表参数为正数型
        str += String.format("%04d", searchNextSequence);
        /**
         * 生成的收货单号会放在弹出窗口page3的头信息中。
         */
        receiveGoodsMapping.setReceiveGoodsId(str);
        receiveGoodsMapping.setReceiveDate(date);

        model.addAttribute("receiveGoodsMapping", receiveGoodsMapping);
        model.addAttribute("receiveGoodsMappingList", receiveGoodsMappingList);
        return "purchase/receive_goods_add";
    }

    /**
     * 更改收货数量    需求明细   查询  全部获得(页面加载完后直接执行)
     * js中有变量purchaseApplyMappingList来接受此list
     * 修改收获数量弹窗page2中遍历purchaseApplyMappingList展示。
     *
     * @param purchaseId
     * @return
     */
    @RequestMapping("/api/searchPurchaseApplyMappingByPurchaseId")
    @ResponseBody
    public List<ReceiveGoodsMapping> searchPurchaseApplyMappingByPurchaseId(String purchaseId) {
        List<ReceiveGoodsMapping> purchaseApplyMappingList = receiveGoodsMappingService.searchPurchaseApplyMappingByPurchaseId(purchaseId);
        return purchaseApplyMappingList;
    }

    /**
     * page1点击确定按钮，弹出page3弹窗
     * 获取收货单的所有行（前台进行匹配行 展示）
     *
     * @param purchaseId
     * @return
     */
    @RequestMapping("/api/addReceiveGoodsLine")
    @ResponseBody
    public List<Object> addReceiveGoodsLine(String purchaseId) {
        //收货单新增页面的行明细，根据订单id获取行明细
        List<ReceiveGoodsMapping> receiveGoodsMappingline = receiveGoodsMappingService.searchPurchaseOrderDetailMappingByPurchaseId(purchaseId);
        /**
         * 下面是获得sap资产编码（在这之前应该先，查询公共表里已经存在的sap次级编码，然后下面查询出，其他的）
         */
        List<ReceiveGoodsMapping> pcrchaseCommonSapAssetsCodeList = receiveGoodsMappingService.searchPurchaseCommonSapAssetsCodeByPurchaseId(purchaseId);
        PurchaseCommon pc = null;
        List<PurchaseCommon> pclifst = new ArrayList<PurchaseCommon>();
        for (ReceiveGoodsMapping rgd : pcrchaseCommonSapAssetsCodeList) {
            pc = new PurchaseCommon();
            pc.setDemandId(rgd.getDemandId());
            pc.setDemandDetailId(rgd.getDemandDetailId());
            pc.setPurchaseId(rgd.getPurchaseId());
            pc.setPurchaseDetailId(rgd.getPurchaseDetailId());
            pc.setApplyDetailId(rgd.getApplyDetailId());
            pc.setAssetsName(rgd.getAssetsName());
            pc.setSapAssetsCode(rgd.getSapAssetsCode());
            pc.setSapMainAssetCode(rgd.getSapMainAssetCode());
            pc.setSapSecAssetCode(rgd.getSapSecAssetCode());
            pc.setRecWarrantyPeriod(rgd.getRecWarrantyPeriod());
            pc.setStorageLocation(rgd.getStorageLocation());
            pc.setBrand(rgd.getBrand());
            pc.setFunctions(rgd.getFunctions());
            pclifst.add(pc);
        }

//		查询所有的sap资产编码，接口表(查处所有的资产编码后，从里面删除已经用掉的sap资产编码，把没用过的资产编码分配给刚收货的设备)
        List<ReceiveGoodsModel> sapAssetsCodeETZMM = receiveGoodsMappingService.searchSapAssetsCodeByPurchaseId(purchaseId);
        List<PurchaseCommon> sapAssetsCodelist = new ArrayList<PurchaseCommon>();
        for (ReceiveGoodsModel rgm : sapAssetsCodeETZMM) {
            pc = new PurchaseCommon();
            pc.setDemandId(rgm.getDemandId());
            pc.setDemandDetailId(rgm.getDemandDetailId());
            pc.setPurchaseId(rgm.getPurchaseId());
            pc.setPurchaseDetailId(rgm.getPurchaseDetailId());
            pc.setApplyDetailId(rgm.getApplyDetailId());
            pc.setAssetsName(rgm.getAssetsName());
            pc.setSapAssetsCode(rgm.getSapAssetsCode());
            pc.setSapMainAssetCode(rgm.getSapMainAssetCode());
            pc.setSapSecAssetCode(rgm.getSapSecAssetCode());
            pc.setCheckApplyCode(rgm.getCheckApplyCode());
            pc.setCheckApplyUser(rgm.getCheckApplyUser());
            pc.setBrand(rgm.getBrand() == null ? "" : rgm.getBrand());
            pc.setBudgetDepartmentCode(rgm.getBudgetDepartmentCode());
            pc.setBudgetDepartmentName(rgm.getBudgetDepartmentName());
            pc.setCostCenter(rgm.getCostCenter());
            pc.setCostCenterCode(rgm.getCostCenterCode());
            pc.setFunctions(rgm.getFunctions());
            sapAssetsCodelist.add(pc);
        }

        if (sapAssetsCodelist.size() > 0) {
            Iterator<PurchaseCommon> stuIter = sapAssetsCodelist.iterator();
            while (stuIter.hasNext()) {
                PurchaseCommon pc1 = stuIter.next();
                String code = pc1.getSapAssetsCode();

                for (int j = 0; j < pclifst.size(); j++) {
                    String ep = pclifst.get(j).getSapAssetsCode();
                /*if (code.equals(ep)) {
                    stuIter.remove();
                }*/
                    /**
                     * 当需求单号不为空时，说明这条记录收过货了,通过资产总编码确定唯一记录，从list中删掉
                     */
                    if (null != pclifst.get(j).getDemandId() && !"".equals(pclifst.get(j).getDemandId())) {
                        if (code.equals(ep)) {
                            stuIter.remove();
                        }
                    }
                }
            }
        }

//		去除已经存在的sap资产编码
        /**
         * 下面这种removeall方法会出现bug！移除不掉！原因：
         * 第二个for循环中对象加了pc.setCheckApplyCode(rgm.getCheckApplyCode());
         *                   pc.setCheckApplyUser(rgm.getCheckApplyUser());
         * 而第一个for循环中对象没有，因此对象不同，因此移除不掉！
         */
        // sapAssetsCodelist.removeAll(pclifst);

        List<Object> objList = new ArrayList<Object>();
        objList.add(receiveGoodsMappingline);
//		objList.add(sapAssetsCodeETZMM);
        objList.add(sapAssetsCodelist);

        return objList;
    }

    /**
     * 导出功能
     */
    @RequestMapping(value = "api/exportReceive", method = RequestMethod.GET)
    @ResponseBody
    public void exportReceive(ModelMap model, HttpServletRequest req, HttpServletResponse response, ReceiveGoodsMapping receiveGoodsMapping) {
        if ("".equals(receiveGoodsMapping.getDoProcess()) || (null == (receiveGoodsMapping.getDoProcess()))) { //待收货
            receiveGoodsMapping.setDoProcess("Y");
        }
        receiveGoodsMapping.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));
        String loginUserName = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        AttchConfig attchConfig = attchConfigService.searchByPurchaseManagerCode(loginUserCode);
        List<ReceiveGoodsMapping> list = new ArrayList<ReceiveGoodsMapping>();
        if (null == attchConfig) {
            receiveGoodsMapping.setLoginUserName(loginUserName);
            receiveGoodsMapping.setLoginUserCode(loginUserCode);
            if ("Y".equals(receiveGoodsMapping.getDoProcess()) || "ALL".equals(receiveGoodsMapping.getDoProcess())) { //请选择和待收货显示同样信息

                list = receiveGoodsMappingService.exportReceive(receiveGoodsMapping);
            } else {

                list = receiveGoodsMappingService.exportReceiveHavingReceived(receiveGoodsMapping);
            }


        } else {
            String ids = "";
            ids = "'" + attchConfig.getBuyerCodea() + "','" + attchConfig.getBuyerCodeb() + "','" + attchConfig.getBuyerCodec() + "','" + attchConfig.getBuyerCoded() + "'";
            //当查询条件采购员为空时，根据采购经理下级人员查数据，否则根据buyerName查数据
            if ("".equals(receiveGoodsMapping.getBuyerName() == null ? "" : receiveGoodsMapping.getBuyerName().trim())) {
                receiveGoodsMapping.setBuyerCode(ids);
            }
            if ("Y".equals(receiveGoodsMapping.getDoProcess()) || "ALL".equals(receiveGoodsMapping.getDoProcess())) { //请选择和待收货显示同样信息

                list = receiveGoodsMappingService.exportReceive1(receiveGoodsMapping);
            } else {

                list = receiveGoodsMappingService.export1ReceiveHavingReceived(receiveGoodsMapping);
            }

        }


        String fileName = "收货详情.xlsx";
        String sheetName = "收货详情";
        int[] columnWidths = new int[]{10, 10, 10};
        Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
        data.put("1", new Object[]{"资产类型", "采购订单号（SAP）", "订单编码", "收货单编码", "订单数量", "收货数量", "采购员", "供应商编码", "供应商", "收货单日期", "公司", "是否草稿"});
        int dataIdx = 2;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder assetsType = null;
        StringBuilder puchaseOderSapId = null;
        StringBuilder purchaseId = null;
        StringBuilder receiveGoodsId = null;
        StringBuilder applyCountAll = null;
        StringBuilder goodsCountAll = null;
        StringBuilder buyerName = null;
        StringBuilder supplierCode = null;
        StringBuilder supplierName = null;
        StringBuilder receiveDate = new StringBuilder("");
        StringBuilder companyName = null;
        StringBuilder draft = new StringBuilder("");
        for (ReceiveGoodsMapping receiveGoodsMappings : list) {
            assetsType = new StringBuilder(receiveGoodsMappings.getAssetsType() == null ? "" : receiveGoodsMappings.getAssetsType());
            puchaseOderSapId = new StringBuilder(receiveGoodsMappings.getPurchaseOrderSapId() == null ? "" : receiveGoodsMappings.getPurchaseOrderSapId());
            purchaseId = new StringBuilder(receiveGoodsMappings.getPurchaseId() == null ? "" : receiveGoodsMappings.getPurchaseId());
            receiveGoodsId = new StringBuilder(receiveGoodsMappings.getReceiveGoodsId() == null ? "" : receiveGoodsMappings.getReceiveGoodsId());
            applyCountAll = new StringBuilder(receiveGoodsMappings.getApplyCountAll().toString());
            goodsCountAll = new StringBuilder(receiveGoodsMappings.getGoodsCountAll().toString());
            buyerName = new StringBuilder(receiveGoodsMappings.getBuyerName() == null ? "" : receiveGoodsMappings.getBuyerName());
            supplierCode = new StringBuilder(receiveGoodsMappings.getSupplierCode() == null ? "" : receiveGoodsMappings.getSupplierCode());
            supplierName = new StringBuilder(receiveGoodsMappings.getSupplierName() == null ? "" : receiveGoodsMappings.getSupplierName());

            if (null != receiveGoodsMappings.getReceiveDate() && !"".equals(receiveGoodsMappings.getReceiveDate())) {
                receiveDate = new StringBuilder(sdf.format(receiveGoodsMappings.getReceiveDate()));

            }
            companyName = new StringBuilder(receiveGoodsMappings.getCompanyName() == null ? "" : receiveGoodsMappings.getCompanyName());

            if (null != receiveGoodsMappings.getDraft() && !"".equals(receiveGoodsMappings.getDraft())) {
                if ("Y".equals(receiveGoodsMappings.getDraft())) {
                    draft = new StringBuilder("是");
                } else {
                    draft = new StringBuilder("否");
                }
            }
            Object[] newRow = new Object[]{
                    assetsType.toString(),
                    puchaseOderSapId.toString(),
                    purchaseId.toString(),
                    receiveGoodsId.toString(),
                    applyCountAll.toString(),
                    goodsCountAll.toString(),
                    buyerName.toString(),
                    supplierCode.toString(),
                    supplierName.toString(),
                    receiveDate.toString(),
                    companyName.toString(),
                    draft.toString()
            };
            data.put("" + dataIdx++, newRow);
        }
        try {
            ExportExcle.generateExcel(response, data, fileName, sheetName, columnWidths);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
    }

    @RequestMapping("/api/deleteReceiveGoods")
    @ResponseBody
    public ExecuteResult<ReceiveGoods> deleteReceiveGoods(@RequestParam(value = "list[]", required = false) List<String> list) {
        ExecuteResult<ReceiveGoods> executeResult = new ExecuteResult<ReceiveGoods>();
        try {
            receiveGoodsService.deleteReceiveGoods(list); //删除所有收货时保存的数据和回写的数据
        } catch (Exception e) {
            LOGGER.error("e", e);
            executeResult.addErrorMessage("删除失败");
        }
        return executeResult;
    }

    @RequestMapping("/api/updateCloseReceiveGoods")
    @ResponseBody
    public ExecuteResult<ReceiveGoods> updateCloseReceiveGoods(@RequestParam(value = "list[]", required = false) List<String> list) {
        ExecuteResult<ReceiveGoods> executeResult = new ExecuteResult<ReceiveGoods>();
        try {
            receiveGoodsService.updateCloseReceiveGoods(list); //只更改收货单主表中orderStatus字段（0未关闭，1已关闭），改为1
        } catch (Exception e) {
            LOGGER.error("e", e);
            executeResult.addErrorMessage("关闭失败");
        }
        return executeResult;
    }

    @RequestMapping("api/searchCanBeDelete")
    @ResponseBody
    public String searchCanBeDelete(@RequestParam(value = "list[]", required = false) List<String> list) {
        String data = "0"; //0代表没有被验收过
        for (String receiveId : list) {
            Long num = checkAcceptMappingService.getCountCheckAcceptanceByReceiveGoodsId(receiveId);
            if (num > 0) {
                data = "1";
            }
        }
        return data;
    }

    /**
     * 查询验收人（根据同一个需求部门查找）
     *
     * @param model
     * @param request
     * @param searchName 手填查询
     * @param searchCode 手填查询
     * @return
     */
    @RequestMapping("/api/getUserByDepartList")
    @ResponseBody
    public List<ReceiveGoods> getUserByDepartList(Model model, HttpServletRequest request,
                                                  String userCode, String userName, String budgetDepartmentCode, String searchName, String searchCode) {
        List<ReceiveGoods> list = receiveGoodsService.getUserByDepartList(userCode, userName, budgetDepartmentCode, searchName, searchCode);
        return list;

    }

    /**
     * 查询此条收货单是否被验收，如果已经验收，则不可修改
     *
     * @param receiveGoodsId
     * @return
     */
    @RequestMapping("/api/isChecked")
    @ResponseBody
    public String isChecked(String receiveGoodsId) {
        Long num = checkAcceptMappingService.getCountCheckAcceptanceByReceiveGoodsId(receiveGoodsId);
        return num.toString();

    }
}