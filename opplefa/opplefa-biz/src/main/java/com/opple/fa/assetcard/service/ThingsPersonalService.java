package com.opple.fa.assetcard.service;

import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.util.Pager;

public interface ThingsPersonalService {

	/**
	 * 查询个人资产
	 * 
	 * @param pager
	 * @param generalAsset
	 * @return
	 */
	Pager<GeneralAsset> searchTbFaGeneralAsset(Pager<GeneralAsset> pager,
			GeneralAsset generalAsset);

	/**
	 * 查找详情
	 * 
	 * @param id
	 * @return
	 */
	GeneralAsset searchDetails(long id);

}
