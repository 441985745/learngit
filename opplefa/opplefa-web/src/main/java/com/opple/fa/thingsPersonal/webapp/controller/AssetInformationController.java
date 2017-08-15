package com.opple.fa.thingsPersonal.webapp.controller;

import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcard.service.ThingsPersonalService;
import com.opple.util.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/property") 
public class AssetInformationController {
	
	@Resource
	private ThingsPersonalService thingsPersonalService;
	
	
	@RequestMapping("/lookOver")
	public String lookOver(Model model, Integer id) {
		
		
		GeneralAsset generalAsset = thingsPersonalService.searchDetails(id);
		model.addAttribute("generalAsset", generalAsset);
		
		return "property/viewWindow";
	}
	
	@RequestMapping("/searchTbFaGeneralAsset")
	
	private String searchTbFaGeneralAsset(Model model, HttpServletRequest req, Long offset, Long pageSize, GeneralAsset generalAsset) {
		
		Pager<GeneralAsset> pager = new Pager<GeneralAsset>();               
		pager.setUri(req.getRequestURI());
		pager.setOffset(null == offset ? pager.getOffset() : offset);
		pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
		Pager<GeneralAsset> personalPager = thingsPersonalService.searchTbFaGeneralAsset(pager, generalAsset);
		personalPager.setUri(req.getRequestURI());
		model.addAttribute("personalPager", personalPager);
		
		return "property/thingsPersonal";
	}
	
	
	
}
