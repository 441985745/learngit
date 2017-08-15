package com.opple.fa.allocation.service.impl;


import com.opple.fa.allocation.dao.AssetAllocationDAO;
import com.opple.fa.allocation.dao.AssetAllocationDetailDAO;
import com.opple.fa.allocation.entity.AssetAllocation;
import com.opple.fa.allocation.entity.AssetAllocationDetail;
import com.opple.fa.allocation.service.AssetAllocationDetailService;
import com.opple.fa.assetcard.dao.GeneralAssetDAO;
import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcard.entity.ISUPDATESAP;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class AssetAllocationDetailServiceImpl implements
		AssetAllocationDetailService {
	@Getter
	@Setter
	private AssetAllocationDetailDAO assetAllocationDetailDAO;
	@Getter
	@Setter
	private GeneralAssetDAO generalAssetDAO;
	@Getter
	@Setter
	private AssetAllocationDAO assetAllocationDAO;

	@Override
	public List<AssetAllocationDetail> searchAssetAllocationDetail(
			AssetAllocation assetAllocation) {
		List<AssetAllocationDetail> assetAllocationDetailList = assetAllocationDetailDAO
                .searchAssetAllocationDetailById(Integer.parseInt(String.valueOf(assetAllocation.getId())));

		return assetAllocationDetailList;
	}

	@Override
	public void updateAllocationDetailById(
			AssetAllocationDetail assetAllocationDetail) {
		assetAllocationDetailDAO
				.updateAllocationDetailById(assetAllocationDetail);

	}

	@Override
	public void updateAllocationDetailNewCode(
			AssetAllocationDetail assetAllocationDetail) {
		assetAllocationDetailDAO
				.updateAllocationDetailNewCode(assetAllocationDetail);
		AssetAllocation assetAllocation = new AssetAllocation();
		assetAllocation.setId(Long.parseLong(String
				.valueOf(assetAllocationDetail.getAssetAllocationId())));
		assetAllocation.setIsGetSapCode(ISUPDATESAP.HASBEENUPDATED.getIsUpdateSap()); 
		assetAllocationDAO.updateGetSapStatus(assetAllocation);

	}

	@Override
	public void updateAssetCard(AssetAllocation assetAllocation) {
		List<AssetAllocationDetail> assetAllocationDetailList = assetAllocationDetailDAO
				.searchAssetAllocationDetailById(Integer.parseInt(String
						.valueOf(assetAllocation.getId())));
		for (AssetAllocationDetail assetAllocationDetail : assetAllocationDetailList) {
			long generalAssetId = assetAllocationDetail.getGeneralAssetId();

			GeneralAsset generalAsset = new GeneralAsset();
			generalAsset.setAssetPerson(assetAllocation
					.getPostAllocationPeople());
			generalAsset
					.setAssetPersonCode(assetAllocation.getPostPeopleCode());
			generalAsset.setPersentDepartment(assetAllocation
					.getPostAllocationDepartment());
			generalAsset.setCompanyName(assetAllocation
					.getPostAllocationCompany());
			generalAsset.setPersentStorageLocation(assetAllocation
					.getPostAllocationStoragePlace());
			generalAsset.setOfficeLocation(assetAllocation.getOfficeLocation());
			generalAsset.setCompanyCode(assetAllocation.getPostCompanyCode());
			generalAsset.setDepartmentCode(assetAllocation
					.getPostDepartmentCode());
			generalAsset.setCostCenterCode(assetAllocation
					.getPostCostcenterCode());
			generalAsset.setUseDepartment(assetAllocation.getPostAllocationDepartment());
			if (null != assetAllocationDetail.getNewAssetCode()
					&& !"".equals(assetAllocationDetail.getNewAssetCode())) {
				generalAsset.setAssetCode(assetAllocationDetail
						.getNewAssetCode());
				generalAsset.setOldAssetCode(assetAllocationDetail
						.getAssetCode());
			}
			generalAsset.setId(generalAssetId);

			generalAssetDAO.updateCard(generalAsset);

		}

	}

}
