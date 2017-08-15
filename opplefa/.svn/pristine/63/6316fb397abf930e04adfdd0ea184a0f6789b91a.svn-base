package com.opple.fa.report.webapp.controller;

import com.opple.fa.report.entity.FullProcess;
import com.opple.fa.report.entity.FullProcessDetail;
import com.opple.fa.report.entity.FullProcessPay;
import com.opple.fa.report.entity.FullProcessPayDetail;
import com.opple.fa.report.service.FullProcessService;
import com.opple.util.Env;
import com.opple.util.ExportExcle;
import com.opple.util.Pager;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Wangkaixuan on 2017/5/8.
 */
@Controller
@RequestMapping("/report")
public class FullProcessController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FullProcessController.class);
    @Resource
    private FullProcessService fullProcessService;


    private String fileName;

    /**
     * 验收模糊查询
     *
     * @param model
     * @param request
     * @param offset
     * @param pageSize
     * @param fullProcess
     * @return
     */
    @RequestMapping("/searchReceiveFullProcess")
    public String searchReceiveFullProcess(Model model, HttpServletRequest request, Long offset, Long pageSize, FullProcess fullProcess) {
        fullProcess = getQueryCriteria(fullProcess);

        Pager<FullProcess> pager = new Pager<FullProcess>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<FullProcess> fullProcessPager = fullProcessService.searchReceiveFullProcess(pager, fullProcess);

        fullProcessPager.setUri(request.getRequestURI());
        model.addAttribute("fullProcessPager", fullProcessPager);
        return "report/receive_full_process_list";
    }

    /**
     * 验收模糊查询------------明细
     *
     * @param model
     * @param request
     * @param offset
     * @param pageSize
     * @param fullProcessDetail
     * @return
     */
    @RequestMapping("/searchReceiveFullProcessDetail")
    public String searchReceiveFullProcessDetail(Model model, HttpServletRequest request, Long offset, Long pageSize, FullProcessDetail fullProcessDetail) {

        try {
            fullProcessDetail = (FullProcessDetail) getQueryCriteria1(fullProcessDetail);
        } catch (NoSuchMethodException e) {
            LOGGER.error("e", e);
        } catch (InvocationTargetException e) {
            LOGGER.error("e", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("e", e);
        } catch (ParseException e) {
            LOGGER.error("e", e);
        }
        Pager<FullProcessDetail> pager = new Pager<FullProcessDetail>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<FullProcessDetail> fullProcessPager = fullProcessService.searchReceiveFullProcessDetail(pager, fullProcessDetail);

        fullProcessPager.setUri(request.getRequestURI());
        model.addAttribute("fullProcessPager", fullProcessPager);
        return "report/receive_full_process_detail_list";
    }


    private Object getQueryCriteria1(Object fullProcessDetail) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException {
        Class cls = fullProcessDetail.getClass();
        Field[] fields = fullProcessDetail.getClass().getDeclaredFields();
        FullProcess fp = new FullProcess();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            String type = f.getGenericType().toString();
            String name = f.getName();    //获取属性的名字

            name = name.substring(0, 1).toUpperCase() + name.substring(1); //将属性的首字符大写，方便构造get，set方法
            f.setAccessible(true);
            if ("class java.lang.String".equals(type)) {
//                Method m = cls.getClass().getMethod("get" + name);
//                String value = (String) m.invoke(cls);    //调用getter方法获取属性值
                String value = (String) f.get(fullProcessDetail);
                if (value != null && !"".equals(value)) {
                    f.set(fullProcessDetail, ((String) f.get(fullProcessDetail)).trim());
                }
            }

           /* if ("class java.math.BigDecimal".equals(type)) {
                Method m = cls.getClass().getMethod("get" + name);
                BigDecimal value = (BigDecimal) m.invoke(cls);    //调用getter方法获取属性值
                if (value != null && !"".equals(value)) {
                    f.set(fullProcessDetail, f.get(fullProcessDetail).toString());
                }
            }*/

           /* if ("class java.util.Date".equals(type)) {
                Method m = cls.getClass().getMethod("get" + name);
                Date value = (Date) m.invoke(cls);    //调用getter方法获取属性值
                if (value != null && !"".equals(value)) {
                    f.set(fullProcessDetail, sdf.parse(f.get(fullProcessDetail).toString()));
                }
            }*/

        }

        return fullProcessDetail;
    }

    public Object getQueryCriteria2(Object fullProcessDetail) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException {
        Field[] fields = fullProcessDetail.getClass().getDeclaredFields();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            String type = f.getGenericType().toString();
            f.setAccessible(true);
            if ("class java.lang.String".equals(type)) {
                if (f.get(fullProcessDetail) == null) {
                    f.set(fullProcessDetail, "");
                }
            }

        }
        return fullProcessDetail;
    }

    /**
     * 付款模糊查询
     *
     * @param model
     * @param request
     * @param offset
     * @param pageSize
     * @param fullProcess
     * @return
     */
    @RequestMapping("/searchPaymentFullProcess")
    public String searchPaymentFullProcess(Model model, HttpServletRequest request, Long offset, Long pageSize, FullProcessPay fullProcess) {
        try {
            fullProcess = (FullProcessPay) getQueryCriteria1(fullProcess);
        } catch (NoSuchMethodException e) {
            LOGGER.error("e", e);
        } catch (InvocationTargetException e) {
            LOGGER.error("e", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("e", e);
        } catch (ParseException e) {
            LOGGER.error("e", e);
        }

        Pager<FullProcessPay> pager = new Pager<FullProcessPay>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<FullProcessPay> fullProcessPager = fullProcessService.searchPaymentFullProcess(pager, fullProcess);

        fullProcessPager.setUri(request.getRequestURI());
        model.addAttribute("fullProcessPager", fullProcessPager);
        return "report/payment_full_process_list";
    }


    /**
     * 付款模糊查询
     *
     * @param model
     * @param request
     * @param offset
     * @param pageSize
     * @param fullProcess
     * @return
     */
    @RequestMapping("/searchPaymentFullProcessDetail")
    public String searchPaymentFullProcessDetail(Model model, HttpServletRequest request, Long offset, Long pageSize, FullProcessPayDetail fullProcess) {
        try {
            fullProcess = (FullProcessPayDetail) getQueryCriteria1(fullProcess);
        } catch (NoSuchMethodException e) {
            LOGGER.error("e", e);
        } catch (InvocationTargetException e) {
            LOGGER.error("e", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("e", e);
        } catch (ParseException e) {
            LOGGER.error("e", e);
        }

        Pager<FullProcessPayDetail> pager = new Pager<FullProcessPayDetail>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<FullProcessPayDetail> fullProcessPager = fullProcessService.searchPaymentFullProcessDetail(pager, fullProcess);

        fullProcessPager.setUri(request.getRequestURI());
        model.addAttribute("fullProcessPager", fullProcessPager);
        return "report/payment_detail_process_list";
    }

    /**
     * 验收处理查询条件
     *
     * @param fullProcess
     * @return
     */
    private FullProcess getQueryCriteria(FullProcess fullProcess) {
        fullProcess.setDoId(null == fullProcess.getDoId() ? null : fullProcess.getDoId().trim());
        fullProcess.setAoId(null == fullProcess.getAoId() ? null : fullProcess.getAoId().trim());
        fullProcess.setPurchaseOrderId(null == fullProcess.getPurchaseOrderId() ? null : fullProcess.getPurchaseOrderId().trim());
        fullProcess.setPurchaseOrderSapId(null == fullProcess.getPurchaseOrderSapId() ? null : fullProcess.getPurchaseOrderSapId().trim());
        fullProcess.setReceiveGoodsId(null == fullProcess.getReceiveGoodsId() ? null : fullProcess.getReceiveGoodsId().trim());
        fullProcess.setCheckAcceptId(null == fullProcess.getCheckAcceptId() ? null : fullProcess.getCheckAcceptId().trim());
        fullProcess.setDoApplyUser(null == fullProcess.getDoApplyUser() ? null : fullProcess.getDoApplyUser().trim());
        fullProcess.setDoApplyDepartment(null == fullProcess.getDoApplyDepartment() ? null : fullProcess.getDoApplyDepartment().trim());
        fullProcess.setStartDate(null == fullProcess.getStartDate() ? null : fullProcess.getStartDate().trim());
        fullProcess.setEndDate(null == fullProcess.getEndDate() ? null : fullProcess.getEndDate().trim());
        return fullProcess;
    }

    @RequestMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response, FullProcess fullProcess) {
        // 文件名获取
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String f = "全流程报表-" + format.format(date);
        this.fileName = f;
        setResponseHeader(response);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            List<FullProcess> list = fullProcessService.exportReceiveFullProcess(fullProcess);
            toExcel(list, request, 10000, f, out);
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

    /**
     * 收货明细导出
     *
     * @param request
     * @param response
     * @param fullProcess
     */
    @RequestMapping("/exportDetail")
    public void exportDetail(HttpServletRequest request, HttpServletResponse response, FullProcessDetail fullProcess) {
        // 文件名获取
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String f = "全流程明细报表-" + format.format(date);
        this.fileName = f;
        setResponseHeader(response);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            List<FullProcessDetail> list = fullProcessService.exportReceiveFullProcessDetail(fullProcess);
            toExcelDetail(list, request, 10000, f, out);
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

    /**
     * 付款明细导出
     *
     * @param request
     * @param response
     * @param fullProcess
     */
    @RequestMapping("/exportPayDetail")
    public void exportPayDetail(HttpServletRequest request, HttpServletResponse response, FullProcessPayDetail fullProcess) {
        // 文件名获取
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String f = "全流程明细报表-" + format.format(date);
        this.fileName = f;
        setResponseHeader(response);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            List<FullProcessPayDetail> list = fullProcessService.exportPayDetail(fullProcess);
            toExportPayDetail(list, request, 10000, f, out);
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

    /**
     * 付款全流程导出
     *
     * @param request
     * @param response
     * @param fullProcessPay
     */
    @RequestMapping("/exportFullPay")
    public void exportFullPay(HttpServletRequest request, HttpServletResponse response, FullProcessPay fullProcessPay) {
        // 文件名获取
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String f = "付款全流程报表-" + format.format(date);
        this.fileName = f;
        setResponseHeader(response);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            List<FullProcessPay> list = fullProcessService.exportPaymentFullProcess(fullProcessPay);
            toExcelFullpay(list, request, 10000, f, out);
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

    /**
     * 付款全流程导出
     *
     * @param list
     * @param request
     * @param length
     * @param f
     * @param out
     * @throws IOException
     */
    private void toExcelFullpay(List<FullProcessPay> list, HttpServletRequest request,
                                int length, String f, OutputStream out) throws IOException {
        List<String> fileNames = new ArrayList(); // 用于存放生成的文件名称s
        String path = Env.getProperty("file.upload.excel.path");
        File zip = new File(path + "excel/" + f + ".zip"); // 压缩文件
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        // 生成excel
        for (int j = 0, n = list.size() / length + 1; j < n; j++) {
            Workbook book = new HSSFWorkbook();
            Sheet sheet = book.createSheet("付款全流程报表");
            // double d = 0; // 用来统计
            String file = path + "excel/" + f + "-" + j
                    + ".xls";
            fileNames.add(file);
            FileOutputStream o = null;
            try {
                o = new FileOutputStream(file);

                // sheet.addMergedRegion(new
                // CellRangeAddress(list.size()+1,0,list.size()+5,6));
                Row row = sheet.createRow(0);
                row.createCell(0).setCellValue("所属公司");
                row.createCell(1).setCellValue("采购申请单号");
                row.createCell(2).setCellValue("采购申请人");
                row.createCell(3).setCellValue("采购申请日期");
                row.createCell(4).setCellValue("采购申请数量");
                row.createCell(5).setCellValue("采购申请金额");
                row.createCell(6).setCellValue("采购申请审批状态");

                row.createCell(7).setCellValue("订单单据号");
                row.createCell(8).setCellValue("SAP订单号");
                row.createCell(9).setCellValue("供应商");
                row.createCell(10).setCellValue("币种");
                row.createCell(11).setCellValue("税率");
                row.createCell(12).setCellValue("订单申请人");
                row.createCell(13).setCellValue("订单申请日期");
                row.createCell(14).setCellValue("订单数量");
                row.createCell(15).setCellValue("订单金额");
                row.createCell(16).setCellValue("订单审批状态");

                row.createCell(17).setCellValue("付款单号");
                row.createCell(18).setCellValue("付款类别");
                row.createCell(19).setCellValue("付款数量");
                row.createCell(20).setCellValue("付款金额");
                row.createCell(21).setCellValue("付款日期");
                row.createCell(22).setCellValue("审批状态");
                row.createCell(23).setCellValue("sap凭证号");

                int m = 1;
                for (int i = 1, min = (list.size() - j * length + 1) > (length + 1) ? (length + 1)
                        : (list.size() - j * length + 1); i < min; i++) {
                    m++;
                    FullProcessPay user = list.get(length * j + i - 1);
                    user = (FullProcessPay) getQueryCriteria2(user);
                    Row row1 = sheet.createRow(i);
                    row1.createCell(0).setCellValue(user.getCompanyName());
                    row1.createCell(1).setCellValue(user.getAoid());
                    row1.createCell(2).setCellValue(user.getAoapplyuser());
                    row1.createCell(3).setCellValue(user.getAoapplydate() == null ? "" : sdf.format(user.getAoapplydate()));
                    row1.createCell(4).setCellValue(user.getAoApplyCount() == null ? "" : user.getAoApplyCount().toString());
                    row1.createCell(5).setCellValue(user.getAoIamoney() == null ? "" : user.getAoIamoney().toString());
                    row1.createCell(6).setCellValue(user.getAoCapprovalstate());

                    row1.createCell(7).setCellValue(user.getPurchaseOrderId() == null ? "" : user.getPurchaseOrderId());
                    row1.createCell(8).setCellValue(user.getPurchaseOrderSapId() == null ? "" : user.getPurchaseOrderSapId());
                    row1.createCell(9).setCellValue(user.getSupplierName() == null ? "" : user.getSupplierName());
                    row1.createCell(10).setCellValue(user.getCurrencyName() == null ? "" : user.getCurrencyName());
                    row1.createCell(11).setCellValue(user.getTaxRate() == null ? "" : user.getTaxRate().toString());
                    row1.createCell(12).setCellValue(user.getPoApplyUser() == null ? "" : user.getPoApplyUser());
                    row1.createCell(13).setCellValue(user.getPoApplyDate() == null ? "" : sdf.format(user.getPoApplyDate()));
                    row1.createCell(14).setCellValue(user.getPoApplyCount() == null ? "" : user.getPoApplyCount().toString());
                    row1.createCell(15).setCellValue(user.getPoIamoney() == null ? "" : user.getPoIamoney().toString());
                    row1.createCell(16).setCellValue(user.getPoCapprovalstate() == null ? "" : user.getPoCapprovalstate());

                    row1.createCell(17).setCellValue(user.getPayId());
                    row1.createCell(18).setCellValue(user.getAdvancePayment());
                    row1.createCell(19).setCellValue(user.getAdvancePayCount() == null ? "" : user.getAdvancePayCount().toString());
                    row1.createCell(20).setCellValue(user.getAdvancePayPrice() == null ? "" : user.getAdvancePayPrice().toString());
                    row1.createCell(21).setCellValue(user.getAdvanceApplyDate() == null ? "" : sdf.format(user.getAdvanceApplyDate()));
                    row1.createCell(22).setCellValue(user.getAdvancePoCapprovalstate());
                    row1.createCell(23).setCellValue(user.getAdvancePrepayment());


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

    /**
     * 生成excel
     *
     * @param list
     * @param request
     * @param length
     * @param f
     * @param out
     * @throws IOException
     */
    public void toExcel(List<FullProcess> list, HttpServletRequest request,
                        int length, String f, OutputStream out) throws IOException {
        List<String> fileNames = new ArrayList(); // 用于存放生成的文件名称s
        String path = Env.getProperty("file.upload.excel.path");
        File zip = new File(path + "excel/" + f + ".zip"); // 压缩文件
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        // 生成excel
        for (int j = 0, n = list.size() / length + 1; j < n; j++) {
            Workbook book = new HSSFWorkbook();
            Sheet sheet = book.createSheet("全流程报表");
            // double d = 0; // 用来统计
            String file = path + "excel/" + f + "-" + j
                    + ".xls";
            fileNames.add(file);
            FileOutputStream o = null;
            try {
                o = new FileOutputStream(file);

                // sheet.addMergedRegion(new
                // CellRangeAddress(list.size()+1,0,list.size()+5,6));
                Row row = sheet.createRow(0);
                row.createCell(1).setCellValue("公司名称");
                row.createCell(1).setCellValue("需求单据号");
                row.createCell(2).setCellValue("需求人");
                row.createCell(3).setCellValue("需求日期");
                row.createCell(4).setCellValue("需求部门");
                row.createCell(5).setCellValue("是否有立项报告");
                row.createCell(6).setCellValue("项目名称");
                row.createCell(7).setCellValue("立项金额");
                row.createCell(8).setCellValue("收益核算方式");
                row.createCell(9).setCellValue("年收益金额");
                row.createCell(10).setCellValue("投资回收期");
                row.createCell(11).setCellValue("项目类别");
                row.createCell(12).setCellValue("需求数量");
                row.createCell(13).setCellValue("需求金额");
                row.createCell(14).setCellValue("需求审批状态");
                row.createCell(15).setCellValue("成本中心编码");
                row.createCell(16).setCellValue("成本中心");

                row.createCell(17).setCellValue("采购申请单号");
                row.createCell(18).setCellValue("采购申请人");
                row.createCell(19).setCellValue("采购申请日期");
                row.createCell(20).setCellValue("采购申请数量");
                row.createCell(21).setCellValue("采购申请金额");
                row.createCell(22).setCellValue("采购申请审批状态");

                row.createCell(23).setCellValue("订单单据号");
                row.createCell(24).setCellValue("SAP订单号");
                row.createCell(25).setCellValue("供应商");
                row.createCell(26).setCellValue("币种");
                row.createCell(27).setCellValue("税率");
                row.createCell(28).setCellValue("订单申请人");
                row.createCell(29).setCellValue("订单申请日期");
                row.createCell(30).setCellValue("订单数量");
                row.createCell(31).setCellValue("订单金额");
                row.createCell(32).setCellValue("订单审批状态");
                row.createCell(33).setCellValue("收货单据号");
                row.createCell(34).setCellValue("收货数量");
                row.createCell(35).setCellValue("收货日期");
                row.createCell(36).setCellValue("验收单号");
                row.createCell(37).setCellValue("验收数量");
                row.createCell(38).setCellValue("验收状态");
                row.createCell(39).setCellValue("验收日期");
                row.createCell(40).setCellValue("验收人");
                row.createCell(41).setCellValue("物料凭证号");
                int m = 1;
                for (int i = 1, min = (list.size() - j * length + 1) > (length + 1) ? (length + 1)
                        : (list.size() - j * length + 1); i < min; i++) {
                    m++;
                    FullProcess user = list.get(length * j + i - 1);
                    //Double dd = user.getMoney();
                    /*if (dd == null) {
                        dd = 0.0;
                    }
                    d += dd;*/
                    Row row1 = sheet.createRow(i);
                    row1.createCell(0).setCellValue(user.getCompanyName() == null ? "" : user.getCompanyName().toString());
                    row1.createCell(1).setCellValue(user.getDoId() == null ? "" : user.getDoId().toString());
                    row1.createCell(2).setCellValue(user.getDoApplyUser() == null ? "" : user.getDoApplyUser());
                    row1.createCell(3).setCellValue(user.getDoApplyDate() == null ? "" : sdf.format(user.getDoApplyDate()));
                    row1.createCell(4).setCellValue(user.getDoApplyDepartment() == null ? "" : user.getDoApplyDepartment());
                    row1.createCell(5).setCellValue(user.getProjectProposal() == null ? "" : user.getProjectProposal());
                    row1.createCell(6).setCellValue(user.getEntryName() == null ? "" : user.getEntryName());
                    row1.createCell(7).setCellValue(user.getProjectMoney() == null ? "" : user.getProjectMoney().toString());
                    row1.createCell(8).setCellValue(user.getIncomeAccountingMethod() == null ? "" : user.getIncomeAccountingMethod());
                    row1.createCell(9).setCellValue(user.getAnnualIncomeAmount() == null ? "" : user.getAnnualIncomeAmount().toString());
                    row1.createCell(10).setCellValue(user.getPaybackPeriodOfInvestment() == null ? "" : user.getPaybackPeriodOfInvestment());
                    row1.createCell(11).setCellValue(user.getProjectType() == null ? "" : user.getProjectType());
                    row1.createCell(12).setCellValue(user.getDoDemandCount() == null ? "" : user.getDoDemandCount().toString());
                    row1.createCell(13).setCellValue(user.getDoIamoney() == null ? "" : user.getDoIamoney().toString());
                    row1.createCell(14).setCellValue(user.getDoCapprovalstate() == null ? "" : user.getDoCapprovalstate());
                    row1.createCell(15).setCellValue(user.getCostCenterCode() == null ? "" : user.getCostCenterCode());
                    row1.createCell(16).setCellValue(user.getCostCenter() == null ? "" : user.getCostCenter());

                    row1.createCell(17).setCellValue(user.getAoId() == null ? "" : user.getAoId());
                    row1.createCell(18).setCellValue(user.getAoApplyUser() == null ? "" : user.getAoApplyUser());
                    row1.createCell(19).setCellValue(user.getAoApplyDate() == null ? "" : sdf.format(user.getAoApplyDate()));
                    row1.createCell(20).setCellValue(user.getApApplyCount() == null ? "" : user.getApApplyCount().toString());
                    row1.createCell(21).setCellValue(user.getAoIamoney() == null ? "" : user.getAoIamoney().toString());
                    row1.createCell(22).setCellValue(user.getAoCapprovalstate() == null ? "" : user.getAoCapprovalstate());
                    row1.createCell(23).setCellValue(user.getPurchaseOrderId() == null ? "" : user.getPurchaseOrderId());
                    row1.createCell(24).setCellValue(user.getPurchaseOrderSapId() == null ? "" : user.getPurchaseOrderSapId());
                    row1.createCell(25).setCellValue(user.getSupplierName() == null ? "" : user.getSupplierName());
                    row1.createCell(26).setCellValue(user.getCurrencyName() == null ? "" : user.getCurrencyName());
                    row1.createCell(27).setCellValue(user.getTaxRate() == null ? "" : user.getTaxRate());
                    row1.createCell(28).setCellValue(user.getPoApplyUser() == null ? "" : user.getPoApplyUser());
                    row1.createCell(29).setCellValue(user.getPoApplyDate() == null ? "" : sdf.format(user.getPoApplyDate()));
                    row1.createCell(30).setCellValue(user.getPoApplyCount() == null ? "" : user.getPoApplyCount().toString());
                    row1.createCell(31).setCellValue(user.getPoIamoney() == null ? "" : user.getPoIamoney().toString());
                    row1.createCell(32).setCellValue(user.getPoCapprovalstate() == null ? "" : user.getPoCapprovalstate());
                    row1.createCell(33).setCellValue(user.getReceiveGoodsId() == null ? "" : user.getReceiveGoodsId());
                    row1.createCell(34).setCellValue(user.getReceiveDate() == null ? "" : sdf.format(user.getReceiveDate()));
                    row1.createCell(35).setCellValue(user.getReceiveGoodsCount() == null ? "" : user.getReceiveGoodsCount().toString());
                    row1.createCell(36).setCellValue(user.getCheckAcceptId() == null ? "" : user.getCheckAcceptId());
                    row1.createCell(37).setCellValue(user.getCheckCount() == null ? "" : user.getCheckCount().toString());
                    row1.createCell(38).setCellValue(user.getCaCapprovalstate() == null ? "" : user.getCaCapprovalstate());
                    row1.createCell(39).setCellValue(user.getCaCheckDate() == null ? "" : sdf.format(user.getCaCheckDate()));
                    row1.createCell(40).setCellValue(user.getApplyCheckUserName() == null ? "" : user.getApplyCheckUserName());
                    row1.createCell(41).setCellValue(user.getEpMaterialdocument() == null ? "" : user.getEpMaterialdocument());
                }
               /* CellStyle cellStyle2 = book.createCellStyle();
                cellStyle2.setAlignment(CellStyle.ALIGN_CENTER);
                row = sheet.createRow(m);
                Cell cell0 = row.createCell(0);
                cell0.setCellValue("Total");
                cell0.setCellStyle(cellStyle2);
                Cell cell4 = row.createCell(4);
                cell4.setCellValue(d);
                cell4.setCellStyle(cellStyle2);
                sheet.addMergedRegion(new CellRangeAddress(m, m, 0, 3));*/
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

    /**
     * 生成excel
     *
     * @param list
     * @param request
     * @param length
     * @param f
     * @param out
     * @throws IOException
     */
    public void toExcelDetail(List<FullProcessDetail> list, HttpServletRequest request,
                              int length, String f, OutputStream out) throws IOException {
        List<String> fileNames = new ArrayList(); // 用于存放生成的文件名称s
        String path = Env.getProperty("file.upload.excel.path");
        File zip = new File(path + "excel/" + f + ".zip"); // 压缩文件
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        // 生成excel
        for (int j = 0, n = list.size() / length + 1; j < n; j++) {
            Workbook book = new HSSFWorkbook();
            Sheet sheet = book.createSheet("全流程报表");
            // double d = 0; // 用来统计
            String file = path + "excel/" + f + "-" + j
                    + ".xls";
            fileNames.add(file);
            FileOutputStream o = null;
            try {
                o = new FileOutputStream(file);

                // sheet.addMergedRegion(new
                // CellRangeAddress(list.size()+1,0,list.size()+5,6));
                Row row = sheet.createRow(0);
                row.createCell(0).setCellValue("需求单据号");
                row.createCell(1).setCellValue("需求人");
                row.createCell(2).setCellValue("需求日期");
                row.createCell(3).setCellValue("需求部门");
                row.createCell(4).setCellValue("项目编码");
                row.createCell(5).setCellValue("项目名称");
                row.createCell(6).setCellValue("资产名称");
                row.createCell(7).setCellValue("规格参数");
                row.createCell(8).setCellValue("功能");
                row.createCell(9).setCellValue("单位");
                row.createCell(10).setCellValue("用途");
                row.createCell(11).setCellValue("资产类别");
                row.createCell(12).setCellValue("总账科目");
                row.createCell(13).setCellValue("折旧年限");
                row.createCell(14).setCellValue("办公室地点");
                row.createCell(15).setCellValue("所属公司");
                row.createCell(16).setCellValue("本次申请数量");
                row.createCell(17).setCellValue("已用预算金额");
                row.createCell(18).setCellValue("可用预算金额");
                row.createCell(19).setCellValue("本次申请金额");
                row.createCell(20).setCellValue("是否有立项报告书");
                row.createCell(21).setCellValue("需求到位日期");
                row.createCell(22).setCellValue("成本中心");
                row.createCell(23).setCellValue("资产类型");
                row.createCell(24).setCellValue("车间类型");
                row.createCell(25).setCellValue("需求审批状态");

                row.createCell(26).setCellValue("采购申请单号");
                row.createCell(27).setCellValue("采购申请人");
                row.createCell(28).setCellValue("采购申请日期");
                row.createCell(29).setCellValue("资产编码");
                row.createCell(30).setCellValue("次级");
                row.createCell(31).setCellValue("申请数量");
                row.createCell(32).setCellValue("参考单价");
                row.createCell(33).setCellValue("参考总价");
                row.createCell(34).setCellValue("申请单审批状态");

                row.createCell(35).setCellValue("订单单据号");
                row.createCell(36).setCellValue("SAP订单号");
                row.createCell(37).setCellValue("采购员");
                row.createCell(38).setCellValue("供应商");
                row.createCell(39).setCellValue("订单数量");
                row.createCell(40).setCellValue("币种");
                row.createCell(41).setCellValue("税率");
                row.createCell(42).setCellValue("采购单价（含税）");
                row.createCell(43).setCellValue("采购总价（含税）");
                row.createCell(44).setCellValue("约定交货日期");
                row.createCell(45).setCellValue("质保周期（月）");
                row.createCell(46).setCellValue("订单审批状态");

                row.createCell(47).setCellValue("收货单据号");
                row.createCell(48).setCellValue("收货日期");
                row.createCell(49).setCellValue("收货数量");
                row.createCell(50).setCellValue("合同号");

                row.createCell(51).setCellValue("验收单号");
                row.createCell(52).setCellValue("验收数量");
                row.createCell(53).setCellValue("验收审批状态");

                for (int i = 1, min = (list.size() - j * length + 1) > (length + 1) ? (length + 1)
                        : (list.size() - j * length + 1); i < min; i++) {

                    FullProcessDetail user = list.get(length * j + i - 1);
                    user = (FullProcessDetail) getQueryCriteria2(user);
                    Row row1 = sheet.createRow(i);
                    row1.createCell(0).setCellValue(user.getDemandId());
                    row1.createCell(1).setCellValue(user.getDoApplyUser());
                    row1.createCell(2).setCellValue(user.getDoApplyDate() == null ? "" : sdf.format(user.getDoApplyDate()));
                    row1.createCell(3).setCellValue(user.getBudgetDepartmentName());
                    row1.createCell(4).setCellValue(user.getProjectCode());
                    row1.createCell(5).setCellValue(user.getProjectName());
                    row1.createCell(6).setCellValue(user.getAssetsName());
                    row1.createCell(7).setCellValue(user.getSpecificationParameter());
                    row1.createCell(8).setCellValue(user.getFunctions());
                    row1.createCell(9).setCellValue(user.getUnits());
                    row1.createCell(10).setCellValue(user.getUseDescription());
                    row1.createCell(11).setCellValue(user.getAssetClassification());
                    row1.createCell(12).setCellValue(user.getLedgerAccount());
                    row1.createCell(13).setCellValue(user.getDepreciation() == null ? "" : user.getDepreciation().toString());
                    row1.createCell(14).setCellValue(user.getOfficeLocation());
                    row1.createCell(15).setCellValue(user.getCompanyName());
                    row1.createCell(16).setCellValue(user.getDemandCount() == null ? "" : user.getDemandCount().toString());
                    row1.createCell(17).setCellValue(user.getUsedSumMoney() == null ? "" : user.getUsedSumMoney().toString());
                    row1.createCell(18).setCellValue(user.getAvaliableSumMoneyOld() == null ? "" : user.getAvaliableSumMoneyOld().toString());
                    row1.createCell(19).setCellValue(user.getReferencePrice() == null ? "" : user.getReferencePrice().toString());
                    row1.createCell(20).setCellValue(user.getProjectProposal());
                    row1.createCell(21).setCellValue(user.getRequirementsDate() == null ? "" : sdf.format(user.getRequirementsDate()));
                    row1.createCell(22).setCellValue(user.getCostCenter());
                    row1.createCell(23).setCellValue(user.getAssetType());
                    row1.createCell(24).setCellValue(user.getWorkShopType());
                    row1.createCell(25).setCellValue(user.getDemandCapprovalstate());

                    row1.createCell(26).setCellValue(user.getApplyOrderId());
                    row1.createCell(27).setCellValue(user.getAoApplyUser() == null ? "" : user.getAoApplyUser());
                    row1.createCell(28).setCellValue(user.getAoApplyDate() == null ? "" : sdf.format(user.getAoApplyDate()));
                    row1.createCell(29).setCellValue(user.getMainAssetCode());
                    row1.createCell(30).setCellValue(user.getSecondary());
                    row1.createCell(31).setCellValue(user.getApplyCount() == null ? "" : user.getApplyCount().toString());
                    row1.createCell(32).setCellValue(user.getInquiryUnitPrice() == null ? "" : user.getInquiryUnitPrice().toString());
                    row1.createCell(33).setCellValue(user.getInquiryPrice() == null ? "" : user.getInquiryPrice().toString());
                    row1.createCell(34).setCellValue(user.getApplyCapprovalstate());


                    row1.createCell(35).setCellValue(user.getPurchaseId());
                    row1.createCell(36).setCellValue(user.getPurchaseOrderSapId() == null ? "" : user.getPurchaseOrderSapId());
                    row1.createCell(37).setCellValue(user.getBuyerName());
                    row1.createCell(38).setCellValue(user.getSupplierName());
                    row1.createCell(39).setCellValue(user.getPurchaseCount() == null ? "" : user.getPurchaseCount().toString());
                    row1.createCell(40).setCellValue(user.getCurrencyCode());
                    row1.createCell(41).setCellValue(user.getExchangeRate() == null ? "" : user.getExchangeRate().toString());
                    row1.createCell(42).setCellValue(user.getPurchaseUnitPrice() == null ? "" : user.getPurchaseUnitPrice().toString());
                    row1.createCell(43).setCellValue(user.getPurchasePrice() == null ? "" : user.getPurchasePrice().toString());
                    row1.createCell(44).setCellValue(user.getPdRequirementsDate() == null ? "" : sdf.format(user.getPdRequirementsDate()));
                    row1.createCell(45).setCellValue(user.getWarrantyPeriod() == null ? "" : user.getWarrantyPeriod().toString());
                    row1.createCell(46).setCellValue(user.getPurchaseCapprovalstate());

                    row1.createCell(47).setCellValue(user.getReceiveGoodsId() == null ? "" : user.getReceiveGoodsId());
                    row1.createCell(48).setCellValue(user.getReceiveDate() == null ? "" : sdf.format(user.getReceiveDate()));
                    row1.createCell(49).setCellValue(user.getReceiveCount() == null ? "" : user.getReceiveCount().toString());
                    row1.createCell(50).setCellValue(user.getContractNo());

                    row1.createCell(51).setCellValue(user.getCheckId() == null ? "" : user.getCheckId());
                    row1.createCell(52).setCellValue(user.getCaCapprovalstate() == null ? "" : user.getCaCapprovalstate());
                    row1.createCell(53).setCellValue(user.getCheckCount() == null ? "" : user.getCheckCount().toString());
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

    /**
     * 生成excel--付款明细
     *
     * @param list
     * @param request
     * @param length
     * @param f
     * @param out
     * @throws IOException
     */
    public void toExportPayDetail(List<FullProcessPayDetail> list, HttpServletRequest request,
                                  int length, String f, OutputStream out) throws IOException {
        List<String> fileNames = new ArrayList(); // 用于存放生成的文件名称s
        String path = Env.getProperty("file.upload.excel.path");
        File zip = new File(path + "excel/" + f + ".zip"); // 压缩文件
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        // 生成excel
        for (int j = 0, n = list.size() / length + 1; j < n; j++) {
            Workbook book = new HSSFWorkbook();
            Sheet sheet = book.createSheet("全流程付款明细报表");
            // double d = 0; // 用来统计
            String file = path + "excel/" + f + "-" + j
                    + ".xls";
            fileNames.add(file);
            FileOutputStream o = null;
            try {
                o = new FileOutputStream(file);

                // sheet.addMergedRegion(new
                // CellRangeAddress(list.size()+1,0,list.size()+5,6));
                Row row = sheet.createRow(0);


                row.createCell(0).setCellValue("所属公司");
                row.createCell(1).setCellValue("采购申请单号");
                row.createCell(2).setCellValue("设备名称");
                row.createCell(3).setCellValue("采购申请人");
                row.createCell(4).setCellValue("采购申请日期");
                row.createCell(5).setCellValue("资产编码");
                row.createCell(6).setCellValue("次级");
                row.createCell(7).setCellValue("申请数量");
                row.createCell(8).setCellValue("参考单价");
                row.createCell(9).setCellValue("参考总价");
                row.createCell(10).setCellValue("申请单审批状态");

                row.createCell(11).setCellValue("订单单据号");
                row.createCell(12).setCellValue("SAP订单号");
                row.createCell(13).setCellValue("采购员");
                row.createCell(14).setCellValue("供应商");
                row.createCell(15).setCellValue("订单数量");
                row.createCell(16).setCellValue("币种");
                row.createCell(17).setCellValue("税率");
                row.createCell(18).setCellValue("采购单价（含税）");
                row.createCell(19).setCellValue("采购总价（含税）");
                row.createCell(20).setCellValue("约定交货日期");
                row.createCell(21).setCellValue("质保周期（月）");
                row.createCell(22).setCellValue("订单审批状态");

                row.createCell(23).setCellValue("付款单据号");
                row.createCell(24).setCellValue("付款类别");
                row.createCell(25).setCellValue("付款金额");
                row.createCell(26).setCellValue("付款单审批状态");
                row.createCell(27).setCellValue("到期日");
                for (int i = 1, min = (list.size() - j * length + 1) > (length + 1) ? (length + 1)
                        : (list.size() - j * length + 1); i < min; i++) {

                    FullProcessPayDetail user = list.get(length * j + i - 1);
                    user = (FullProcessPayDetail) getQueryCriteria2(user);
                    Row row1 = sheet.createRow(i);

                    row1.createCell(0).setCellValue(user.getCompanyName());
                    row1.createCell(1).setCellValue(user.getApplyOrderId());
                    row1.createCell(2).setCellValue(user.getAssetsName());
                    row1.createCell(3).setCellValue(user.getAoApplyUser() == null ? "" : user.getAoApplyUser());
                    row1.createCell(4).setCellValue(user.getAoApplyDate() == null ? "" : sdf.format(user.getAoApplyDate()));
                    row1.createCell(5).setCellValue(user.getMainAssetCode());
                    row1.createCell(6).setCellValue(user.getSecondary());
                    row1.createCell(7).setCellValue(user.getApplyCount() == null ? "" : user.getApplyCount().toString());
                    row1.createCell(8).setCellValue(user.getInquiryUnitPrice() == null ? "" : user.getInquiryUnitPrice().toString());
                    row1.createCell(9).setCellValue(user.getInquiryPrice() == null ? "" : user.getInquiryPrice().toString());
                    row1.createCell(10).setCellValue(user.getApplyCapprovalstate());


                    row1.createCell(11).setCellValue(user.getPurchaseId());
                    row1.createCell(12).setCellValue(user.getPurchaseOrderSapId() == null ? "" : user.getPurchaseOrderSapId());
                    row1.createCell(13).setCellValue(user.getBuyerName());
                    row1.createCell(14).setCellValue(user.getSupplierName());
                    row1.createCell(15).setCellValue(user.getPurchaseCount() == null ? "" : user.getPurchaseCount().toString());
                    row1.createCell(16).setCellValue(user.getCurrencyCode());
                    row1.createCell(17).setCellValue(user.getExchangeRate() == null ? "" : user.getExchangeRate().toString());
                    row1.createCell(18).setCellValue(user.getPurchaseUnitPrice() == null ? "" : user.getPurchaseUnitPrice().toString());
                    row1.createCell(19).setCellValue(user.getPurchasePrice() == null ? "" : user.getPurchasePrice().toString());
                    row1.createCell(20).setCellValue(user.getRequirementsDate() == null ? "" : sdf.format(user.getRequirementsDate()));
                    row1.createCell(21).setCellValue(user.getWarrantyPeriod() == null ? "" : user.getWarrantyPeriod().toString());
                    row1.createCell(22).setCellValue(user.getPurchaseCapprovalstate());

                    row.createCell(23).setCellValue(user.getPaymentOrderId());
                    row.createCell(24).setCellValue(user.getPaymentType());
                    row.createCell(25).setCellValue(user.getPayPrice() == null ? "" : user.getPayPrice().toString());
                    row.createCell(26).setCellValue(user.getPayCapprovalstate());
                    row.createCell(27).setCellValue(user.getNextPayment() == null ? "" : sdf.format(user.getNextPayment()));

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

}
