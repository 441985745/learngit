package com.opple.fa.processasset.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.processasset.entity.ProcessAssets;
import com.opple.util.Pager;

public interface ProcessAssetsDAO {

    /**
     * 流程国定资产查询
     *
     * @param pager
     * @param processAssets
     * @return
     */
    List<ProcessAssets> searchProcessAssets(@Param("pager") Pager<ProcessAssets> pager, @Param("processAssets") ProcessAssets processAssets);

    /**
     * 流程国定资产查询条数
     *
     * @param processAssets
     * @return
     */
    Long searchProcessAssetsCount(@Param("processAssets") ProcessAssets processAssets);

    /**
     * 查看
     *
     * @param processAssets
     * @return
     */
    ProcessAssets getProcessAssets(@Param("processAssets") ProcessAssets processAssets);

    /**
     * 流程固定资产保存
     *
     * @param processAssets
     */
    void saveProcessAssets(ProcessAssets processAssets);

    /**
     * 修改
     *
     * @param processAssetsDetall
     */
    void updateProcessAssets(@Param("processAssets") ProcessAssets processAssets);

    /**
     * 关闭删除
     *
     * @param processAssets
     */
    void deleteProcessAssets(@Param("processAssets") ProcessAssets processAssets);

    /**
     * 导出参数查询
     *
     * @param processAssets
     * @return
     */
    List<ProcessAssets> export(@Param("processAssets") ProcessAssets processAssets);

    /**
     * 删除申请单据
     *
     * @param document
     */
    void deleteProcessAssetsByDocument(String document);

    /**
     * 查看单据是否存在
     *
     * @param document
     */
    ProcessAssets getProcessAssetsByDocument(String document);
}
