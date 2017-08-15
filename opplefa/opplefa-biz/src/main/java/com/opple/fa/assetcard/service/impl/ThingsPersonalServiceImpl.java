package com.opple.fa.assetcard.service.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.opple.fa.allocation.dao.AssetAllocationDAO;
import com.opple.fa.allocation.dao.AssetAllocationDetailDAO;
import com.opple.fa.allocation.entity.AssetAllocation;
import com.opple.fa.allocation.entity.AssetAllocationDetail;
import com.opple.fa.assetcard.dao.AdministrationAssetDAO;
import com.opple.fa.assetcard.dao.GeneralAssetDAO;
import com.opple.fa.assetcard.dao.ComputerAssetDAO;
import com.opple.fa.assetcard.dao.MachineryEquipmentDAO;
import com.opple.fa.assetcard.dao.MeasuringInstrumentsDAO;
import com.opple.fa.assetcard.entity.ASSETTYPE;
import com.opple.fa.assetcard.entity.AdministrationAsset;
import com.opple.fa.assetcard.entity.ComputerAsset;
import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcard.entity.MachineryEquipment;
import com.opple.fa.assetcard.entity.MeasuringInstruments;
import com.opple.fa.assetcard.service.ThingsPersonalService;
import com.opple.fa.discardsell.dao.DiscardSellDAO;
import com.opple.fa.discardsell.dao.DiscardSellDetailDAO;
import com.opple.fa.discardsell.entity.DiscardSell;
import com.opple.fa.discardsell.entity.DiscardSellDetail;
import com.opple.util.Pager;
import com.opple.workflow.entity.COMMITTYPE;

public class ThingsPersonalServiceImpl implements ThingsPersonalService {

    @Getter
    @Setter
    private GeneralAssetDAO generalAssetDAO;
    @Getter
    @Setter
    private MachineryEquipmentDAO machineryEquipmentDAO;
    @Getter
    @Setter
    private MeasuringInstrumentsDAO measuringInstrumentsDAO;
    @Getter
    @Setter
    private ComputerAssetDAO computerAssetDAO;
    @Getter
    @Setter
    private AssetAllocationDetailDAO assetAllocationDetailDAO;
    @Getter
    @Setter
    private AssetAllocationDAO assetAllocationDAO;
    @Getter
    @Setter
    private DiscardSellDAO discardSellDAO;
    @Getter
    @Setter
    private DiscardSellDetailDAO discardSellDetailDAO;
    @Getter
    @Setter
    private AdministrationAssetDAO administrationAssetDAO;

    @Override
    public Pager<GeneralAsset> searchTbFaGeneralAsset(
            Pager<GeneralAsset> pager, GeneralAsset generalAsset) {

        List<GeneralAsset> list = generalAssetDAO.searchTbFaGeneralAsset(pager,
                generalAsset);
        Long count = generalAssetDAO.searchTbFaGeneralAssetCount(generalAsset);

        return Pager.cloneFromPager(pager, count, list);
    }

    @Override
    public GeneralAsset searchDetails(long id) {

        GeneralAsset listGA = new GeneralAsset();
        listGA.setId(id);
        if (!"".equals(id)) {
            listGA = generalAssetDAO.searchDetails(listGA);

            List<AssetAllocation> assetAllocation = new ArrayList<AssetAllocation>();
            // 获得资产的相关调拨
            List<AssetAllocationDetail> allocationDetail = assetAllocationDetailDAO
                    .searchAssetAllocationDetail(Integer.parseInt(String
                            .valueOf(listGA.getId())));

            for (AssetAllocationDetail assetAllocationDetail : allocationDetail) {
                AssetAllocation assetAllocation1 = assetAllocationDAO
                        .searchAssetAllocation(assetAllocationDetail
                                .getAssetAllocationId());
                if (null != assetAllocation1
                        && COMMITTYPE.SUBMIT.getCommitType().equals(assetAllocation1.getCommitType())) {
                    assetAllocation.add(assetAllocation1);
                }

            }

            listGA.setAssetAllocation(assetAllocation);
            // 获得资产相关的报废变卖
            DiscardSellDetail discardSellDetail = new DiscardSellDetail();
            discardSellDetail.setGeneralAssetId(Long.parseLong(String
                    .valueOf(id)));
            List<DiscardSell> listDiscardSells = new ArrayList<DiscardSell>();
            List<DiscardSellDetail> listDiscardSellDetails = discardSellDetailDAO
                    .serachDiscardSellDetailByAssetId(discardSellDetail);

            for (DiscardSellDetail discardSellDetail2 : listDiscardSellDetails) {
                DiscardSell discardSell = new DiscardSell();

                Long sellId = discardSellDetail2.getDiscardSellId();
                if (null != sellId && !"".equals(sellId)) {
                    discardSell.setId(Long.parseLong(String.valueOf(sellId)));
                    discardSell = discardSellDAO
                            .getDiscardSellByCardId(discardSell);
                }
                if (null != discardSell && COMMITTYPE.SUBMIT.getCommitType().equals(discardSell.getCommitType())
                        ) {
                    listDiscardSells.add(discardSell);
                }

            }
            listGA.setListDiscardSells(listDiscardSells);
            if (ASSETTYPE.ITASSET.getAssetType().equals(listGA.getAssetType())) {
                ComputerAsset computerAsset = new ComputerAsset();
                Integer cardId = listGA.getAssetDetailId();
                if (null != cardId && !"".equals(cardId)) {
                    computerAsset.setId(Long.parseLong(cardId.toString()));
                    computerAsset = computerAssetDAO
                            .searchComputerAsset(computerAsset);
                    listGA.setComputerAsset(computerAsset);
                }

            }
            if (ASSETTYPE.ADMINISTRATIVE.getAssetType().equals(listGA.getAssetType())) {
                AdministrationAsset administrationAsset = new AdministrationAsset();
                Integer cardId = listGA.getAssetDetailId();
                if (null != cardId && !"".equals(cardId)) {
                    administrationAsset
                            .setId(Long.parseLong(cardId.toString()));
                    administrationAsset = administrationAssetDAO
                            .getAdministrationAsset(administrationAsset);
                    listGA.setAdministrationAsset(administrationAsset);
                }
            } else if (ASSETTYPE.MEASURINGINSTRUMENTS.getAssetType().equals(listGA.getAssetType())) {
                MeasuringInstruments measuringInstruments = new MeasuringInstruments();
                Integer cardId = listGA.getAssetDetailId();
                if (null != cardId && !"".equals(cardId)) {
                    measuringInstruments.setId(Long.parseLong(String
                            .valueOf(cardId.toString())));
                    measuringInstruments = measuringInstrumentsDAO
                            .searchMeasuringInstruments(measuringInstruments);
                    listGA.setMeasuringInstruments(measuringInstruments);
                }

            } else if (ASSETTYPE.MACHINERYEQUIPMENT.getAssetType().equals(listGA.getAssetType())) {
                MachineryEquipment machineryEquipment = new MachineryEquipment();
                Integer cardId = listGA.getAssetDetailId();
                if (null != cardId && !"".equals(cardId)) {
                    machineryEquipment.setId(Long.parseLong(String
                            .valueOf(cardId.toString())));
                    machineryEquipment = machineryEquipmentDAO
                            .searchMachineryEquipment(machineryEquipment);
                    listGA.setMachineryEquipment(machineryEquipment);
                }

            }
        }

        return listGA;
    }

}
