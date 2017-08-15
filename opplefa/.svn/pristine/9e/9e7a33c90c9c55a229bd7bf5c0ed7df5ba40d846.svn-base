package com.opple.fa.purchase.dao;

import com.opple.fa.purchase.entity.CheckAcceptance;
import com.opple.fa.purchase.entity.CheckMachine;
import com.opple.fa.purchase.model.ApproveHistoryCheckPrint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  *<p>@Description: 验收表（收货单保存时传递的数据，已收货未验收的）
 *  *<p>@author lipengju
 *  *<p>@date 2016年11月8日 下午5:01:36
 */
public interface CheckAcceptanceDAO {

    /**
     * 生成验收编码用
     *
     * @return
     */
    Long searchNextSequence();


    /**
     * 保存验收头
     *
     * @param checkAcceptance
     * @return
     */
    Long saveCheckAcceptance(CheckAcceptance checkAcceptance);

    CheckAcceptance getCheckAcceptanceByDocument(String checkAcceptId);

    /**
     * 绑定审批流 失败 后删除验收单的表头
     *
     * @param checkAcceptId
     */
    void delCheckAcceptanceByCheckAcceptId(String checkAcceptId);

    /**
     * 修改 验收单的头
     *
     * @param checkAcceptance
     * @return
     */
    Long updateCheckAcceptance(CheckAcceptance checkAcceptance);

    /**
     * (验收单 保存)的头(sap验收单接口调用返回值保存)
     *
     * @param checkAcceptance
     * @return
     */
    Long updateCheckAcceptanceBySap(CheckAcceptance checkAcceptance);

    /**
     * 审批同意最后一步,根据主键,更新审批流最后一步同意时间为当前系统时间
     * @param cdocument
     */
    void updateLastCheckDate(String cdocument);


    List<String> getUserCode(String userCode);

    void updateCheckAcceptanceByUpdateSap(String document);

    CheckMachine getCheckMachineByCheckId(String checkId);

    void saveCheckMachine(CheckMachine checkMachine);
    void updateCheckMachineByCheckId(CheckMachine checkMachine);

    void updateIsprint(String checkId);

    List<ApproveHistoryCheckPrint> getApproveHistory(@Param("checkAcceptId") String checkAcceptId, @Param("typeId") String typeId);

    void saveDelete(CheckAcceptance checkAcceptance);
}
