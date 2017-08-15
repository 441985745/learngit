package com.opple.fa.assetcard.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.assetcard.entity.AdministrationAsset;
import com.opple.fa.assetcard.entity.ComputerAsset;
import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcard.entity.MachineryEquipment;
import com.opple.fa.assetcard.entity.MeasuringInstruments;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;

public interface GeneralAssetService {
    /**
     * 分页查询所有的卡片信息
     *
     * @param pager
     * @param generalAsset
     * @return
     */
    Pager<GeneralAsset> serchAssetCard(Pager<GeneralAsset> pager,
                                       GeneralAsset generalAsset);

    /**
     * 分页查询所有的卡片信息 不加权限 资产会计所展示的列表
     *
     * @param pager
     * @param generalAsset
     * @return
     */
    Pager<GeneralAsset> serchAssetCardByAccounting(Pager<GeneralAsset> pager,
                                                   GeneralAsset generalAsset);

    /**
     * 不带分页查询所有的卡片信息
     *
     * @param generalAsset
     * @return
     */
    List<GeneralAsset> serchAssetCardData(@Param("generalAsset") GeneralAsset generalAsset);

    /**
     * 不带分页查询所有的卡片信息
     *
     * @param generalAsset
     * @return
     */
    List<GeneralAsset> serchAssetCardDataIT(@Param("generalAsset") GeneralAsset generalAsset);

    /**
     * 不带分页查询所有的卡片信息
     *
     * @param generalAsset
     * @return
     */
    List<GeneralAsset> serchAssetCardDataXZ(@Param("generalAsset") GeneralAsset generalAsset);

    /**
     * 不带分页查询所有的卡片信息
     *
     * @param generalAsset
     * @return
     */
    List<GeneralAsset> serchAssetCardDataJL(@Param("generalAsset") GeneralAsset generalAsset);

    /**
     * 不带分页查询所有的卡片信息
     *
     * @param generalAsset
     * @return
     */
    List<GeneralAsset> serchAssetCardDataJX(@Param("generalAsset") GeneralAsset generalAsset);

    /**
     * 不带分页查询所有的卡片信息 导出
     *
     * @param generalAsset
     * @return
     */
    List<GeneralAsset> serchAssetCardDateByAccounting(@Param("generalAsset") GeneralAsset generalAsset);

    /**
     * 不带分页查询所有的卡片信息 导出
     *
     * @param generalAsset
     * @return
     */
    List<GeneralAsset> serchAssetCardDateByAccountingIT(@Param("generalAsset") GeneralAsset generalAsset);

    /**
     * 不带分页查询所有的卡片信息 导出
     *
     * @param generalAsset
     * @return
     */
    List<GeneralAsset> serchAssetCardDateByAccountingXZ(@Param("generalAsset") GeneralAsset generalAsset);

    /**
     * 不带分页查询所有的卡片信息 导出
     *
     * @param generalAsset
     * @return
     */
    List<GeneralAsset> serchAssetCardDateByAccountingJL(@Param("generalAsset") GeneralAsset generalAsset);

    /**
     * 不带分页查询所有的卡片信息 导出
     *
     * @param generalAsset
     * @return
     */
    List<GeneralAsset> serchAssetCardDateByAccountingJX(@Param("generalAsset") GeneralAsset generalAsset);

    /**
     * 查看资产编码是否重复
     *
     * @param GeneralAsset
     * @return
     */
    Long assetCodeCount(GeneralAsset generalAsset);

    /**
     * 保存卡片信息
     *
     * @return
     */
    ExecuteResult<GeneralAsset> insertAssetCard(
            GeneralAsset generalAsset, ComputerAsset computerAsset,
            MachineryEquipment machineryEquipment,
            MeasuringInstruments measuringInstruments,
            AdministrationAsset administrationAsset);

    /**
     * 修改卡片信息
     *
     * @return
     */
    ExecuteResult<GeneralAsset> updateAssetCard(
            GeneralAsset generalAsset, ComputerAsset computerAsset,
            MachineryEquipment machineryEquipment,
            MeasuringInstruments measuringInstruments,
            AdministrationAsset administrationAsset);

    /**
     * 导入卡片信息
     *
     * @return
     */
    void excelInsertAssetCard(List<GeneralAsset> listGeneralAsset,
                                     List<ComputerAsset> listComputerAsset,
                                     List<MachineryEquipment> listMachineryEquipment,
                                     List<MeasuringInstruments> listMeasuringInstruments,
                                     List<AdministrationAsset> listAdministrationAsset, boolean isFyorZb);

    /**
     * 删除卡片信息
     *
     * @param generalAsset
     * @return
     */
    void deleteAssetCard(GeneralAsset generalAsset);

    /**
     * 增行信息
     *
     * @param generalAsset
     * @return
     */
    List<GeneralAsset> searchAssetCardInfo(GeneralAsset generalAsset);

    /**
     * 增行信息(资产会计)
     *
     * @param generalAsset
     * @return
     */
    List<GeneralAsset> searchAssetCardInfoForBursar(GeneralAsset generalAsset);

    /**
     * 增行信息内部查询
     *
     * @param generalAsset
     * @return
     */
    List<GeneralAsset> searchGeneralAsset(GeneralAsset generalAsset);

    /**
     * 流程同意完之后更新数据库的数据
     *
     * @return
     * @Param discardSell
     */
    void updateGeneralAssetByDocument(GeneralAsset generalAsset);

    /**
     * 获得对象
     *
     * @param id
     * @return
     */
    GeneralAsset getGeneralAsset(String id);

    /**
     * 更新sap状态
     *
     * @param id
     * @return
     */
    void updateSapById(@Param("id") Integer id);

    /**
     * 获得所有资产 以便从sap中获取资产更新本地
     *
     * @param generalAsset
     * @return
     */
    List<GeneralAsset> searchAssetBySapCode(GeneralAsset generalAsset);

    /**
     * 从sap中获取资产信息 更新本地卡片信息
     *
     * @param generalAsset
     */
    void updateAssetBySapCode(GeneralAsset generalAsset);

    /**
     * 报废完成后修改资产状态
     *
     * @param generalAsset
     */
    void updateAssetState(GeneralAsset generalAsset);

    /**
     * 资产会计查询所有更新sap
     */
    List<GeneralAsset> serachByacctingUpdateSap(GeneralAsset generalAsset);

    /**
     * 修改是否被流程类资产占用
     */
    void updateAssembleOccupancy(GeneralAsset generalAsset);
    
    ExecuteResult<GeneralAsset> updateAssetFromSap(Integer id, String userCode);
    /**
     * 定时任务接口
     */
    ExecuteResult<GeneralAsset> updateAssetFromSapByList();
}
