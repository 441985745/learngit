package com.opple.fa.config.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opple.fa.config.entity.Supplier;
import com.opple.fa.config.service.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	@Resource
	private SupplierService supplierService;
	
	@RequestMapping(value = "/api/searchSupplier")
	@ResponseBody
	public List<Supplier> currencyRate(Supplier supplier) {
		List<Supplier> supplierList = new ArrayList<Supplier>();
		supplierList = supplierService.searchSupplier(supplier);
		return supplierList;
	}

}
