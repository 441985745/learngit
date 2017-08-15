package com.opple.fa.purchase.service.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.opple.fa.purchase.entity.OutBudgetException;
import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import lombok.Getter;
import lombok.Setter;

import com.opple.fa.config.dao.AssetsBudgetDAO;
import com.opple.fa.config.entity.AssetsBudget;
import com.opple.fa.purchase.dao.DemandOrderDAO;
import com.opple.fa.purchase.dao.DemandOrderDetailDAO;
import com.opple.fa.purchase.entity.DemandOrder;
import com.opple.fa.purchase.entity.DemandOrderDetail;
import com.opple.fa.purchase.model.DemandOrderDetailModel;
import com.opple.fa.purchase.model.DemandOrderExamineWrapper;
import com.opple.fa.purchase.model.DemandOrderModel;
import com.opple.fa.purchase.service.DemandOrderService;
import com.opple.util.Env;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import com.opple.workflow.WorkFlowStub;

public class DemandOrderServiceImpl implements DemandOrderService {
    // private static final Logger LOGGER =
    // LoggerFactory.getLogger(DemandOrderServiceImpl.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(DemandOrderServiceImpl.class);
    @Getter
    @Setter
    private DemandOrderDAO demandOrderDAO;
    @Getter
    @Setter
    private DemandOrderDetailDAO demandOrderDetailDAO;
    @Getter
    @Setter
    private AssetsBudgetDAO assetsBudgetDAO;

    private final TransactionTemplate transactionTemplate;

