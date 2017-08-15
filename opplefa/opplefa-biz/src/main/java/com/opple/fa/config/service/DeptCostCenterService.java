package com.opple.fa.config.service;

import com.opple.fa.config.entity.DeptCostCenter;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import java.util.List;

/**
 * Created by Wangkaixuan on 2017/4/11.
 */
public interface DeptCostCenterService {

    /**单条保存
     * @param deptCostCenter
     * @return
     */
    ExecuteResult<DeptCostCenter> save(DeptCostCenter deptCostCenter);

    /**批量保存
     * @param deptCostCenter
     * @return
     */
    ExecuteResult<List<DeptCostCenter>> saveBatch(List<DeptCostCenter> deptCostCenter);

    /**删除，根据cDeptCostCenterCodes的值删除  ---（private List<String> cDeptCostCenterCodes;// 资产编码--删除使用）
     * @param deptCostCenterList
     * @return
     */
    ExecuteResult<DeptCostCenter> delete(List<DeptCostCenter> deptCostCenterList);

    /**更新
     * @param deptCostCenter
     * @return
     */
    ExecuteResult<DeptCostCenter> update(DeptCostCenter deptCostCenter);

    /**
     * 判断成本中心是否存在
     * @param costCenterCode
     * @return
     */
    Integer checkCostCenter(String costCenterCode);
    /**
     * 判断公司是否存在
     * @param companyCode
     * @return
     */
    Integer checkCompanyCode(String companyCode);

    /**单条查询
     * @param deptCostCenter
     * @return
     */
    DeptCostCenter get(DeptCostCenter deptCostCenter);

    /**分页查询
     * @param pager
     * @param deptCostCenter
     * @return
     */
    Pager<DeptCostCenter> searchPagerList(Pager<DeptCostCenter> pager, DeptCostCenter deptCostCenter);

    /**分页查询
     * @param deptCostCenter
     * @return
     */
    List<DeptCostCenter> getList(DeptCostCenter deptCostCenter);


    List<String> selectExistCodes(List<String> cpositionCodes);

    /**
     * 判断表中是否存在预算体成本中心对应数据
     * @param deptCostCenter
     * @return
     */
    Integer checkDeptCostCenterByEntity(DeptCostCenter deptCostCenter);

    /**
     * 更新或添加
     * @param addList
     * @param updateList
     * @return
     */
    String addOrUpdateDeptCostCenter(List<DeptCostCenter> addList, List<DeptCostCenter> updateList);

    Integer selectExistCodes(DeptCostCenter deptCostCenter);
}
