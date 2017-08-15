package com.opple.fa.config.dao;

import com.opple.fa.config.entity.DeptCostCenter;
import org.apache.ibatis.annotations.Param;
import com.opple.util.Pager;
import java.util.List;

/**
 * Created by Wangkaixuan on 2017/4/11.
 */
public interface DeptCostCenterDAO {

    /**
     * 判断成本中心编码是否存在
     * @param costCenterCode
     * @return
     */
    Integer checkCostCenter(String costCenterCode);
    /**
     * 判断成本中心编码是否存在
     * @param companyCode
     * @return
     */
    Integer checkCompanyCode(String companyCode);

    int save(@Param("deptCostCenter") DeptCostCenter deptCostCenter);
    Long saveBatch(List<DeptCostCenter> deptCostCenter);
    int delete(@Param("deptCostCenter") DeptCostCenter deptCostCenter);
    int update(@Param("deptCostCenter") DeptCostCenter deptCostCenter);

    List<DeptCostCenter> searchList(@Param("pager") Pager<DeptCostCenter> pager, @Param("deptCostCenter") DeptCostCenter deptCostCenter);
    Long searchListCount(@Param("deptCostCenter") DeptCostCenter deptCostCenter);

    DeptCostCenter get(@Param("deptCostCenter") DeptCostCenter deptCostCenter);

    List<String> selectExistCodes(@Param("deptCodes") List<String> deptCodes);

    List<DeptCostCenter> getList(@Param("deptCostCenter") DeptCostCenter deptCostCenter);

    /**
     * 查询数据库中符合条件的预算体-成本中心数量
     *
     * @param deptCostCenter
     */
    Integer checkDeptCostCenterByEntity(@Param("deptCostCenter") DeptCostCenter deptCostCenter);

    Integer checkExisted(@Param("deptCostCenter") DeptCostCenter deptCostCenter);
}
