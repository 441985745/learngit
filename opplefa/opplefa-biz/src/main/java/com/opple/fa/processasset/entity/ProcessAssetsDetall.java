package com.opple.fa.processasset.entity;

import com.opple.workflow.entity.WorkFlowCommon;

import java.util.Date;

public class ProcessAssetsDetall extends WorkFlowCommon {

    /**
     * 流程类资产子表
     */
    private static final long serialVersionUID = 1L;

    private String createBy; // 创建人
    private Date createDate; // 创建时间
    private String updateBy; // 更新人
    private Date updateDate; // 更新时间
    private String status; // 删除列标识（0可用1删除）
    private Long processAssetsId; //流程类资产业务表ID
    private Long generalAssetId; //资产信息表ID

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getProcessAssetsId() {
        return processAssetsId;
    }

    public void setProcessAssetsId(Long processAssetsId) {
        this.processAssetsId = processAssetsId;
    }

    public Long getGeneralAssetId() {
        return generalAssetId;
    }

    public void setGeneralAssetId(Long generalAssetId) {
        this.generalAssetId = generalAssetId;
    }


}