    public DemandOrderServiceImpl(PlatformTransactionManager transactionManager) {
        Assert.notNull(transactionManager, "The 'transactionManager' argument must not be null.");
        transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Override
    public Pager<DemandOrder> searchDemandOrder(Pager<DemandOrder> pager, DemandOrderModel demandOrderModel) {
        List<DemandOrder> searchDemandOrder = demandOrderDAO.searchDemandOrder(pager, demandOrderModel);
        Long searchDemandOrderCount = demandOrderDAO.searchDemandOrderCount(demandOrderModel);
        return Pager.cloneFromPager(pager, searchDemandOrderCount, searchDemandOrder);
    }

    @Override
    public DemandOrder searchDemandOrderByOrderId(DemandOrder demandOrder) {

        return demandOrderDAO.searchDemandOrderByOrderId(demandOrder);
    }

    @Override
    public DemandOrderModel searchDemandOrderModelByOrderId(DemandOrderModel demandOrderModel) {

        return demandOrderDAO.searchDemandOrderModelByOrderId(demandOrderModel);
    }

    @Override
    public ExecuteResult<DemandOrder> saveDemandOrder(DemandOrder demandOrder) {
        ExecuteResult<DemandOrder> result = new ExecuteResult<DemandOrder>();

        // 根据订单ID查询该订单是否已存在
        DemandOrderModel dOrder = new DemandOrderModel();
        dOrder.setCdocument(demandOrder.getCdocument());
        DemandOrder searchDemandOrderByOrderId = searchDemandOrderByOrderId(dOrder);

        if (searchDemandOrderByOrderId != null) {
            result.addErrorMessage("新增订单失败,该订单号已存在！");
        } else {
            demandOrderDAO.save(demandOrder);
        }
        return result;
    }

    @Override
    public ExecuteResult<DemandOrder> addSaveDemand(DemandOrder demandOrder,
                                                    List<DemandOrderDetail> demandOrderDetail) throws OutBudgetException, RemoteException {
        ExecuteResult<DemandOrder> result = new ExecuteResult<DemandOrder>();
        boolean ifRollback = false;
        List<AssetsBudget> assetsBudgets = new ArrayList<AssetsBudget>();
        for (DemandOrderDetail dod : demandOrderDetail) {
            boolean flag = false;
            demandOrderDetailDAO.save(dod);
            if ("N".equals(demandOrder.getDraft())) {
                // 如果不是草稿 则统计预算
                if (assetsBudgets.size() == 0) {
                    AssetsBudget assetsBudget = new AssetsBudget();
                    assetsBudget.setProjectCode(dod.getProjectCode());
                    assetsBudget.setBudgetYear(dod.getBudgetYear());
                    assetsBudget.setUsedAmount(dod.getDemandCount());
                    assetsBudget.setUsedSumMoney(dod.getReferencePrice());
                    assetsBudgets.add(assetsBudget);
                } else {
                    for (int i = 0; i < assetsBudgets.size(); i++) {
                        if (flag) {
                            break;
                        }
                        // 如果相同，则累加
                        if (assetsBudgets.get(i).getProjectCode().equals(dod.getProjectCode())) {
                            assetsBudgets.get(i).setUsedAmount(
                                    assetsBudgets.get(i).getUsedAmount() + dod.getDemandCount());
                            assetsBudgets.get(i).setUsedSumMoney(
                                    assetsBudgets.get(i).getUsedSumMoney().add(dod.getReferencePrice()));
                            flag = true;
                            break;
                        }
                        // 最后一次，不相同，则新增
                        if (assetsBudgets.size() - 1 == i) {
                            if (!assetsBudgets.get(i).getProjectCode().equals(dod.getProjectCode())) {
                                AssetsBudget assetsBudget = new AssetsBudget();
                                assetsBudget.setProjectCode(dod.getProjectCode());
                                assetsBudget.setBudgetYear(dod.getBudgetYear());
                                assetsBudget.setUsedAmount(dod.getDemandCount());
                                assetsBudget.setUsedSumMoney(dod.getReferencePrice());
                                assetsBudgets.add(assetsBudget);
                                flag = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        // 如果不是草稿，回写预算信息
        if ("N".equals(demandOrder.getDraft())) {
            for (AssetsBudget assetsBudget : assetsBudgets) {
                assetsBudgetDAO.writeBack(assetsBudget);
            }
        }

        // 判断预算是否超出
        for (DemandOrderDetail dod : demandOrderDetail) {
            AssetsBudget assetsBudget = new AssetsBudget();
            assetsBudget.setProjectCode(dod.getProjectCode());
            assetsBudget.setBudgetYear(dod.getBudgetYear());
            AssetsBudget searchAssetsBudgetsByProjectCode = assetsBudgetDAO
                    .getAssetsBudgetbyProjectCodeAndYear(dod.getBudgetYear(), dod.getProjectCode());
            // 如果预算可用金额小于零 抛出异常
            if (searchAssetsBudgetsByProjectCode == null) {
                ifRollback = true;
                throw new OutBudgetException("项目编码:" + dod.getProjectCode() + " 无效或已被删除，请修改！");
            }
            if (searchAssetsBudgetsByProjectCode.getAvaliableSumMoney().compareTo(BigDecimal.ZERO) == -1) { //若可用预算小于0
                ifRollback = true;
                throw new OutBudgetException("项目编码:" + dod.getProjectCode() + " 预算超出，请修改！");
            }
        }


        if (ifRollback) {
            // 回滚后 删除表头信息
            demandOrderDAO.delDemandOrderByCdocument(demandOrder);
        } else {
            if ("Y".equals(demandOrder.getDraft())) {
                return result;
            }
            // 单据保存成功之后，绑定审批流
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
            WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();

            createNextFlowStepParam.setBillCode(demandOrder.getCdocument());
            createNextFlowStepParam.setBillTableName(Env.getProperty("demand.info.table"));
            createNextFlowStepParam.setBillCodeColumnName("CDOCUMENT");
            createNextFlowStepParam.setDeptCode(demandOrder.getBudgetDepartmentCode());
            createNextFlowStepParam.setDeptName(demandOrder.getBudgetDepartmentName());
            createNextFlowStepParam.setCreatorCode(demandOrder.getApplyUserCode());
            createNextFlowStepParam.setCreatorName(demandOrder.getApplyUser());
            Calendar instance = Calendar.getInstance();
            instance.setTime(demandOrder.getApplyDate());
            createNextFlowStepParam.setCreateDate(instance);
            createNextFlowStepParam.setMoney(demandOrder.getApplyBudgetMoney() + "");
            createNextFlowStepParam.setTypeCode(Env.getProperty("demand.info.type.id"));
            createNextFlowStepParam.setFeeItemCode("");
            createNextFlowStepParam.setIsEmail(demandOrder.getIsMail());
            createNextFlowStepParam.setIsPhoneMessage(demandOrder.getIsPhoneMessage());
            createNextFlowStepParam.setCApprovalId(UUID.randomUUID().toString());
            createNextFlowStepParam.setIdea(demandOrder.getIdea());
            // createNextFlowStepParam.setCCommitActivityId("");
            createNextFlowStepParam.setCCostCenterCode(demandOrder.getCostCenterCode());

            createNextFlowStep.setParam(createNextFlowStepParam);

            createNextFlowStep.setUserCode(demandOrder.getApplyUserCode());
            createNextFlowStep.setUserName(demandOrder.getApplyUser());
            WorkFlowStub.CreateNextFlowStepResponse response = stub.createNextFlowStep(createNextFlowStep);
            if (StringUtils.isNotBlank(response.getErrMessage())) {
                result.addErrorMessage(response.getErrMessage());
                LOGGER.error("====" + response.getErrMessage() + "====");
                throw new OutBudgetException(response.getErrMessage());
            }
        }
        return result;

    }
    /*@Override
    public ExecuteResult<DemandOrder> addSaveDemand(final DemandOrder demandOrder,
													final List<DemandOrderDetail> demandOrderDetail) {
		final ExecuteResult<DemandOrder> result = new ExecuteResult<DemandOrder>();

		return transactionTemplate.execute(new TransactionCallback<ExecuteResult<DemandOrder>>() {
			@Override
			public ExecuteResult<DemandOrder> doInTransaction(TransactionStatus transactionStatus) {
				boolean ifRollback = false;
				try {
					// demandOrderDAO.save(demandOrder);
          *//*
           * for (DemandOrderDetail dod : demandOrderDetail) {
           * demandOrderDetailDAO.save(dod); if
           * ("N".equals(demandOrder.getDraft())) { // 如果不是草稿 则回写预算
           * AssetsBudget assetsBudget = new AssetsBudget();
           * assetsBudget.setProjectCode(dod.getProjectCode());
           * assetsBudget.setUsedAmount(dod.getDemandCount());
           * assetsBudget.setUsedSumMoney(dod.getReferencePrice());
           * assetsBudgetDAO.writeBack(assetsBudget); } }
           *//*
                    List<AssetsBudget> assetsBudgets = new ArrayList<AssetsBudget>();
					for (DemandOrderDetail dod : demandOrderDetail) {
						boolean flag = false;
						demandOrderDetailDAO.save(dod);
						if ("N".equals(demandOrder.getDraft())) {
							// 如果不是草稿 则统计预算
							if (assetsBudgets.size() == 0) {
								AssetsBudget assetsBudget = new AssetsBudget();
								assetsBudget.setProjectCode(dod.getProjectCode());
								assetsBudget.setBudgetYear(dod.getBudgetYear());
								assetsBudget.setUsedAmount(dod.getDemandCount());
								assetsBudget.setUsedSumMoney(dod.getReferencePrice());
								assetsBudgets.add(assetsBudget);
							} else {
								for (int i = 0; i < assetsBudgets.size(); i++) {
									if (flag) {
										break;
									}
									// 如果相同，则累加
									if (assetsBudgets.get(i).getProjectCode().equals(dod.getProjectCode())) {
										assetsBudgets.get(i).setUsedAmount(
												assetsBudgets.get(i).getUsedAmount() + dod.getDemandCount());
										assetsBudgets.get(i).setUsedSumMoney(
												assetsBudgets.get(i).getUsedSumMoney().add(dod.getReferencePrice()));
										flag = true;
										break;
									}
									// 最后一次，不相同，则新增
									if (assetsBudgets.size() - 1 == i) {
										if (!assetsBudgets.get(i).getProjectCode().equals(dod.getProjectCode())) {
											AssetsBudget assetsBudget = new AssetsBudget();
											assetsBudget.setProjectCode(dod.getProjectCode());
											assetsBudget.setBudgetYear(dod.getBudgetYear());
											assetsBudget.setUsedAmount(dod.getDemandCount());
											assetsBudget.setUsedSumMoney(dod.getReferencePrice());
											assetsBudgets.add(assetsBudget);
											flag = true;
											break;
										}
									}
								}
							}
						}
					}
					// 如果不是草稿，回写预算信息
					if ("N".equals(demandOrder.getDraft())) {
						for (AssetsBudget assetsBudget : assetsBudgets) {
							assetsBudgetDAO.writeBack(assetsBudget);
						}
					}

					// 判断预算是否超出
					for (DemandOrderDetail dod : demandOrderDetail) {
						AssetsBudget assetsBudget = new AssetsBudget();
						assetsBudget.setProjectCode(dod.getProjectCode());
						assetsBudget.setBudgetYear(dod.getBudgetYear());
						AssetsBudget searchAssetsBudgetsByProjectCode = assetsBudgetDAO
								.getAssetsBudgetbyProjectCodeAndYear(dod.getBudgetYear(), dod.getProjectCode());
						// 如果预算可用金额小于零 抛出异常
						if (searchAssetsBudgetsByProjectCode == null) {
							ifRollback = true;
							result.addErrorMessage("项目编码:" + dod.getProjectCode() + " 无效或已被删除，请修改！");
							break;
						}
						if (searchAssetsBudgetsByProjectCode.getAvaliableSumMoney().compareTo(BigDecimal.ZERO) == -1) { //若可用预算小于0
							ifRollback = true;
							result.addErrorMessage("项目编码:" + dod.getProjectCode() + " 预算超出，请修改！");
							break;
						}
					}
				} catch (Exception e) {
					ifRollback = true;
					result.addErrorMessage("保存单据失败！");
					LOGGER.error("资产需求单据保存", e);
				}

				if (ifRollback) {
					transactionStatus.setRollbackOnly();
					// 回滚后 删除表头信息
					demandOrderDAO.delDemandOrderByCdocument(demandOrder);
				} else {
					if ("Y".equals(demandOrder.getDraft())) {
						return result;
					}
					// 单据保存成功之后，绑定审批流
					try {
						WorkFlowStub stub = new WorkFlowStub();
						WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
						WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();

						createNextFlowStepParam.setBillCode(demandOrder.getCdocument());
						createNextFlowStepParam.setBillTableName(Env.getProperty("demand.info.table"));
						createNextFlowStepParam.setBillCodeColumnName("CDOCUMENT");
						createNextFlowStepParam.setDeptCode(demandOrder.getBudgetDepartmentCode());
						createNextFlowStepParam.setDeptName(demandOrder.getBudgetDepartmentName());
						createNextFlowStepParam.setCreatorCode(demandOrder.getApplyUserCode());
						createNextFlowStepParam.setCreatorName(demandOrder.getApplyUser());
						Calendar instance = Calendar.getInstance();
						instance.setTime(demandOrder.getApplyDate());
						createNextFlowStepParam.setCreateDate(instance);
						createNextFlowStepParam.setMoney(demandOrder.getApplyBudgetMoney() + "");
						createNextFlowStepParam.setTypeCode(Env.getProperty("demand.info.type.id"));
						createNextFlowStepParam.setFeeItemCode("");
						createNextFlowStepParam.setIsEmail(demandOrder.getIsMail());
						createNextFlowStepParam.setIsPhoneMessage(demandOrder.getIsPhoneMessage());
						createNextFlowStepParam.setCApprovalId(UUID.randomUUID().toString());
						createNextFlowStepParam.setIdea(demandOrder.getIdea());
						// createNextFlowStepParam.setCCommitActivityId("");
						createNextFlowStepParam.setCCostCenterCode(demandOrder.getCostCenterCode());

						createNextFlowStep.setParam(createNextFlowStepParam);

						createNextFlowStep.setUserCode(demandOrder.getApplyUserCode());
						createNextFlowStep.setUserName(demandOrder.getApplyUser());
						WorkFlowStub.CreateNextFlowStepResponse response = stub.createNextFlowStep(createNextFlowStep);
						if (StringUtils.isNotBlank(response.getErrMessage())) {
							ifRollback = true;
							result.addErrorMessage(response.getErrMessage());
							LOGGER.error("====" + response.getErrMessage() + "====");
						}
					} catch (AxisFault axisFault) {
						ifRollback = true;
						result.addErrorMessage("调用审批流失败:" + axisFault);
						LOGGER.error("axisFault", axisFault);
					} catch (RemoteException e) {
						ifRollback = true;
						result.addErrorMessage("调用审批流失败:" + e);
						LOGGER.error("RemoteException", e);
					} catch (Exception e) {
						ifRollback = true;
						result.addErrorMessage("调用审批流失败:" + e);
						LOGGER.error("Exception", e);
					}
					if (ifRollback) {
						transactionStatus.setRollbackOnly();
					}
				}
				return result;
			}
		});
	}*/

    @Override
    public ExecuteResult<DemandOrder> delDemandAndWorkFlow(DemandOrder demandOrder) {
        ExecuteResult<DemandOrder> result = new ExecuteResult<DemandOrder>();

        // 删除表头信息
        demandOrderDAO.delDemandOrderByCdocument(demandOrder);
        return result;
    }

    @Override
    public int searchNextSequence() {
        return demandOrderDAO.searchNextSequence();
    }

    @Override
    public List<DemandOrder> exportDemandOrder(DemandOrderModel demandOrderModel) {
        List<DemandOrder> demandlist = demandOrderDAO.exportDemandOrder(demandOrderModel);
        return demandlist;
    }


    @Override
    public ExecuteResult<DemandOrder> addSaveUpdateDemand(DemandOrder demandOrder,
                                                          List<DemandOrderDetail> demandOrderDetail, DemandOrder oldDemandOrder) throws OutBudgetException, RemoteException {
        ExecuteResult<DemandOrder> result = new ExecuteResult<DemandOrder>();
        boolean ifRollback = false;
        // 根据订单ID查询该订单之前是不是草稿
        DemandOrderModel dOrder = new DemandOrderModel();
        dOrder.setCdocument(demandOrder.getCdocument());
        // DemandOrder searchDemandOrderByOrderId =
        // searchDemandOrderByOrderId(dOrder);
        // 之前状态是否是草稿
        String oldDraft = oldDemandOrder.getDraft();

        // 修改前的订单明细
        List<DemandOrderDetailModel> demandOrderDetailModels = demandOrderDetailDAO
                .searchDemandOrderDetailByOrderId(dOrder);

        // 回写预算信息(释放) 修改前不是草稿
        if ("N".equals(oldDraft)) {
            for (DemandOrderDetailModel dod : demandOrderDetailModels) {
                // 回写预算 如果询价总价不为0，则释放询价总价(因为审核时，回写的是询价总价)
                AssetsBudget assetsBudget = new AssetsBudget();
                assetsBudget.setProjectCode(dod.getProjectCode());
                assetsBudget.setBudgetYear(dod.getBudgetYear());
                dod.setAllocationCount(dod.getAllocationCount() == null ? 0 : dod.getAllocationCount());
                BigDecimal sumMoney = new BigDecimal("0");
                // 如果询价总价不是Null，则释放询价总价(因为审核时，回写的是询价总价)
                if (dod.getInquiryPrice() != null) { //如果是审批退回的  审批时单价*（全部数量-调拨数量）即notapplycount
                    sumMoney = sumMoney.subtract(dod.getInquiryUnitPrice().multiply(BigDecimal.valueOf(dod.getNotApplyCount())));
                } else {
                    // 否则释放参考总价
                    sumMoney = sumMoney.subtract(dod.getReferencePrice());
                }
                assetsBudget.setUsedAmount(0 - (dod.getDemandCount() - dod.getAllocationCount()));
                assetsBudget.setUsedSumMoney(sumMoney);
                assetsBudgetDAO.writeBack(assetsBudget);
            }
        }

        // 删除 表单明细
        for (DemandOrderDetailModel dod : demandOrderDetailModels) {
            demandOrderDetailDAO.deleteOrderDetailById(dod);
        }

        // 此次不是草稿 回写预算
        BigDecimal sumMoney = new BigDecimal("0");
        if ("N".equals(demandOrder.getDraft())) {
            for (DemandOrderDetail dod : demandOrderDetail) {
                AssetsBudget assetsBudget = new AssetsBudget();
                assetsBudget.setProjectCode(dod.getProjectCode());
                assetsBudget.setBudgetYear(dod.getBudgetYear());
                assetsBudget.setUsedAmount(dod.getDemandCount() - dod.getAllocationCount());
                assetsBudget.setUsedSumMoney(dod.getInquiryUnitPrice().multiply(BigDecimal.valueOf(dod.getDemandCount() - dod.getAllocationCount())));
                assetsBudgetDAO.writeBack(assetsBudget);
                sumMoney = sumMoney.add(assetsBudget.getUsedSumMoney());
            }
        }

        // 保存表单明细
        for (DemandOrderDetail dod : demandOrderDetail) {
            demandOrderDetailDAO.save(dod);
        }

        // 判断预算是否超出
        for (DemandOrderDetail dod : demandOrderDetail) {
            AssetsBudget assetsBudget = new AssetsBudget();
            assetsBudget.setProjectCode(dod.getProjectCode());
            assetsBudget.setBudgetYear(dod.getBudgetYear());
            AssetsBudget searchAssetsBudgetsByProjectCode = assetsBudgetDAO
                    .getAssetsBudgetbyProjectCodeAndYear(dod.getBudgetYear(), dod.getProjectCode());
            // 如果预算可用金额小于零 抛出异常
            if (searchAssetsBudgetsByProjectCode == null) {
                ifRollback = true;
                throw new OutBudgetException("项目编码:" + dod.getProjectCode() + " 无效或已被删除，请修改！");
            }
            if (searchAssetsBudgetsByProjectCode.getAvaliableSumMoney().compareTo(BigDecimal.ZERO) == -1) {
                ifRollback = true;
                throw new OutBudgetException("项目编码:" + dod.getProjectCode() + " 预算超出，请修改！");
            }
        }
        if (ifRollback) {
            return result;
        }
        // 修改表头信息
        // demandOrderDAO.update(demandOrder);

        if ("Y".equals(demandOrder.getDraft())) {
            return result;
        }
        // 单据保存成功之后，绑定审批流
        WorkFlowStub stub = new WorkFlowStub();
        WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
        WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();

        createNextFlowStepParam.setBillCode(demandOrder.getCdocument());
        createNextFlowStepParam.setBillTableName(Env.getProperty("demand.info.table"));
        createNextFlowStepParam.setBillCodeColumnName("CDOCUMENT");
        createNextFlowStepParam.setDeptCode(demandOrder.getBudgetDepartmentCode());
        createNextFlowStepParam.setDeptName(demandOrder.getBudgetDepartmentName());
        createNextFlowStepParam.setCreatorCode(demandOrder.getApplyUserCode());
        createNextFlowStepParam.setCreatorName(demandOrder.getApplyUser());
        Calendar instance = Calendar.getInstance();
        instance.setTime(demandOrder.getApplyDate());
        createNextFlowStepParam.setCreateDate(instance);
        createNextFlowStepParam.setMoney(sumMoney + "");
        createNextFlowStepParam.setTypeCode(Env.getProperty("demand.info.type.id"));
        createNextFlowStepParam.setFeeItemCode("");
        createNextFlowStepParam.setIsEmail(demandOrder.getIsMail());
        createNextFlowStepParam.setIsPhoneMessage(demandOrder.getIsPhoneMessage());
        createNextFlowStepParam.setCApprovalId(UUID.randomUUID().toString());
        createNextFlowStepParam.setIdea(demandOrder.getIdea());
        createNextFlowStepParam.setCCommitActivityId(demandOrder.getActivityId());
        createNextFlowStepParam.setCCostCenterCode(demandOrder.getCostCenterCode());

        createNextFlowStep.setParam(createNextFlowStepParam);

        createNextFlowStep.setUserCode(demandOrder.getApplyUserCode());
        createNextFlowStep.setUserName(demandOrder.getApplyUser());
        WorkFlowStub.CreateNextFlowStepResponse response = stub.createNextFlowStep(createNextFlowStep);
        if (StringUtils.isNotBlank(response.getErrMessage())) {
            ifRollback = true;
            result.addErrorMessage(response.getErrMessage());
            LOGGER.error("====" + response.getErrMessage() + "====");
            throw new OutBudgetException(response.getErrMessage());
        }
        return result;
    }




    /*@Override
    public ExecuteResult<DemandOrder> addSaveUpdateDemand(final DemandOrder demandOrder,
                                                          final List<DemandOrderDetail> demandOrderDetail, final DemandOrder oldDemandOrder) {
        final ExecuteResult<DemandOrder> result = new ExecuteResult<DemandOrder>();

        return transactionTemplate.execute(new TransactionCallback<ExecuteResult<DemandOrder>>() {
            @Override
            public ExecuteResult<DemandOrder> doInTransaction(TransactionStatus transactionStatus) {
                boolean ifRollback = false;
                try {
                    // 根据订单ID查询该订单之前是不是草稿
                    DemandOrderModel dOrder = new DemandOrderModel();
                    dOrder.setCdocument(demandOrder.getCdocument());
                    // DemandOrder searchDemandOrderByOrderId =
                    // searchDemandOrderByOrderId(dOrder);
                    // 之前状态是否是草稿
                    String oldDraft = oldDemandOrder.getDraft();

                    // 修改前的订单明细
                    List<DemandOrderDetailModel> demandOrderDetailModels = demandOrderDetailDAO
                            .searchDemandOrderDetailByOrderId(dOrder);

                    // 回写预算信息(释放) 修改前不是草稿
                    if ("N".equals(oldDraft)) {
                        for (DemandOrderDetailModel dod : demandOrderDetailModels) {
                            // 回写预算 如果询价总价不为0，则释放询价总价(因为审核时，回写的是询价总价)
                            AssetsBudget assetsBudget = new AssetsBudget();
                            assetsBudget.setProjectCode(dod.getProjectCode());
                            assetsBudget.setBudgetYear(dod.getBudgetYear());
                            dod.setAllocationCount(dod.getAllocationCount() == null ? 0 : dod.getAllocationCount());
                            BigDecimal sumMoney = new BigDecimal("0");
                            // 如果询价总价不是Null，则释放询价总价(因为审核时，回写的是询价总价)
                            if (dod.getInquiryPrice() != null) { //如果是审批退回的  审批时单价*（全部数量-调拨数量）即notapplycount
                                sumMoney = sumMoney.subtract(dod.getInquiryUnitPrice().multiply(BigDecimal.valueOf(dod.getNotApplyCount())));
                            } else {
                                // 否则释放参考总价
                                sumMoney = sumMoney.subtract(dod.getReferencePrice());
                            }
                            assetsBudget.setUsedAmount(0 - (dod.getDemandCount() - dod.getAllocationCount()));
                            assetsBudget.setUsedSumMoney(sumMoney);
                            assetsBudgetDAO.writeBack(assetsBudget);
                        }
                    }

                    // 删除 表单明细
                    for (DemandOrderDetailModel dod : demandOrderDetailModels) {
                        demandOrderDetailDAO.deleteOrderDetailById(dod);
                    }

                    // 此次不是草稿 回写预算
                    BigDecimal sumMoney = new BigDecimal("0");
                    if ("N".equals(demandOrder.getDraft())) {
                        for (DemandOrderDetail dod : demandOrderDetail) {
                            AssetsBudget assetsBudget = new AssetsBudget();
                            assetsBudget.setProjectCode(dod.getProjectCode());
                            assetsBudget.setBudgetYear(dod.getBudgetYear());
                            assetsBudget.setUsedAmount(dod.getDemandCount() - dod.getAllocationCount());
                            assetsBudget.setUsedSumMoney(dod.getInquiryUnitPrice().multiply(BigDecimal.valueOf(dod.getDemandCount() - dod.getAllocationCount())));
                            assetsBudgetDAO.writeBack(assetsBudget);
                            sumMoney = sumMoney.add(assetsBudget.getUsedSumMoney());
                        }
                    }

                    // 保存表单明细
                    for (DemandOrderDetail dod : demandOrderDetail) {
                        demandOrderDetailDAO.save(dod);
                    }

                    // 判断预算是否超出
                    for (DemandOrderDetail dod : demandOrderDetail) {
                        AssetsBudget assetsBudget = new AssetsBudget();
                        assetsBudget.setProjectCode(dod.getProjectCode());
                        assetsBudget.setBudgetYear(dod.getBudgetYear());
                        AssetsBudget searchAssetsBudgetsByProjectCode = assetsBudgetDAO
                                .getAssetsBudgetbyProjectCodeAndYear(dod.getBudgetYear(), dod.getProjectCode());
                        // 如果预算可用金额小于零 抛出异常
                        if (searchAssetsBudgetsByProjectCode == null) {
                            ifRollback = true;
                            result.addErrorMessage("项目编码:" + dod.getProjectCode() + " 无效或已被删除，请修改！");
                            break;
                        }
                        if (searchAssetsBudgetsByProjectCode.getAvaliableSumMoney().compareTo(BigDecimal.ZERO) == -1) {
                            ifRollback = true;
                            result.addErrorMessage("项目编码:" + dod.getProjectCode() + " 预算超出，请修改！");
                            break;
                        }
                    }
                    if (ifRollback) {
                        transactionStatus.setRollbackOnly();
                        return result;
                    }
                    // 修改表头信息
                    // demandOrderDAO.update(demandOrder);

                    if ("Y".equals(demandOrder.getDraft())) {
                        return result;
                    }
                    // 单据保存成功之后，绑定审批流
                    try {
                        WorkFlowStub stub = new WorkFlowStub();
                        WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
                        WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();

                        createNextFlowStepParam.setBillCode(demandOrder.getCdocument());
                        createNextFlowStepParam.setBillTableName(Env.getProperty("demand.info.table"));
                        createNextFlowStepParam.setBillCodeColumnName("CDOCUMENT");
                        createNextFlowStepParam.setDeptCode(demandOrder.getBudgetDepartmentCode());
                        createNextFlowStepParam.setDeptName(demandOrder.getBudgetDepartmentName());
                        createNextFlowStepParam.setCreatorCode(demandOrder.getApplyUserCode());
                        createNextFlowStepParam.setCreatorName(demandOrder.getApplyUser());
                        Calendar instance = Calendar.getInstance();
                        instance.setTime(demandOrder.getApplyDate());
                        createNextFlowStepParam.setCreateDate(instance);
                        createNextFlowStepParam.setMoney(sumMoney + "");
                        createNextFlowStepParam.setTypeCode(Env.getProperty("demand.info.type.id"));
                        createNextFlowStepParam.setFeeItemCode("");
                        createNextFlowStepParam.setIsEmail(demandOrder.getIsMail());
                        createNextFlowStepParam.setIsPhoneMessage(demandOrder.getIsPhoneMessage());
                        createNextFlowStepParam.setCApprovalId(UUID.randomUUID().toString());
                        createNextFlowStepParam.setIdea(demandOrder.getIdea());
                        createNextFlowStepParam.setCCommitActivityId(demandOrder.getActivityId());
                        createNextFlowStepParam.setCCostCenterCode(demandOrder.getCostCenterCode());

                        createNextFlowStep.setParam(createNextFlowStepParam);

                        createNextFlowStep.setUserCode(demandOrder.getApplyUserCode());
                        createNextFlowStep.setUserName(demandOrder.getApplyUser());
                        WorkFlowStub.CreateNextFlowStepResponse response = stub.createNextFlowStep(createNextFlowStep);
                        if (StringUtils.isNotBlank(response.getErrMessage())) {
                            ifRollback = true;
                            result.addErrorMessage(response.getErrMessage());
                            LOGGER.error("====" + response.getErrMessage() + "====");
                        }
                    } catch (AxisFault axisFault) {
                        ifRollback = true;
                        result.addErrorMessage("调用审批流失败:" + axisFault);
                        LOGGER.error("axisFault", axisFault);
                    } catch (RemoteException e) {
                        ifRollback = true;
                        result.addErrorMessage("调用审批流失败:" + e);
                        LOGGER.error("RemoteException", e);
                    } catch (Exception e) {
                        ifRollback = true;
                        result.addErrorMessage("调用审批流失败:" + e);
                        LOGGER.error("Exception", e);
                    }
                    if (ifRollback) {
                        transactionStatus.setRollbackOnly();
                    }
                } catch (Exception e) {
                    ifRollback = true;
                    result.addErrorMessage("保存单据失败！:" + e);
                    LOGGER.error("资产需求单据保存", e);
                }
                if (ifRollback) {
                    transactionStatus.setRollbackOnly();
                }
                return result;

            }
        });
    }*/

    @Override
    public DemandOrder searchAttachDepartByOrder(DemandOrder demandOrder) {
        return demandOrderDAO.searchAttachDepartByOrder(demandOrder);
    }

    @Override
    public ExecuteResult<DemandOrder> agreeWorkFlow(final DemandOrderExamineWrapper demandOrderExamineWrapper,
                                                    final boolean isPurchasingManager) {
        final ExecuteResult<DemandOrder> executeResult = new ExecuteResult<DemandOrder>();
        return transactionTemplate.execute(new TransactionCallback<ExecuteResult<DemandOrder>>() {
            @Override
            public ExecuteResult<DemandOrder> doInTransaction(TransactionStatus transactionStatus) {

                DemandOrderModel demandOrderModel = demandOrderExamineWrapper.getDemandOrderModel();
                List<DemandOrderDetail> demandOrderDetails = demandOrderExamineWrapper.getDemandOrderDetails();
                DemandOrderDetailModel orderDetail = new DemandOrderDetailModel();
                boolean ifRollback = false;
                // demandOrderDAO.updateExamine(demandOrderModel);
                BigDecimal sum = new BigDecimal("0");
                if (demandOrderDetails != null) {
                    if (demandOrderDetails.get(0).getAssetClassification() != null) {
                        for (DemandOrderDetail demandOrderDetail : demandOrderDetails) {
                            demandOrderDetailDAO.updateExamine(demandOrderDetail);
                        }
                    }
                    if (demandOrderDetails.get(0).getInquiryPrice() != null) {
                        // 回写预算
                        for (DemandOrderDetail dod : demandOrderDetails) {
                            orderDetail = demandOrderDetailDAO.searchDemandOrderDetailById(dod);
                            if (orderDetail.getAllocationCount() == null) {
                                orderDetail.setAllocationCount(0);
                            }
                            AssetsBudget assetsBudget = new AssetsBudget();
                            assetsBudget.setProjectCode(dod.getProjectCode());
                            assetsBudget.setUsedAmount(dod.getDemandCount());
                            assetsBudget.setBudgetYear(dod.getBudgetYear());
//              DemandOrderDetailModel searchDemandOrderDetailById = demandOrderDetailDAO
//                  .searchDemandOrderDetailById(dod);
                            //当前单价*（需求总量-当前调拨数量）-之前单价*（需求总量-之前调拨数量）
                            assetsBudget.setUsedSumMoney(dod.getInquiryUnitPrice().
                                    multiply(BigDecimal.valueOf(orderDetail.getDemandCount() - dod.getAllocationCount())).
                                    subtract(orderDetail.getInquiryUnitPrice().
                                            multiply(BigDecimal.valueOf(orderDetail.getDemandCount() - orderDetail.getAllocationCount()))));
                            sum = sum.add(dod.getInquiryUnitPrice().
                                    multiply(BigDecimal.valueOf(orderDetail.getDemandCount() - dod.getAllocationCount())));

                            // 如果有调拨数量
              /*if ("Y".equals(dod.getIsAllocationCount())) {
                assetsBudget.setUsedSumMoney(
                    BigDecimal.ZERO.subtract(new BigDecimal(dod.getAllocationCount()))
                        .multiply(dod.getInquiryUnitPrice()));
                // assetsBudget.setUsedSumMoney(BigDecimal.ZERO.subtract(new
                // BigDecimal(dod.getDemandCount()-dod.getAllocationCount())).multiply(dod.getInquiryUnitPrice()));

                // 释放调拨预算

              } else {
                assetsBudget.setUsedSumMoney(dod.getInquiryPrice()
                    .subtract(searchDemandOrderDetailById.getReferencePrice()));
              }*/
                            AssetsBudget ab = assetsBudgetDAO.getAssetsBudgetbyProjectCodeAndYear(assetsBudget.getBudgetYear(), assetsBudget.getProjectCode());
                            if (ab.getAvaliableSumMoney().compareTo(assetsBudget.getUsedSumMoney()) == -1) {
                                executeResult.addErrorMessage("超出预算");
                                LOGGER.error("====超出预算====");
                                return executeResult;
                            }
                            assetsBudgetDAO.writeBack(assetsBudget);
                        }
//						demandOrderModel.setIamoney(sum);
//						demandOrderDAO.updateExamine(demandOrderModel);//当前单价*（需求总量-当前调拨数量）更新到iamoney中，controller中王眩光代码有问题，此处覆盖
            /*for (DemandOrderDetail demandOrderDetail : demandOrderDetails) {
              if (isPurchasingManager) {
                demandOrderDetailDAO.updateExamine1(demandOrderDetail);
              }
            }*/


                    }
                }

                try {
                    WorkFlowStub stub = new WorkFlowStub();

          /*
           * Options options = stub._getServiceClient().getOptions();
           * options.setTimeOutInMilliSeconds(120000);//设置超时(单位是毫秒)
           * stub._getServiceClient().setOptions(options);
           */

                    WorkFlowStub.Agree agree = new WorkFlowStub.Agree();
                    WorkFlowStub.AgreeFlowParam agreeFlowParam = new WorkFlowStub.AgreeFlowParam();

                    agreeFlowParam.setBillCode(demandOrderModel.getCdocument());
                    agreeFlowParam.setBillTableName(Env.getProperty("demand.info.table"));
                    agreeFlowParam.setBillCodeColumnName("CDOCUMENT");
                    agreeFlowParam.setCApprovalId(UUID.randomUUID().toString());
                    agreeFlowParam.setDeptCode(demandOrderModel.getBudgetDepartmentCode());
                    agreeFlowParam.setDeptName(demandOrderModel.getBudgetDepartmentName());
                    agreeFlowParam.setCreatorCode(demandOrderModel.getApplyUserCode());
                    agreeFlowParam.setCreatorName(demandOrderModel.getApplyUser());
          /*
           * if (null == demandOrderModel.getCreateDate()) {
           * executeResult.addErrorMessage("创建日期不能为空"); return
           * executeResult; }
           */
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(demandOrderModel.getCreateDate());
                    agreeFlowParam.setCreateDate(instance);
                    agreeFlowParam.setCUserCode(demandOrderModel.getLoginUserCode());
                    agreeFlowParam.setCUserName(demandOrderModel.getLoginUserName());
                    agreeFlowParam.setUserDepCode(demandOrderModel.getUserDepCode());

                    agreeFlowParam.setAMoney(demandOrderModel.getIamoney() + "");
                    agreeFlowParam.setTypeCode(Env.getProperty("demand.info.type.id"));
                    agreeFlowParam.setMoney(sum + "");
                    agreeFlowParam.setIdea(demandOrderModel.getIdea());
                    agreeFlowParam.setIsEmail(demandOrderModel.getIsMail());
                    agreeFlowParam.setIsPhoneMessage(demandOrderModel.getIsPhoneMessage());
                    agreeFlowParam.setFeeItemCode("");
                    agreeFlowParam.setCCostCenterCode(demandOrderModel.getCostCenterCode());
                    agreeFlowParam.setCCommitActivityId(demandOrderModel.getActivityId());

                    agree.setUserCode(demandOrderModel.getLoginUserCode());
                    agree.setUserName(demandOrderModel.getLoginUserName());
                    agree.setParam(agreeFlowParam);

                    WorkFlowStub.AgreeResponse agreeResponse = stub.agree(agree);

                    if (StringUtils.isNotBlank(agreeResponse.getErrMessage())) {
                        ifRollback = true;
                        executeResult.addErrorMessage(agreeResponse.getErrMessage());
                        LOGGER.error("====" + agreeResponse.getErrMessage() + "====");
                        return executeResult;
                    }
                } catch (AxisFault axisFault) {
                    ifRollback = true;
                    executeResult.addErrorMessage("调用审批流失败:" + axisFault);
                    LOGGER.error("AxisFault", axisFault);
                } catch (RemoteException e) {
                    ifRollback = true;
                    executeResult.addErrorMessage("调用审批流失败:" + e);
                    LOGGER.error("RemoteException", e);
                } catch (Exception e) {
                    ifRollback = true;
                    executeResult.addErrorMessage("调用审批流失败:" + e);
                    LOGGER.error("Exception", e);
                }

                if (ifRollback) {
                    transactionStatus.setRollbackOnly();
                    return executeResult;
                }
                executeResult.setSuccessMessage("调用同意审批流成功");
                return executeResult;
            }
        });
    }

    @Override
    public ExecuteResult<DemandOrder> denyWorkFlow(DemandOrderModel demandOrderModel) {
        ExecuteResult<DemandOrder> executeResult = new ExecuteResult<DemandOrder>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();

            WorkFlowStub.Back back = new WorkFlowStub.Back();
            WorkFlowStub.BackFlowParam backFlowParam = new WorkFlowStub.BackFlowParam();

            backFlowParam.setBillCode(demandOrderModel.getCdocument());
            backFlowParam.setBillTableName(Env.getProperty("demand.info.table"));
            backFlowParam.setCreatorCode(demandOrderModel.getApplyUserCode());
            backFlowParam.setCreatorName(demandOrderModel.getApplyUser());
            backFlowParam.setIdea(demandOrderModel.getIdea());
            backFlowParam.setCUserCode(demandOrderModel.getLoginUserCode());
            backFlowParam.setCUserName(demandOrderModel.getLoginUserName());
            backFlowParam.setUserDepCode(demandOrderModel.getUserDepCode());
            backFlowParam.setTypeCode(Env.getProperty("demand.info.type.id"));
            backFlowParam.setActivityCode(demandOrderModel.getActivityId());
            backFlowParam.setCApprovalId(UUID.randomUUID().toString());

            backFlowParam.setIsEmail(demandOrderModel.getIsMail());
            backFlowParam.setIsPhoneMessage(demandOrderModel.getIsPhoneMessage());

            back.setParam(backFlowParam);

            back.setUserCode(demandOrderModel.getLoginUserCode());
            back.setUserName(demandOrderModel.getLoginUserName());

            WorkFlowStub.BackResponse backResponse = stub.back(back);
            if (StringUtils.isNotBlank(backResponse.getErrMessage())) {
                ifRollback = true;
                executeResult.addErrorMessage(backResponse.getErrMessage());
                LOGGER.error("====" + backResponse.getErrMessage() + "====");
                return executeResult;
            }

        } catch (AxisFault axisFault) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + axisFault);
            LOGGER.error("AxisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + e);
            LOGGER.error("RemoteException", e);
        } catch (Exception e) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + e);
            LOGGER.error("Exception", e);
        }

        if (ifRollback) {
            return executeResult;
        }
        executeResult.setSuccessMessage("调用退回审批流成功");
        return executeResult;
    }

    @Override
    public ExecuteResult<DemandOrder> dropWorkFlow(DemandOrderModel demandOrderModel) {
        ExecuteResult<DemandOrder> executeResult = new ExecuteResult<DemandOrder>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.UnApproval unApproval = new WorkFlowStub.UnApproval();

            WorkFlowStub.UnApprovalFlowParam unApprovalFlowParam = new WorkFlowStub.UnApprovalFlowParam();
            unApprovalFlowParam.setBillCode(demandOrderModel.getCdocument());
            unApprovalFlowParam.setBillTableName(Env.getProperty("demand.info.table"));
            unApprovalFlowParam.setBillCodeColumnName("CDOCUMENT");
            unApprovalFlowParam.setCreatorCode(demandOrderModel.getApplyUserCode());
            unApprovalFlowParam.setCreatorName(demandOrderModel.getApplyUser());
            unApprovalFlowParam.setIdea(demandOrderModel.getIdea());
            unApprovalFlowParam.setCUserCode(demandOrderModel.getLoginUserCode());
            unApprovalFlowParam.setCUserName(demandOrderModel.getLoginUserName());
            unApprovalFlowParam.setUserDepCode(demandOrderModel.getUserDepCode());
            unApprovalFlowParam.setTypeCode(Env.getProperty("demand.info.type.id"));

            unApproval.setParam(unApprovalFlowParam);

            unApproval.setUserCode(demandOrderModel.getLoginUserCode());
            unApproval.setUserName(demandOrderModel.getLoginUserName());

            WorkFlowStub.UnApprovalResponse unApprovalResponse = stub.unApproval(unApproval);

            if (StringUtils.isNotBlank(unApprovalResponse.getErrMessage())) {
                executeResult.addErrorMessage(unApprovalResponse.getErrMessage());
                return executeResult;
            }

        } catch (AxisFault axisFault) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + axisFault);
            LOGGER.error("AxisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + e);
            LOGGER.error("RemoteException", e);
        } catch (Exception e) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + e);
            LOGGER.error("Exception", e);
        }

