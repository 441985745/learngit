package com.opple.fa.assetcard.dao;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.assetcard.entity.MeasuringInstruments;


public interface MeasuringInstrumentsDAO {

	/**
	 * 获得资产明细表（计量设备类）基础数据
	 * @return
	 */
	MeasuringInstruments searchMeasuringInstruments(@Param("measuringInstruments") MeasuringInstruments measuringInstruments);
	/**
	 * 保存资产明细表（计量设备类）基础数据
	 * @return
	 */
	void insertMeasuringInstruments(MeasuringInstruments measuringInstruments);
	
	void updateMeasuringInstruments(MeasuringInstruments measuringInstruments);

	/**
	 * 更新计量器具资产卡片 -- 验收
	 * @param measuringInstruments
	 */
    void updateMeasuringInstrumentsCard(MeasuringInstruments measuringInstruments);
    /**
     * 导入更新资产
     * @param measuringInstruments
     */
    void updateLeadingMeasuringInstruments(MeasuringInstruments measuringInstruments);

    void deleteMeasuringInstruments(Integer id);

    void updateMeasuringInstrumentsCard1(MeasuringInstruments measuringInstruments);

    void updateMeasuringInstrumentsCard2(MeasuringInstruments measuringInstruments);
}
