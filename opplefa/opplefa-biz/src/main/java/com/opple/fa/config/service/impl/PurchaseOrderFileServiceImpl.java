package com.opple.fa.config.service.impl;


import com.opple.fa.config.dao.PurchaseOrderFileDAO;
import com.opple.fa.config.entity.PurchaseOrderFile;
import com.opple.fa.config.service.PurchaseOrderFileService;
import com.opple.util.Pager;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by lk on 10/24/16.
 */
public class PurchaseOrderFileServiceImpl implements PurchaseOrderFileService {
    @Getter @Setter
    private PurchaseOrderFileDAO purchaseOrderFileDAO;

    @Override
    public Pager<PurchaseOrderFile> searchPurchaseOrderFiles(Pager<PurchaseOrderFile> pager, PurchaseOrderFile purchaseOrderFile) {
        List<PurchaseOrderFile> purchaseOrderFileList = purchaseOrderFileDAO.searchPurchaseOrderFiles(pager, purchaseOrderFile);
        Long purchaseOrderFilesCount = purchaseOrderFileDAO.searchPurchaseOrderFilesCount(purchaseOrderFile);

        pager.setRecords(purchaseOrderFileList);
        pager.setTotal(purchaseOrderFilesCount);

        return Pager.cloneFromPager(pager, purchaseOrderFilesCount, purchaseOrderFileList);
    }

	@Override
	public List<PurchaseOrderFile> searchPurchaseOrderFilesList(PurchaseOrderFile purchaseOrderFile) {
		List<PurchaseOrderFile> purchaseOrderFileList = purchaseOrderFileDAO.searchPurchaseOrderFilesList(purchaseOrderFile);
		return purchaseOrderFileList;
	}

	@Override
	public Long save(PurchaseOrderFile purchaseOrderFile) {
		// TODO Auto-generated method stub
		return purchaseOrderFileDAO.save(purchaseOrderFile);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return purchaseOrderFileDAO.delete(id);
	}

	@Override
	public Integer deletePurchaseOrderFilesByIds(String ids) {
		// TODO Auto-generated method stub
		return purchaseOrderFileDAO.deleteByIds(ids);
	}

}
