package com.opple.fa.purchase.service.impl;

import java.util.List;

import com.opple.fa.purchase.entity.DemandOrderDetail;
import com.opple.fa.purchase.model.ApplyDemandMappingModel;
import lombok.Getter;
import lombok.Setter;

import com.opple.fa.purchase.dao.ApplyOrderDetailDAO;
import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.entity.ApplyOrderDetail;
import com.opple.fa.purchase.model.ApplyOrderModel;
import com.opple.fa.purchase.service.ApplyOrderDetailService;

public class ApplyOrderDetailServiceImpl implements ApplyOrderDetailService {
    @Getter
    @Setter
    private ApplyOrderDetailDAO applyOrderDetailDAO;

    @Override
    public List<ApplyOrderDetail> searchApplyOrderDetailByOrderId(ApplyOrder applyOrder) {

        return applyOrderDetailDAO.searchApplyOrderDetailByOrderId(applyOrder);
    }

    @Override
    public ApplyOrderDetail get(String strId) {
        // TODO Auto-generated method stub
        Long id = null;
        try {
            id = Long.parseLong(strId);
            return applyOrderDetailDAO.get(id);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ApplyOrderDetail> searchApplyOrderDetailList(ApplyOrderModel applyOrderModel) {

        return applyOrderDetailDAO.searchApplyOrderDetailList(applyOrderModel);
    }


    @Override
    public ApplyOrderDetail searchApplyOrderDetailById(ApplyOrderDetail applyOrderDetail) {

        return applyOrderDetailDAO.searchApplyOrderDetailById(applyOrderDetail);
    }

    @Override
    public void updateNextSecondaryCoding(List<ApplyOrderDetail> applyOrderDetailList) {
        for (ApplyOrderDetail applyOrderDetail : applyOrderDetailList) {

            applyOrderDetailDAO.updateNextSecondaryCoding(applyOrderDetail);
        }

    }

    @Override
    public void updateUnits(List<ApplyOrderDetail> applyOrderDetailList) {
        for (ApplyOrderDetail applyOrderDetail : applyOrderDetailList) {
            applyOrderDetailDAO.updateUnits(applyOrderDetail);
        }
    }

    @Override
    public DemandOrderDetail searchApplyDemandMapping(ApplyDemandMappingModel applyDemandMappingModel) {
        return applyOrderDetailDAO.searchApplyDemandMapping(applyDemandMappingModel);
    }

}
