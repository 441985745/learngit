package com.opple.fa.assetcard.webapp.controller;

import com.opple.fa.assetcard.entity.ASSETTYPE;
import com.opple.fa.assetcard.entity.AdministrationAsset;
import com.opple.fa.assetcard.entity.ComputerAsset;
import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcard.entity.ISEXPENSEASSETS;
import com.opple.fa.assetcard.entity.MachineryEquipment;
import com.opple.fa.assetcard.entity.MeasuringInstruments;
import com.opple.fa.assetcard.entity.OFFICELOCATION;
import com.opple.fa.assetcard.entity.USESTATE;
import com.opple.fa.assetcard.model.AdministrativeAssetModel;
import com.opple.fa.assetcard.model.AssetCardWrapper;
import com.opple.fa.assetcard.model.ComputerAssetModel;
import com.opple.fa.assetcard.model.GeneralAssetModel;
import com.opple.fa.assetcard.model.MachineryEquipmentModel;
import com.opple.fa.assetcard.model.MeasuringInstrumentsModel;
import com.opple.fa.assetcard.service.GeneralAssetService;
import com.opple.fa.assetcard.service.ThingsPersonalService;
import com.opple.fa.config.entity.AssetClassification;
import com.opple.fa.config.entity.AssetUnit;
import com.opple.fa.config.entity.AssetsBudgetFromBUD;
import com.opple.fa.config.entity.AttchConfig;
import com.opple.fa.config.entity.CompanyDepCostcenter;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.entity.UserRole;
import com.opple.fa.config.entity.WorkAddress;
import com.opple.fa.config.service.AssetClassificationService;
import com.opple.fa.config.service.AssetUnitService;
import com.opple.fa.config.service.AttchConfigService;
import com.opple.fa.config.service.CompanyDepCostcenterService;
import com.opple.fa.config.service.UserInfoService;
import com.opple.fa.config.service.UserRoleService;
import com.opple.fa.config.service.WorkAddressService;
import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.entity.ApplyOrderDetail;
import com.opple.fa.purchase.entity.DemandOrder;
import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.service.ApplyOrderService;
import com.opple.fa.purchase.service.DemandOrderService;
import com.opple.fa.purchase.service.PurchaseOrderService;
import com.opple.fa.sap.model.ReadAssetsCodeModel;
import com.opple.fa.sap.service.SapUtilService;
import com.opple.fa.webapp.controller.BaseOpenApiController;
import com.opple.security.SessionSecurityConstants;
import com.opple.security.entity.FileUpload;
import com.opple.security.service.FileUploadService;
import com.opple.security.service.UserService;
import com.opple.util.Env;
import com.opple.util.ExecuteResult;
import com.opple.util.ExportExcle;
import com.opple.util.Pager;
import com.opple.workflow.service.WorkFlowService;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/assetCard")
public class GeneralAssetController extends BaseOpenApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralAssetController.class);
    @Resource
    private GeneralAssetService generalAssetService;
    @Resource
    private ThingsPersonalService thingsPersonalService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private SapUtilService sapUtilService;
    @Resource
    private WorkFlowService workFlowService;
    @Resource
    private UserService userService;
    @Resource
    private FileUploadService fileUploadService;
    @Resource
    private ApplyOrderService applyOrderService;
    @Resource
    private DemandOrderService demandOrderService;
    @Resource
    private PurchaseOrderService purchaseOrderService;
    @Resource
    private CompanyDepCostcenterService companyDepCostcenterService;
    @Resource
    private AttchConfigService attchConfigService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private AssetClassificationService assetClassificationService;
    @Resource
    private WorkAddressService workAddressService;
    @Resource
    private AssetUnitService assetUnitService;
    private String fileName;

    @RequestMapping("/assetCardDetail")
    public String getAssetCard(Model model, HttpServletRequest req, int id) {
        GeneralAsset generalAsset = thingsPersonalService.searchDetails(id);

        String demandId = generalAsset.getDemandId(); // 采购需求单号
        if (null != demandId && !"".equals(demandId)) {
            DemandOrder demandOrder = new DemandOrder();
            demandOrder.setCdocument(demandId);
            demandOrder = demandOrderService
                    .searchDemandOrderByOrderId(demandOrder);
            generalAsset.setDemandOrder(demandOrder);
        }
        // 采购申请单号
        String applyId = generalAsset.getApplyId();
        if (null != applyId && !"".equals(applyId)) {
            ApplyOrder applyOrder = new ApplyOrder();
            applyOrder.setCdocument(applyId);
            applyOrder = applyOrderService
                    .searchApplyOrderByOrderId(applyOrder);
            generalAsset.setApplyOrder(applyOrder);
        }
        String purchaseId = generalAsset.getPurchaseId(); // 采购订单
        if (null != purchaseId && !"".equals(purchaseId)) {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setCdocument(purchaseId);
            purchaseOrder = purchaseOrderService.get(purchaseOrder);
            generalAsset.setPurchaseOrder(purchaseOrder);
        }
        List<FileUpload> listFile = fileUploadService
                .getFilesByDocument(generalAsset.getSysteManagementCode());
        generalAsset.setListFile(listFile);

        model.addAttribute("generalAsset", generalAsset);
        return "assetcard/asset_card_detail";
    }

    @RequestMapping("/showAssetPic")
    public String showAssetpic(Model model, HttpServletRequest req, String uuid) {
        GeneralAsset generalAsset = new GeneralAsset();
        generalAsset.setAssetPicId(uuid);
        FileUpload fileUpload = fileUploadService.getFileUpload(uuid);
        model.addAttribute("generalAsset", generalAsset);
        model.addAttribute("fileUpload", fileUpload);
        String picFormat = "";
        if (null != fileUpload) {
            String fileName1 = fileUpload.getFileName();
            picFormat = fileName1.split("\\.")[1];
            picFormat.toUpperCase();
        }

        if ("PDF".equals(picFormat)) {
            return "assetcard/show_pdf_pic";
        } else {
            return "assetcard/show_asset_pic";
        }

    }

    @RequestMapping("/api/getAssetBySapCode")
    @ResponseBody
    public JSONObject getAssetBySapCode(Model model, HttpServletRequest req, String companyCode, String assetCode) {
        JSONObject sapCard = new JSONObject();
        ReadAssetsCodeModel readAssetsCodeModel = new ReadAssetsCodeModel();
        if ("" == companyCode || null == companyCode) {
            sapCard.put("error", "公司编码不能为空!");
            return sapCard;
        }
        if ("" == assetCode || null == assetCode) {
            sapCard.put("error", "资产编码不能为空!");
            return sapCard;
        }
        String[] code = assetCode.split("-");
        String mainCode = code[0];
        String secondaryCode = code[1]; // 次级编码

        readAssetsCodeModel.setCompanyCode(companyCode);
        readAssetsCodeModel.setMasterAssetCode(mainCode);
        readAssetsCodeModel.setSecondaryAssetCode(secondaryCode);

        sapCard = sapUtilService.readAssetsCodeFromSAP(readAssetsCodeModel);
        if ("".equals(sapCard.getString("error"))) {
            CompanyDepCostcenter companyDepCostcenter = new CompanyDepCostcenter();
            String nameCode = sapCard.getString("inventNo");
            if (!"".equals(nameCode)) {
                if (nameCode.contains("-")) {
                    String[] name = nameCode.split("-");
                    sapCard.put("userName", name[1]);
                    sapCard.put("userCode", name[0]);
                } else {
                    sapCard.put("userName", nameCode);
                    sapCard.put("userCode", "");
                }
            }

            companyDepCostcenter.setCostCenterCode(sapCard.getString("costcenter"));
            List<CompanyDepCostcenter> companyDep = companyDepCostcenterService.serachCompanyDepCostcenter(companyDepCostcenter);
            sapCard.put("companyName", companyDep.get(0).getCompanyName());
            sapCard.put("costCenterName", companyDep.get(0).getCostCenterName());
            ApplyOrderDetail applyOrderDetail = new ApplyOrderDetail();
            applyOrderDetail.setAssetClassification(sapCard.getString("assetClass"));
            String unitCode = sapCard.getString("baseUom"); // 单位编码
            sapCard.put("unitCode", unitCode);
            AssetUnit assetUnit = new AssetUnit();
            assetUnit.setUnitCode(unitCode);
            assetUnit = assetUnitService.getAssetUnit(assetUnit);
            if (null != assetUnit) {
                sapCard.put("unit", assetUnit.getUnitName()); // 单位名称
            }
            AssetClassification assetClassification = assetClassificationService.searchAssetClassificationByAssetClassification(applyOrderDetail);
            sapCard.put("assetClassName", assetClassification.getAssetstypeInfo());
        }
        return sapCard;
    }

    @RequestMapping("/getGeneralAssetSap")
    @ResponseBody
    public JSONObject updateGeneralAssetSap(HttpServletRequest request,
                                            GeneralAsset generalAsset, String ids, String assetType) {
        String errorList = "";
        JSONObject sapCard = new JSONObject();
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        String userName = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
        ReadAssetsCodeModel readAssetsCodeModel = new ReadAssetsCodeModel();
        List<GeneralAsset> listGeneralAsset = new ArrayList<GeneralAsset>();

        boolean isUpdateSapStatus = true;
        if ("".equals(ids)) {
            generalAsset.setIsExpenseAssets("0");
            generalAsset.setAssetState("1");
            generalAsset.setLoginUseCode(userCode);
            UserRole userRole = new UserRole();
            userRole.setCusercode(userCode);
            userRole.setCrolecode(Env.getProperty("role.assetAccounting.id"));
            if ("".equals(assetType)) {
                sapCard.put("errorList", "请选择一种资产类型");
                return sapCard;
            } else {
                generalAsset.setAssetType(assetType);
                if (userRoleService.searchUserRole(userRole) > 0) {
                    listGeneralAsset = generalAssetService.serachByacctingUpdateSap(generalAsset);
                } else {
                    listGeneralAsset = generalAssetService.serchAssetCardData(generalAsset);
                }
            }


        } else {
            String[] idArry = ids.split(",");
            for (String string : idArry) {
                GeneralAsset generalAssetUP = generalAssetService.getGeneralAsset(string);
                if ("0".equals(generalAssetUP.getIsExpenseAssets()) && "1".equals(generalAssetUP.getAssetState())) {
                    listGeneralAsset.add(generalAssetUP);
                } else {
                    isUpdateSapStatus = false;
                }

            }
        }
        if (!isUpdateSapStatus) {
            sapCard.put("errorList", "只能更新资本化并且已验收的资产");
            return sapCard;
        } else {
            for (GeneralAsset generalAssetTwo : listGeneralAsset) {
                String assetCode = generalAssetTwo.getAssetCode();
                String companyCode = generalAssetTwo.getCompanyCode();

                String[] code = assetCode.split("-");
                String mainCode = code[0];
                String secondaryCode = code[1]; // 次级编码

                readAssetsCodeModel.setCompanyCode(companyCode);
                readAssetsCodeModel.setMasterAssetCode(mainCode);
                readAssetsCodeModel.setSecondaryAssetCode(secondaryCode);
                sapCard = sapUtilService.readAssetsCodeFromSAP(readAssetsCodeModel);
                if (sapCard.has("error")) {
                    // 入账日
                    if (!"".equals(sapCard.getString("capDate")) && null != sapCard.getString("capDate")) {
                        generalAsset.setPostingDate(this.getDataExcel(sapCard.getString("capDate")));
                    }
                    // 资产原值
                    if (!"".equals(sapCard.getString("currentApc")) && null != sapCard.getString("currentApc")) {
                        generalAsset.setAssetOriginalValue(sapCard.getString("currentApc"));
                    }
                    String nameCode = sapCard.getString("inventNo");

                    if (!"".equals(nameCode)) {
                        if (nameCode.contains("-")) {
                            String[] name = nameCode.split("-");
                            generalAsset.setAssetPerson(name[1]);
                            generalAsset.setAssetPersonCode(name[0]);
                        } else {
                            generalAsset.setAssetPerson(nameCode);
                        }
                    }
                    // 资产净值
                    generalAsset.setAssetNetValue(sapCard.getString("currentNbv"));
                    // 资产分类
                    generalAsset.setAssetClassification(sapCard
                            .getString("assetClass"));
                    // 资产名称
                    generalAsset.setAssetName(sapCard.getString("descript"));
                    // 规格型号
                    generalAsset.setSpecificationModel(sapCard
                            .getString("descript"));
                    // 成本中心编码
                    generalAsset.setCostCenterCode(sapCard.getString("costcenter"));
                    // 折旧年限
                    generalAsset
                            .setPeriodOfDepreciation(sapCard.getString("ulife"));
                    // 折旧开始日期
                    if (!"".equals(sapCard.getString("deprBeginDate"))
                            && null != sapCard.getString("deprBeginDate")) {
                        generalAsset.setDepreciationStartDate(this
                                .getDataExcel(sapCard.getString("deprBeginDate")));
                    }
                    // 本年已计提累计折旧
                    generalAsset.setCurrentYearDepreciation(sapCard
                            .getString("deprCurrYear"));
                    // 实际验收日期
                    generalAsset.setActualDateAcceptance(this.getDataExcel(sapCard
                            .getString("initialAcq")));
                    // 月折旧额
                    generalAsset.setMonthCountingDepreciation(sapCard
                            .getString("monthlyDepreciation1"));
                    // 累计折旧
                    generalAsset.setAddDepreciation(sapCard.getString("accumDepr"));
                    CompanyDepCostcenter companyDepCostcenter = new CompanyDepCostcenter();
                    companyDepCostcenter.setCostCenterCode(sapCard.getString("costcenter"));
                    generalAsset.setUseDepartmentCode(sapCard.getString("costcenter"));
                    List<CompanyDepCostcenter> companyDep = companyDepCostcenterService
                            .serachCompanyDepCostcenter(companyDepCostcenter);
                    if (null != companyDep
                            && 0 != companyDep.size()) {
                        generalAsset.setCompanyName(companyDep.get(
                                0).getCompanyName()); // 公司名称
                        generalAsset.setPersentDepartment(companyDep
                                .get(0).getCostCenterName()); // 成本中心
                        generalAsset.setUseDepartment(companyDep
                                .get(0).getCostCenterName()); //部门名称

                    }
                    String s = String.valueOf(generalAssetTwo.getId());
                    generalAsset.setId(Long.parseLong(s));
                    generalAsset.setUpdateDate(new Date());
                    generalAsset.setUpdateBy(userName);
                    generalAssetService.updateAssetBySapCode(generalAsset);
                } else {
                    if ("".equals(errorList)) {
                        errorList = generalAssetTwo.getAssetCode();
                    } else {
                        errorList = errorList + ","
                                + generalAssetTwo.getAssetCode();
                    }
                }
            }
            if (!"".equals(errorList)) {
                errorList = errorList + "," + sapCard.getString("error");
                sapCard.put("errorList", errorList);
            }
        }
        return sapCard;
    }

    private Date getDataExcel(String data) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fmt.parse(data);
        } catch (ParseException e) {

            LOGGER.error("ParseException", e);
        }
        return date;

    }

    @RequestMapping(value = "/image/{imageName}")
    @ResponseBody
    public void getImage(@PathVariable(value = "imageName") String imageName,
                         HttpServletResponse response) throws IOException {
        OutputStream os = null;

        byte[] bytes = Files.readAllBytes(new File(Env
                .getProperty(Env.FILE_UPLOAD_URL) + imageName).toPath());

        os = new BufferedOutputStream(response.getOutputStream());
        os.write(bytes);
        os.flush();
    }

    /**
     * 是否允许修改
     *
     * @param document
     * @return
     */
    @RequestMapping("/ifUpdatePermitted")
    @ResponseBody
    public ExecuteResult<GeneralAsset> ifUpdatePermitted(Model model,
                                                         HttpServletRequest req, int id) {
        ExecuteResult<GeneralAsset> executeResult = new ExecuteResult<GeneralAsset>();

        // 获得权限
        UserRole userRole = new UserRole();
        // 判断当前登录人是不是归口管理员
        String isManager = "";
        userRole.setCrolecode(Env.getProperty("role.putMangerPerson.id"));
        userRole.setCusercode(req.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NAME)
                .toString().trim());
        if (userRoleService.searchUserRole(userRole) > 0) {
            isManager = "Y";
        } else {
            isManager = "N";
        }
        if ("Y".equals(isManager)) {
            assetCardUpdateDetail(model, req, id);
        } else {
            executeResult.addErrorMessage("您不是归口管理员，无法修改！");
        }
        return executeResult;
    }

    @RequestMapping("/assetCardUpdateDetail")
    public String assetCardUpdateDetail(Model model, HttpServletRequest req,
                                        int id) {
        GeneralAsset generalAsset = thingsPersonalService.searchDetails(id);
        List<FileUpload> listFile = fileUploadService
                .getFilesByDocument(generalAsset.getSysteManagementCode());
        generalAsset.setListFile(listFile);
        String demandId = generalAsset.getDemandId(); // 采购需求单号
        if (null != demandId && !"".equals(demandId)) {
            DemandOrder demandOrder = new DemandOrder();
            demandOrder.setCdocument(demandId);
            demandOrder = demandOrderService
                    .searchDemandOrderByOrderId(demandOrder);
            generalAsset.setDemandOrder(demandOrder);
        }
        // 采购申请单号
        String applyId = generalAsset.getApplyId();
        if (null != applyId && !"".equals(applyId)) {
            ApplyOrder applyOrder = new ApplyOrder();
            applyOrder.setCdocument(applyId);
            applyOrder = applyOrderService
                    .searchApplyOrderByOrderId(applyOrder);
            generalAsset.setApplyOrder(applyOrder);
        }
        String purchaseId = generalAsset.getPurchaseId(); // 采购订单
        if (null != purchaseId && !"".equals(purchaseId)) {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setCdocument(purchaseId);
            purchaseOrder = purchaseOrderService.get(purchaseOrder);
            generalAsset.setPurchaseOrder(purchaseOrder);
        }
        model.addAttribute("generalAsset", generalAsset);

        return "assetcard/asset_card_update";
    }

    /**
     * 新增页面带出公司等信息
     *
     * @return
     */
    @RequestMapping("/addAssetCard")
    public String addAssetCard(Model model, HttpServletRequest req) {
        List<UserInfo> userInfoList = userInfoService.searchAddressInfo();
        GeneralAsset generalAsset = new GeneralAsset();
        req.setAttribute("userInfoList", userInfoList);
        /* String document=workFlowService.getDocumentByType("FA_ASCD"); */
        String systeManagementCode = workFlowService
                .getDocumentByType("FA_MMZB");
        List<UserInfo> listCompany = userInfoService.searchAllCompany();
        generalAsset.setSysteManagementCode(systeManagementCode);

        req.setAttribute("generalAsset", generalAsset);
        req.setAttribute("listCompany", listCompany);
        return "assetcard/asset_card_add";
    }

    /**
     * 新增页面带出公司等信息
     *
     * @return
     */
    @RequestMapping("assetCardStamp")
    public String getGeneralAssetStamp(Model model, HttpServletRequest req,
                                       int id) {
        GeneralAsset generalAsset = thingsPersonalService.searchDetails(id);
        model.addAttribute("generalAsset", generalAsset);
        return "assetcard/asset_card_stamp";
    }

    /**
     * 新增资产卡片
     *
     * @return
     */
    @RequestMapping("/api/insertAssetCard")
    @ResponseBody
    public ExecuteResult<GeneralAsset> insetAssetCard(
            @RequestBody AssetCardWrapper assetCardWrapper, HttpServletRequest request) {
        ComputerAssetModel computerAssetModel = assetCardWrapper
                .getComputerAssetModel();
        GeneralAssetModel generalAssetModel = assetCardWrapper
                .getGeneralAssetModel();
        AdministrativeAssetModel administrativeModel = assetCardWrapper
                .getAdministrativeModel();
        MeasuringInstrumentsModel measuringInstrumentsModel = assetCardWrapper
                .getMeasuringInstrumentsModel();
        MachineryEquipmentModel machineryEquipmentModel = assetCardWrapper
                .getMachineryEquipmentModel();
        ExecuteResult<GeneralAsset> result = new ExecuteResult<GeneralAsset>();
        GeneralAsset generalAsset = new GeneralAsset();
        AdministrationAsset administrativeAsset = new AdministrationAsset();
        ComputerAsset computerAsset = new ComputerAsset();
        MeasuringInstruments measuringInstruments = new MeasuringInstruments();
        MachineryEquipment machineryEquipment = new MachineryEquipment();
        String userCode = (String) request.getSession().getAttribute(
                SessionSecurityConstants.KEY_USER_NAME);
        String userName = (String) request.getSession().getAttribute(
                SessionSecurityConstants.KEY_USER_NICK_NAME);
        if (null == generalAssetModel.getAssetType()
                || "".equals(generalAssetModel.getAssetType())
                || null == generalAssetModel.getAssetState()
                || "".equals(generalAssetModel.getAssetState())
                || null == generalAssetModel.getAssetName()
                || "".equals(generalAssetModel.getAssetName())
                || null == generalAssetModel.getUseState()
                || "".equals(generalAssetModel.getUseState())
                || null == generalAssetModel.getAssetPerson()
                || "".equals(generalAssetModel.getAssetPerson())
                || null == generalAssetModel.getAssetCode()
                || "".equals(generalAssetModel.getAssetCode())
                || null == generalAssetModel.getCompanyCode()
                || "".equals(generalAssetModel.getCompanyCode())) {
            result.addErrorMessage("必填項不能為空！");
            return result;

        }
        if (generalAssetModel.getAssetType().equals(
                ASSETTYPE.ADMINISTRATIVE.getAssetType())) {
            if (null == administrativeModel.getManagementCode()
                    || "".equals(administrativeModel.getManagementCode())
                    || null != administrativeModel.getIsBuildingContruction()
                    || "".equals(administrativeModel.getIsBuildingContruction())
                    || null == administrativeModel.getSerialNumber()
                    || "".equals(administrativeModel.getSerialNumber())) {
                result.addErrorMessage("必填项不能为空！");
                return result;
            }
        }
        if (generalAssetModel.getAssetType().equals(
                ASSETTYPE.MEASURINGINSTRUMENTS.getAssetType())) {
            if (null == measuringInstrumentsModel.getFacrotyNumber()
                    || "".equals(measuringInstrumentsModel.getFacrotyNumber())
                    || null == measuringInstrumentsModel.getManagementLevel()
                    || "".equals(measuringInstrumentsModel.getManagementLevel())
                    || null == measuringInstrumentsModel
                    .getManufactureFactory()
                    || "".equals(measuringInstrumentsModel
                    .getManufactureFactory())
                    || null == measuringInstrumentsModel.getManagementCode()
                    || "".equals(measuringInstrumentsModel.getManagementCode())
                    || null == measuringInstrumentsModel.getVerificationCycle()
                    || "".equals(measuringInstrumentsModel
                    .getVerificationCycle())
                    || null == measuringInstrumentsModel.getIsThirdReport()
                    || "".equals(measuringInstrumentsModel.getIsThirdReport())) {
                result.addErrorMessage("必填项不能为空！");
                return result;

            }
        }
        if (generalAssetModel.getAssetType().equals(
                ASSETTYPE.MACHINERYEQUIPMENT.getAssetType())) {
            if (null == machineryEquipmentModel.getMachineCode()
                    || "".equals(machineryEquipmentModel.getMachineCode())) {
                result.addErrorMessage("必填项不能为空！");
                return result;
            }
        }
        generalAsset.setAssetCode(generalAssetModel.getAssetCode());
        generalAsset.setCompanyCode(generalAssetModel.getCompanyCode());
        generalAsset.setCreateBy(userCode + "-" + userName);
        Long count = generalAssetService.assetCodeCount(generalAsset);
        if (count != 0) {
            result.addErrorMessage("此资产编码已存在！");
            return result;
        }
        if (ASSETTYPE.ITASSET.getAssetType().equals(
                generalAssetModel.getAssetType())) {
            BeanUtils.copyProperties(generalAssetModel, generalAsset);

            BeanUtils.copyProperties(computerAssetModel, computerAsset);
        }
        if (ASSETTYPE.ADMINISTRATIVE.getAssetType().equals(
                generalAssetModel.getAssetType())) {

            BeanUtils.copyProperties(generalAssetModel, generalAsset);

            BeanUtils.copyProperties(administrativeModel, administrativeAsset);
        }
        if (ASSETTYPE.MEASURINGINSTRUMENTS.getAssetType().equals(
                generalAssetModel.getAssetType())) {
            BeanUtils.copyProperties(generalAssetModel, generalAsset);
            BeanUtils.copyProperties(measuringInstrumentsModel,
                    measuringInstruments);

        }
        if (ASSETTYPE.MACHINERYEQUIPMENT.getAssetType().equals(
                generalAssetModel.getAssetType())) {
            BeanUtils.copyProperties(generalAssetModel, generalAsset);
            BeanUtils.copyProperties(machineryEquipmentModel,
                    machineryEquipment);

        }

        result = generalAssetService.insertAssetCard(generalAsset,
                computerAsset, machineryEquipment, measuringInstruments,
                administrativeAsset);

        return result;
    }

    @RequestMapping("/api/searchAssetCardInfo")
    @ResponseBody
    public List<GeneralAsset> searchAssetCardInfo(HttpServletRequest req,
                                                  String isExpenseAssets, String assetType, String costCenterCode,
                                                  String useState, String officeLocation) {
        GeneralAsset generalAsset = new GeneralAsset();
        String userCode = (String) req.getSession().getAttribute(
                SessionSecurityConstants.KEY_USER_NAME);
        generalAsset.setLoginUseCode(userCode);
        generalAsset.setIsExpenseAssets(isExpenseAssets);
        generalAsset.setAssetType(assetType);
        generalAsset.setUseState(useState);
        generalAsset.setCostCenterCode(costCenterCode);
        AttchConfig attchConfig = new AttchConfig();
        attchConfig.setAttachDepartManagerCode(userCode);
        List<AttchConfig> listAttchConfig = attchConfigService
                .serachByUseCode(attchConfig);
        if (listAttchConfig.size() > 0) {
            generalAsset.setListAttchConfig(listAttchConfig);
        }
        if (!"1".equals(officeLocation)) {
            WorkAddress workAddress = workAddressService.get(officeLocation);
            generalAsset.setOfficeLocation(workAddress.getCaddressName());
        } else {
            generalAsset.setOfficeLocation("");
        }
        UserRole userRole = new UserRole();
        userRole.setCusercode(userCode);
        userRole.setCrolecode(Env.getProperty("role.assetAccounting.id"));
        List<GeneralAsset> generalAssetList = new ArrayList<GeneralAsset>();
        // 判断当前登录人是否为资产会计
        if (userRoleService.searchUserRole(userRole) > 0) {
            generalAssetList = generalAssetService
                    .searchAssetCardInfoForBursar(generalAsset);
        } else {
            generalAssetList = generalAssetService
                    .searchAssetCardInfo(generalAsset);
        }

        return generalAssetList;
    }

    @RequestMapping("/api/searchGeneralAsset")
    @ResponseBody
    public List<GeneralAsset> searchGeneralAsset(HttpServletRequest req,
                                                 String txtAssetsName, String txtAssetsCode, String txtAssetPerson,
                                                 String txtPutUnderManageCode, String persentDepartment,
                                                 String persentCompany, String useState, String isExpenseAssets,
                                                 String assetType, String costCenterCode, String officeLocation, String isAssembleOccupancy) {
        String userCode = (String) req.getSession().getAttribute(
                SessionSecurityConstants.KEY_USER_NAME);
        GeneralAsset generalAsset = new GeneralAsset();
        generalAsset.setLoginUseCode(userCode);
        generalAsset.setAssetName(null == txtAssetsName ? null : txtAssetsName
                .trim());
        generalAsset.setAssetCode(null == txtAssetsCode ? null : txtAssetsCode
                .trim());
        generalAsset.setIsAssembleOccupancy(null == isAssembleOccupancy ? null : isAssembleOccupancy
                .trim());
        generalAsset.setAssetPerson(null == txtAssetPerson ? null
                : txtAssetPerson.trim());
        generalAsset.setSpecificationModel(null == txtPutUnderManageCode ? null
                : txtPutUnderManageCode.trim());
        generalAsset.setCompanyName(null == persentCompany ? null
                : persentCompany.trim());

        generalAsset.setUseState(useState);
        generalAsset.setAssetType(assetType);
        generalAsset.setIsExpenseAssets(isExpenseAssets);
        generalAsset.setCostCenterCode(costCenterCode);
        generalAsset.setLoginUseCode(userCode);
        AttchConfig attchConfig = new AttchConfig();
        attchConfig.setAttachDepartManagerCode(userCode);
        List<AttchConfig> listAttchConfig = attchConfigService
                .serachByUseCode(attchConfig);
        if (listAttchConfig.size() > 0) {
            generalAsset.setListAttchConfig(listAttchConfig);
        }
        if (!"1".equals(officeLocation) && !"null".equals(officeLocation) & null != officeLocation && !"".equals(officeLocation)) {
            WorkAddress workAddress = workAddressService.get(officeLocation);
            generalAsset.setOfficeLocation(workAddress.getCaddressName());
        } else {
            generalAsset.setOfficeLocation("");
        }
        List<GeneralAsset> generalAssetList = new ArrayList<GeneralAsset>();
  /*      // 判断当前登录人是否为资产会计
        UserRole userRole = new UserRole();
        userRole.setCusercode(userCode);
        userRole.setCrolecode(Env.getProperty("role.assetAccounting.id"));
        if (userRoleService.searchUserRole(userRole) > 0) {
            generalAssetList = generalAssetService
                    .searchAssetCardInfoForBursar(generalAsset);
        } else {
         
        }*/
        generalAssetList = generalAssetService
                .searchGeneralAsset(generalAsset);
        return generalAssetList;

    }

    /**
     * 查询卡片列表 带分页
     *
     * @return
     */
    @RequestMapping("/searchAssetCard")
    public String searchAssetCard(Model model, HttpServletRequest req,
                                  Long offset, Long pageSize, GeneralAsset generalAsset) {
        String userCode = (String) req.getSession().getAttribute(
                SessionSecurityConstants.KEY_USER_NAME);
        generalAsset
                .setAssetCode(generalAsset.getAssetCode() == null ? generalAsset
                        .getAssetCode() : generalAsset.getAssetCode().trim());
        generalAsset
                .setPersentDepartment(generalAsset.getPersentDepartment() == null ? generalAsset
                        .getPersentDepartment() : generalAsset
                        .getPersentDepartment().trim());
        generalAsset
                .setAssetName(generalAsset.getAssetName() == null ? generalAsset
                        .getAssetName() : generalAsset.getAssetName().trim());
        generalAsset
                .setPutUnderManageCode(generalAsset.getPutUnderManageCode() == null ? generalAsset
                        .getPutUnderManageCode() : generalAsset
                        .getPutUnderManageCode().trim());
        generalAsset
                .setAssetPerson(generalAsset.getAssetPerson() == null ? generalAsset
                        .getAssetPerson() : generalAsset.getAssetPerson()
                        .trim());
        generalAsset
                .setSpecificationModel(generalAsset.getSpecificationModel() == null ? generalAsset
                        .getSpecificationModel() : generalAsset
                        .getSpecificationModel().trim());
        generalAsset
                .setOfficeLocation(generalAsset.getOfficeLocation() == null ? generalAsset
                        .getOfficeLocation() : generalAsset.getOfficeLocation()
                        .trim());
        generalAsset
                .setCompanyName(generalAsset.getCompanyName() == null ? generalAsset
                        .getCompanyName() : generalAsset.getCompanyName()
                        .trim());
        generalAsset
                .setAssetType(generalAsset.getAssetType() == null ? generalAsset
                        .getAssetType() : generalAsset.getAssetType().trim());
        generalAsset.setAssetPersonCode(userCode);
        generalAsset.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));
        AttchConfig attchConfig = new AttchConfig();
        attchConfig.setAttachDepartManagerCode(userCode);
        List<AttchConfig> listAttchConfig = attchConfigService
                .serachByUseCode(attchConfig);
        if (listAttchConfig.size() > 0) {
            generalAsset.setListAttchConfig(listAttchConfig);
        }
        Pager<GeneralAsset> pager = new Pager<GeneralAsset>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<GeneralAsset> assetCardPager = null;
        // 获得权限
        UserRole userRole = new UserRole();
        userRole.setCrolecode(Env.getProperty("role.assetAccounting.id"));
        userRole.setCusercode(req.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NAME)
                .toString().trim());
        generalAsset.setLoginUseCode(userCode);
        if (userRoleService.searchUserRole(userRole) > 0) {

            assetCardPager = generalAssetService.serchAssetCardByAccounting(
                    pager, generalAsset);
        } else {
            generalAsset.setRuleId(Env.getProperty("role.platformFinanceManager.id"));

            assetCardPager = generalAssetService.serchAssetCard(pager,
                    generalAsset);
        }

        assetCardPager.setUri(req.getRequestURI());
        model.addAttribute("assetCardPager", assetCardPager);
        return "assetcard/asset_card_list";
    }

    /**
     * 删除卡片
     *
     * @return
     */
    /*
     * @RequestMapping("/deleteAssetCard")
     * 
     * @ResponseBody public ExecuteResult<GeneralAsset> deleteAssetCard(String
     * ids) {
     * 
     * ExecuteResult<GeneralAsset> result = new ExecuteResult<GeneralAsset>();
     * String statusIds[] = ids.split("#"); GeneralAsset generalAsset = new
     * GeneralAsset(); String stats = ""; String cardIds = ""; if (null !=
     * statusIds && 0 != statusIds.length) { for (String string : statusIds) {
     * String stat = string.split(",")[1]; if
     * (!APPROVASTATUS.WAITING.getApprovaStatus().equals(stat)) { if
     * (!"".equals(stats)) { stats = stat + "," + stats; } else { stats = stat;
     * } } if (APPROVASTATUS.WAITING.getApprovaStatus().equals(stat) ||
     * "".equals(stat)) { String cardId = string.split(",")[0]; if
     * (!"".equals(cardIds)) { cardIds = cardId + "," + cardIds; } else {
     * cardIds = cardId; } }
     * 
     * } if (stats == null || stats.length() == 0) { String strIds[] =
     * cardIds.split(","); for (String string : strIds) { Integer id =
     * Integer.parseInt(string); generalAsset.setId(id);
     * generalAssetService.deleteAssetCard(generalAsset); } } else {
     * result.addErrorMessage("有不可删除的数据!"); } } else {
     * result.addErrorMessage("请选择一条数据！"); }
     * 
     * return result; }
     * 
     * /** 关闭
     * 
     * @return
     */
    @RequestMapping("/api/closeAssetCard")
    @ResponseBody
    public ExecuteResult<GeneralAsset> closeAssetCard(String ids,
                                                      HttpServletRequest request) {

        ExecuteResult<GeneralAsset> result = new ExecuteResult<GeneralAsset>();
        UserInfo userInfo = new UserInfo();
        String loginUserCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        userInfo.setLoginUserCode(loginUserCode);

        List<UserInfo> userInfos = userInfoService.searchCauthorizerCode(userInfo);

        Boolean sumFlag = true; // 判断全部是否可以删除
        if (null != ids && !"".equals(ids)) {
            GeneralAsset generalAsset = null;
            String[] closeIds = ids.split(",");
            for (String string : closeIds) {
                Boolean flag = false;
                generalAsset = generalAssetService.getGeneralAsset(string);
                for (UserInfo userInfo2 : userInfos) {
                    if (generalAsset.getAssetPersonCode().equals(userInfo2.getCauthorizerCode())) {
                        flag = true;
                        break;
                    }
                }
                if (loginUserCode.equals(generalAsset.getAssetPersonCode())) {
                    flag = true;
                }
                if (!flag) {
                    sumFlag = false;
                    result.addErrorMessage("您没有删除权限！");
                    return result;
                }

            }
            if (sumFlag) {
                for (String string : closeIds) {
                    generalAsset.setId(Long.parseLong(string));
                    generalAssetService.deleteAssetCard(generalAsset);
                }
            }
        } else {
            result.addErrorMessage("请选择要删除的数据！");
        }
        return result;
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/Export", method = RequestMethod.GET)
    @ResponseBody
    public void export(ModelMap model, HttpServletRequest request,
                       HttpServletResponse response, GeneralAsset generalAsset) {
        String fileName = "";
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        generalAsset.setLoginUseCode(userCode);
        List<GeneralAsset> generalAssetList = null;
        // 获得权限
        UserRole userRole = new UserRole();

        userRole.setCrolecode(Env.getProperty("role.assetAccounting.id"));
        userRole.setCusercode(request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        UserRole userRole2 = new UserRole();
        userRole2.setCusercode(userCode);
        userRole2.setCrolecode("'RFA0000000'");
        if (userRoleService.searchUserRole(userRole) > 0 || userRoleService.searchUserRole(userRole2) > 0) {

            if ("0".equals(generalAsset.getAssetType())) { // IT资产
                generalAsset.setRuleId(Env.getProperty("role.platformFinanceManager.id"));
                generalAssetList = generalAssetService.serchAssetCardDateByAccountingIT(generalAsset);
            } else if ("1".equals(generalAsset.getAssetType())) { // 行政资产
                generalAsset.setRuleId(Env.getProperty("role.platformFinanceManager.id"));
                generalAssetList = generalAssetService.serchAssetCardDateByAccountingXZ(generalAsset);
            } else if ("2".equals(generalAsset.getAssetType())) { // 计量器具
                generalAsset.setRuleId(Env.getProperty("role.platformFinanceManager.id"));
                generalAssetList = generalAssetService.serchAssetCardDateByAccountingJL(generalAsset);
            } else if ("3".equals(generalAsset.getAssetType())) { // 机器设备
                generalAsset.setRuleId(Env.getProperty("role.platformFinanceManager.id"));
                generalAssetList = generalAssetService.serchAssetCardDateByAccountingJX(generalAsset);
            }
        } else {
            if ("0".equals(generalAsset.getAssetType())) { // IT资产
                generalAsset.setRuleId(Env.getProperty("role.platformFinanceManager.id"));
                generalAssetList = generalAssetService.serchAssetCardDataIT(generalAsset);
            } else if ("1".equals(generalAsset.getAssetType())) { // 行政资产
                generalAsset.setRuleId(Env.getProperty("role.platformFinanceManager.id"));
                generalAssetList = generalAssetService.serchAssetCardDataXZ(generalAsset);
            } else if ("2".equals(generalAsset.getAssetType())) { // 计量器具
                generalAsset.setRuleId(Env.getProperty("role.platformFinanceManager.id"));
                generalAssetList = generalAssetService.serchAssetCardDataJL(generalAsset);
            } else if ("3".equals(generalAsset.getAssetType())) { // 机器设备
                generalAsset.setRuleId(Env.getProperty("role.platformFinanceManager.id"));
                generalAssetList = generalAssetService.serchAssetCardDataJX(generalAsset);
            }
        }
        if ("0".equals(generalAsset.getAssetType())) { // IT资产
                /* fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
                ExportAssetCardTemplateIT exportAssetCardTemplate = new ExportAssetCardTemplateIT();
            	exportAssetCardTemplate.doExport(response, fileName,generalAssetList);*/
            // 文件名获取
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String f = "IT资产-" + format.format(date);
            this.fileName = f;
            setResponseHeader(response);
            OutputStream out = null;
            try {
                out = response.getOutputStream();
                toExportPayDetail(generalAssetList, request, 10000, f, out);
            } catch (IOException e1) {
                LOGGER.error("e", e1);
            } catch (Exception e) {
                LOGGER.error("e", e);
            } finally {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    LOGGER.error("e", e);
                }
            }
        } else if ("1".equals(generalAsset.getAssetType())) { // 行政资产
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String f = "行政资产-" + format.format(date);
            this.fileName = f;
            setResponseHeader(response);
            OutputStream out = null;
            try {
                out = response.getOutputStream();
                toExportXZ(generalAssetList, request, 10000, f, out);
            } catch (IOException e1) {
                LOGGER.error("e", e1);
            } catch (Exception e) {
                LOGGER.error("e", e);
            } finally {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    LOGGER.error("e", e);
                }
            }
        } else if ("2".equals(generalAsset.getAssetType())) { // 计量器具
            /*	fileName = "计量器具.xlsx";
                fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
	            ExportAssetCardTemplateJL exportAssetCardTemplate = new ExportAssetCardTemplateJL();
	            exportAssetCardTemplate.doExport(response, fileName,generalAssetList);*/
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String f = "计量器具-" + format.format(date);
            this.fileName = f;
            setResponseHeader(response);
            OutputStream out = null;
            try {
                out = response.getOutputStream();
                toExportJL(generalAssetList, request, 10000, f, out);
            } catch (IOException e1) {
                LOGGER.error("e", e1);
            } catch (Exception e) {
                LOGGER.error("e", e);
            } finally {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    LOGGER.error("e", e);
                }
            }
        } else if ("3".equals(generalAsset.getAssetType())) { // 机器设备
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String f = "机器设备-" + format.format(date);
            this.fileName = f;
            setResponseHeader(response);
            OutputStream out = null;
            try {
                out = response.getOutputStream();
                toExportJQ(generalAssetList, request, 10000, f, out);
            } catch (IOException e1) {
                LOGGER.error("e", e1);
            } catch (Exception e) {
                LOGGER.error("e", e);
            } finally {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    LOGGER.error("e", e);
                }
            }
        }
    }

    /**
     * 导入
     *
     * @return
     */
    @RequestMapping(value = "/api/uploadGeneralAssetExcel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject uploadGeneralAssetExcel(
            MultipartHttpServletRequest request, HttpSession session, HttpServletResponse response) {
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        String userName = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        String errorNull = "";
        // 获得文件上传的文件
        MultipartFile myfile = request.getFile("inputfile");

        String fileName = myfile.getOriginalFilename();
        String namePattern = fileName.substring(0, fileName.length() - 5);
        if (!ASSETTYPE.FONTITASSET.getAssetType().equals(namePattern)
                && !ASSETTYPE.FONTADMINISTRATIVE.getAssetType().equals(namePattern)
                && !ASSETTYPE.FONTMACHINERYEQUIPMENT.getAssetType().equals(namePattern)
                && !ASSETTYPE.FONTMEASURINGINSTRUMENTS.getAssetType().equals(namePattern)) {
            errorNull = "文件名称需为：IT资产  || 行政资产  || 计量器具 || 机器设备";
            jsonMap.put("errorNull", errorNull);
            try {
                response.setContentType("text/html; charset=utf-8");
                // response.getWriter().write(JSONObject.fromObject(jsonMap).toString());
                response.getWriter().write(JSONObject.fromObject(jsonMap).toString() + "<script>document.domain='opple.com'</script>");
                response.getWriter().flush();
            } catch (IOException e1) {
                LOGGER.error("IOException", e1);
            }
            return null;
        }

        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(myfile.getInputStream());
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
        Sheet sheet = wb.getSheetAt(0);

        List<GeneralAsset> listGeneral = new ArrayList<GeneralAsset>();
        List<ComputerAsset> listComputerAsset = new ArrayList<ComputerAsset>();
        List<MeasuringInstruments> listMeasuringInstruments = new ArrayList<MeasuringInstruments>();
        List<MachineryEquipment> listMachineryEquipment = new ArrayList<MachineryEquipment>();
        List<AdministrationAsset> listAdministrationAsset = new ArrayList<AdministrationAsset>();
        // 返回物理行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        if (rowNum < 1 || rowNum == 1) {
            jsonMap.put("errorNull", "请填写需要导入的数据");
            try {
                response.setContentType("text/html; charset=utf-8");
                // response.getWriter().write(JSONObject.fromObject(jsonMap).toString());
                response.getWriter().write(JSONObject.fromObject(jsonMap).toString() + "<script>document.domain='opple.com'</script>");
                response.getWriter().flush();
            } catch (IOException e1) {
                LOGGER.error("IOException", e1);
            }
            return null;
        }
        int countSum = 0;
        boolean isFyorZb = false;
        for (int i = 1; i < rowNum; i++) {
            Row row = sheet.getRow(i);
            GeneralAsset generalAsset = new GeneralAsset();
            generalAsset.setDifferentiatePurchase("1"); // 用来区分是卡片新增还是收货
            generalAsset.setIsAllocationOccupancy("0"); // 是否被调拨占用
            generalAsset.setIsDiscardOccupancy("0"); // 是否被报废变卖占用
            generalAsset.setIsAssembleOccupancy("0"); //是否被 组合资产占用
            generalAsset.setCreateBy(userCode + "-" + userName); // 创建人
            generalAsset.setCreateDate(new Date());
            if (null != row.getCell(6) && !"".equals(row.getCell(6))) {
                String isExpenseAssets = row.getCell(6).getStringCellValue();
                if (isExpenseAssets == null || "".equals(isExpenseAssets)) {
                    if ("".equals(errorNull)) {
                        errorNull = "第" + i + "行是否一次性费用化资产不可为空";
                    } else {
                        errorNull = errorNull + "\n" + "第" + i + "行是否一次性费用化资产不可为空";
                    }
                    continue;
                } else {
                    if (ISEXPENSEASSETS.FONTYES.getIsExpenseassets().equals(
                            isExpenseAssets)) {
                        isFyorZb = true; //判断是否是费用化资产
                        String mmfy = workFlowService.getDocumentByType("FA_MMFY");
                        generalAsset.setSysteManagementCode(mmfy);
                        generalAsset.setIsExpenseAssets("1");
                        if (null != row.getCell(0) && !"".equals(row.getCell(0))) {
                            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                            String companyCode = row.getCell(0).getStringCellValue();
                            if (!"".equals(row.getCell(0).getStringCellValue())) {
                                // 公司编码
                                generalAsset.setCompanyCode(companyCode);
                            } else {
                                if ("".equals(errorNull)) {
                                    errorNull = "第" + i + "行公司编码不能为空";
                                } else {
                                    errorNull = errorNull + "\n" + "第" + i + "行公司编码不能为空";
                                }
                                continue;
                            }
                        }
                        if (null != row.getCell(15) && !"".equals(row.getCell(15))) {
                            row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                            // 成本中心
                            String costCenter = row.getCell(15).getStringCellValue();
                            String reg = "[a-zA-Z]";
                            boolean isCract = costCenter.matches(reg);
                            AssetsBudgetFromBUD assetsBudgetFromBUD = new AssetsBudgetFromBUD();
                            CompanyDepCostcenter companyDepCostcenter = new CompanyDepCostcenter();
                            assetsBudgetFromBUD.setAssetType(namePattern);

                            if (!isCract) {
                                generalAsset.setCostCenterCode("00" + costCenter);
                                companyDepCostcenter.setCostCenterCode("00" + costCenter);
                                assetsBudgetFromBUD.setCostCenter("00" + costCenter);

                            } else {
                                generalAsset.setCostCenterCode(costCenter);
                                companyDepCostcenter.setCostCenterCode(costCenter);
                                assetsBudgetFromBUD.setCostCenter(costCenter);
                            }
                            AttchConfig attchConfig = attchConfigService.searchByCostcenterCode(assetsBudgetFromBUD);
                            if (attchConfig != null) {
                                generalAsset.setPlatform(attchConfig.getPlatform());
                            }
                            //companyDepCostcenter.setCostCenterCode();
                            List<CompanyDepCostcenter> companyDep = companyDepCostcenterService.serachCompanyDepCostcenter(companyDepCostcenter);
                            if (companyDep.size() != 0 && companyDep != null) {
                                generalAsset.setCompanyName(companyDep.get(0).getCompanyName());
                                generalAsset.setPersentDepartment(companyDep.get(0).getCostCenterName());
                            }
                        } else {
                            if ("".equals(errorNull)) {
                                errorNull = "第" + i + "行成本中心不能为空";
                            } else {
                                errorNull = errorNull + "\n" + "第" + i + "行成本中心不能为空";
                            }
                            continue;
                        }
                        // 费用化资产获得资产编码并更新

                        /*
                         * if(null!=row.getCell(1)&&!"".equals(row.getCell(1))){
                         * row.getCell(1).setCellType(Cell.CELL_TYPE_STRING); //
                         * 资产编号
                         * if(!"".equals(row.getCell(1).getStringCellValue())){
                         * generalAsset
                         * .setAssetCode(row.getCell(1).getStringCellValue());
                         * }else{
                         * 
                         * generalAsset.setAssetCode(""); } }else{
                         * generalAsset.setAssetCode(""); }
                         */
                        if (null != row.getCell(2) && !"".equals(row.getCell(2))) {
                            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                            // 资产名称
                            if (!"".equals(row.getCell(2).getStringCellValue())) {
                                generalAsset.setAssetName(row.getCell(2).getStringCellValue());
                                generalAsset.setSpecificationModel(row.getCell(2).getStringCellValue());
                            } else {
                                if ("".equals(errorNull)) {
                                    errorNull = "第" + i + "行资产名称不能为空";
                                } else {
                                    errorNull = errorNull + "\n" + "第" + i + "行资产名称不能为空";
                                }
                                continue;
                            }
                        }
                        if (null != row.getCell(3) && !"".equals(row.getCell(3))) {
                            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                            // 原资产编码
                            generalAsset.setOldAssetCode(row.getCell(3).getStringCellValue());
                        }
                        if (null != row.getCell(4) && !"".equals(row.getCell(4))) {
                            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                            // 原归属公司
                            generalAsset.setOldHomeCompany(row.getCell(4).getStringCellValue());
                        }
                        String assetClass = "";
                        if (null != row.getCell(5) && !"".equals(row.getCell(5))) {
                            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                            assetClass = row.getCell(5).getStringCellValue();
                            // 资产分类
                            generalAsset.setAssetClassification(row.getCell(5).getStringCellValue());

                        } else {
                            if ("".equals(errorNull)) {
                                errorNull = "第" + i + "行资产分类不能为空";
                            } else {
                                errorNull = errorNull + "\n" + "第" + i + "行资产分类不能为空";
                            }
                            continue;
                        }
                        if (!"".equals(assetClass)) {
                            AssetClassification assetClassification = assetClassificationService.getAssetClassificationByClass(assetClass);
                            if (null != assetClassification && !"".equals(assetClassification)) {
                                int newMax = Integer.parseInt(assetClassification.getMax()) + 1;
                                assetClassification.setMax(String.valueOf(newMax));
                                assetClassification.setAssetClassification(assetClass);
                                assetClassificationService.updateMaxByAssetClass(assetClassification);
                                generalAsset.setAssetCode("F" + newMax + "-" + "0");
                            } else {
                                if ("".equals(errorNull)) {
                                    errorNull = "第" + i + "行资产分类有误";
                                } else {
                                    errorNull = errorNull + "\n" + "第" + i + "行资产分类有误";
                                }
                                continue;
                            }
                        }
                        if (null != row.getCell(7)
                                && !"".equals(row.getCell(7))) {
                            row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                            // 选配件
                            generalAsset.setSelectAccessories(row.getCell(7).getStringCellValue());
                        }
                        generalAsset.setAssetState("1"); // 资产状态默认已验收
                        if (null != row.getCell(11) && !"".equals(row.getCell(11))) {
                            row.getCell(11).setCellType(Cell.CELL_TYPE_NUMERIC);
                            // 实际验收日期
                            generalAsset.setActualDateAcceptance(row.getCell(11).getDateCellValue());
                        } else {
                            if ("".equals(errorNull)) {
                                errorNull = "第" + i + "行实际验收日期不能为空";
                            } else {
                                errorNull = errorNull + "\n" + "第" + i + "行实际验收日期不能为空";
                            }
                            continue;
                        }
                        if (null != row.getCell(14) && !"".equals(row.getCell(14))) {
                            row.getCell(14).setCellType(Cell.CELL_TYPE_NUMERIC);
                            // 入账日期
                            generalAsset.setPostingDate(row.getCell(14).getDateCellValue());
                        } else {
                            if ("".equals(errorNull)) {
                                errorNull = "第" + i + "行入账日期不能为空";
                            } else {
                                errorNull = errorNull + "\n" + "第" + i + "行入账日期不能为空";
                            }
                            continue;
                        }

                        if (null != row.getCell(16) && !"".equals(row.getCell(16))) {
                            row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
                            String nameAnCode = row.getCell(16).getStringCellValue();
                            if (!"".equals(nameAnCode) && null != nameAnCode) {
                                if (nameAnCode.contains("-")) {
                                    String[] name = nameAnCode.split("-");
                                    generalAsset.setAssetPerson(name[1]); // 资产责任人名称
                                    generalAsset.setAssetPersonCode(name[0]); // 资产责任人编码
                                } else {
                                    generalAsset.setAssetPerson(nameAnCode); // 资产责任人编码
                                }
                            }
                        } else {
                            if ("".equals(errorNull)) {
                                errorNull = "第" + i + "行管理责任人不能为空";
                            } else {
                                errorNull = errorNull + "\n" + "第" + i + "行管理责任人不能为空";
                            }
                            continue;
                        }
                        if (null != row.getCell(20) && !"".equals(row.getCell(20))) {
                            row.getCell(20).setCellType(Cell.CELL_TYPE_STRING);
                            // 资产原值
                            generalAsset.setAssetOriginalValue(row.getCell(20).getStringCellValue());

                        } else {
                            if ("".equals(errorNull)) {
                                errorNull = "第" + i + "行资产原值不能为空";
                            } else {
                                errorNull = errorNull + "\n" + "第" + i + "行资产原值不能为空";
                            }
                            continue;
                        }
                        if (null != row.getCell(21) && !"".equals(row.getCell(21))) {
                            row.getCell(21).setCellType(Cell.CELL_TYPE_STRING);
                            // 资产净值
                            generalAsset.setAssetNetValue(row.getCell(21).getStringCellValue());

                        } else {
                            if ("".equals(errorNull)) {
                                errorNull = "第" + i + "行资产净值不能为空";
                            } else {
                                errorNull = errorNull + "\n" + "第" + i + "行资产净值不能为空";
                            }
                            continue;
                        }
                        if (null != row.getCell(22) && !"".equals(row.getCell(22))) {
                            row.getCell(22).setCellType(Cell.CELL_TYPE_STRING);
                            // 折旧年限
                            generalAsset.setPeriodOfDepreciation(row.getCell(22).getStringCellValue());

                        } else {
                            generalAsset.setPeriodOfDepreciation("0");
                        }
                        if (null != row.getCell(23) && !"".equals(row.getCell(23))) {
                            row.getCell(23).setCellType(Cell.CELL_TYPE_NUMERIC);
                            // 折旧开始日期
                            generalAsset.setDepreciationStartDate(row.getCell(23).getDateCellValue());

                        } else {
                            generalAsset.setDepreciationStartDate(null);
                        }
                        if (null != row.getCell(24) && !"".equals(row.getCell(24))) {
                            row.getCell(24).setCellType(Cell.CELL_TYPE_STRING);
                            // 月折旧额
                            generalAsset.setMonthCountingDepreciation(row.getCell(24).getStringCellValue());
                        }
                        if (null != row.getCell(25) && !"".equals(row.getCell(25))) {
                            row.getCell(25).setCellType(Cell.CELL_TYPE_STRING);
                            // 本年已计提累计折旧
                            generalAsset.setCurrentYearDepreciation(row.getCell(25).getStringCellValue());
                        }
                        if (null != row.getCell(26) && !"".equals(row.getCell(26))) {
                            row.getCell(26).setCellType(Cell.CELL_TYPE_STRING);
                            // 累计折旧
                            generalAsset.setAddDepreciation(row.getCell(26).getStringCellValue());
                        }
                    }
                    if (null != row.getCell(8) && !"".equals(row.getCell(8))) {
                        row.getCell(8).setCellType(Cell.CELL_TYPE_NUMERIC);
                        // 采购到货日期
                        if (!"".equals(row.getCell(8).getStringCellValue())) {
                            generalAsset.setPurchaseArrivalDate(row.getCell(8).getDateCellValue());
                        } else {
                            generalAsset.setPurchaseArrivalDate(null);
                        }
                    }
                    if (null != row.getCell(9) && !"".equals(row.getCell(9))) {
                        row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                        // 验收周期（日）
                        if (!"".equals(row.getCell(9).getStringCellValue())) {
                            generalAsset.setAcceptanceCycle(row.getCell(9).getStringCellValue());
                        } else {
                            generalAsset.setAcceptanceCycle("");
                        }
                    }
                    if (null != row.getCell(10) && !"".equals(row.getCell(10))) {
                        row.getCell(10).setCellType(Cell.CELL_TYPE_NUMERIC);
                        // 计划验收日期
                        if (!"".equals(row.getCell(10).getStringCellValue())) {
                            generalAsset.setDateOfAcceptance(row.getCell(10).getDateCellValue());
                        } else {
                            generalAsset.setDateOfAcceptance(null);
                        }
                    }
                    if (null != row.getCell(12) && !"".equals(row.getCell(12))) {
                        row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                        // 质保周期
                        if (!"".equals(row.getCell(12).getStringCellValue())) {
                            generalAsset.setWarrantyPeriod(row.getCell(12).getStringCellValue());
                        } else {
                            generalAsset.setWarrantyPeriod("");
                        }
                    }
                    if (null != row.getCell(13) && !"".equals(row.getCell(13))) {
                        row.getCell(13).setCellType(Cell.CELL_TYPE_NUMERIC);
                        // 质保到期日
                        if (!"".equals(row.getCell(13).getStringCellValue())) {
                            generalAsset.setExpirationDate(row.getCell(13).getDateCellValue());
                        } else {
                            generalAsset.setExpirationDate(null);
                        }
                    }
                    if (null != row.getCell(17) && !"".equals(row.getCell(17))) {
                        row.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
                        // 办公地点
                        if (!"".equals(row.getCell(17).getStringCellValue())) {
                            String office = row.getCell(17).getStringCellValue();
                            if (OFFICELOCATION.SHANGHAI.getOfficeLocation().equals(office)
                                    || OFFICELOCATION.WUJIANG.getOfficeLocation().equals(office)
                                    || OFFICELOCATION.OVERSEAS.getOfficeLocation().equals(office)
                                    || OFFICELOCATION.MIDDLEMOUNTAINS.getOfficeLocation().equals(office)) {
                                generalAsset.setOfficeLocation(row.getCell(17).getStringCellValue());

                            } else {
                                if ("".equals(errorNull)) {
                                    errorNull = "第" + i + "行办公 地点有误只能为：吴江||上海||中山||驻外";
                                } else {
                                    errorNull = errorNull + "\n" + "第" + i + "行办公地点有误只能为：吴江||上海||中山||驻外";
                                }
                                continue;
                            }
                        } else {
                            if ("".equals(errorNull)) {
                                errorNull = "第" + i + "行办公 地点不能为空";
                            } else {
                                errorNull = errorNull + "\n" + "第" + i + "行办公地点不能为空";
                            }
                            continue;
                        }
                    } else {
                        if ("".equals(errorNull)) {
                            errorNull = "第" + i + "行办公 地点不能为空";
                        } else {
                            errorNull = errorNull + "\n" + "第" + i + "行办公地点不能为空";
                        }
                        continue;
                    }
                    if (null != row.getCell(18) && !"".equals(row.getCell(18))) {
                        row.getCell(18).setCellType(Cell.CELL_TYPE_STRING);
                        // 存放位置
                        if (!"".equals(row.getCell(18).getStringCellValue())) {
                            generalAsset.setLocation(row.getCell(18).getStringCellValue());
                        }
                        /*
                         * boolean bool=false; List<UserInfo>
                         * userList=getCpsition
                         * (row.getCell(17).getStringCellValue().trim()); for
                         * (UserInfo userInfo : userList) {
                         * if(userInfo.getCpsition
                         * ().equals(row.getCell(18).getStringCellValue())){
                         * bool=true; } } if(bool){
                         * generalAsset.setLocation(row.
                         * getCell(18).getStringCellValue()); }else{
                         * if("".equals(errorNull)){ errorNull="第"+i+"行存放位置有误";
                         * }else{ errorNull=errorNull+"\n"+"第"+i+"行存放位置有误"; }
                         * continue; } }else{ if("".equals(errorNull)){
                         * errorNull="第"+i+"行存放位置不能为空"; }else{
                         * errorNull=errorNull+"\n"+"第"+i+"行存放位置不能为空"; }
                         * continue; }
                         */
                    }
                    if (null != row.getCell(19) && !"".equals(row.getCell(19))) {
                        row.getCell(19).setCellType(Cell.CELL_TYPE_STRING);

                        if (!"".equals(row.getCell(19).getStringCellValue())) {
                            String useState = row.getCell(19).getStringCellValue();
                            if (USESTATE.FONTINUSE.getUseState().equals(useState.trim())
                                    || USESTATE.FONTIDLEIN.getUseState().equals(useState)
                                    || USESTATE.FONTIDLEIN.equals(useState)
                                    || USESTATE.FONTMAJORREPAIR.getUseState().equals(useState)
                                    || USESTATE.FONTINTRANSFORMATION.getUseState().equals(useState)
                                    || USESTATE.FONTDISCARDAPPLY.getUseState().equals(useState)
                                    || USESTATE.FONTSCRAPPED.getUseState().equals(useState)
                                    || USESTATE.FONTSELLAPPLY.getUseState().equals(useState)
                                    || USESTATE.FONTALREADYSOLD.getUseState().equals(useState)) {
                                if (USESTATE.FONTINUSE.getUseState().equals(useState)) {
                                    generalAsset.setUseState(USESTATE.INUSE.getUseState()); // 在用
                                }
                                if (USESTATE.FONTIDLEIN.equals(useState)) {
                                    generalAsset.setUseState(USESTATE.IDLEIN.getUseState()); // 闲置中
                                }
                                if (USESTATE.FONTMAJORREPAIR.getUseState().equals(useState)) {
                                    generalAsset.setUseState(USESTATE.MAJORREPAIR.getUseState()); // 大修理中
                                }
                                if (USESTATE.FONTINTRANSFORMATION.getUseState().equals(useState)) {
                                    generalAsset.setUseState(USESTATE.INTRANSFORMATION.getUseState()); // 改造中
                                }
                                if (USESTATE.FONTDISCARDAPPLY.getUseState().equals(useState)) {
                                    generalAsset.setUseState(USESTATE.DISCARDAPPLY.getUseState()); // 报废申请中
                                }
                                if (USESTATE.FONTSCRAPPED.getUseState().equals(useState)) {
                                    generalAsset.setUseState(USESTATE.SCRAPPED.getUseState()); // 已报废
                                }
                                if (USESTATE.FONTSELLAPPLY.getUseState().equals(useState)) {
                                    generalAsset.setUseState(USESTATE.SELLAPPLY.getUseState()); // 报废申请中
                                }
                                if (USESTATE.FONTALREADYSOLD.getUseState().equals(useState)) {
                                    generalAsset.setUseState(USESTATE.ALREADYSOLD.getUseState()); // 报废申请中
                                }
                            } else {
                                if ("".equals(errorNull)) {
                                    errorNull = "第" + i
                                            + "行使用状态有误需为：在用||闲置中||大修理中||改造中||报废申请中||已报废||变卖申请中||已变卖";
                                } else {
                                    errorNull = errorNull + "\n"
                                            + "第" + i + "行使用状态有误需为：在用||闲置中||大修理中||改造中||报废申请中||已报废||变卖申请中||已变卖";
                                }
                                continue;
                            }
                        } else {
                            if ("".equals(errorNull)) {
                                errorNull = "第" + i + "行使用状态不能为空";
                            } else {
                                errorNull = errorNull + "\n" + "第" + i + "行使用状态不能为空";
                            }
                            continue;
                        }
                    }
                    if (null != row.getCell(28) && !"".equals(row.getCell(28))) {
                        row.getCell(28).setCellType(Cell.CELL_TYPE_STRING);
                        // 处理价值
                        if (!"".equals(row.getCell(28).getStringCellValue())) {
                            generalAsset.setUnit(row.getCell(28).getStringCellValue());
                        } else {
                            generalAsset.setUnit("");
                        }
                    }
                    if (null != row.getCell(29) && !"".equals(row.getCell(29))) {
                        row.getCell(29).setCellType(Cell.CELL_TYPE_STRING);
                        // 供应商编码
                        if (!"".equals(row.getCell(29).getStringCellValue())) {
                            generalAsset.setSupplierCode(row.getCell(29).getStringCellValue());
                        } else {
                            generalAsset.setSupplierCode("");
                        }
                    }
                    if (null != row.getCell(30) && !"".equals(row.getCell(30))) {
                        row.getCell(30).setCellType(Cell.CELL_TYPE_STRING);
                        // 供应商名称
                        if (!"".equals(row.getCell(30).getStringCellValue())) {
                            generalAsset.setSupplierName(row.getCell(30).getStringCellValue());
                        } else {
                            generalAsset.setSupplierName("");
                        }
                    }
                    if (null != row.getCell(31) && !"".equals(row.getCell(31))) {
                        row.getCell(31).setCellType(Cell.CELL_TYPE_STRING);
                        // 供应商联系人
                        if (!"".equals(row.getCell(31).getStringCellValue())) {
                            generalAsset.setSupplierLinkman(row.getCell(31).getStringCellValue());
                        } else {
                            generalAsset.setSupplierLinkman("");
                        }
                    }
                    if (null != row.getCell(32) && !"".equals(row.getCell(32))) {
                        row.getCell(32).setCellType(Cell.CELL_TYPE_STRING);
                        // 供应商联系电话
                        if (!"".equals(row.getCell(30).getStringCellValue())) {
                            generalAsset.setSupplierPhone(row.getCell(32).getStringCellValue());
                        } else {
                            generalAsset.setSupplierPhone("");
                        }
                    }
                    if (null != row.getCell(33) && !"".equals(row.getCell(33))) {
                        row.getCell(33).setCellType(Cell.CELL_TYPE_STRING);
                        // 备注一
                        if (!"".equals(row.getCell(33).getStringCellValue())) {
                            generalAsset.setRemarkOne(row.getCell(33).getStringCellValue());
                        } else {
                            generalAsset.setRemarkOne("");
                        }
                    }
                    if (null != row.getCell(34) && !"".equals(row.getCell(34))) {
                        row.getCell(34).setCellType(Cell.CELL_TYPE_STRING);
                        // 备注二
                        if (!"".equals(row.getCell(34).getStringCellValue())) {
                            generalAsset.setRemarkTwo(row.getCell(32).getStringCellValue());
                        } else {
                            generalAsset.setRemarkTwo("");
                        }
                    }
                    if (null != row.getCell(35) && !"".equals(row.getCell(35))) {
                        row.getCell(35).setCellType(Cell.CELL_TYPE_STRING);
                        // 备注三
                        if (!"".equals(row.getCell(35).getStringCellValue())) {
                            generalAsset.setRemarkThree(row.getCell(35).getStringCellValue());
                        } else {
                            generalAsset.setRemarkThree("");
                        }
                    }
                    if (null != row.getCell(36) && !"".equals(row.getCell(36))) {
                        row.getCell(36).setCellType(Cell.CELL_TYPE_STRING);
                        // 备注四
                        if (!"".equals(row.getCell(36).getStringCellValue())) {
                            generalAsset.setRemark(row.getCell(36).getStringCellValue());
                        } else {
                            generalAsset.setRemark("");
                        }
                    }
                    if (ISEXPENSEASSETS.FONTNO.getIsExpenseassets().equals(
                            isExpenseAssets)) {
                        String mmzb = workFlowService.getDocumentByType("FA_MMZB");
                        generalAsset.setSysteManagementCode(mmzb);
                        generalAsset.setIsExpenseAssets("0");
                        String companyCode = "";
                        if (null != row.getCell(0) && !"".equals(row.getCell(0))) {
                            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                            companyCode = row.getCell(0).getStringCellValue();
                            if (!"".equals(row.getCell(0).getStringCellValue())) {
                                // 公司编码
                                generalAsset.setCompanyCode(companyCode);
                            } else {
                                if ("".equals(errorNull)) {
                                    errorNull = "第" + i + "行公司编码不能为空";
                                } else {
                                    errorNull = errorNull + "\n" + "第" + i + "行公司编码不能为空";
                                }
                                continue;
                            }
                        }
                        String assetCode = "";
                        if (null != row.getCell(1) && !"".equals(row.getCell(2))) {
                            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                            assetCode = row.getCell(1).getStringCellValue();
                            // 资产编号
                            if (!"".equals(row.getCell(1).getStringCellValue())) {
                                generalAsset.setAssetCode(assetCode);
                            } else {
                                if ("".equals(errorNull)) {
                                    errorNull = "第" + i + "行资产编号不能为空";
                                } else {
                                    errorNull = errorNull + "\n" + "第" + i + "行资产编号不能为空";
                                }
                                continue;
                            }

                        }
                        if (!"".equals(assetCode) && !"".equals(companyCode)) {
                            ReadAssetsCodeModel readAssetsCodeModel = new ReadAssetsCodeModel();
                            String[] code = assetCode.split("-");
                            String mainCode = code[0];
                            String secondaryCode = code[1]; // 次级编码

                            readAssetsCodeModel.setCompanyCode(companyCode);
                            readAssetsCodeModel.setMasterAssetCode(mainCode);
                            readAssetsCodeModel.setSecondaryAssetCode(secondaryCode);

                            JSONObject sapCard = sapUtilService.readAssetsCodeFromSAP(readAssetsCodeModel);
                            if ("".equals(sapCard.getString("error"))) {
                                CompanyDepCostcenter companyDepCostcenter = new CompanyDepCostcenter();
                                String nameCode = sapCard.getString("inventNo");

                                if (null != nameCode && !"".equals(nameCode)) {
                                    if (nameCode.contains("-")) {
                                        String[] name = nameCode.split("-");
                                        generalAsset.setAssetPerson(name[1]); // 资产责任人名称
                                        generalAsset.setAssetPersonCode(name[0]); // 资产责任人编码
                                    } else {
                                        generalAsset.setAssetPerson(nameCode); // 资产责任人编码
                                    }
                                } else {
                                    generalAsset.setAssetPerson("");
                                    generalAsset.setAssetPersonCode("");
                                }
                                companyDepCostcenter.setCostCenterCode(sapCard.getString("costcenter"));

                                List<CompanyDepCostcenter> companyDep = companyDepCostcenterService.serachCompanyDepCostcenter(companyDepCostcenter);
                                generalAsset.setAssetName(sapCard.getString("descript")); // 资产名称
                                generalAsset.setOldHomeCompany(sapCard.getString("tradeId")); // 原归属公司
                                generalAsset.setOldAssetCode(sapCard.getString("originAssetNo")); // 原资产编码
                                companyDepCostcenter.setCostCenterCode(sapCard.getString("costcenter"));
                                AssetsBudgetFromBUD assetsBudgetFromBUD = new AssetsBudgetFromBUD();
                                assetsBudgetFromBUD.setCostCenter(sapCard.getString("costcenter"));
                                assetsBudgetFromBUD.setAssetType(namePattern);
                                AttchConfig attchConfig = attchConfigService.searchByCostcenterCode(assetsBudgetFromBUD);
                                if (attchConfig != null) {
                                    generalAsset.setPlatform(attchConfig.getPlatform());
                                }
                                if (null != companyDep && 0 != companyDep.size()) {
                                    generalAsset.setCompanyName(companyDep.get(0).getCompanyName()); // 公司名称
                                    generalAsset.setPersentDepartment(companyDep.get(0).getCostCenterName()); // 成本中心
                                    generalAsset.setUseDepartment(companyDep.get(0).getCostCenterName()); //部门名称

                                }
                                generalAsset.setUseDepartmentCode(sapCard.getString("costcenter")); //使用部门编码
                                generalAsset.setAssetClassification(sapCard.getString("assetClass")); // 资产分类
                                generalAsset.setSpecificationModel(sapCard.getString("descript")); // 规格型号
                                generalAsset.setSelectAccessories(sapCard.getString("descript2")); // 选配件
                                generalAsset.setAssetState("1"); // 资产状态，默认已验收

                                if (!"".equals(sapCard.getString("initialAcq")) && null != sapCard.getString("initialAcq")) {
                                    generalAsset.setActualDateAcceptance(getDate(sapCard.getString("initialAcq"))); // 实际验收日期
                                }
                                if (!"".equals(sapCard.getString("capDate")) && null != sapCard.getString("capDate")) {
                                    generalAsset.setPostingDate(getDate(sapCard.getString("capDate"))); // 入账日期
                                }
                                ApplyOrderDetail applyOrderDetail = new ApplyOrderDetail();
                                applyOrderDetail.setAssetClassification(sapCard.getString("assetClass")); // 获取资产分类名称
                                AssetClassification assetClassification = assetClassificationService.searchAssetClassificationByAssetClassification(applyOrderDetail);
                                if (null != assetClassification) {
                                    generalAsset.setAssetClassificationName(assetClassification
                                            .getAssetstypeInfo()); // 资产分类名称
                                }
                                generalAsset.setCostCenterCode(sapCard.getString("costcenter")); // 成本中心编码
                                generalAsset.setAssetOriginalValue(sapCard.getString("currentApc")); // 资产原值
                                generalAsset.setAssetNetValue(sapCard.getString("currentNbv")); // 资产净值
                                generalAsset.setPeriodOfDepreciation(sapCard.getString("ulife")); // 折旧年限
                                // generalAsset
                                if (!"".equals(sapCard.getString("deprBeginDate"))
                                        && sapCard.getString("deprBeginDate") != null) {
                                    generalAsset.setDepreciationStartDate(getDate(sapCard.getString("deprBeginDate"))); // 折旧开始日期
                                } else {
                                    generalAsset.setDepreciationStartDate(null);
                                }
                                generalAsset.setMonthCountingDepreciation(sapCard.getString("monthlyDepreciation1")); // 月折旧额
                                generalAsset.setCurrentYearDepreciation(sapCard.getString("deprCurrYear")); // 本年已计提累计折旧
                                generalAsset.setAddDepreciation(sapCard.getString("accumDepr")); // 累计折旧
                                String unitCode = sapCard.getString("baseUom"); // 单位编码
                                AssetUnit assetUnit = new AssetUnit();
                                if (unitCode != null && !"".equals(unitCode)) {
                                    assetUnit.setUnitCode(unitCode);
                                    assetUnit = assetUnitService.getAssetUnit(assetUnit);
                                }
                                if (null != assetUnit) {
                                    generalAsset.setUnit(assetUnit.getUnitName()); // 单位名称
                                    generalAsset.setUnitCode(unitCode);
                                }

                            } else {
                                if ("".equals(errorNull)) {
                                    errorNull = "第" + i + "行数据" + sapCard.getString("error");
                                } else {
                                    errorNull = errorNull + "\n" + "第" + i + "行数据" + sapCard.getString("error");
                                }
                                continue;
                            }
                        }
                    }
                    generalAsset.setDeletedFlag(0);
                    if (ASSETTYPE.FONTITASSET.getAssetType().equals(namePattern)) {
                        ComputerAsset computerAsset = new ComputerAsset();
                        if (null != row.getCell(37) && !"".equals(row.getCell(37))) {
                            row.getCell(37).setCellType(Cell.CELL_TYPE_STRING);
                            // 序列号
                            generalAsset.setSerialNumber(row.getCell(37).getStringCellValue());
                        }
                        if (null != row.getCell(38) && !"".equals(row.getCell(38))) {
                            row.getCell(38).setCellType(Cell.CELL_TYPE_STRING);
                            // 归口管理编码
                            generalAsset.setPutUnderManageCode(row.getCell(38).getStringCellValue());
                        }
                        if (null != row.getCell(39)) {
                            row.getCell(39).setCellType(Cell.CELL_TYPE_STRING);
                            // CPU
                            computerAsset.setCpu(row.getCell(39).getStringCellValue());
                        }
                        if (null != row.getCell(40)) {
                            row.getCell(40).setCellType(Cell.CELL_TYPE_STRING);
                            // 内存
                            computerAsset.setInternalMemory(row.getCell(40).getStringCellValue());
                        }
                        if (null != row.getCell(41)) {
                            row.getCell(41).setCellType(Cell.CELL_TYPE_STRING);
                            // 硬盘
                            computerAsset.setHardpan(row.getCell(41).getStringCellValue());
                        }
                        if (null != row.getCell(42)) {
                            row.getCell(42).setCellType(Cell.CELL_TYPE_STRING);
                            // IP地址
                            computerAsset.setIpAddress(row.getCell(42).getStringCellValue());
                        }
                        if (null != row.getCell(43)) {
                            row.getCell(43).setCellType(Cell.CELL_TYPE_STRING);
                            // 加入域
                            computerAsset.setJoinDomain(row.getCell(43).getStringCellValue());
                        }
                        if (null != row.getCell(44)) {
                            row.getCell(44).setCellType(Cell.CELL_TYPE_STRING);
                            // 操作系统
                            computerAsset.setOperatingSystem(row.getCell(44)
                                    .getStringCellValue());
                        }
                        if (null != row.getCell(45)) {
                            row.getCell(45).setCellType(Cell.CELL_TYPE_STRING);
                            // 应用系统
                            computerAsset.setUtilitySystem(row.getCell(45)
                                    .getStringCellValue());
                        }
                        if (null != row.getCell(46)) {
                            row.getCell(46).setCellType(Cell.CELL_TYPE_STRING);
                            // 功能描述
                            computerAsset.setDescription(row.getCell(46)
                                    .getStringCellValue());
                        }
                        if (null != row.getCell(47)) {
                            row.getCell(47).setCellType(Cell.CELL_TYPE_STRING);
                            // 数据库
                            computerAsset.setDataBase(row.getCell(47)
                                    .getStringCellValue());
                        }
                        generalAsset.setAssetType("0");

                        listGeneral.add(generalAsset);
                        listComputerAsset.add(computerAsset);

                    }
                    if (ASSETTYPE.FONTADMINISTRATIVE.getAssetType().equals(
                            namePattern)) {
                        AdministrationAsset administrationAsset = new AdministrationAsset();
                        if (null != row.getCell(37)
                                && !"".equals(row.getCell(37))) {
                            row.getCell(37).setCellType(Cell.CELL_TYPE_STRING);
                            // 出厂编号/序列号
                            administrationAsset.setSerialNumber(row.getCell(37)
                                    .getStringCellValue());
                        } else {
                            administrationAsset.setSerialNumber("");
                        }
                        if (null != row.getCell(38)
                                && !"".equals(row.getCell(38))) {
                            row.getCell(38).setCellType(Cell.CELL_TYPE_STRING);
                            // 管理编号
                            administrationAsset.setManagementCode(row.getCell(
                                    38).getStringCellValue());
                        } else {
                            administrationAsset.setManagementCode("");
                        }
                        if (null != row.getCell(39)
                                && !"".equals(row.getCell(39))) {
                            row.getCell(39).setCellType(Cell.CELL_TYPE_STRING);
                            String isBuild = row
                                    .getCell(39).getStringCellValue();
                            // 管理编号
                            if (ISEXPENSEASSETS.FONTYES.getIsExpenseassets().equals(isBuild)) {
                                administrationAsset.setIsBuildingContruction("Y");
                            } else if (ISEXPENSEASSETS.FONTNO.getIsExpenseassets().equals(isBuild)) {
                                administrationAsset.setIsBuildingContruction("N");
                            }
                        }
                        generalAsset.setAssetType("1");

                        listGeneral.add(generalAsset);
                        listAdministrationAsset.add(administrationAsset);
                    }
                    if (ASSETTYPE.FONTMEASURINGINSTRUMENTS.getAssetType()
                            .equals(namePattern)) {
                        MeasuringInstruments measuringInstruments = new MeasuringInstruments();
                        if (null != row.getCell(37)
                                && !"".equals(row.getCell(37))) {
                            row.getCell(37).setCellType(Cell.CELL_TYPE_STRING);
                            // 出厂编号
                            measuringInstruments.setFacrotyNumber(row.getCell(
                                    37).getStringCellValue());
                        }
                        if (null != row.getCell(38)
                                && !"".equals(row.getCell(38))) {
                            row.getCell(38).setCellType(Cell.CELL_TYPE_STRING);
                            // 管理编号
                            measuringInstruments.setManagementCode(row.getCell(
                                    38).getStringCellValue());
                        }
                        if (null != row.getCell(39)
                                && !"".equals(row.getCell(39))) {
                            row.getCell(39).setCellType(Cell.CELL_TYPE_STRING);
                            // 生产厂家
                            measuringInstruments.setManufactureFactory(row
                                    .getCell(39).getStringCellValue());
                        }
                        if (null != row.getCell(40)
                                && !"".equals(row.getCell(40))) {
                            row.getCell(40).setCellType(Cell.CELL_TYPE_STRING);
                            // 管理级别
                            measuringInstruments.setManagementLevel(row
                                    .getCell(40).getStringCellValue());
                        }
                        if (null != row.getCell(41)
                                && !"".equals(row.getCell(41))) {
                            row.getCell(41).setCellType(Cell.CELL_TYPE_STRING);
                            // 精度等级
                            measuringInstruments.setAccuracyClass(row.getCell(
                                    41).getStringCellValue());
                        }
                        if (null != row.getCell(42)
                                && !"".equals(row.getCell(42))) {
                            row.getCell(42).setCellType(Cell.CELL_TYPE_STRING);
                            // 检定周期
                            measuringInstruments.setVerificationCycle(row
                                    .getCell(42).getStringCellValue());
                        }
                        if (null != row.getCell(43)
                                && !"".equals(row.getCell(43))) {
                            row.getCell(43).setCellType(Cell.CELL_TYPE_NUMERIC);
                            Date date = row.getCell(43).getDateCellValue();
                            // 校准日期
                            measuringInstruments.setCalibrationDate(date);
                        }
                        if (null != row.getCell(44)
                                && !"".equals(row.getCell(44))) {
                            row.getCell(44).setCellType(Cell.CELL_TYPE_NUMERIC);
                            Date date = row.getCell(44).getDateCellValue();
                            // 下次校准日期
                            measuringInstruments.setNextCalibrationDate(date);
                        }
                        if (null != row.getCell(45)
                                && !"".equals(row.getCell(45))) {
                            row.getCell(45).setCellType(Cell.CELL_TYPE_STRING);
                            // 校准单位
                            measuringInstruments.setCalibrationUnit(row
                                    .getCell(45).getStringCellValue());
                        }
                        if (null != row.getCell(46)
                                && !"".equals(row.getCell(46))) {
                            row.getCell(46).setCellType(Cell.CELL_TYPE_STRING);
                            // 是否有第三方检验报告
                            measuringInstruments.setIsThirdReport(row.getCell(
                                    46).getStringCellValue());
                        }
                        generalAsset.setAssetType("2");

                        listGeneral.add(generalAsset);
                        listMeasuringInstruments.add(measuringInstruments);
                    }

                    if (ASSETTYPE.FONTMACHINERYEQUIPMENT.getAssetType().equals(
                            namePattern)) {
                        MachineryEquipment machineryEquipment = new MachineryEquipment();
                        if (null != row.getCell(37)
                                && !"".equals(row.getCell(37))) {
                            row.getCell(37).setCellType(Cell.CELL_TYPE_STRING);
                            // 出厂编号
                            machineryEquipment.setMachineCode(row.getCell(37)
                                    .getStringCellValue());
                        }
                        if (null != row.getCell(38)
                                && !"".equals(row.getCell(38))) {
                            row.getCell(38).setCellType(Cell.CELL_TYPE_STRING);
                            // 归口管理编码
                            machineryEquipment.setPutUnderManageCode(row
                                    .getCell(38).getStringCellValue());
                        }
                        if (null != row.getCell(38)
                                && !"".equals(row.getCell(38))) {
                            row.getCell(38).setCellType(Cell.CELL_TYPE_STRING);
                            // 车间类型
                            machineryEquipment.setShopType(row.getCell(38)
                                    .getStringCellValue());
                        }
                        generalAsset.setAssetType("3");
                        listMachineryEquipment.add(machineryEquipment);
                        listGeneral.add(generalAsset);
                    }
                }
            }
            countSum = i;
        }
        generalAssetService.excelInsertAssetCard(listGeneral,
                listComputerAsset, listMachineryEquipment,
                listMeasuringInstruments, listAdministrationAsset, isFyorZb);
        if (StringUtils.isBlank(errorNull)) {

            jsonMap.put("success", true);
            jsonMap.put("successMage", "共" + countSum + "行数据导入成功");
        } else if (errorNull.length() > 100) {
            errorNull = errorNull.substring(0, 100);
            errorNull = errorNull.substring(0, errorNull.lastIndexOf("\n"));
            jsonMap.put("errorNull", errorNull + "\n...");
        } else {
            jsonMap.put("errorNull", errorNull);
        }
        try {
            response.setContentType("text/html; charset=utf-8");
            // response.getWriter().write(JSONObject.fromObject(jsonMap).toString());
            response.getWriter().write(
                    JSONObject.fromObject(jsonMap).toString()
                            + "<script>document.domain='opple.com'</script>");
            response.getWriter().flush();
        } catch (IOException e1) {
            LOGGER.error("IOException", e1);
        }
        return null;

    }

    @RequestMapping("/api/updateAssetCard")
    @ResponseBody
    public ExecuteResult<GeneralAsset> updateAssetCard(
            @RequestBody AssetCardWrapper assetCardWrapper) {
        ComputerAssetModel computerAssetModel = assetCardWrapper
                .getComputerAssetModel();
        GeneralAssetModel generalAssetModel = assetCardWrapper
                .getGeneralAssetModel();
        AdministrativeAssetModel administrativeModel = assetCardWrapper
                .getAdministrativeModel();
        MeasuringInstrumentsModel measuringInstrumentsModel = assetCardWrapper
                .getMeasuringInstrumentsModel();
        MachineryEquipmentModel machineryEquipmentModel = assetCardWrapper
                .getMachineryEquipmentModel();
        ExecuteResult<GeneralAsset> result = new ExecuteResult<GeneralAsset>();
        GeneralAsset generalAsset = new GeneralAsset();
        ComputerAsset computerAsset = new ComputerAsset();
        MeasuringInstruments measuringInstruments = new MeasuringInstruments();
        MachineryEquipment machineryEquipment = new MachineryEquipment();
        AdministrationAsset administrationAsset = new AdministrationAsset();
        BeanUtils.copyProperties(generalAssetModel, generalAsset);
        if (generalAssetModel.getAssetType().equals(
                ASSETTYPE.ITASSET.getAssetType())) {
            BeanUtils.copyProperties(computerAssetModel, computerAsset);
            BeanUtils.copyProperties(generalAssetModel, generalAsset);
        }
        if (generalAssetModel.getAssetType().equals(
                ASSETTYPE.ADMINISTRATIVE.getAssetType())) {
            BeanUtils.copyProperties(administrativeModel, administrationAsset);
            BeanUtils.copyProperties(generalAssetModel, generalAsset);
        }
        if (generalAssetModel.getAssetType().equals(
                ASSETTYPE.MEASURINGINSTRUMENTS.getAssetType())) {
            BeanUtils.copyProperties(measuringInstrumentsModel,
                    measuringInstruments);
            BeanUtils.copyProperties(generalAssetModel, generalAsset);
        }
        if (generalAssetModel.getAssetType().equals(
                ASSETTYPE.MACHINERYEQUIPMENT.getAssetType())) {

            BeanUtils.copyProperties(generalAssetModel, generalAsset);
            BeanUtils.copyProperties(machineryEquipmentModel,
                    machineryEquipment);
        }
        generalAssetService.updateAssetCard(generalAsset, computerAsset,
                machineryEquipment, measuringInstruments, administrationAsset);
        return result;
    }

    public List<UserInfo> getCpsition(String workAddress) {
        UserInfo userInfo = new UserInfo();
        userInfo.setWorkAddress(workAddress);
        List<UserInfo> userInfos = userInfoService.getCpsition(userInfo);
        return userInfos;
    }

    public Date getDate(String dat) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @RequestMapping("/ceshi")
    public String ceshi() {
        return "show_pdf_pic";
    }

    /**
     * 设置响应头
     */
    public void setResponseHeader(HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + java.net.URLEncoder.encode(this.fileName, "UTF-8")
                    + ".zip");
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            LOGGER.error("e", ex);
        }
    }

    public void toExportPayDetail(List<GeneralAsset> list,
                                  HttpServletRequest request, int length, String f, OutputStream out)
            throws IOException {
        List<String> fileNames = new ArrayList(); // 用于存放生成的文件名称s
        String path = Env.getProperty("file.upload.excel.path");
        File zip = new File(path + "excel/" + f + ".zip"); // 压缩文件
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        // 生成excel
        for (int j = 0, n = list.size() / length + 1; j < n; j++) {
            Workbook book = new HSSFWorkbook();
            Sheet sheet = book.createSheet("IT资产");
            // double d = 0; // 用来统计
            String file = path + "excel/" + f + "-" + j + ".xls";
            fileNames.add(file);
            FileOutputStream o = null;
            try {
                o = new FileOutputStream(file);
                Row row = sheet.createRow(0);

                row.createCell(0).setCellValue("公司编码");
                row.createCell(1).setCellValue("资产编号");
                row.createCell(2).setCellValue("资产名称");
                row.createCell(3).setCellValue("原资产编码");
                row.createCell(4).setCellValue("原归属公司");
                row.createCell(5).setCellValue("资产分类");
                row.createCell(6).setCellValue("是否为一次性费用化资产");
                row.createCell(7).setCellValue("选配件");
                row.createCell(8).setCellValue("采购到货日期");
                row.createCell(9).setCellValue("验收周期(日)");
                row.createCell(10).setCellValue("计划验收日期");
                row.createCell(11).setCellValue("实际验收日期");
                row.createCell(12).setCellValue("质保周期");
                row.createCell(13).setCellValue("质保到期日");
                row.createCell(14).setCellValue("入账日期");
                row.createCell(15).setCellValue("成本中心编码");
                row.createCell(16).setCellValue("管理责任人");
                row.createCell(17).setCellValue("办公地点");
                row.createCell(18).setCellValue("存放位置");
                row.createCell(19).setCellValue("使用状态");
                row.createCell(20).setCellValue("资产原值");
                row.createCell(21).setCellValue("资产净值");
                row.createCell(22).setCellValue("折旧年限(月)");
                row.createCell(23).setCellValue("折旧开始日期");
                row.createCell(24).setCellValue("月折旧额");
                row.createCell(25).setCellValue("本年已计提累计折旧");
                row.createCell(26).setCellValue("累计折旧");
                row.createCell(27).setCellValue("处理价值");
                row.createCell(28).setCellValue("单位名称");
                row.createCell(29).setCellValue("供应商编码");
                row.createCell(30).setCellValue("供应商名称");
                row.createCell(31).setCellValue("供应商联系人");
                row.createCell(32).setCellValue("供应商联系电话");
                row.createCell(33).setCellValue("备注一");
                row.createCell(34).setCellValue("备注二");
                row.createCell(35).setCellValue("备注三");
                row.createCell(36).setCellValue("备注四");
                row.createCell(37).setCellValue("序列号");
                row.createCell(38).setCellValue("归口管理编码");
                row.createCell(39).setCellValue("CPU");
                row.createCell(40).setCellValue("内存");
                row.createCell(41).setCellValue("硬盘");
                row.createCell(42).setCellValue("IP地址");
                row.createCell(43).setCellValue("加入域");
                row.createCell(44).setCellValue("操作系统");
                row.createCell(45).setCellValue("应用系统");
                row.createCell(46).setCellValue("功能描述");
                row.createCell(47).setCellValue("数据库");

                for (int i = 1, min = (list.size() - j * length + 1) > (length + 1) ? (length + 1)
                        : (list.size() - j * length + 1); i < min; i++) {
                    GeneralAsset generaAsset2 = list.get(length * j + i - 1);
                    generaAsset2 = (GeneralAsset) getQueryCriteria2(generaAsset2);
                    Row row1 = sheet.createRow(i);

                    row1.createCell(0).setCellValue(generaAsset2.getCompanyCode() == null ? "" : generaAsset2.getCompanyCode().toString());
                    row1.createCell(1).setCellValue(generaAsset2.getAssetCode() == null ? "" : generaAsset2.getAssetCode());
                    row1.createCell(2).setCellValue(generaAsset2.getAssetName() == null ? "" : generaAsset2.getAssetName());
                    row1.createCell(3).setCellValue(generaAsset2.getOldAssetCode() == null ? "" : generaAsset2.getOldAssetCode());
                    row1.createCell(4).setCellValue(generaAsset2.getOldHomeCompany() == null ? "" : generaAsset2.getOldHomeCompany());
                    row1.createCell(5).setCellValue(generaAsset2.getAssetClassification() == null ? "" : generaAsset2.getAssetClassification());
                    if (StringUtils.isNotBlank(generaAsset2.getIsExpenseAssets())) {
                        if (ISEXPENSEASSETS.NO.getIsExpenseassets().equals(generaAsset2.getIsExpenseAssets())) {
                            row1.createCell(6).setCellValue(ISEXPENSEASSETS.FONTNO.getIsExpenseassets());
                        } else if (ISEXPENSEASSETS.YES.getIsExpenseassets().equals(generaAsset2.getIsExpenseAssets())) {
                            row1.createCell(6).setCellValue(ISEXPENSEASSETS.FONTYES.getIsExpenseassets());
                        } else {
                            row1.createCell(6).setCellValue(generaAsset2.getIsExpenseAssets());
                        }
                    } else {
                        row1.createCell(6).setCellValue("");
                    }
                    row1.createCell(7).setCellValue(generaAsset2.getSelectAccessories() == null ? "" : generaAsset2.getSelectAccessories());
                    row1.createCell(8).setCellValue(generaAsset2.getPurchaseArrivalDate() == null ? "" : sdf.format(generaAsset2.getPurchaseArrivalDate()));
                    row1.createCell(9).setCellValue(generaAsset2.getAcceptanceCycle() == null ? "" : generaAsset2.getAcceptanceCycle());
                    row1.createCell(10).setCellValue(generaAsset2.getDateOfAcceptance() == null ? "" : sdf.format(generaAsset2.getDateOfAcceptance()));
                    row1.createCell(11).setCellValue(generaAsset2.getActualDateAcceptance() == null ? "" : sdf.format(generaAsset2.getActualDateAcceptance()));
                    row1.createCell(12).setCellValue(generaAsset2.getWarrantyPeriod() == null ? "" : generaAsset2.getWarrantyPeriod());
                    row1.createCell(13).setCellValue(generaAsset2.getExpirationDate() == null ? "" : sdf.format(generaAsset2.getExpirationDate()));
                    row1.createCell(14).setCellValue(generaAsset2.getPostingDate() == null ? "" : sdf.format(generaAsset2.getPostingDate()));
                    row1.createCell(15).setCellValue(generaAsset2.getCostCenterCode() == null ? "" : generaAsset2.getCostCenterCode());
                    row1.createCell(16).setCellValue(generaAsset2.getAssetPersonCode() == null ? "" : "(" + generaAsset2.getAssetPersonCode() + ")" + generaAsset2.getAssetPerson());
                    row1.createCell(17).setCellValue(generaAsset2.getOfficeLocation() == null ? "" : generaAsset2.getOfficeLocation());
                    if (StringUtils.isNotBlank(generaAsset2.getUseState())) {
                        if (USESTATE.INUSE.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTINUSE.getUseState());
                        } else if (USESTATE.IDLEIN.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTIDLEIN.getUseState());
                        } else if (USESTATE.MAJORREPAIR.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTMAJORREPAIR.getUseState());
                        } else if (USESTATE.INTRANSFORMATION.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTINTRANSFORMATION.getUseState());
                        } else if (USESTATE.DISCARDAPPLY.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTDISCARDAPPLY.getUseState());
                        } else if (USESTATE.SCRAPPED.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTSCRAPPED.getUseState());
                        } else if (USESTATE.SELLAPPLY.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTSELLAPPLY.getUseState());
                        } else if (USESTATE.ALREADYSOLD.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTALREADYSOLD.getUseState());
                        } else {
                            row1.createCell(19).setCellValue(generaAsset2.getUseState());
                        }
                    } else {
                        row1.createCell(19).setCellValue("");
                    }
                    row1.createCell(20).setCellValue(generaAsset2.getAssetOriginalValue() == null ? "" : generaAsset2.getAssetOriginalValue());
                    row1.createCell(21).setCellValue(generaAsset2.getAssetNetValue() == null ? "" : generaAsset2.getAssetNetValue());
                    row1.createCell(22).setCellValue(generaAsset2.getPeriodOfDepreciation() == null ? "" : generaAsset2.getPeriodOfDepreciation());
                    row1.createCell(23).setCellValue(generaAsset2.getDepreciationStartDate() == null ? "" : sdf.format(generaAsset2.getDepreciationStartDate()));
                    row1.createCell(24).setCellValue(generaAsset2.getMonthCountingDepreciation() == null ? "" : generaAsset2.getMonthCountingDepreciation());
                    row1.createCell(25).setCellValue(generaAsset2.getCurrentYearDepreciation() == null ? "" : generaAsset2.getCurrentYearDepreciation());
                    row1.createCell(26).setCellValue(generaAsset2.getAddDepreciation() == null ? "" : generaAsset2.getAddDepreciation());
                    row1.createCell(27).setCellValue(generaAsset2.getSalvageValue() == null ? "" : generaAsset2.getSalvageValue());
                    row1.createCell(28).setCellValue(generaAsset2.getUnit() == null ? "" : generaAsset2.getUnit());
                    row1.createCell(29).setCellValue(generaAsset2.getSupplierCode() == null ? "" : generaAsset2.getSupplierCode());
                    row1.createCell(30).setCellValue(generaAsset2.getSupplierName() == null ? "" : generaAsset2.getSupplierName());
                    row1.createCell(31).setCellValue(generaAsset2.getSupplierLinkman() == null ? "" : generaAsset2.getSupplierLinkman());
                    row1.createCell(32).setCellValue(generaAsset2.getSupplierPhone() == null ? "" : generaAsset2.getSupplierPhone());
                    row1.createCell(33).setCellValue(generaAsset2.getRemarkOne() == null ? "" : generaAsset2.getRemarkOne());
                    row1.createCell(34).setCellValue(generaAsset2.getRemarkTwo() == null ? "" : generaAsset2.getRemarkTwo());
                    row1.createCell(35).setCellValue(generaAsset2.getRemarkThree() == null ? "" : generaAsset2.getRemarkThree());
                    row1.createCell(36).setCellValue(generaAsset2.getRemark() == null ? "" : generaAsset2.getRemark());
                    row1.createCell(37).setCellValue(generaAsset2.getSerialNumber() == null ? "" : generaAsset2.getSerialNumber());
                    row1.createCell(38).setCellValue(generaAsset2.getComputerAsset().getPutUnderManageCode() == null ? "" : generaAsset2.getComputerAsset().getPutUnderManageCode());
                    row1.createCell(39).setCellValue(generaAsset2.getComputerAsset().getCpu() == null ? "" : generaAsset2.getComputerAsset().getCpu());
                    row1.createCell(40).setCellValue(generaAsset2.getComputerAsset().getInternalMemory() == null ? "" : generaAsset2.getComputerAsset().getInternalMemory());
                    row1.createCell(41).setCellValue(generaAsset2.getComputerAsset().getHardpan() == null ? "" : generaAsset2.getComputerAsset().getHardpan());
                    row1.createCell(42).setCellValue(generaAsset2.getComputerAsset().getIpAddress() == null ? "" : generaAsset2.getComputerAsset().getIpAddress());
                    row1.createCell(43).setCellValue(generaAsset2.getComputerAsset().getJoinDomain() == null ? "" : generaAsset2.getComputerAsset().getJoinDomain());
                    row1.createCell(44).setCellValue(generaAsset2.getComputerAsset().getOperatingSystem() == null ? "" : generaAsset2.getComputerAsset().getOperatingSystem());
                    row1.createCell(45).setCellValue(generaAsset2.getComputerAsset().getUtilitySystem() == null ? "" : generaAsset2.getComputerAsset().getUtilitySystem());
                    row1.createCell(46).setCellValue(generaAsset2.getComputerAsset().getDescription() == null ? "" : generaAsset2.getComputerAsset().getDescription());
                    row1.createCell(47).setCellValue(generaAsset2.getComputerAsset().getDataBase() == null ? "" : generaAsset2.getComputerAsset().getDataBase());
                }
            } catch (Exception e) {
                LOGGER.error("e", e);
            }
            try {
                book.write(o);
            } catch (Exception ex) {
                LOGGER.error("e", ex);
            } finally {
                o.flush();
                o.close();
            }
        }
        try {
            ExportExcle.exportZip(fileNames, path, zip, out, f);
        } catch (IOException e) {
            LOGGER.error("e", e);
        } finally {
            out.close();
        }

    }

    public void toExportXZ(List<GeneralAsset> list, HttpServletRequest request, int length, String f, OutputStream out) throws IOException {
        List<String> fileNames = new ArrayList(); // 用于存放生成的文件名称s
        String path = Env.getProperty("file.upload.excel.path");
        File zip = new File(path + "excel/" + f + ".zip"); // 压缩文件
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        // 生成excel
        for (int j = 0, n = list.size() / length + 1; j < n; j++) {
            Workbook book = new HSSFWorkbook();
            Sheet sheet = book.createSheet("行政资产");
            // double d = 0; // 用来统计
            String file = path + "excel/" + f + "-" + j + ".xls";
            fileNames.add(file);
            FileOutputStream o = null;
            try {
                o = new FileOutputStream(file);

                Row row = sheet.createRow(0);
                row.createCell(0).setCellValue("公司编码");
                row.createCell(1).setCellValue("资产编号");
                row.createCell(2).setCellValue("资产名称");
                row.createCell(3).setCellValue("原资产编码");
                row.createCell(4).setCellValue("原归属公司");
                row.createCell(5).setCellValue("资产分类");
                row.createCell(6).setCellValue("是否为一次性费用化资产");
                row.createCell(7).setCellValue("选配件");
                row.createCell(8).setCellValue("采购到货日期");
                row.createCell(9).setCellValue("验收周期(日)");
                row.createCell(10).setCellValue("计划验收日期");
                row.createCell(11).setCellValue("实际验收日期");
                row.createCell(12).setCellValue("质保周期");
                row.createCell(13).setCellValue("质保到期日");
                row.createCell(14).setCellValue("入账日期");
                row.createCell(15).setCellValue("成本中心编码");
                row.createCell(16).setCellValue("管理责任人");
                row.createCell(17).setCellValue("办公地点");
                row.createCell(18).setCellValue("存放位置");
                row.createCell(19).setCellValue("使用状态");
                row.createCell(20).setCellValue("资产原值");
                row.createCell(21).setCellValue("资产净值");
                row.createCell(22).setCellValue("折旧年限(月)");
                row.createCell(23).setCellValue("折旧开始日期");
                row.createCell(24).setCellValue("月折旧额");
                row.createCell(25).setCellValue("本年已计提累计折旧");
                row.createCell(26).setCellValue("累计折旧");
                row.createCell(27).setCellValue("处理价值");
                row.createCell(28).setCellValue("单位名称");
                row.createCell(29).setCellValue("供应商编码");
                row.createCell(30).setCellValue("供应商名称");
                row.createCell(31).setCellValue("供应商联系人");
                row.createCell(32).setCellValue("供应商联系电话");
                row.createCell(33).setCellValue("备注一");
                row.createCell(34).setCellValue("备注二");
                row.createCell(35).setCellValue("备注三");
                row.createCell(36).setCellValue("备注四");
                row.createCell(37).setCellValue("出厂编号/序列号");
                row.createCell(38).setCellValue("管理编号");
                row.createCell(39).setCellValue("是否为房屋建筑");


                for (int i = 1, min = (list.size() - j * length + 1) > (length + 1) ? (length + 1) : (list.size() - j * length + 1); i < min; i++) {
                    GeneralAsset generaAsset2 = list.get(length * j + i - 1);
                    generaAsset2 = (GeneralAsset) getQueryCriteria2(generaAsset2);
                    Row row1 = sheet.createRow(i);

                    row1.createCell(0).setCellValue(generaAsset2.getCompanyCode() == null ? "" : generaAsset2.getCompanyCode().toString());
                    row1.createCell(1).setCellValue(generaAsset2.getAssetCode() == null ? "" : generaAsset2.getAssetCode());
                    row1.createCell(2).setCellValue(generaAsset2.getAssetName() == null ? "" : generaAsset2.getAssetName());
                    row1.createCell(3).setCellValue(generaAsset2.getOldAssetCode() == null ? "" : generaAsset2.getOldAssetCode());
                    row1.createCell(4).setCellValue(generaAsset2.getOldHomeCompany() == null ? "" : generaAsset2.getOldHomeCompany());
                    row1.createCell(5).setCellValue(generaAsset2.getAssetClassification() == null ? "" : generaAsset2.getAssetClassification());
                    if (StringUtils.isNotBlank(generaAsset2.getIsExpenseAssets())) {
                        if (ISEXPENSEASSETS.NO.getIsExpenseassets().equals(generaAsset2.getIsExpenseAssets())) {
                            row1.createCell(6).setCellValue(ISEXPENSEASSETS.FONTNO.getIsExpenseassets());
                        } else if (ISEXPENSEASSETS.YES.getIsExpenseassets().equals(generaAsset2.getIsExpenseAssets())) {
                            row1.createCell(6).setCellValue(ISEXPENSEASSETS.FONTYES.getIsExpenseassets());
                        } else {
                            row1.createCell(6).setCellValue(generaAsset2.getIsExpenseAssets());
                        }
                    } else {
                        row1.createCell(6).setCellValue("");
                    }
                    row1.createCell(7).setCellValue(generaAsset2.getSelectAccessories() == null ? "" : generaAsset2.getSelectAccessories());
                    row1.createCell(8).setCellValue(generaAsset2.getPurchaseArrivalDate() == null ? "" : sdf.format(generaAsset2.getPurchaseArrivalDate()));
                    row1.createCell(9).setCellValue(generaAsset2.getAcceptanceCycle() == null ? "" : generaAsset2.getAcceptanceCycle());
                    row1.createCell(10).setCellValue(generaAsset2.getDateOfAcceptance() == null ? "" : sdf.format(generaAsset2.getDateOfAcceptance()));
                    row1.createCell(11).setCellValue(generaAsset2.getActualDateAcceptance() == null ? "" : sdf.format(generaAsset2.getActualDateAcceptance()));
                    row1.createCell(12).setCellValue(generaAsset2.getWarrantyPeriod() == null ? "" : generaAsset2.getWarrantyPeriod());
                    row1.createCell(13).setCellValue(generaAsset2.getExpirationDate() == null ? "" : sdf.format(generaAsset2.getExpirationDate()));
                    row1.createCell(14).setCellValue(generaAsset2.getPostingDate() == null ? "" : sdf.format(generaAsset2.getPostingDate()));
                    row1.createCell(15).setCellValue(generaAsset2.getCostCenterCode() == null ? "" : generaAsset2.getCostCenterCode());
                    row1.createCell(16).setCellValue(generaAsset2.getAssetPersonCode() == null ? "" : "(" + generaAsset2.getAssetPersonCode() + ")" + generaAsset2.getAssetPerson());
                    row1.createCell(17).setCellValue(generaAsset2.getOfficeLocation() == null ? "" : generaAsset2.getOfficeLocation());
                    if (StringUtils.isNotBlank(generaAsset2.getUseState())) {
                        if (USESTATE.INUSE.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTINUSE.getUseState());
                        } else if (USESTATE.IDLEIN.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTIDLEIN.getUseState());
                        } else if (USESTATE.MAJORREPAIR.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTMAJORREPAIR.getUseState());
                        } else if (USESTATE.INTRANSFORMATION.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTINTRANSFORMATION.getUseState());
                        } else if (USESTATE.DISCARDAPPLY.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTDISCARDAPPLY.getUseState());
                        } else if (USESTATE.SCRAPPED.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTSCRAPPED.getUseState());
                        } else if (USESTATE.SELLAPPLY.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTSELLAPPLY.getUseState());
                        } else if (USESTATE.ALREADYSOLD.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTALREADYSOLD.getUseState());
                        } else {
                            row1.createCell(19).setCellValue(generaAsset2.getUseState());
                        }
                    } else {
                        row1.createCell(19).setCellValue("");
                    }
                    row1.createCell(20).setCellValue(generaAsset2.getAssetOriginalValue() == null ? "" : generaAsset2.getAssetOriginalValue());
                    row1.createCell(21).setCellValue(generaAsset2.getAssetNetValue() == null ? "" : generaAsset2.getAssetNetValue());
                    row1.createCell(22).setCellValue(generaAsset2.getPeriodOfDepreciation() == null ? "" : generaAsset2.getPeriodOfDepreciation());
                    row1.createCell(23).setCellValue(generaAsset2.getDepreciationStartDate() == null ? "" : sdf.format(generaAsset2.getDepreciationStartDate()));
                    row1.createCell(24).setCellValue(generaAsset2.getMonthCountingDepreciation() == null ? "" : generaAsset2.getMonthCountingDepreciation());
                    row1.createCell(25).setCellValue(generaAsset2.getCurrentYearDepreciation() == null ? "" : generaAsset2.getCurrentYearDepreciation());
                    row1.createCell(26).setCellValue(generaAsset2.getAddDepreciation() == null ? "" : generaAsset2.getAddDepreciation());
                    row1.createCell(27).setCellValue(generaAsset2.getSalvageValue() == null ? "" : generaAsset2.getSalvageValue());
                    row1.createCell(28).setCellValue(generaAsset2.getUnit() == null ? "" : generaAsset2.getUnit());
                    row1.createCell(29).setCellValue(generaAsset2.getSupplierCode() == null ? "" : generaAsset2.getSupplierCode());
                    row1.createCell(30).setCellValue(generaAsset2.getSupplierName() == null ? "" : generaAsset2.getSupplierName());
                    row1.createCell(31).setCellValue(generaAsset2.getSupplierLinkman() == null ? "" : generaAsset2.getSupplierLinkman());
                    row1.createCell(32).setCellValue(generaAsset2.getSupplierPhone() == null ? "" : generaAsset2.getSupplierPhone());
                    row1.createCell(33).setCellValue(generaAsset2.getRemarkOne() == null ? "" : generaAsset2.getRemarkOne());
                    row1.createCell(34).setCellValue(generaAsset2.getRemarkTwo() == null ? "" : generaAsset2.getRemarkTwo());
                    row1.createCell(35).setCellValue(generaAsset2.getRemarkThree() == null ? "" : generaAsset2.getRemarkThree());
                    row1.createCell(36).setCellValue(generaAsset2.getRemark() == null ? "" : generaAsset2.getRemark());
                    row1.createCell(37).setCellValue(generaAsset2.getAdministrationAsset().getSerialNumber() == null ? "" : generaAsset2.getAdministrationAsset().getSerialNumber());
                    row1.createCell(38).setCellValue(generaAsset2.getAdministrationAsset().getManagementCode() == null ? "" : generaAsset2.getAdministrationAsset().getManagementCode());
                    row1.createCell(39).setCellValue(generaAsset2.getAdministrationAsset().getIsBuildingContruction() == null ? "" : generaAsset2.getAdministrationAsset().getIsBuildingContruction());

                }
            } catch (Exception e) {
                LOGGER.error("e", e);

            }
            try {
                book.write(o);
            } catch (Exception ex) {
                LOGGER.error("e", ex);
            } finally {
                o.flush();
                o.close();
            }
        }

        try {
            ExportExcle.exportZip(fileNames, path, zip, out, f);
        } catch (IOException e) {
            LOGGER.error("e", e);
        } finally {
            out.close();
        }

    }

    public void toExportJQ(List<GeneralAsset> list,
                           HttpServletRequest request, int length, String f, OutputStream out) throws IOException {
        List<String> fileNames = new ArrayList(); // 用于存放生成的文件名称s
        String path = Env.getProperty("file.upload.excel.path");
        File zip = new File(path + "excel/" + f + ".zip"); // 压缩文件
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        // 生成excel
        for (int j = 0, n = list.size() / length + 1; j < n; j++) {
            Workbook book = new HSSFWorkbook();
            Sheet sheet = book.createSheet("机器设备");
            // double d = 0; // 用来统计
            String file = path + "excel/" + f + "-" + j + ".xls";
            fileNames.add(file);
            FileOutputStream o = null;
            try {
                o = new FileOutputStream(file);

                Row row = sheet.createRow(0);
                row.createCell(0).setCellValue("公司编码");
                row.createCell(1).setCellValue("资产编号");
                row.createCell(2).setCellValue("资产名称");
                row.createCell(3).setCellValue("原资产编码");
                row.createCell(4).setCellValue("原归属公司");
                row.createCell(5).setCellValue("资产分类");
                row.createCell(6).setCellValue("是否为一次性费用化资产");
                row.createCell(7).setCellValue("选配件");
                row.createCell(8).setCellValue("采购到货日期");
                row.createCell(9).setCellValue("验收周期(日)");
                row.createCell(10).setCellValue("计划验收日期");
                row.createCell(11).setCellValue("实际验收日期");
                row.createCell(12).setCellValue("质保周期");
                row.createCell(13).setCellValue("质保到期日");
                row.createCell(14).setCellValue("入账日期");
                row.createCell(15).setCellValue("成本中心编码");
                row.createCell(16).setCellValue("管理责任人");
                row.createCell(17).setCellValue("办公地点");
                row.createCell(18).setCellValue("存放位置");
                row.createCell(19).setCellValue("使用状态");
                row.createCell(20).setCellValue("资产原值");
                row.createCell(21).setCellValue("资产净值");
                row.createCell(22).setCellValue("折旧年限(月)");
                row.createCell(23).setCellValue("折旧开始日期");
                row.createCell(24).setCellValue("月折旧额");
                row.createCell(25).setCellValue("本年已计提累计折旧");
                row.createCell(26).setCellValue("累计折旧");
                row.createCell(27).setCellValue("处理价值");
                row.createCell(28).setCellValue("单位名称");
                row.createCell(29).setCellValue("供应商编码");
                row.createCell(30).setCellValue("供应商名称");
                row.createCell(31).setCellValue("供应商联系人");
                row.createCell(32).setCellValue("供应商联系电话");
                row.createCell(33).setCellValue("备注一");
                row.createCell(34).setCellValue("备注二");
                row.createCell(35).setCellValue("备注三");
                row.createCell(36).setCellValue("备注四");
                row.createCell(37).setCellValue("机器编码");
                row.createCell(38).setCellValue("归口管理编码");
                row.createCell(39).setCellValue("车间类型");

                for (int i = 1, min = (list.size() - j * length + 1) > (length + 1) ? (length + 1) : (list.size() - j * length + 1); i < min; i++) {


                    GeneralAsset generaAsset2 = list.get(length * j + i - 1);
                    generaAsset2 = (GeneralAsset) getQueryCriteria2(generaAsset2);
                    Row row1 = sheet.createRow(i);

                    row1.createCell(0).setCellValue(generaAsset2.getCompanyCode() == null ? "" : generaAsset2.getCompanyCode().toString());
                    row1.createCell(1).setCellValue(generaAsset2.getAssetCode() == null ? "" : generaAsset2.getAssetCode());
                    row1.createCell(2).setCellValue(generaAsset2.getAssetName() == null ? "" : generaAsset2.getAssetName());
                    row1.createCell(3).setCellValue(generaAsset2.getOldAssetCode() == null ? "" : generaAsset2.getOldAssetCode());
                    row1.createCell(4).setCellValue(generaAsset2.getOldHomeCompany() == null ? "" : generaAsset2.getOldHomeCompany());
                    row1.createCell(5).setCellValue(generaAsset2.getAssetClassification() == null ? "" : generaAsset2.getAssetClassification());
                    if (StringUtils.isNotBlank(generaAsset2.getIsExpenseAssets())) {
                        if (ISEXPENSEASSETS.NO.getIsExpenseassets().equals(generaAsset2.getIsExpenseAssets())) {
                            row1.createCell(6).setCellValue(ISEXPENSEASSETS.FONTNO.getIsExpenseassets());
                        } else if (ISEXPENSEASSETS.YES.getIsExpenseassets().equals(generaAsset2.getIsExpenseAssets())) {
                            row1.createCell(6).setCellValue(ISEXPENSEASSETS.FONTYES.getIsExpenseassets());
                        } else {
                            row1.createCell(6).setCellValue(generaAsset2.getIsExpenseAssets());
                        }
                    } else {
                        row1.createCell(6).setCellValue("");
                    }
                    row1.createCell(7).setCellValue(generaAsset2.getSelectAccessories() == null ? "" : generaAsset2.getSelectAccessories());
                    row1.createCell(8).setCellValue(generaAsset2.getPurchaseArrivalDate() == null ? "" : sdf.format(generaAsset2.getPurchaseArrivalDate()));
                    row1.createCell(9).setCellValue(generaAsset2.getAcceptanceCycle() == null ? "" : generaAsset2.getAcceptanceCycle());
                    row1.createCell(10).setCellValue(generaAsset2.getDateOfAcceptance() == null ? "" : sdf.format(generaAsset2.getDateOfAcceptance()));
                    row1.createCell(11).setCellValue(generaAsset2.getActualDateAcceptance() == null ? "" : sdf.format(generaAsset2.getActualDateAcceptance()));
                    row1.createCell(12).setCellValue(generaAsset2.getWarrantyPeriod() == null ? "" : generaAsset2.getWarrantyPeriod());
                    row1.createCell(13).setCellValue(generaAsset2.getExpirationDate() == null ? "" : sdf.format(generaAsset2.getExpirationDate()));
                    row1.createCell(14).setCellValue(generaAsset2.getPostingDate() == null ? "" : sdf.format(generaAsset2.getPostingDate()));
                    row1.createCell(15).setCellValue(generaAsset2.getCostCenterCode() == null ? "" : generaAsset2.getCostCenterCode());
                    row1.createCell(16).setCellValue(generaAsset2.getAssetPersonCode() == null ? "" : "(" + generaAsset2.getAssetPersonCode() + ")" + generaAsset2.getAssetPerson());
                    row1.createCell(17).setCellValue(generaAsset2.getOfficeLocation() == null ? "" : generaAsset2.getOfficeLocation());
                    if (StringUtils.isNotBlank(generaAsset2.getUseState())) {
                        if (USESTATE.INUSE.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTINUSE.getUseState());
                        } else if (USESTATE.IDLEIN.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTIDLEIN.getUseState());
                        } else if (USESTATE.MAJORREPAIR.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTMAJORREPAIR.getUseState());
                        } else if (USESTATE.INTRANSFORMATION.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTINTRANSFORMATION.getUseState());
                        } else if (USESTATE.DISCARDAPPLY.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTDISCARDAPPLY.getUseState());
                        } else if (USESTATE.SCRAPPED.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTSCRAPPED.getUseState());
                        } else if (USESTATE.SELLAPPLY.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTSELLAPPLY.getUseState());
                        } else if (USESTATE.ALREADYSOLD.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTALREADYSOLD.getUseState());
                        } else {
                            row1.createCell(19).setCellValue(generaAsset2.getUseState());
                        }
                    } else {
                        row1.createCell(19).setCellValue("");
                    }
                    row1.createCell(20).setCellValue(generaAsset2.getAssetOriginalValue() == null ? "" : generaAsset2.getAssetOriginalValue());
                    row1.createCell(21).setCellValue(generaAsset2.getAssetNetValue() == null ? "" : generaAsset2.getAssetNetValue());
                    row1.createCell(22).setCellValue(generaAsset2.getPeriodOfDepreciation() == null ? "" : generaAsset2.getPeriodOfDepreciation());
                    row1.createCell(23).setCellValue(generaAsset2.getDepreciationStartDate() == null ? "" : sdf.format(generaAsset2.getDepreciationStartDate()));
                    row1.createCell(24).setCellValue(generaAsset2.getMonthCountingDepreciation() == null ? "" : generaAsset2.getMonthCountingDepreciation());
                    row1.createCell(25).setCellValue(generaAsset2.getCurrentYearDepreciation() == null ? "" : generaAsset2.getCurrentYearDepreciation());
                    row1.createCell(26).setCellValue(generaAsset2.getAddDepreciation() == null ? "" : generaAsset2.getAddDepreciation());
                    row1.createCell(27).setCellValue(generaAsset2.getSalvageValue() == null ? "" : generaAsset2.getSalvageValue());
                    row1.createCell(28).setCellValue(generaAsset2.getUnit() == null ? "" : generaAsset2.getUnit());
                    row1.createCell(29).setCellValue(generaAsset2.getSupplierCode() == null ? "" : generaAsset2.getSupplierCode());
                    row1.createCell(30).setCellValue(generaAsset2.getSupplierName() == null ? "" : generaAsset2.getSupplierName());
                    row1.createCell(31).setCellValue(generaAsset2.getSupplierLinkman() == null ? "" : generaAsset2.getSupplierLinkman());
                    row1.createCell(32).setCellValue(generaAsset2.getSupplierPhone() == null ? "" : generaAsset2.getSupplierPhone());
                    row1.createCell(33).setCellValue(generaAsset2.getRemarkOne() == null ? "" : generaAsset2.getRemarkOne());
                    row1.createCell(34).setCellValue(generaAsset2.getRemarkTwo() == null ? "" : generaAsset2.getRemarkTwo());
                    row1.createCell(35).setCellValue(generaAsset2.getRemarkThree() == null ? "" : generaAsset2.getRemarkThree());
                    row1.createCell(36).setCellValue(generaAsset2.getRemark() == null ? "" : generaAsset2.getRemark());
                    row1.createCell(37).setCellValue(generaAsset2.getMachineryEquipment().getMachineCode() == null ? "" : generaAsset2.getMachineryEquipment().getMachineCode());
                    row1.createCell(38).setCellValue(generaAsset2.getMachineryEquipment().getPutUnderManageCode() == null ? "" : generaAsset2.getMachineryEquipment().getPutUnderManageCode());
                    row1.createCell(39).setCellValue(generaAsset2.getMachineryEquipment().getShopType() == null ? "" : generaAsset2.getMachineryEquipment().getShopType());
                }
            } catch (Exception e) {
                LOGGER.error("e", e);

            }
            try {
                book.write(o);
            } catch (Exception ex) {
                LOGGER.error("e", ex);
            } finally {
                o.flush();
                o.close();
            }
        }

        try {
            ExportExcle.exportZip(fileNames, path, zip, out, f);
        } catch (IOException e) {
            LOGGER.error("e", e);
        } finally {
            out.close();
        }

    }

    public void toExportJL(List<GeneralAsset> list, HttpServletRequest request, int length, String f, OutputStream out) throws IOException {
        List<String> fileNames = new ArrayList(); // 用于存放生成的文件名称s
        String path = Env.getProperty("file.upload.excel.path");
        File zip = new File(path + "excel/" + f + ".zip"); // 压缩文件
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        // 生成excel
        for (int j = 0, n = list.size() / length + 1; j < n; j++) {
            Workbook book = new HSSFWorkbook();
            Sheet sheet = book.createSheet("计量器具");
            // double d = 0; // 用来统计
            String file = path + "excel/" + f + "-" + j + ".xls";
            fileNames.add(file);
            FileOutputStream o = null;
            try {
                o = new FileOutputStream(file);

                Row row = sheet.createRow(0);

                row.createCell(0).setCellValue("公司编码");
                row.createCell(1).setCellValue("资产编号");
                row.createCell(2).setCellValue("资产名称");
                row.createCell(3).setCellValue("原资产编码");
                row.createCell(4).setCellValue("原归属公司");
                row.createCell(5).setCellValue("资产分类");
                row.createCell(6).setCellValue("是否为一次性费用化资产");
                row.createCell(7).setCellValue("选配件");
                row.createCell(8).setCellValue("采购到货日期");
                row.createCell(9).setCellValue("验收周期(日)");
                row.createCell(10).setCellValue("计划验收日期");
                row.createCell(11).setCellValue("实际验收日期");
                row.createCell(12).setCellValue("质保周期");
                row.createCell(13).setCellValue("质保到期日");
                row.createCell(14).setCellValue("入账日期");
                row.createCell(15).setCellValue("成本中心编码");
                row.createCell(16).setCellValue("管理责任人");
                row.createCell(17).setCellValue("办公地点");
                row.createCell(18).setCellValue("存放位置");
                row.createCell(19).setCellValue("使用状态");
                row.createCell(20).setCellValue("资产原值");
                row.createCell(21).setCellValue("资产净值");
                row.createCell(22).setCellValue("折旧年限(月)");
                row.createCell(23).setCellValue("折旧开始日期");
                row.createCell(24).setCellValue("月折旧额");
                row.createCell(25).setCellValue("本年已计提累计折旧");
                row.createCell(26).setCellValue("累计折旧");
                row.createCell(27).setCellValue("处理价值");
                row.createCell(28).setCellValue("单位名称");
                row.createCell(29).setCellValue("供应商编码");
                row.createCell(30).setCellValue("供应商名称");
                row.createCell(31).setCellValue("供应商联系人");
                row.createCell(32).setCellValue("供应商联系电话");
                row.createCell(33).setCellValue("备注一");
                row.createCell(34).setCellValue("备注二");
                row.createCell(35).setCellValue("备注三");
                row.createCell(36).setCellValue("备注四");
                row.createCell(37).setCellValue("出厂编号");
                row.createCell(38).setCellValue("管理编号");
                row.createCell(39).setCellValue("生产厂家");
                row.createCell(40).setCellValue("管理级别");
                row.createCell(41).setCellValue("精度等级");
                row.createCell(42).setCellValue("检定周期(月)");
                row.createCell(43).setCellValue("校准日期");
                row.createCell(44).setCellValue("下次校准日期");
                row.createCell(45).setCellValue("校准单位");
                row.createCell(46).setCellValue("是否有第三方检验报告");

                for (int i = 1, min = (list.size() - j * length + 1) > (length + 1) ? (length + 1) : (list.size() - j * length + 1); i < min; i++) {

                    GeneralAsset generaAsset2 = list.get(length * j + i - 1);
                    generaAsset2 = (GeneralAsset) getQueryCriteria2(generaAsset2);
                    Row row1 = sheet.createRow(i);

                    row1.createCell(0).setCellValue(generaAsset2.getCompanyCode() == null ? "" : generaAsset2.getCompanyCode().toString());
                    row1.createCell(1).setCellValue(generaAsset2.getAssetCode() == null ? "" : generaAsset2.getAssetCode());
                    row1.createCell(2).setCellValue(generaAsset2.getAssetName() == null ? "" : generaAsset2.getAssetName());
                    row1.createCell(3).setCellValue(generaAsset2.getOldAssetCode() == null ? "" : generaAsset2.getOldAssetCode());
                    row1.createCell(4).setCellValue(generaAsset2.getOldHomeCompany() == null ? "" : generaAsset2.getOldHomeCompany());
                    row1.createCell(5).setCellValue(generaAsset2.getAssetClassification() == null ? "" : generaAsset2.getAssetClassification());
                    if (StringUtils.isNotBlank(generaAsset2.getIsExpenseAssets())) {
                        if (ISEXPENSEASSETS.NO.getIsExpenseassets().equals(generaAsset2.getIsExpenseAssets())) {
                            row1.createCell(6).setCellValue(ISEXPENSEASSETS.FONTNO.getIsExpenseassets());
                        } else if (ISEXPENSEASSETS.YES.getIsExpenseassets().equals(generaAsset2.getIsExpenseAssets())) {
                            row1.createCell(6).setCellValue(ISEXPENSEASSETS.FONTYES.getIsExpenseassets());
                        } else {
                            row1.createCell(6).setCellValue(generaAsset2.getIsExpenseAssets());
                        }
                    } else {
                        row1.createCell(6).setCellValue("");
                    }
                    row1.createCell(7).setCellValue(generaAsset2.getSelectAccessories() == null ? "" : generaAsset2.getSelectAccessories());
                    row1.createCell(8).setCellValue(generaAsset2.getPurchaseArrivalDate() == null ? "" : sdf.format(generaAsset2.getPurchaseArrivalDate()));
                    row1.createCell(9).setCellValue(generaAsset2.getAcceptanceCycle() == null ? "" : generaAsset2.getAcceptanceCycle());
                    row1.createCell(10).setCellValue(generaAsset2.getDateOfAcceptance() == null ? "" : sdf.format(generaAsset2.getDateOfAcceptance()));
                    row1.createCell(11).setCellValue(generaAsset2.getActualDateAcceptance() == null ? "" : sdf.format(generaAsset2.getActualDateAcceptance()));
                    row1.createCell(12).setCellValue(generaAsset2.getWarrantyPeriod() == null ? "" : generaAsset2.getWarrantyPeriod());
                    row1.createCell(13).setCellValue(generaAsset2.getExpirationDate() == null ? "" : sdf.format(generaAsset2.getExpirationDate()));
                    row1.createCell(14).setCellValue(generaAsset2.getPostingDate() == null ? "" : sdf.format(generaAsset2.getPostingDate()));
                    row1.createCell(15).setCellValue(generaAsset2.getCostCenterCode() == null ? "" : generaAsset2.getCostCenterCode());
                    row1.createCell(16).setCellValue(generaAsset2.getAssetPersonCode() == null ? "" : "(" + generaAsset2.getAssetPersonCode() + ")" + generaAsset2.getAssetPerson());
                    row1.createCell(17).setCellValue(generaAsset2.getOfficeLocation() == null ? "" : generaAsset2.getOfficeLocation());
                    if (StringUtils.isNotBlank(generaAsset2.getUseState())) {
                        if (USESTATE.INUSE.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTINUSE.getUseState());
                        } else if (USESTATE.IDLEIN.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTIDLEIN.getUseState());
                        } else if (USESTATE.MAJORREPAIR.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTMAJORREPAIR.getUseState());
                        } else if (USESTATE.INTRANSFORMATION.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTINTRANSFORMATION.getUseState());
                        } else if (USESTATE.DISCARDAPPLY.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTDISCARDAPPLY.getUseState());
                        } else if (USESTATE.SCRAPPED.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTSCRAPPED.getUseState());
                        } else if (USESTATE.SELLAPPLY.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTSELLAPPLY.getUseState());
                        } else if (USESTATE.ALREADYSOLD.getUseState().equals(generaAsset2.getUseState())) {
                            row1.createCell(19).setCellValue(USESTATE.FONTALREADYSOLD.getUseState());
                        } else {
                            row1.createCell(19).setCellValue(generaAsset2.getUseState());
                        }
                    } else {
                        row1.createCell(19).setCellValue("");
                    }
                    row1.createCell(20).setCellValue(generaAsset2.getAssetOriginalValue() == null ? "" : generaAsset2.getAssetOriginalValue());
                    row1.createCell(21).setCellValue(generaAsset2.getAssetNetValue() == null ? "" : generaAsset2.getAssetNetValue());
                    row1.createCell(22).setCellValue(generaAsset2.getPeriodOfDepreciation() == null ? "" : generaAsset2.getPeriodOfDepreciation());
                    row1.createCell(23).setCellValue(generaAsset2.getDepreciationStartDate() == null ? "" : sdf.format(generaAsset2.getDepreciationStartDate()));
                    row1.createCell(24).setCellValue(generaAsset2.getMonthCountingDepreciation() == null ? "" : generaAsset2.getMonthCountingDepreciation());
                    row1.createCell(25).setCellValue(generaAsset2.getCurrentYearDepreciation() == null ? "" : generaAsset2.getCurrentYearDepreciation());
                    row1.createCell(26).setCellValue(generaAsset2.getAddDepreciation() == null ? "" : generaAsset2.getAddDepreciation());
                    row1.createCell(27).setCellValue(generaAsset2.getSalvageValue() == null ? "" : generaAsset2.getSalvageValue());
                    row1.createCell(28).setCellValue(generaAsset2.getUnit() == null ? "" : generaAsset2.getUnit());
                    row1.createCell(29).setCellValue(generaAsset2.getSupplierCode() == null ? "" : generaAsset2.getSupplierCode());
                    row1.createCell(30).setCellValue(generaAsset2.getSupplierName() == null ? "" : generaAsset2.getSupplierName());
                    row1.createCell(31).setCellValue(generaAsset2.getSupplierLinkman() == null ? "" : generaAsset2.getSupplierLinkman());
                    row1.createCell(32).setCellValue(generaAsset2.getSupplierPhone() == null ? "" : generaAsset2.getSupplierPhone());
                    row1.createCell(33).setCellValue(generaAsset2.getRemarkOne() == null ? "" : generaAsset2.getRemarkOne());
                    row1.createCell(34).setCellValue(generaAsset2.getRemarkTwo() == null ? "" : generaAsset2.getRemarkTwo());
                    row1.createCell(35).setCellValue(generaAsset2.getRemarkThree() == null ? "" : generaAsset2.getRemarkThree());
                    row1.createCell(36).setCellValue(generaAsset2.getRemark() == null ? "" : generaAsset2.getRemark());

                    row1.createCell(37).setCellValue(generaAsset2.getMeasuringInstruments().getFacrotyNumber() == null ? "" : generaAsset2.getMeasuringInstruments().getFacrotyNumber());
                    row1.createCell(38).setCellValue(generaAsset2.getMeasuringInstruments().getManagementCode() == null ? "" : generaAsset2.getMeasuringInstruments().getManagementCode());
                    row1.createCell(39).setCellValue(generaAsset2.getMeasuringInstruments().getManufactureFactory() == null ? "" : generaAsset2.getMeasuringInstruments().getManufactureFactory());
                    row1.createCell(40).setCellValue(generaAsset2.getMeasuringInstruments().getManagementLevel() == null ? "" : generaAsset2.getMeasuringInstruments().getManagementLevel());
                    row1.createCell(41).setCellValue(generaAsset2.getMeasuringInstruments().getAccuracyClass() == null ? "" : generaAsset2.getMeasuringInstruments().getAccuracyClass());
                    row1.createCell(42).setCellValue(generaAsset2.getMeasuringInstruments().getVerificationCycle() == null ? "" : generaAsset2.getMeasuringInstruments().getVerificationCycle());
                    row1.createCell(43).setCellValue(generaAsset2.getMeasuringInstruments().getCalibrationDate() == null ? "" : sdf.format(generaAsset2.getMeasuringInstruments().getCalibrationDate()));
                    row1.createCell(44).setCellValue(generaAsset2.getMeasuringInstruments().getNextCalibrationDate() == null ? "" : sdf.format(generaAsset2.getMeasuringInstruments().getNextCalibrationDate()));
                    row1.createCell(45).setCellValue(generaAsset2.getMeasuringInstruments().getCalibrationUnit() == null ? "" : generaAsset2.getMeasuringInstruments().getCalibrationUnit());

                    if (StringUtils.isNotBlank(generaAsset2.getMeasuringInstruments().getIsThirdReport())) {
                        if ("1".equals(generaAsset2.getIsExpenseAssets())) {
                            row1.createCell(46).setCellValue("否");
                        } else if ("0".equals(generaAsset2.getMeasuringInstruments().getIsThirdReport())) {
                            row1.createCell(46).setCellValue("是");
                        } else {
                            row1.createCell(46).setCellValue(generaAsset2.getMeasuringInstruments().getIsThirdReport());
                        }
                    } else {
                        row1.createCell(46).setCellValue("");
                    }
                }
            } catch (Exception e) {
                LOGGER.error("e", e);

            }
            try {
                book.write(o);
            } catch (Exception ex) {
                LOGGER.error("e", ex);
            } finally {
                o.flush();
                o.close();
            }
        }

        try {
            ExportExcle.exportZip(fileNames, path, zip, out, f);
        } catch (IOException e) {
            LOGGER.error("e", e);
        } finally {
            out.close();
        }

    }

    public Object getQueryCriteria2(Object generaAsset) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException {
        Field[] fields = generaAsset.getClass().getDeclaredFields();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            String type = f.getGenericType().toString();
            f.setAccessible(true);
            if ("class java.lang.String".equals(type)) {
                if (f.get(generaAsset) == null) {
                    f.set(generaAsset, "");
                }
            }
        }
        return generaAsset;
    }
}
