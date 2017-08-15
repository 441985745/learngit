package com.opple.fa.config.service.impl;

import java.util.List;

import com.opple.fa.config.dao.SupplierDAO;
import com.opple.fa.config.entity.Supplier;
import com.opple.fa.config.service.SupplierService;

import lombok.Getter;
import lombok.Setter;

public class SupplierServiceImpl implements SupplierService {
	@Getter @Setter
	private SupplierDAO supplierDAO;

	@Override
	public List<Supplier> searchSupplier(Supplier supplier) {
		
		return supplierDAO.searchSupplier(supplier);
	}
	@Override
	public List<Supplier> searchSupplierByCodeAndName(Supplier supplier) {
		
		return supplierDAO.searchSupplierByCodeAndName(supplier);
	}
}
