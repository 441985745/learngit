package com.opple.fa.assetcard.dao;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.assetcard.entity.MachineryEquipment;


public interface MachineryEquipmentDAO {
	
	/**
	 * 获得资产明细表（机器设备类）基础数据
	 * @return
	 */
	MachineryEquipment searchMachineryEquipment(@Param("machineryEquipment")MachineryEquipment machineryEquipment);
	/**
	 * 保存资产明细表（机器设备类）基础数据
	 * @return
	 */
	void insertMachineryEquipment(MachineryEquipment machineryEquipment); 
	
	void updateMachineryEquipment(MachineryEquipment machineryEquipment);

	/**
	 * 更新资产卡片明细 -- 验收
	 * @param machineryEquipment
	 */
    void updateMachineryEquipmentCard(MachineryEquipment machineryEquipment);
    /**
     * 导入更新 卡片
     * @param machineryEquipment
     */
    void  updateLeadingMachinery(MachineryEquipment machineryEquipment);

    void deleteMachineryEquipment(Integer id);

    void updateMachineryEquipmentCard1(MachineryEquipment machineryEquipment);

    void updateMachineryEquipmentCard2(MachineryEquipment machineryEquipment);
}