        if (ifRollback) {
            return executeResult;
        }

        executeResult.setSuccessMessage("调用弃审审批流成功");

        return executeResult;
    }

    @Override
    public void updateExamine(DemandOrder demandOrder) {
        demandOrderDAO.updateExamine(demandOrder);
    }

    @Override
    public void update(DemandOrder demandOrder) {
        demandOrderDAO.update(demandOrder);
    }

    @Override
    public ExecuteResult<DemandOrder> deleteDemandOrder(DemandOrder demandOrder,
                                                        List<DemandOrderDetailModel> demandOrderDetails) throws OutBudgetException {
        ExecuteResult<DemandOrder> result = new ExecuteResult<DemandOrder>();
    /*for (DemandOrderDetailModel demandOrderDetailModel : demandOrderDetails) {

    }*/
        // 回写预算信息(释放) 单据不是草稿
        if ("N".equals(demandOrder.getDraft())) {
            for (DemandOrderDetailModel dod : demandOrderDetails) {
                // 回写预算 如果询价总价不为0，则释放询价总价(因为审核时，回写的是询价总价)
                AssetsBudget assetsBudget = new AssetsBudget();
                assetsBudget.setProjectCode(dod.getProjectCode());
                assetsBudget.setBudgetYear(dod.getBudgetYear());

                BigDecimal sumMoney = new BigDecimal("0");
                // 如果询价总价不是Null，则释放询价总价(因为审核时，回写的是询价总价)  //释放iamoney也对，这里如果测试错误，就改为iamoney
                if (dod.getInquiryPrice() != null) {
//					sumMoney = sumMoney.subtract(dod.getInquiryPrice().subtract(dod.getInquiryUnitPrice().multiply(BigDecimal.valueOf(dod.getAllocationCount()))));
                    sumMoney = sumMoney.subtract(dod.getInquiryPrice());
                } else {
                    // 否则释放参考总价
                    sumMoney = sumMoney.subtract(dod.getReferencePrice());
                }
                assetsBudget.setUsedAmount(0 - dod.getDemandCount());
                assetsBudget.setUsedSumMoney(sumMoney);
                assetsBudgetDAO.writeBack(assetsBudget);
                AssetsBudget searchAssetsBudgetsByProjectCode = assetsBudgetDAO
                        .getAssetsBudgetbyProjectCodeAndYear(dod.getBudgetYear(), dod.getProjectCode());
                if (searchAssetsBudgetsByProjectCode == null) {
                    throw new OutBudgetException("项目编码:" + dod.getProjectCode() + " 无效或已被删除，请修改！");
                }
                if (searchAssetsBudgetsByProjectCode.getAvaliableSumMoney().compareTo(BigDecimal.ZERO) == -1) {
                    throw new OutBudgetException("项目编码:" + dod.getProjectCode() + " 预算超出，不可删除！");
                }
            }
        }

        // 删除 表单明细
        for (DemandOrderDetailModel dod : demandOrderDetails) {
            DemandOrderDetail dod1 = (DemandOrderDetail) demandOrderDetailDAO.searchDemandOrderDetailById(dod);
            demandOrderDetailDAO.saveDelete(dod1); //删除前将数据保存到备份表中
            demandOrderDetailDAO.deleteOrderDetailById(dod);
        }
        DemandOrder do1 = demandOrderDAO.searchDemandOrderByOrderId(demandOrder);
        demandOrderDAO.saveDelete(do1);
        // 删除表头信息
        demandOrderDAO.delDemandOrderByCdocument(demandOrder);
        return result;
    }

    @Override
    public ExecuteResult<DemandOrder> updateCloseDemandOrder(DemandOrder demandOrder,
                                                             List<DemandOrderDetailModel> demandOrderDetails) {
        ExecuteResult<DemandOrder> result = new ExecuteResult<DemandOrder>();
        // 回写剩余数量的预算信息(释放) 单据不是草稿
        if ("N".equals(demandOrder.getDraft())) {
            for (DemandOrderDetailModel dod : demandOrderDetails) {
                // 回写预算 如果询价单价不为0，则释放询价单价*未申请数量(因为审核时，回写的是询价)
                AssetsBudget assetsBudget = new AssetsBudget();
                assetsBudget.setProjectCode(dod.getProjectCode());
                assetsBudget.setBudgetYear(dod.getBudgetYear());

                BigDecimal sumMoney = new BigDecimal("0");
                // 如果询价单价不是Null，则释放询价单价*未申请数量(因为审核时，回写的是询价)
                if (dod.getInquiryPrice() != null) {
                    sumMoney = sumMoney
                            .subtract(dod.getInquiryUnitPrice().multiply(new BigDecimal(dod.getNotApplyCount())));
                } else {
                    // 否则释放参考价格
                    sumMoney = sumMoney
                            .subtract(dod.getReferenceUnitPrice().multiply(new BigDecimal(dod.getNotApplyCount())));
                }
                assetsBudget.setUsedAmount(0 - dod.getNotApplyCount());
                assetsBudget.setUsedSumMoney(sumMoney);
                assetsBudgetDAO.writeBack(assetsBudget);
            }
        }

        // 修改订单状态
        demandOrder.setOrderStatus("已关闭");
        demandOrderDAO.update(demandOrder);
        return result;
    }

    @Override
    public Long searchNextApplyCount(DemandOrder demandOrder) {
        return demandOrderDAO.searchNextApplyCount(demandOrder);
    }

    @Override
    public void updateSearchCloseDemand(DemandOrder demandOrder) {
        demandOrderDAO.updateSearchCloseDemand(demandOrder);
    }

    @Override
    public void updatePrint(DemandOrder demandOrder) {
        demandOrderDAO.updatePrint(demandOrder);
    }

}
