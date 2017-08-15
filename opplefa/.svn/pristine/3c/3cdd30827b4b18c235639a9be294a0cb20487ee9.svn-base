package com.opple.fa.config.service.impl;

import com.opple.fa.config.dao.DeptCostCenterDAO;
import com.opple.fa.config.entity.DeptCostCenter;
import com.opple.fa.config.service.DeptCostCenterService;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Wangkaixuan on 2017/4/11.
 */
public class DeptCostCenterServiceImpl implements DeptCostCenterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeptCostCenterServiceImpl.class);
    @Getter @Setter
    private DeptCostCenterDAO deptCostCenterDAO;

    @Override
    public ExecuteResult<DeptCostCenter> save(DeptCostCenter deptCostCenter) {
        ExecuteResult<DeptCostCenter> executeResult = new ExecuteResult<DeptCostCenter>();
        try {


            if (deptCostCenter.getCompanyCode() != "" && deptCostCenter.getCompanyCode() != null
                    && deptCostCenter.getDeptCode() != "" && deptCostCenter.getDeptCode() != null
                    && deptCostCenter.getDeptName() != "" && deptCostCenter.getDeptName() != null
                    && deptCostCenter.getCostCenterCode() != "" && deptCostCenter.getCostCenterCode() != null
                    && deptCostCenter.getCostCenterName() != "" && deptCostCenter.getCostCenterName() != null) {
                deptCostCenterDAO.save(deptCostCenter);
                executeResult.setSuccessMessage("保存成功");
            } else {
                executeResult.addWarningMessage("存在必填项为空");
            }



        } catch (Exception e) {
            executeResult.addErrorMessage(e.getMessage());
            e.printStackTrace();
        }
        return executeResult;
    }

    @Override
    public ExecuteResult<List<DeptCostCenter>> saveBatch(List<DeptCostCenter> deptCostCenters) {
        ExecuteResult<List<DeptCostCenter>> executeResult = new ExecuteResult<List<DeptCostCenter>>();
        try {
            for (int i = 0; i < deptCostCenters.size(); i++) {

                DeptCostCenter dept = deptCostCenters.get(i);

//                if(!StringDataCas.notEmpty(dept.getCompanyCode())
//                        &&!StringDataCas.notEmpty(dept.getDeptCode())
//                        &&!StringDataCas.notEmpty(dept.getCostCenterCode())) {
                    deptCostCenterDAO.save(dept);
//                } else {
//                    executeResult.addWarningMessage("存在必填项为空");
//                    return executeResult;
//                }
            }
            executeResult.setSuccessMessage("保存成功");
        } catch (Exception e) {
            executeResult.addErrorMessage(e.getMessage());
            e.printStackTrace();
        }
        return executeResult;
    }

    @Override
    public ExecuteResult<DeptCostCenter> delete(List<DeptCostCenter> deptCostCenterList) {
        ExecuteResult<DeptCostCenter> executeResult = new ExecuteResult<DeptCostCenter>();
        try {
            for (DeptCostCenter deptCostCenter : deptCostCenterList) {
                deptCostCenterDAO.delete(deptCostCenter);
            }
        } catch (Exception e) {
            executeResult.addErrorMessage(e.getMessage());
            e.printStackTrace();
        }
        executeResult.setSuccessMessage("删除成功");
        return executeResult;
    }

    @Override
    public ExecuteResult<DeptCostCenter> update(DeptCostCenter deptCostCenter) {
        ExecuteResult<DeptCostCenter> executeResult = new ExecuteResult<DeptCostCenter>();

        try {
            if (deptCostCenter.getCompanyCode() != "" && deptCostCenter.getCompanyCode() != null
                    && deptCostCenter.getCompanyName() != "" && deptCostCenter.getCompanyName() != null
                    && deptCostCenter.getDeptCode() != "" && deptCostCenter.getDeptCode() != null
                    && deptCostCenter.getDeptName() != "" && deptCostCenter.getDeptName() != null
                    && deptCostCenter.getCostCenterCode() != "" && deptCostCenter.getCostCenterCode() != null
                    && deptCostCenter.getCostCenterName() != "" && deptCostCenter.getCostCenterName() != null
                    && deptCostCenter.getId() != null) {
                deptCostCenterDAO.update(deptCostCenter);
                executeResult.setSuccessMessage("修改成功");
            }
        } catch (Exception e) {
            executeResult.addWarningMessage("存在必填项为空");
            e.printStackTrace();
        }
        return executeResult;
    }

    @Override
    public Integer checkCostCenter(String costCenterCode) {
        return deptCostCenterDAO.checkCostCenter(costCenterCode);
    }

    @Override
    public Integer checkCompanyCode(String companyCode) {
        return deptCostCenterDAO.checkCompanyCode(companyCode);
    }

    @Override
    public Pager<DeptCostCenter> searchPagerList(Pager<DeptCostCenter> pager, DeptCostCenter deptCostCenter) {
        List<DeptCostCenter> records = deptCostCenterDAO.searchList(pager, deptCostCenter);
        Long total = deptCostCenterDAO.searchListCount(deptCostCenter);
        return Pager.cloneFromPager(pager, total, records);
    }

    @Override
    public DeptCostCenter get(DeptCostCenter deptCostCenter) {
        return deptCostCenterDAO.get(deptCostCenter);
    }

    @Override
    public List<DeptCostCenter> getList(DeptCostCenter deptCostCenter) {
        return deptCostCenterDAO.getList(deptCostCenter);
    }

    @Override
    public List<String> selectExistCodes(List<String> deptCodes) {
        return deptCostCenterDAO.selectExistCodes(deptCodes);
    }

    @Override
    public Integer checkDeptCostCenterByEntity(DeptCostCenter deptCostCenter) {
        return deptCostCenterDAO.checkDeptCostCenterByEntity(deptCostCenter);
    }

    @Override
    public String addOrUpdateDeptCostCenter(List<DeptCostCenter> addList, List<DeptCostCenter> updateList) {
        String result = "";
        try {
            if (null != addList && addList.size() > 0) {
                for (DeptCostCenter deptCostCenter : addList) {
                    deptCostCenterDAO.save(deptCostCenter);
                }
            }

            if (null != updateList && updateList.size() > 0) {
                for (DeptCostCenter deptCostCenter : updateList) {
                    deptCostCenterDAO.update(deptCostCenter);
                }
            }
        } catch (Exception e) {
            result = "预算体成本中心维护失败";
            LOGGER.error("Exception", e);
        }
        return result;
    }

    @Override
    public Integer selectExistCodes(DeptCostCenter deptCostCenter) {
        return deptCostCenterDAO.checkExisted(deptCostCenter);
    }
}
