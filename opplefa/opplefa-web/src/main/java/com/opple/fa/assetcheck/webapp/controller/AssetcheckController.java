package com.opple.fa.assetcheck.webapp.controller;

import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcheck.entity.AssetCheckBusiness;
import com.opple.fa.assetcheck.export.controller.ExportAssetcheckTemplate;
import com.opple.fa.assetcheck.model.INVENTORYSTATUS;
import com.opple.fa.assetcheck.model.ISDRAFT;
import com.opple.fa.assetcheck.service.AssetCheckBusinessService;
import com.opple.fa.webapp.controller.BaseOpenApiController;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/check")
public class AssetcheckController extends BaseOpenApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AssetcheckController.class);

    @Resource
    private AssetCheckBusinessService assetCheckBusinessService;

    /**
     * 模糊查询资产盘点信息
     *
     * @param model
     * @param req
     * @param assetCheckBusiness
     * @return
     */
    @RequestMapping("/assetCheck")
    public String assetCheck(Model model, HttpServletRequest req, Long offset, Long pageSize, AssetCheckBusiness assetCheckBusiness) {

        String documentNumber = req.getParameter("documentNumber");
        assetCheckBusiness.setDocumentNumber(null == documentNumber ? null : documentNumber.trim());
        String inventoryPeople = req.getParameter("inventoryPeople");
        assetCheckBusiness.setInventoryPeople(null == inventoryPeople ? null : inventoryPeople.trim());
        String inventoryDepartment = req.getParameter("inventoryDepartment");
        assetCheckBusiness.setInventoryDepartment(null == inventoryDepartment ? null : inventoryDepartment.trim());
        String inventoryStatus = req.getParameter("inventoryStatus");
        assetCheckBusiness.setInventoryStatus(null == inventoryStatus ? null : inventoryStatus.trim());
        String inventoryYear = req.getParameter("inventoryYear");
        assetCheckBusiness.setInventoryYear(null == inventoryYear ? null : inventoryYear.trim());
        String inventoryMonth = req.getParameter("inventoryMonth");
        assetCheckBusiness.setInventoryMonth(null == inventoryMonth ? null : inventoryMonth.trim());

        Pager<AssetCheckBusiness> pager = new Pager<AssetCheckBusiness>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<AssetCheckBusiness> assetCheckBusinessPager = assetCheckBusinessService.searchAssetCheckBusiness(pager, assetCheckBusiness);
        assetCheckBusinessPager.setUri(req.getRequestURI());
        model.addAttribute("assetCheckBusinessPager", assetCheckBusinessPager);

        return "assetscheck/assets_check_list";
    }


    /**
     * 保存资产申请单
     *
     * @param model
     * @param req
     * @param assetCheckBusiness
     * @return
     */
    @RequestMapping("/api/saveSponsorCheck")
    @ResponseBody
    public ExecuteResult<String> saveSponsorCheck(Model model, HttpServletRequest req, AssetCheckBusiness assetCheckBusiness, String sublist) {
        //拆分年月

        String applicationDate1 = req.getParameter("applicationDate1");
        if (applicationDate1 != null && !"".equals(applicationDate1)) {
            String[] array = applicationDate1.split("-");
            String year = array[0];
            String month = array[1];
            assetCheckBusiness.setInventoryYear(year);
            assetCheckBusiness.setInventoryMonth(month);
        }

        if (ISDRAFT.SUBMIT.getIsDraft().equals(assetCheckBusiness.getIsDraft())) {
            assetCheckBusiness.setInventoryStatus(INVENTORYSTATUS.WAITING.getInventoryStatus());
        }
        //在页面提示添加成功与否
        ExecuteResult<String> result = new ExecuteResult<String>();
        /*try {*/
        if (sublist != null && !"".equals(sublist)) {
            assetCheckBusinessService.saveAssetCheckBusiness(assetCheckBusiness, sublist);
        } else {
            result.addErrorMessage("请勾选盘点详情信息");
        }
        /*	result.setSuccessMessage("添加成功");
            result.setResult("");
		} catch (Exception e) {
			LOGGER.error("Exception", e);
			result.addErrorMessage("添加失败");
		}*/
        return result;

    }


    /**
     * 弹出盘点差异
     *
     * @return
     */
    @RequestMapping("/checkDifference")
    public String checkDifference() {
        return "assetscheck/assets_check_difference";
    }

    /**
     * 弹出查看
     *
     * @return
     */
    @RequestMapping("/propertyExamine")
    public String propertyExamine(Model model, HttpServletRequest req, long id) {
        return "assetscheck/assets_check_detail";
    }

    /**
     * 录入盘点结果
     *
     * @param model
     * @param req
     * @param id
     * @return
     */
    @RequestMapping("/input/result")
    public String inputResult(Model model, HttpServletRequest req, long id) {
        AssetCheckBusiness assetCheckBusiness = new AssetCheckBusiness();
        assetCheckBusiness.setId(id);
        AssetCheckBusiness assetCheckBusinessResult = assetCheckBusinessService.getAssetCheckBusiness(assetCheckBusiness);
        model.addAttribute("assetCheckBusiness", assetCheckBusinessResult);
        return "assetscheck/assets_check_input_result";
    }

    @RequestMapping("/checkList")
    public String checkList() {
        return "assetscheck/check_list";
    }

    @RequestMapping("/InventoryDifference")
    public String inventoryDifference() {
        return "assetscheck/Inventory_difference";
    }

	/*@RequestMapping("/deleteAssetCheck")
    @ResponseBody
	public String deleteAssetCheck(String ids){
		String[] id=ids.split(",");
		GeneralAsset generalAsset =new GeneralAsset();
		for (String string : id) {
			int idCard=Integer.parseInt(string);
			generalAsset.setId(idCard);
			assetCheckBusinessService.deleteAssetCheck(generalAsset);
		}
		return "asda";

	}*/


    /**
     * 弹出盘点申请单
     *
     * @param model
     * @param req
     * @return
     */
    @RequestMapping("/sponsorCheck")
    public String sponsorCheck(Model model, HttpServletRequest req, Long offset, Long pageSize) {

        return "assetscheck/assets_check_apply";
    }

    /**
     * 模糊查询盘点申请单中的资产详情信息
     *
     * @param model
     * @param req
     * @param assetCheckBusiness
     * @return
     */
    @RequestMapping("/searchDetails")
    public String searchDetails(Model model, HttpServletRequest req, Long offset, Long pageSize, AssetCheckBusiness assetCheckBusiness) {
        String inventoryPeople = req.getParameter("inventoryPeople");
        assetCheckBusiness.setInventoryPeople(null == inventoryPeople ? null : inventoryPeople.trim());

        Pager<GeneralAsset> pager = new Pager<GeneralAsset>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<GeneralAsset> generalAssetPager = assetCheckBusinessService.searchGeneralAssetDetails(pager, assetCheckBusiness);
        generalAssetPager.setUri(req.getRequestURI());
        model.addAttribute("generalAssetPager", generalAssetPager);
        return "assetscheck/assets_check_apply";
    }

    /**
     * 盘点删除
     *
     * @param req
     * @return
     */
    @RequestMapping("/api/deleteCheck")
    @ResponseBody
    public ExecuteResult<AssetCheckBusiness> deleteCheck(HttpServletRequest req) {
        ExecuteResult<AssetCheckBusiness> result = new ExecuteResult<AssetCheckBusiness>();
        String id = req.getParameter("id");
        AssetCheckBusiness assetCheckBusiness = new AssetCheckBusiness();
        assetCheckBusiness.setId(Long.parseLong(id));
        assetCheckBusiness = assetCheckBusinessService.getAssetCheckBusiness(assetCheckBusiness);
        String isDraft = assetCheckBusiness.getIsDraft();
        if (null != id && id != "") {
            if (ISDRAFT.DRAFT.getIsDraft().equals(isDraft)) {

                assetCheckBusinessService.deleteAssetCheck(assetCheckBusiness);

            } else {
                result.addErrorMessage("有不可删除的数据!");
            }
        } else {
            result.addErrorMessage("请选择一条数据！");
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
                       HttpServletResponse response, AssetCheckBusiness assetCheckBusiness) {
        String fileName = "盘点列表.xlsx";
        List<AssetCheckBusiness> assetCheckBusinessList = assetCheckBusinessService
                .serchAssetCheckBusinessData(assetCheckBusiness);
        try {
            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");

            ExportAssetcheckTemplate exportAssetcheckTemplate = new ExportAssetcheckTemplate();
            exportAssetcheckTemplate.doExport(response, fileName,
                    assetCheckBusinessList);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
    }
}
