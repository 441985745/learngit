package com.opple.fa.config.webapp.controller;

import com.opple.fa.config.entity.AssetsBudget;
import com.opple.fa.config.entity.AssetsBudgetFromBUD;
import com.opple.fa.config.entity.AttchConfig;
import com.opple.fa.config.entity.BudgetAdjustmentDetail;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.model.ASSETBUDGETTYPE;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.AssetsBudgetService;
import com.opple.fa.config.service.AttchConfigService;
import com.opple.fa.config.service.BudgetAdjustmentService;
import com.opple.fa.config.service.UserInfoService;
import com.opple.security.SessionSecurityConstants;
import com.opple.security.service.UserService;
import com.opple.workflow.service.WorkFlowService;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Wangkaixuan on 2017/3/21.
 */
@Controller
@RequestMapping("/budgetAdjustmentDetail")
public class BudgetAdjustmentDetailController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BudgetAdjustmentDetailController.class);
    //@Resource
    //private BudgetAdjustmentDetailService budgetAdjustmentDetailService;
    @Resource
    private BudgetAdjustmentService budgetAdjustmentService;
    @Resource
    private AssetsBudgetService assetsBudgetService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private AttchConfigService attchConfigService;
    @Resource
    private WorkFlowService workFlowService;
    @Resource
    private UserService userService;


    /**
     * 详细导入
     *
     * @param request
     * @param response
     * @param type
     * @return
     */
    @RequestMapping(value = "/api/budgetAdjustmentDetailImport", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject budgetAdjustmentDetailImport(MultipartHttpServletRequest request,
                                                   HttpServletResponse response, String type) {
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        String userName = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        DecimalFormat df = new DecimalFormat("######0.00");

        //错误信息
        String errorNull = "";
        //年度和成本中心  用于判断导入是否是同一个
        String year = "";
        String costCenter = "";
        String costCenterName = "";
        String plateForm = "";
        // 获得文件上传的文件
        MultipartFile myfile = request.getFile("inputFile");

        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(myfile.getInputStream());
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
        Sheet sheet = wb.getSheetAt(0);
        Row row1 = sheet.getRow(0);

        List<BudgetAdjustmentDetail> budgetAdjustmentDetails = new ArrayList<BudgetAdjustmentDetail>();
        // 返回物理行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            // 获取第i行数据
            Row row = sheet.getRow(i);
            BudgetAdjustmentDetail budgetAdjustmentDetail = new BudgetAdjustmentDetail();
            AssetsBudget assetsBudget = new AssetsBudget();

            if (null == row) {
                continue;
            }

            // 年度
            if (null != row.getCell(0) && !"".equals(row.getCell(0).toString().trim())) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                budgetAdjustmentDetail.setBudgetYear(row.getCell(0).getStringCellValue().trim());
                assetsBudget.setBudgetYear(row.getCell(0).getStringCellValue().trim());
                //如果是第一行数据则给年度赋值，不是第一行则与第一行比较是否相同
                if (i == 1) {
                    year = row.getCell(0).getStringCellValue().trim();
                } else if (!year.equals(row.getCell(0).getStringCellValue().trim())) {
                    errorNull = errorNull + "第" + i + "行的年度与其他行年度不同 !\n";
                }
            } else {
                errorNull = errorNull + "第" + i + "行的年度为空 !\n";
            }
            //非新增时从数据库中查出预算信息
            if (!ASSETBUDGETTYPE.ADD.getAssetBudgetType().equals(type)) {
                //项目编码
                if (null != row.getCell(1) && !"".equals(row.getCell(1).toString().trim())) {
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    budgetAdjustmentDetail.setProjectCode(row.getCell(1).getStringCellValue().trim());
                    assetsBudget.setProjectCode(row.getCell(1).getStringCellValue().trim());
                    //判断数据库项目编码是否存在
                    if (StringUtils.isNotBlank(assetsBudget.getBudgetYear()) && assetsBudgetService.getAssetsBudgetsbyProjectCodeAndYear(assetsBudget) == 0) {
                        //判断项目编码对应的年度是否存在（若存在则更新，若不存在说明导入新的年度预算则新增）
                        errorNull = errorNull + "第" + i + "行的项目编码或其对应的年度不存在！\n";
                    } else {
                        String document = budgetAdjustmentService.getDocumentByProjectCodeAndYear(budgetAdjustmentDetail);
                        if (StringUtils.isNotBlank(document)) {
                            errorNull = errorNull
                                    + "第" + i + "行的项目编码及对应的年度存在未完成审批流程【"
                                    + document + "】，请对应流程审批完成后再进行调整！\n";
                        } else {
                            budgetAdjustmentDetail = assetsBudgetService.getBudgetDetailsbyProjectCodeAndYear(assetsBudget.getBudgetYear(), assetsBudget.getProjectCode());
                            //根据成本中心编码获取部门、二级部门负责人
                            AssetsBudgetFromBUD assetsBudgetFromBUD = new AssetsBudgetFromBUD();
                            assetsBudgetFromBUD.setCostCenter(budgetAdjustmentDetail.getCostCenter());
                            UserInfo userInfo = userInfoService.searchDepInfoByCostCenter(budgetAdjustmentDetail);
                            if (null != userInfo) {
                                budgetAdjustmentDetail.setCostCenterName(userInfo.getCcostcentername());
                                budgetAdjustmentDetail.setCallInDepartmentCode(userInfo.getCdepCode());
                                budgetAdjustmentDetail.setCallInDepartmentName(userInfo.getCdepName());
                            }
                            AttchConfig attchConfig = attchConfigService.searchByCostcenterCode(assetsBudgetFromBUD);
                            //budgetAdjustmentDetail.setPlatform(attchConfig.getPlatform());
                            budgetAdjustmentDetail.setTwoDepartmentHeadCode(attchConfig.getTwoDepartmentHeadCode());
                            budgetAdjustmentDetail.setTwoDepartmentHeadName(attchConfig.getTwoDepartmentHeadName());
                            //如果是第一行数据则给成本中心赋值，不是第一行则与第一行比较是否相同
                            if (i == 1) {
                                costCenter = budgetAdjustmentDetail.getCostCenter();
                            } else if (!costCenter.equals(budgetAdjustmentDetail.getCostCenter())) {
                                errorNull = errorNull + "第" + i + "行的成本中心与其他行成本中心不同 !\n";
                            }
                        }
                    }
                } else {
                    errorNull = errorNull + "第" + i + "行的项目编码为空 !\n";
                }

                //调整数量
                if (null != row.getCell(2) && !"".equals(row.getCell(2).toString().trim())) {
                    row.getCell(2).setCellType(Cell.CELL_TYPE_NUMERIC);
                    double adjustCount = row.getCell(2).getNumericCellValue();
                    budgetAdjustmentDetail.setAdjustCount((int) adjustCount);
                } else {
                    budgetAdjustmentDetail.setAdjustCount(0);
                }
                //调整金额
                if (null != row.getCell(3) && !"".equals(row.getCell(3).toString().trim())) {
                    row.getCell(3).setCellType(Cell.CELL_TYPE_NUMERIC);
                    double adjustMoney = row.getCell(3).getNumericCellValue();
                    BigDecimal bd = new BigDecimal(adjustMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
                    adjustMoney = bd.doubleValue();
                    budgetAdjustmentDetail.setAdjustMoney(adjustMoney);
                } else {
                    budgetAdjustmentDetail.setAdjustMoney(0.00);
                }

                if (StringUtils.isBlank(errorNull)) {
                    if (null == budgetAdjustmentDetail.getUsedAmount()) {
                        budgetAdjustmentDetail.setUsedAmount(0);
                    }
                    //调整后数量 //时海云要求，可用数量不做控制，只需要控制总数量大于0即可
//                    if (budgetAdjustmentDetail.getBeforeAdjustCount() - budgetAdjustmentDetail.getUsedAmount() + budgetAdjustmentDetail.getAdjustCount() >= 0) {
                    if (budgetAdjustmentDetail.getBeforeAdjustCount() + budgetAdjustmentDetail.getAdjustCount() >= 0) {
                        budgetAdjustmentDetail.setAfterAdjustCount(budgetAdjustmentDetail.getBeforeAdjustCount() + budgetAdjustmentDetail.getAdjustCount());
                    } else {
                        errorNull = errorNull + "第" + i + "行的调整后剩余数量小于0 !\n";
                    }
                }
                if (StringUtils.isBlank(errorNull)) {
                    if (null == budgetAdjustmentDetail.getUsedSumMoney()) {
                        budgetAdjustmentDetail.setUsedSumMoney(0.0);
                    }
                    //调整后金额
                    if (budgetAdjustmentDetail.getBeforeAdjustMoney() - budgetAdjustmentDetail.getUsedSumMoney() + budgetAdjustmentDetail.getAdjustMoney() >= 0) {
                        budgetAdjustmentDetail.setAfterAdjustMoney(budgetAdjustmentDetail.getBeforeAdjustMoney() + budgetAdjustmentDetail.getAdjustMoney());
                        //调整后单价
                        double unitPrice = 0.00;
                        if (budgetAdjustmentDetail.getAfterAdjustCount() == 0) {
                            unitPrice = budgetAdjustmentDetail.getAfterAdjustMoney();
                        } else {
                            unitPrice = budgetAdjustmentDetail.getAfterAdjustMoney() / budgetAdjustmentDetail.getAfterAdjustCount();
                        }
                        BigDecimal bd = new BigDecimal(unitPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
                        unitPrice = bd.doubleValue();
                        budgetAdjustmentDetail.setAfterUnitPrice(unitPrice);
                    } else {
                        errorNull = errorNull + "第" + i + "行的调整剩余金额小于0 !\n";
                    }
                }

            } else { ////新增时赋值
                // 成本中心
                if (null != row.getCell(1) && !"".equals(row.getCell(1).toString().trim())) {
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    //budgetAdjustmentDetail.setCostCenter(StringUtils.leftPad(row.getCell(1).getStringCellValue().trim(), 10, "0"));

                    String regex = ".*[a-zA-Z]+.*";
                    Matcher m = Pattern.compile(regex).matcher(row.getCell(1).getStringCellValue().trim());
                    if (!m.matches()) {
                        budgetAdjustmentDetail.setCostCenter(StringUtils.leftPad(row.getCell(1).getStringCellValue().trim(), 10, STATUS.YES_ZERO.getStatus()));
                    } else {
                        budgetAdjustmentDetail.setCostCenter(row.getCell(1).getStringCellValue().trim());
                    }


                    //如果是第一行数据则给成本中心赋值，不是第一行则与第一行比较是否相同
                    if (i == 1) {
                        costCenter = budgetAdjustmentDetail.getCostCenter();
                        //根据成本中心编码获取成本中心名称、部门名称、部门编码
                        AssetsBudgetFromBUD assetsBudgetFromBUD = new AssetsBudgetFromBUD();
                        assetsBudgetFromBUD.setCostCenter(budgetAdjustmentDetail.getCostCenter());
                        UserInfo userInfo = userInfoService.searchDepInfoByCostCenter(budgetAdjustmentDetail);
                        if (null != userInfo) {
                            costCenterName = userInfo.getCcostcentername();
                            budgetAdjustmentDetail.setCostCenterName(userInfo.getCcostcentername());
                            budgetAdjustmentDetail.setCallInDepartmentCode(userInfo.getCdepCode());
                            budgetAdjustmentDetail.setCallInDepartmentName(userInfo.getCdepName());
                        } else {
                            budgetAdjustmentDetail.setCostCenterName("");
                        }
                        //根据成本中心编码获取平台、二级部门负责人
                        AttchConfig attchConfig = attchConfigService.searchByCostcenterCode(assetsBudgetFromBUD);
                        if (null != attchConfig) {
                            plateForm = attchConfig.getPlatform();
                            budgetAdjustmentDetail.setPlatform(attchConfig.getPlatform());
                            budgetAdjustmentDetail.setTwoDepartmentHeadCode(attchConfig.getTwoDepartmentHeadCode());
                            budgetAdjustmentDetail.setTwoDepartmentHeadName(attchConfig.getTwoDepartmentHeadName());
                        } else {
                            budgetAdjustmentDetail.setPlatform("");
                        }
                    } else if (!costCenter.equals(budgetAdjustmentDetail.getCostCenter())) {
                        errorNull = errorNull + "第" + i + "行的成本中心与其他行成本中心不同 !\n";
                    } else {
                        budgetAdjustmentDetail.setCostCenterName(costCenterName);
                        budgetAdjustmentDetail.setPlatform(plateForm);
                    }
                } else {
                    errorNull = errorNull + "第" + i + "行的项目编码不能为空 !\n";
                }
                //项目编码
                budgetAdjustmentDetail.setProjectCode("");

                // 项目名称
                if (null != row.getCell(2) && !"".equals(row.getCell(2).toString().trim())) {
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    budgetAdjustmentDetail.setProjectName(row.getCell(2).getStringCellValue().trim());
                }
                // 资产名称
                if (null != row.getCell(3) && !"".equals(row.getCell(3).toString().trim())) {
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                    budgetAdjustmentDetail.setAssetsName(row.getCell(3).getStringCellValue().trim());
                }
                // 资产类型
                if (null != row.getCell(4) && !"".equals(row.getCell(4).toString().trim())) {
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    budgetAdjustmentDetail.setAssetsType(row.getCell(4).getStringCellValue().trim());
                }
                // 类型
                if (null != row.getCell(5) && !"".equals(row.getCell(5).toString().trim())) {
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                    budgetAdjustmentDetail.setAssetsModel(row.getCell(5).getStringCellValue().trim());
                }
                // 单位
                if (null != row.getCell(6) && !"".equals(row.getCell(6).toString().trim())) {
                    row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                    budgetAdjustmentDetail.setUnit(row.getCell(6).getStringCellValue().trim());
                }
                // 调整前数量
                if (null != row.getCell(7) && !"".equals(row.getCell(7).toString().trim())) {
                    row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                    try {
                        budgetAdjustmentDetail.setBeforeAdjustCount(Integer.parseInt(row.getCell(7).getStringCellValue().trim()));
                    } catch (Exception e) {
                        LOGGER.error("Exception", e);
                        errorNull = errorNull + "第" + i + "行的调整数量不是数字 !\n";
                    }
                } else {
                    errorNull = errorNull + "第" + i + "行的数量不能为空 !\n";
                }
                // 调整前单价
                if (null != row.getCell(8) && !"".equals(row.getCell(8).toString().trim())) {
                    row.getCell(8).setCellType(Cell.CELL_TYPE_NUMERIC);
                    budgetAdjustmentDetail.setBeforeUnitPrice(row.getCell(8).getNumericCellValue());
                } else {
                    errorNull = errorNull + "第" + i + "行的单价不能为空 !\n";
                }
                // 调整前总额
                budgetAdjustmentDetail.setBeforeAdjustMoney(budgetAdjustmentDetail.getBeforeAdjustCount() * budgetAdjustmentDetail.getBeforeUnitPrice());
//                if (null != row.getCell(9) && !"".equals(row.getCell(9))) {
//                    row.getCell(9).setCellType(Cell.CELL_TYPE_NUMERIC);
//                    budgetAdjustmentDetail.setBeforeAdjustMoney(row.getCell(9).getNumericCellValue());
//                }
                // 预算依据
                if (null != row.getCell(9) && !"".equals(row.getCell(9).toString().trim())) {
                    row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                    budgetAdjustmentDetail.setBudgetBasedOn(row.getCell(9).getStringCellValue().trim());
                }
            }
            if (StringUtils.isBlank(errorNull)) {
                budgetAdjustmentDetail.setCreateBy(userCode);
                budgetAdjustmentDetail.setCreateUserName(userName);
                budgetAdjustmentDetail.setUpdateBy(userCode);
                budgetAdjustmentDetail.setUpdateUserName(userName);

                budgetAdjustmentDetails.add(budgetAdjustmentDetail);
            }
        }

        if (StringUtils.isBlank(errorNull)) {
            jsonMap.put("budgetAdjustmentDetails", budgetAdjustmentDetails);
            jsonMap.put("success", true);
        } else {
            jsonMap.put("errorNull", errorNull);
        }

        try {
            response.setContentType("text/html; charset=utf-8");
//            response.getWriter().write(JSONObject.fromObject(jsonMap).toString());
            response.getWriter().write(JSONObject.fromObject(jsonMap).toString() + "<script>document.domain='opple.com'</script>");
            response.getWriter().flush();
        } catch (IOException e1) {
            LOGGER.error("IOException", e1);
        }
        return null;
    }

}
