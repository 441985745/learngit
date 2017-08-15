package com.opple.fa.myTaks.webapp.controller;

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

import com.opple.fa.myTasks.entity.MyApplyTasks;
import com.opple.fa.myTasks.entity.MyTasks;
import com.opple.fa.myTasks.service.MyApplyTasksService;
import com.opple.fa.myTasks.service.MyTasksService;
import com.opple.security.LoginContextHolder;
import com.opple.security.SessionSecurityConstants;
import com.opple.util.Env;
import com.opple.util.ExportExcle;
import com.opple.util.Pager;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhangshulong on 2017/2/28.
 */
@Controller
@RequestMapping("/myTasks")
public class MyTasksController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyTasksController.class);
    @Resource
    private MyTasksService myTasksService;
    @Resource
    private MyApplyTasksService myApplyTasksService;

    /**
     * 模糊查询我的审批待办
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @return
     */
    @RequestMapping("/searchMyTasksList")
    public String searchMyTasksList(Model model, HttpServletRequest req, Long offset, Long pageSize, MyTasks myTasks) {

        Pager<MyTasks> pager = new Pager<MyTasks>();
        Pager<MyTasks> myTasksPager = new Pager<MyTasks>();
        try {
            myTasks.setDocumentId(StringUtils.isNotBlank(myTasks.getDocumentId()) ? myTasks.getDocumentId().trim() : null);
            myTasks.setApplyUser(StringUtils.isNotBlank(myTasks.getApplyUser()) ? myTasks.getApplyUser().trim() : null);
            myTasks.setUserCode(LoginContextHolder.get().getUserName());
            pager.setOffset(null == offset ? pager.getOffset() : offset);
            pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
            pager.setUri(req.getRequestURI());
            if ("".equals(myTasks.getDocumentStatus()) || null == myTasks.getDocumentStatus()) { //所有单据类型
                myTasksPager = myTasksService.searchMyTasksList(pager, myTasks);
            }
            if ("1".equals(myTasks.getDocumentStatus())) { //采购需求单
                myTasksPager = myTasksService.searchMyTasksList1(pager, myTasks);
            }
            if ("2".equals(myTasks.getDocumentStatus())) { //采购申请单
                myTasksPager = myTasksService.searchMyTasksList2(pager, myTasks);
            }
            if ("3".equals(myTasks.getDocumentStatus())) { //采购订单
                myTasksPager = myTasksService.searchMyTasksList3(pager, myTasks);
            }
            if ("4".equals(myTasks.getDocumentStatus())) { //采购收获单
                myTasksPager = myTasksService.searchMyTasksList4(pager, myTasks);
            }
            if ("5".equals(myTasks.getDocumentStatus())) { //采购验收单
                myTasksPager = myTasksService.searchMyTasksList5(pager, myTasks);
            }
            if ("6".equals(myTasks.getDocumentStatus())) { //采购付款单
                myTasksPager = myTasksService.searchMyTasksList6(pager, myTasks);
            }
            /*if ("7".equals(myTasks.getDocumentStatus())) { //盘点单
                myTasksPager = myTasksService.searchMyTasksList7(pager, myTasks);
            }*/
            if ("8".equals(myTasks.getDocumentStatus())) { //报废/变卖
                myTasksPager = myTasksService.searchMyTasksList8(pager, myTasks);
            }
            if ("9".equals(myTasks.getDocumentStatus())) { //报废/变卖
                myTasksPager = myTasksService.searchMyTasksList9(pager, myTasks);
            }
            if ("10".equals(myTasks.getDocumentStatus())) { //预算调整单
                myTasksPager = myTasksService.searchMyTasksList10(pager, myTasks);
            }
            if ("11".equals(myTasks.getDocumentStatus())) { //非po
                myTasksPager = myTasksService.searchMyTasksList11(pager, myTasks);
            }
            myTasksPager.setUri(req.getRequestURI());
            model.addAttribute("myTasksPager", myTasksPager);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
        }
        return "myTasks/my_tasks_list";
    }

    /**
     * 模糊查询我的申请待办
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @return
     */
    @RequestMapping("/searchApplyMyTasksList")
    public String searchApplyMyTasksList(Model model, HttpServletRequest req, Long offset, Long pageSize, MyApplyTasks myApplyTasks) {
        Pager<MyApplyTasks> pager = new Pager<MyApplyTasks>();
        Pager<MyApplyTasks> myApplyTasksPager = new Pager<MyApplyTasks>();
        String caUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        myApplyTasks.setUserCode(caUserCode);
        try {
            myApplyTasks.setDocument(StringUtils.isNotBlank(myApplyTasks.getDocument()) ? myApplyTasks.getDocument().trim() : null);
            myApplyTasks.setApplyUser(StringUtils.isNotBlank(myApplyTasks.getApplyUser()) ? myApplyTasks.getApplyUser().trim() : null);
            myApplyTasks.setAssetType(StringUtils.isNotBlank(myApplyTasks.getAssetType()) ? myApplyTasks.getAssetType().trim() : null);
            myApplyTasks.setUserCode(LoginContextHolder.get().getUserName());
            pager.setOffset(null == offset ? pager.getOffset() : offset);
            pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
            pager.setUri(req.getRequestURI());
            if ("".equals(myApplyTasks.getDocumentType()) || null == myApplyTasks.getDocumentType()) { //所有单据类型
                myApplyTasksPager = myApplyTasksService.searchMyApplyTasksList(pager, myApplyTasks);
            }
            if ("1".equals(myApplyTasks.getDocumentType())) { //采购需求——>申请
                myApplyTasksPager = myApplyTasksService.searchMyDemandOrderList(myApplyTasksPager, myApplyTasks);
            }
            if ("2".equals(myApplyTasks.getDocumentType())) { //采购申请——>订单
                myApplyTasksPager = myApplyTasksService.searchMyApplyOrderList(myApplyTasksPager, myApplyTasks);
            }
            if ("3".equals(myApplyTasks.getDocumentType())) { //采购订单——>收货
                myApplyTasksPager = myApplyTasksService.searchMyPurchaseOrderList(myApplyTasksPager, myApplyTasks);
            }
            if ("4".equals(myApplyTasks.getDocumentType())) { //采购收货——>验收
                myApplyTasksPager = myApplyTasksService.searchMyReceiveGoodsList(myApplyTasksPager, myApplyTasks);
            }
            if ("5".equals(myApplyTasks.getDocumentType())) { //采购订单——>付款
                myApplyTasksPager = myApplyTasksService.searchMyPurchaseFkList(myApplyTasksPager, myApplyTasks);
            }
            myApplyTasksPager.setUri(req.getRequestURI());
            model.addAttribute("myApplyTasksPager", myApplyTasksPager);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
        }
        return "myTasks/my_apply_task_list";
    }

    String fileName = "";

    @RequestMapping(value = "/Export", method = RequestMethod.GET)
    @ResponseBody
    public void export(ModelMap model, HttpServletRequest req,
                       HttpServletResponse response, MyApplyTasks myApplyTasks) {
        Date date = new Date();

        List<MyApplyTasks> myApplyList = new ArrayList<MyApplyTasks>();
        String caUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        myApplyTasks.setUserCode(caUserCode);

        try {
            myApplyTasks.setDocument(StringUtils.isNotBlank(myApplyTasks.getDocument()) ? myApplyTasks.getDocument().trim() : null);
            myApplyTasks.setApplyUser(StringUtils.isNotBlank(myApplyTasks.getApplyUser()) ? myApplyTasks.getApplyUser().trim() : null);
            myApplyTasks.setUserCode(LoginContextHolder.get().getUserName());

            if ("".equals(myApplyTasks.getDocumentType()) || null == myApplyTasks.getDocumentType()) { //所有单据类型
                myApplyList = myApplyTasksService.searchMyApplyTasksListExport(myApplyTasks);

            }
            if ("1".equals(myApplyTasks.getDocumentType())) { //采购需求——>申请
                myApplyList = myApplyTasksService.searchMyDemandOrderListExport(myApplyTasks);

            }
            if ("2".equals(myApplyTasks.getDocumentType())) { //采购申请——>订单
                myApplyList = myApplyTasksService.searchMyApplyOrderListExport(myApplyTasks);

            }
            if ("3".equals(myApplyTasks.getDocumentType())) { //采购订单——>收货
                myApplyList = myApplyTasksService.searchMyPurchaseOrderListExport(myApplyTasks);

            }
            if ("4".equals(myApplyTasks.getDocumentType())) { //采购收货——>验收
                myApplyList = myApplyTasksService.searchMyReceiveGoodsListExport(myApplyTasks);

            }
            if ("5".equals(myApplyTasks.getDocumentType())) { //采购订单——>付款
                myApplyList = myApplyTasksService.searchMyPurchaseFkListExport(myApplyTasks);

            }

        } catch (Exception e) {
            LOGGER.error("Exception", e);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String f = "申请待办-=" + format.format(date);
        this.fileName = f;
        setResponseHeader(response);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            toExportPayDetail(myApplyList, req, 10000, f, out);
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

    public void toExportPayDetail(List<MyApplyTasks> list,
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

                row.createCell(0).setCellValue("单据类型");
                row.createCell(1).setCellValue("单据编号");
                row.createCell(2).setCellValue("申请部门");
                row.createCell(3).setCellValue("申请人");
                row.createCell(4).setCellValue("申请日期");
                row.createCell(5).setCellValue("资产类型");
                row.createCell(6).setCellValue("单据状态");


                for (int i = 1, min = (list.size() - j * length + 1) > (length + 1) ? (length + 1)
                        : (list.size() - j * length + 1); i < min; i++) {
                    MyApplyTasks myApplyTasks = list.get(length * j + i - 1);
                    myApplyTasks = (MyApplyTasks) getQueryCriteria2(myApplyTasks);
                    Row row1 = sheet.createRow(i);

                    if ("1".equals(myApplyTasks.getDocumentType())) {
                        row1.createCell(0).setCellValue("采购需求-->申请");
                    }
                    if ("2".equals(myApplyTasks.getDocumentType())) {
                        row1.createCell(0).setCellValue("采购申请-->订单");
                    }
                    if ("3".equals(myApplyTasks.getDocumentType())) {
                        row1.createCell(0).setCellValue("采购订单-->收货");
                    }
                    if ("4".equals(myApplyTasks.getDocumentType())) {
                        row1.createCell(0).setCellValue("发起收货-->验收");
                    }
                    if ("5".equals(myApplyTasks.getDocumentType())) {
                        row1.createCell(0).setCellValue("采购订单-->付款");
                    }
                    row1.createCell(1).setCellValue(
                            myApplyTasks.getDocument() == null ? ""
                                    : myApplyTasks.getDocument());
                    row1.createCell(2).setCellValue(
                            myApplyTasks.getBudgetDepartmentName() == null ? ""
                                    : myApplyTasks.getBudgetDepartmentName());
                    row1.createCell(3).setCellValue(
                            myApplyTasks.getApplyUser() == null ? ""
                                    : myApplyTasks.getApplyUser());
                    row1.createCell(4).setCellValue(
                            myApplyTasks.getApplyDate() == null ? ""
                                    : myApplyTasks.getApplyDate());
                    row1.createCell(5).setCellValue(
                            myApplyTasks.getAssetType() == null ? ""
                                    : myApplyTasks.getAssetType());
                    row1.createCell(6).setCellValue("待办");

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

    public Object getQueryCriteria2(Object myApplyTasks) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException {
        Field[] fields = myApplyTasks.getClass().getDeclaredFields();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            String type = f.getGenericType().toString();
            f.setAccessible(true);
            if ("class java.lang.String".equals(type)) {
                if (f.get(myApplyTasks) == null) {
                    f.set(myApplyTasks, "");
                }
            }
        }
        return myApplyTasks;
    }

}
