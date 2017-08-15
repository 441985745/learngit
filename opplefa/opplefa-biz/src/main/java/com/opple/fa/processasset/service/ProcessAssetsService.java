package com.opple.fa.processasset.service;

import java.util.List;

import com.opple.fa.processasset.entity.ProcessAssets;
import com.opple.fa.processasset.entity.ProcessAssetsDetall;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;

public interface ProcessAssetsService {

    /**
     * 模糊查询流程固定资产
     *
     * @param pager
     * @param processAssets
     * @return
     */
    Pager<ProcessAssets> searchProcessAssets(Pager<ProcessAssets> pager, ProcessAssets processAssets);

    /**
     * 查看
     *
     * @param processAssets
     * @return
     */
    ProcessAssets examineProcessAssetsList(ProcessAssets processAssets);

    /**
     * 流程固定资产保存
     *
     * @param processAssets
     * @param processAssetsDetall
     * @return
     */
    ExecuteResult<ProcessAssets> saveProcessAssets(ProcessAssets processAssets, String processAssetsDetall);

    /**
     * 流程固定资产修改
     *
     * @param processAssets
     * @param processAssetsDetall
     */
    void updateProcessAssets(ProcessAssets processAssets, String processAssetsDetall);

    /**
     * 流程固定资产逻辑删除
     *
     * @param processAssets
     * @return
     */
    ExecuteResult<ProcessAssets> deleteProcessAssets(ProcessAssets processAssets);

    /**
     * 导出参数查询
     *
     * @param processAssets
     * @return
     */
    List<ProcessAssets> export(ProcessAssets processAssets);

    /**
     * 绑定
     *
     * @return
     * @Param assetAllocation
     */
    ExecuteResult<ProcessAssets> bindApplyWorkFlow(ProcessAssets processAssets);

    /**
     * 删除申请单据
     *
     * @param document
     */
    void deleteProcessAssetsByDocument(String document);

    /**
     * 同意
     *
     * @return
     * @Param assetAllocation
     */
    ExecuteResult<ProcessAssets> agreeWorkFlow(ProcessAssets processAssets);

    /**
     * 查询单据号是否重复
     *
     * @param document
     */
    ProcessAssets getProcessAssetsByDocument(String document);

    /**
     * 退回
     *
     * @return
     * @Param processAssets
     */
    ExecuteResult<ProcessAssets> denyWorkFlow(ProcessAssets processAssets);

    /**
     * 弃审
     *
     * @param processAssets
     * @return
     */
    ExecuteResult<ProcessAssets> dropWorkFlow(ProcessAssets processAssets);

    /**
     * 获得所有的子表明细
     *
     * @param status
     */
    List<String> searchProcessAssetsDetallById(ProcessAssetsDetall processAssetsDetall);
}
