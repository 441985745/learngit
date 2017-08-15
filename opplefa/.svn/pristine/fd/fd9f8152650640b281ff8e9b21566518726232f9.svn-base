package com.opple.fa.assetcard.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

import net.sf.json.JSONObject;
import com.opple.fa.assetcard.dao.AdministrationAssetDAO;
import com.opple.fa.assetcard.dao.GeneralAssetDAO;
import com.opple.fa.assetcard.dao.ComputerAssetDAO;
import com.opple.fa.assetcard.dao.MeasuringInstrumentsDAO;
import com.opple.fa.assetcard.dao.MachineryEquipmentDAO;
import com.opple.fa.assetcard.entity.ASSETTYPE;
import com.opple.fa.assetcard.entity.AdministrationAsset;
import com.opple.fa.assetcard.entity.ComputerAsset;
import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcard.entity.MachineryEquipment;
import com.opple.fa.assetcard.entity.MeasuringInstruments;
import com.opple.fa.assetcard.service.GeneralAssetService;
import com.opple.fa.config.dao.AssetClassificationDAO;
import com.opple.fa.config.entity.CompanyDepCostcenter;
import com.opple.fa.config.service.CompanyDepCostcenterService;
import com.opple.fa.purchase.dao.PurchaseCommonLoggerDAO;
import com.opple.fa.purchase.entity.PurchaseCommonLogger;
import com.opple.fa.sap.model.ReadAssetsCodeModel;
import com.opple.fa.sap.service.SapUtilService;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;

public class GeneralAssetServiceImpl implements GeneralAssetService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralAssetServiceImpl.class);
    @Getter
    @Setter
    private ComputerAssetDAO computerAssetDAO;
    @Getter
    @Setter
    private MachineryEquipmentDAO machineryEquipmentDAO;
    @Getter
    @Setter
    private GeneralAssetDAO generalAssetDAO;
    @Getter
    @Setter
    private MeasuringInstrumentsDAO measuringInstrumentsDAO;
    @Getter
    @Setter
    private AdministrationAssetDAO administrationAssetDAO;
    @Getter
    @Setter
    private AssetClassificationDAO assetClassificationDAO;
    @Setter
    @Getter
    private SapUtilService sapUtilService;
    @Setter
    @Getter
    private CompanyDepCostcenterService companyDepCostcenterService;
    @Setter
    @Getter
    private PurchaseCommonLoggerDAO purchaseCommonLoggerDAO;

    @Override
    public Pager<GeneralAsset> serchAssetCard(Pager<GeneralAsset> pager,
                                              GeneralAsset generalAsset) {
        List<GeneralAsset> serchAssetCard = generalAssetDAO.serchAssetCard(
                pager, generalAsset);
        Long serchAssetCardCount = generalAssetDAO
                .serchAssetCardCount(generalAsset);
        return Pager.cloneFromPager(pager, serchAssetCardCount, serchAssetCard);
    }

    @Override
    public void deleteAssetCard(GeneralAsset generalAsset) {

        generalAssetDAO.deleteAssetCard(generalAsset);
    }

    // 调拨申请，增行
    @Override
    public List<GeneralAsset> searchAssetCardInfo(GeneralAsset generalAsset) {
        List<GeneralAsset> generalAssetList = generalAssetDAO
                .searchAssetCardInfo(generalAsset);
        return generalAssetList;
    }

    // 调拨申请，增行内部查询
    @Override
    public List<GeneralAsset> searchGeneralAsset(GeneralAsset generalAsset) {
        List<GeneralAsset> generalAssetList = generalAssetDAO
                .searchGeneralAsset(generalAsset);
        return generalAssetList;
    }

    @Override
    public List<GeneralAsset> serchAssetCardData(GeneralAsset generalAsset) {

        return generalAssetDAO.serchAssetCardData(generalAsset);
    }

    @Override
    public ExecuteResult<GeneralAsset> insertAssetCard(

            GeneralAsset generalAsset, ComputerAsset computerAsset,
            MachineryEquipment machineryEquipment,
            MeasuringInstruments measuringInstruments,
            AdministrationAsset administrationAsset) {
        ExecuteResult<GeneralAsset> result = new ExecuteResult<GeneralAsset>();
        if (ASSETTYPE.ITASSET.getAssetType().equals(generalAsset.getAssetType())) {
            computerAssetDAO.insertComputerAsset(computerAsset);
            long id = computerAsset.getId();
            generalAsset.setAssetDetailId(Integer.parseInt(String.valueOf(id)));
            generalAssetDAO.insertGeneralAsset(generalAsset);
        }
        if (ASSETTYPE.ADMINISTRATIVE.getAssetType().equals(generalAsset.getAssetType())) {
            administrationAssetDAO
                    .insertAdministrationAsset(administrationAsset);
            long id = administrationAsset.getId();
            generalAsset.setAssetDetailId(Integer.parseInt(String.valueOf(id)));
            generalAssetDAO.insertGeneralAsset(generalAsset);
        }
        if (ASSETTYPE.MEASURINGINSTRUMENTS.getAssetType().equals(generalAsset.getAssetType())) {
            measuringInstrumentsDAO
                    .insertMeasuringInstruments(measuringInstruments);
            long id = measuringInstruments.getId();
            generalAsset.setAssetDetailId(Integer.parseInt(String.valueOf(id)));
            generalAssetDAO.insertGeneralAsset(generalAsset);
        }
        if (ASSETTYPE.MACHINERYEQUIPMENT.getAssetType().equals(generalAsset.getAssetType())) {
            machineryEquipmentDAO.insertMachineryEquipment(machineryEquipment);
            long id = machineryEquipment.getId();
            generalAsset.setAssetDetailId(Integer.parseInt(String.valueOf(id)));
            generalAssetDAO.insertGeneralAsset(generalAsset);
        }

        return result;
    }

    @Override
    public void excelInsertAssetCard(List<GeneralAsset> listGeneralAsset,
                                     List<ComputerAsset> listComputerAsset,
                                     List<MachineryEquipment> listMachineryEquipment,
                                     List<MeasuringInstruments> listMeasuringInstruments,
                                     List<AdministrationAsset> listAdministrationAsset, boolean isFyorZb) {
        List<GeneralAsset> listAssetCode = null;
        GeneralAsset generalAsset = new GeneralAsset();
        if (!isFyorZb) {
            listAssetCode = generalAssetDAO
                    .serchAssetCardByinsert(generalAsset);
        }
        List<GeneralAsset> updateList = new ArrayList<GeneralAsset>();
        List<GeneralAsset> insertList = new ArrayList<GeneralAsset>();
        if ((null != listGeneralAsset && listGeneralAsset.size() != 0
                && null != listAdministrationAsset && listAdministrationAsset
                .size() != 0)
                && (listComputerAsset.size() == 0 || null == listComputerAsset)
                && (listMachineryEquipment.size() == 0 || null == listMachineryEquipment)
                && (listMeasuringInstruments.size() == 0 || null == listMeasuringInstruments)) {

            List<AdministrationAsset> updateAdministrationList = new ArrayList<AdministrationAsset>();
            List<AdministrationAsset> insertAdministrationList = new ArrayList<AdministrationAsset>();
            for (int i = 0; i < listGeneralAsset.size(); i++) {

                if (null == listAssetCode || listAssetCode.size() == 0) {
                    insertList.add(listGeneralAsset.get(i));
                    insertAdministrationList
                            .add(listAdministrationAsset.get(i));
                } else {
                    for (int j = 0; j < listAssetCode.size(); j++) {
                        String assetCode = listAssetCode.get(j).getAssetCode();
                        String assetCompany = listAssetCode.get(j).getCompanyCode();

                        String dataAssetCode = listGeneralAsset.get(i)
                                .getAssetCode();
                        String dataCompany = listGeneralAsset.get(i)
                                .getCompanyCode();
                        if (!"".equals(assetCode) && null != assetCode
                                && !"".equals(dataAssetCode)
                                && null != dataAssetCode
                                && !"".equals(assetCompany) && null != assetCompany
                                && !"".equals(dataCompany) && null != dataCompany) {
                            if (assetCode.equals(dataAssetCode)
                                    && assetCompany.equals(dataCompany)) {
                                updateList.add(listGeneralAsset.get(i));
                                AdministrationAsset administrationAsset = listAdministrationAsset
                                        .get(i);
                                administrationAsset.setId(Long.parseLong(String
                                        .valueOf(listAssetCode.get(j)
                                                .getAssetDetailId())));
                                updateAdministrationList.add(administrationAsset);
                                break;
                            } else {
                                if (j + 1 == listAssetCode.size()) {
                                    insertList.add(listGeneralAsset.get(i));
                                    insertAdministrationList
                                            .add(listAdministrationAsset.get(i));
                                }

                            }
                        }

                    }
                }
            }
            if (null != insertList && 0 != insertList.size()) {
                for (int i = 0; i < insertList.size(); i++) {
                    AdministrationAsset insertAdministrationAsset = insertAdministrationList
                            .get(i);
                    GeneralAsset insertGeneralAsset = insertList.get(i);
                    administrationAssetDAO
                            .insertAdministrationAsset(insertAdministrationAsset);
                    long id = insertAdministrationAsset.getId();
                    insertGeneralAsset.setAssetDetailId(Integer.parseInt(String
                            .valueOf(id)));
                    generalAssetDAO.insertGeneralAsset(insertGeneralAsset);
                }
            }
            if (null != updateList && 0 != updateList.size()) {
                for (int i = 0; i < updateList.size(); i++) {
                    GeneralAsset updateGeneralAsset = updateList.get(i);
                    generalAssetDAO.updateLeadingCard(updateGeneralAsset);

                    AdministrationAsset updateAdministrationAsset = updateAdministrationList
                            .get(i);
                    administrationAssetDAO
                            .updateLeadingAdministration(updateAdministrationAsset);

                }
            }
        }

        if ((null != listGeneralAsset && listGeneralAsset.size() != 0)
                && (listComputerAsset.size() != 0 && null != listComputerAsset)) {
            List<ComputerAsset> updateComputerList = new ArrayList<ComputerAsset>();
            List<ComputerAsset> insertComputerList = new ArrayList<ComputerAsset>();
            for (int i = 0; i < listGeneralAsset.size(); i++) {
                if (0 != listAssetCode.size() && null != listAssetCode) {
                    for (int j = 0; j < listAssetCode.size(); j++) {
                        String assetCode = listAssetCode.get(j).getAssetCode();
                        String assetCompany = listAssetCode.get(j)
                                .getCompanyCode();
                        String dataAssetCode = listGeneralAsset.get(i)
                                .getAssetCode();
                        String dataCompany = listGeneralAsset.get(i)
                                .getCompanyCode();
                        if (!"".equals(dataAssetCode) && null != dataAssetCode
                                && !"".equals(assetCode) && null != assetCode
                                && !"".equals(dataCompany)
                                && null != dataCompany
                                && !"".equals(assetCompany)
                                && null != assetCompany) {
                            if (assetCode.equals(dataAssetCode)
                                    && assetCompany.equals(dataCompany)) {
                                updateList.add(listGeneralAsset.get(i));
                                ComputerAsset computerAsset = listComputerAsset
                                        .get(i);
                                computerAsset.setId(Long.parseLong(String
                                        .valueOf(listAssetCode.get(j)
                                                .getAssetDetailId())));
                                updateComputerList.add(computerAsset);
                                break;
                            } else {
                                if (j + 1 == listAssetCode.size()) {
                                    insertList.add(listGeneralAsset.get(i));
                                    insertComputerList.add(listComputerAsset
                                            .get(i));
                                }
                            }
                        }

                    }
                } else {
                    insertList.add(listGeneralAsset.get(i));
                    insertComputerList.add(listComputerAsset.get(i));
                }

            }
            if (null != insertList && 0 != insertList.size()) {
                for (int i = 0; i < insertList.size(); i++) {
                    ComputerAsset computerAsset = insertComputerList.get(i);
                    GeneralAsset insertGeneralAsset = insertList.get(i);
                    computerAssetDAO.insertComputerAsset(computerAsset);
                    long id = computerAsset.getId();
                    insertGeneralAsset.setAssetDetailId(Integer.parseInt(String
                            .valueOf(id)));
                    generalAssetDAO.insertGeneralAsset(insertGeneralAsset);
                }
            }
            if (null != updateList && 0 != updateList.size()) {
                for (int i = 0; i < updateList.size(); i++) {
                    GeneralAsset updateGeneralAsset = updateList.get(i);
                    generalAssetDAO.updateLeadingCard(updateGeneralAsset);
                    ComputerAsset computerAsset = updateComputerList.get(i);
                    computerAssetDAO.updateComputerAssetCard(computerAsset);

                }
            }
        }
        if ((null != listGeneralAsset && listGeneralAsset.size() != 0)
                && (listMachineryEquipment.size() != 0 && null != listMachineryEquipment)) {
            List<MachineryEquipment> updateMachineryList = new ArrayList<MachineryEquipment>();
            List<MachineryEquipment> insertMachineryList = new ArrayList<MachineryEquipment>();
            for (int i = 0; i < listGeneralAsset.size(); i++) {
                if (0 != listAssetCode.size() && null != listAssetCode) {
                    for (int j = 0; j < listAssetCode.size(); j++) {
                        String assetCode = listAssetCode.get(j).getAssetCode();
                        String assetCompany = listAssetCode.get(j)
                                .getCompanyCode();
                        String dataAssetCode = listGeneralAsset.get(i)
                                .getAssetCode();
                        String dataCompany = listGeneralAsset.get(i)
                                .getCompanyCode();
                        if (!"".equals(assetCode) && null != assetCode
                                && !"".equals(dataAssetCode)
                                && null != dataAssetCode
                                && !"".equals(dataCompany)
                                && null != dataCompany
                                && !"".equals(assetCompany)
                                && null != assetCompany) {
                            if (assetCode.equals(dataAssetCode)
                                    && dataCompany.equals(assetCompany)) {
                                updateList.add(listGeneralAsset.get(i));
                                MachineryEquipment machineryEquipment = listMachineryEquipment
                                        .get(i);
                                machineryEquipment.setId(Long.parseLong(String
                                        .valueOf(listAssetCode.get(j)
                                                .getAssetDetailId())));
                                updateMachineryList.add(machineryEquipment);
                                break;
                            } else {
                                if (j + 1 == listAssetCode.size()) {
                                    insertList.add(listGeneralAsset.get(i));
                                    insertMachineryList
                                            .add(listMachineryEquipment.get(i));
                                }
                            }
                        }
                    }
                } else {
                    insertList.add(listGeneralAsset.get(i));
                    insertMachineryList.add(listMachineryEquipment.get(i));
                }

            }
            if (null != insertList && 0 != insertList.size()) {
                for (int i = 0; i < insertList.size(); i++) {
                    MachineryEquipment machineryEquipment = insertMachineryList
                            .get(i);
                    GeneralAsset insertGeneralAsset = insertList.get(i);
                    machineryEquipmentDAO
                            .insertMachineryEquipment(machineryEquipment);
                    long id = machineryEquipment.getId();
                    insertGeneralAsset.setAssetDetailId(Integer.parseInt(String
                            .valueOf(id)));
                    generalAssetDAO.insertGeneralAsset(insertGeneralAsset);
                }
            }
            if (null != updateList && 0 != updateList.size()) {
                for (int i = 0; i < updateList.size(); i++) {
                    GeneralAsset updateGeneralAsset = updateList.get(i);
                    generalAssetDAO.updateLeadingCard(updateGeneralAsset);
                    MachineryEquipment machineryEquipment = updateMachineryList
                            .get(i);
                    machineryEquipmentDAO
                            .updateLeadingMachinery(machineryEquipment);

                }
            }
        }
        if ((null != listGeneralAsset && listGeneralAsset.size() != 0)
                && (listMeasuringInstruments.size() != 0 && null != listMeasuringInstruments)) {

            List<MeasuringInstruments> updateMeasuringList = new ArrayList<MeasuringInstruments>();
            List<MeasuringInstruments> insertMeasuringList = new ArrayList<MeasuringInstruments>();
            for (int i = 0; i < listGeneralAsset.size(); i++) {
                if (null != listAssetCode && 0 != listAssetCode.size()) {
                    for (int j = 0; j < listAssetCode.size(); j++) {
                        String assetCode = listAssetCode.get(j).getAssetCode();
                        String assetCompany = listAssetCode.get(j)
                                .getCompanyCode();
                        String dataAssetCode = listGeneralAsset.get(i)
                                .getAssetCode();
                        String dataCompany = listGeneralAsset.get(i)
                                .getCompanyCode();
                        if (!"".equals(assetCode) && null != assetCode
                                && !"".equals(dataAssetCode)
                                && null != dataAssetCode
                                && !"".equals(dataCompany)
                                && null != dataCompany
                                && !"".equals(assetCompany)
                                && null != assetCompany) {
                            if (assetCode.equals(dataAssetCode)
                                    && assetCompany.equals(dataCompany)) {
                                updateList.add(listGeneralAsset.get(i));
                                MeasuringInstruments measuringInstruments = listMeasuringInstruments
                                        .get(i);
                                measuringInstruments.setId(Long
                                        .parseLong(String.valueOf(listAssetCode
                                                .get(j).getAssetDetailId())));
                                updateMeasuringList.add(measuringInstruments);
                                break;
                            } else {
                                if (j + 1 == listAssetCode.size()) {
                                    insertList.add(listGeneralAsset.get(i));
                                    insertMeasuringList
                                            .add(listMeasuringInstruments
                                                    .get(i));
                                }
                            }
                        }
                    }
                } else {
                    insertList.add(listGeneralAsset.get(i));
                    insertMeasuringList.add(listMeasuringInstruments.get(i));
                }

            }
            if (null != insertList && 0 != insertList.size()) {
                for (int i = 0; i < insertList.size(); i++) {
                    MeasuringInstruments measuringInstruments = insertMeasuringList
                            .get(i);
                    GeneralAsset insertGeneralAsset = insertList.get(i);
                    measuringInstrumentsDAO
                            .insertMeasuringInstruments(measuringInstruments);
                    long id = measuringInstruments.getId();
                    insertGeneralAsset.setAssetDetailId(Integer.parseInt(String
                            .valueOf(id)));
                    generalAssetDAO.insertGeneralAsset(insertGeneralAsset);
                }
            }
            if (null != updateList && 0 != updateList.size()) {
                for (int i = 0; i < updateList.size(); i++) {
                    GeneralAsset updateGeneralAsset = updateList.get(i);
                    generalAssetDAO.updateLeadingCard(updateGeneralAsset);

                    MeasuringInstruments measuringInstruments = updateMeasuringList
                            .get(i);
                    measuringInstrumentsDAO
                            .updateLeadingMeasuringInstruments(measuringInstruments);

                }
            }
        }

    }

    @Override
    public Long assetCodeCount(GeneralAsset generalAsset) {

        return generalAssetDAO.assetCodeCount(generalAsset);
    }

    @Override
    public void updateGeneralAssetByDocument(GeneralAsset generalAsset) {

        generalAssetDAO.updateGeneralAssetByDocument(generalAsset);
    }

    @Override
    public GeneralAsset getGeneralAsset(String id) {

        GeneralAsset generalAsset = generalAssetDAO.getGeneralAsset(Integer
                .parseInt(id));

        return generalAsset;
    }

    @Override
    public void updateSapById(Integer id) {
        generalAssetDAO.updateSapById(id);
    }

    @Override
    public ExecuteResult<GeneralAsset> updateAssetCard(
            GeneralAsset generalAsset, ComputerAsset computerAsset,
            MachineryEquipment machineryEquipment,
            MeasuringInstruments measuringInstruments,
            AdministrationAsset administrationAsset) {
        ExecuteResult<GeneralAsset> result = new ExecuteResult<GeneralAsset>();
        if (ASSETTYPE.ITASSET.getAssetType().equals(generalAsset.getAssetType())) {
            computerAssetDAO.updateComputerAssetCard(computerAsset);

            generalAssetDAO.updateGeneralAsset(generalAsset);
        }
        if (ASSETTYPE.ADMINISTRATIVE.getAssetType().equals(generalAsset.getAssetType())) {
            generalAssetDAO.updateGeneralAsset(generalAsset);
            administrationAssetDAO
                    .updateAdministrationAsset(administrationAsset);
        }
        if (ASSETTYPE.MEASURINGINSTRUMENTS.getAssetType().equals(generalAsset.getAssetType())) {
            measuringInstrumentsDAO
                    .updateMeasuringInstrumentsCard(measuringInstruments);

            generalAssetDAO.updateGeneralAsset(generalAsset);
        }
        if (ASSETTYPE.MACHINERYEQUIPMENT.getAssetType().equals(generalAsset.getAssetType())) {
            machineryEquipmentDAO
                    .updateMachineryEquipmentCard(machineryEquipment);

            generalAssetDAO.updateGeneralAsset(generalAsset);
        }
        return result;
    }

    @Override
    public List<GeneralAsset> searchAssetBySapCode(GeneralAsset generalAsset) {

        return generalAssetDAO.searchAssetBySapCode(generalAsset);

    }

    @Override
    public void updateAssetBySapCode(GeneralAsset generalAsset) {
        generalAssetDAO.updateAssetBySapCode(generalAsset);
    }

    @Override
    public void updateAssetState(GeneralAsset generalAsset) {
        generalAssetDAO.updateAssetState(generalAsset);

    }

    @Override
    public Pager<GeneralAsset> serchAssetCardByAccounting(
            Pager<GeneralAsset> pager, GeneralAsset generalAsset) {
        List<GeneralAsset> serchAssetCard = generalAssetDAO
                .serchAssetCardByAccounting(pager, generalAsset);
        Long serchAssetCardCount = generalAssetDAO
                .serchAssetCardCountByAccounting(generalAsset);
        return Pager.cloneFromPager(pager, serchAssetCardCount, serchAssetCard);

    }

    @Override
    public List<GeneralAsset> serchAssetCardDateByAccounting(
            GeneralAsset generalAsset) {
        return generalAssetDAO.serchAssetCardDateByAccounting(generalAsset);
    }

    @Override
    public List<GeneralAsset> serachByacctingUpdateSap(GeneralAsset generalAsset) {

        return generalAssetDAO.serachByacctingUpdateSap(generalAsset);
    }

    @Override
    public List<GeneralAsset> searchAssetCardInfoForBursar(
            GeneralAsset generalAsset) {
        return generalAssetDAO.searchAssetCardInfoForBursar(generalAsset);
    }

    @Override
    public List<GeneralAsset> serchAssetCardDataIT(GeneralAsset generalAsset) {
        return generalAssetDAO.serchAssetCardDataIT(generalAsset);
    }

    @Override
    public List<GeneralAsset> serchAssetCardDataXZ(GeneralAsset generalAsset) {
        return generalAssetDAO.serchAssetCardDataXZ(generalAsset);
    }

    @Override
    public List<GeneralAsset> serchAssetCardDataJL(GeneralAsset generalAsset) {
        return generalAssetDAO.serchAssetCardDataJL(generalAsset);
    }

    @Override
    public List<GeneralAsset> serchAssetCardDataJX(GeneralAsset generalAsset) {
        return generalAssetDAO.serchAssetCardDataJX(generalAsset);
    }

    @Override
    public List<GeneralAsset> serchAssetCardDateByAccountingIT(GeneralAsset generalAsset) {
        return generalAssetDAO.serchAssetCardDateByAccountingIT(generalAsset);
    }

    @Override
    public List<GeneralAsset> serchAssetCardDateByAccountingXZ(GeneralAsset generalAsset) {
        return generalAssetDAO.serchAssetCardDateByAccountingXZ(generalAsset);
    }

    @Override
    public List<GeneralAsset> serchAssetCardDateByAccountingJL(GeneralAsset generalAsset) {
        return generalAssetDAO.serchAssetCardDateByAccountingJL(generalAsset);
    }

    @Override
    public List<GeneralAsset> serchAssetCardDateByAccountingJX(GeneralAsset generalAsset) {
        return generalAssetDAO.serchAssetCardDateByAccountingJX(generalAsset);
    }

    @Override
    public void updateAssembleOccupancy(GeneralAsset generalAsset) {
        generalAssetDAO.updateAssembleOccupancy(generalAsset);

    }

    @Override
    public ExecuteResult<GeneralAsset> updateAssetFromSap(Integer id, String userCode) {
        ExecuteResult<GeneralAsset> result = new ExecuteResult<GeneralAsset>();
        GeneralAsset generalAsset = new GeneralAsset();
        GeneralAsset getAsset = generalAssetDAO.getGeneralAsset(id);
        JSONObject sapCard = new JSONObject();

        ReadAssetsCodeModel readAssetsCodeModel = new ReadAssetsCodeModel();


        if (!"".equals(getAsset) && null != getAsset) {
            String[] code = getAsset.getAssetCode().split("-");
            String mainCode = code[0];
            String secondaryCode = code[1]; // 次级编码
            readAssetsCodeModel.setMasterAssetCode(mainCode);
            readAssetsCodeModel.setSecondaryAssetCode(secondaryCode);
            readAssetsCodeModel.setCompanyCode(getAsset.getCompanyCode());
        } else {
            result.addErrorMessage("资产不能为空");
            return result;
        }

        sapCard = sapUtilService.readAssetsCodeFromSAP(readAssetsCodeModel);
        if (sapCard.has("error")) {
            // 入账日
            if (!"".equals(sapCard.getString("capDate")) && null != sapCard.getString("capDate")) {
                generalAsset.setPostingDate(this.getDataExcel(sapCard.getString("capDate")));
            }
            // 资产原值
            if (!"".equals(sapCard.getString("currentApc")) && null != sapCard.getString("currentApc")) {
                generalAsset.setAssetOriginalValue(sapCard.getString("currentApc"));
            }
            String nameCode = sapCard.getString("inventNo");

            if (!"".equals(nameCode)) {
                if (nameCode.contains("-")) {
                    String[] name = nameCode.split("-");
                    generalAsset.setAssetPerson(name[1]);
                    generalAsset.setAssetPersonCode(name[0]);
                } else {
                    generalAsset.setAssetPerson(nameCode);
                }
            }
            // 资产净值
            generalAsset.setAssetNetValue(sapCard.getString("currentNbv"));
            // 资产分类
            generalAsset.setAssetClassification(sapCard
                    .getString("assetClass"));
            // 资产名称
            generalAsset.setAssetName(sapCard.getString("descript"));
            // 规格型号
            generalAsset.setSpecificationModel(sapCard
                    .getString("descript"));
            // 成本中心编码
            generalAsset.setCostCenterCode(sapCard.getString("costcenter"));
            // 折旧年限
            generalAsset
                    .setPeriodOfDepreciation(sapCard.getString("ulife"));
            // 折旧开始日期
            if (!"".equals(sapCard.getString("deprBeginDate"))
                    && null != sapCard.getString("deprBeginDate")) {
                generalAsset.setDepreciationStartDate(this
                        .getDataExcel(sapCard.getString("deprBeginDate")));
            }
            // 本年已计提累计折旧
            generalAsset.setCurrentYearDepreciation(sapCard
                    .getString("deprCurrYear"));
            // 实际验收日期
            generalAsset.setActualDateAcceptance(this.getDataExcel(sapCard
                    .getString("initialAcq")));
            // 月折旧额
            generalAsset.setMonthCountingDepreciation(sapCard
                    .getString("monthlyDepreciation1"));
            // 累计折旧
            generalAsset.setAddDepreciation(sapCard.getString("accumDepr"));
            CompanyDepCostcenter companyDepCostcenter = new CompanyDepCostcenter();
            companyDepCostcenter.setCostCenterCode(sapCard.getString("costcenter"));
            generalAsset.setUseDepartmentCode(sapCard.getString("costcenter"));
            List<CompanyDepCostcenter> companyDep = companyDepCostcenterService
                    .serachCompanyDepCostcenter(companyDepCostcenter);
            if (null != companyDep
                    && 0 != companyDep.size()) {
                generalAsset.setCompanyName(companyDep.get(
                        0).getCompanyName()); // 公司名称
                generalAsset.setPersentDepartment(companyDep
                        .get(0).getCostCenterName()); // 成本中心
                generalAsset.setUseDepartment(companyDep
                        .get(0).getCostCenterName()); //部门名称

            }

            generalAsset.setId(Long.parseLong(String.valueOf(id)));
            generalAsset.setUpdateDate(new Date());
            generalAsset.setUpdateBy(userCode);
            generalAssetDAO.updateAssetBySapCode(generalAsset);
        } else {
            result.addErrorMessage(sapCard.getString("error"));
        }
        return result;
    }

    private Date getDataExcel(String data) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fmt.parse(data);
        } catch (ParseException e) {

            LOGGER.error("ParseException", e);
        }
        return date;

    }

    @Override
    public ExecuteResult<GeneralAsset> updateAssetFromSapByList() {
        ExecuteResult<GeneralAsset> result = new ExecuteResult<GeneralAsset>();
        GeneralAsset generalAssetGet = new GeneralAsset();
        JSONObject sapCard = new JSONObject();
        ReadAssetsCodeModel readAssetsCodeModel = new ReadAssetsCodeModel();
        List<GeneralAsset> updateListGeneralAsset = new ArrayList<GeneralAsset>();
        PurchaseCommonLogger purchaseCommLogger = new PurchaseCommonLogger();
        List<GeneralAsset> searchListGenerals = generalAssetDAO.serachByacctingUpdateSap(generalAssetGet);

        purchaseCommLogger.setDocument("定时任务");
        purchaseCommLogger.setLogger("卡片更新Sap开始!");
        purchaseCommLogger.setUserCode("定时任务");
        purchaseCommonLoggerDAO.savePurchaseCommLogger(purchaseCommLogger);
        for (GeneralAsset generalAssetTwo : searchListGenerals) {
            GeneralAsset generalAsset = new GeneralAsset();
            String assetCode = generalAssetTwo.getAssetCode();
            String companyCode = generalAssetTwo.getCompanyCode();

            String[] code = assetCode.split("-");
            String mainCode = code[0];
            String secondaryCode = code[1]; // 次级编码

            readAssetsCodeModel.setCompanyCode(companyCode);
            readAssetsCodeModel.setMasterAssetCode(mainCode);
            readAssetsCodeModel.setSecondaryAssetCode(secondaryCode);
            sapCard = sapUtilService.readAssetsCodeFromSAP(readAssetsCodeModel);
            if (sapCard.has("error")) {
                // 入账日
                if (!"".equals(sapCard.getString("capDate")) && null != sapCard.getString("capDate")) {
                    generalAsset.setPostingDate(this.getDataExcel(sapCard.getString("capDate")));
                }
                // 资产原值
                if (!"".equals(sapCard.getString("currentApc")) && null != sapCard.getString("currentApc")) {
                    generalAsset.setAssetOriginalValue(sapCard.getString("currentApc"));
                }
                String nameCode = sapCard.getString("inventNo");

                if (!"".equals(nameCode)) {
                    if (nameCode.contains("-")) {
                        String[] name = nameCode.split("-");
                        generalAsset.setAssetPerson(name[1]);
                        generalAsset.setAssetPersonCode(name[0]);
                    } else {
                        generalAsset.setAssetPerson(nameCode);
                    }
                }
                // 资产净值
                generalAsset.setAssetNetValue(sapCard.getString("currentNbv"));
                // 资产分类
                generalAsset.setAssetClassification(sapCard
                        .getString("assetClass"));
                // 资产名称
                generalAsset.setAssetName(sapCard.getString("descript"));
                // 规格型号
                generalAsset.setSpecificationModel(sapCard
                        .getString("descript"));
                // 成本中心编码
                generalAsset.setCostCenterCode(sapCard.getString("costcenter"));
                // 折旧年限
                generalAsset
                        .setPeriodOfDepreciation(sapCard.getString("ulife"));
                // 折旧开始日期
                if (!"".equals(sapCard.getString("deprBeginDate"))
                        && null != sapCard.getString("deprBeginDate")) {
                    generalAsset.setDepreciationStartDate(this
                            .getDataExcel(sapCard.getString("deprBeginDate")));
                }
                // 本年已计提累计折旧
                generalAsset.setCurrentYearDepreciation(sapCard
                        .getString("deprCurrYear"));
                // 实际验收日期
                generalAsset.setActualDateAcceptance(this.getDataExcel(sapCard
                        .getString("initialAcq")));
                // 月折旧额
                generalAsset.setMonthCountingDepreciation(sapCard
                        .getString("monthlyDepreciation1"));
                // 累计折旧
                generalAsset.setAddDepreciation(sapCard.getString("accumDepr"));
                CompanyDepCostcenter companyDepCostcenter = new CompanyDepCostcenter();
                companyDepCostcenter.setCostCenterCode(sapCard.getString("costcenter"));
                generalAsset.setUseDepartmentCode(sapCard.getString("costcenter"));
                List<CompanyDepCostcenter> companyDep = companyDepCostcenterService
                        .serachCompanyDepCostcenter(companyDepCostcenter);
                if (null != companyDep
                        && 0 != companyDep.size()) {
                    generalAsset.setCompanyName(companyDep.get(
                            0).getCompanyName()); // 公司名称
                    generalAsset.setPersentDepartment(companyDep
                            .get(0).getCostCenterName()); // 成本中心
                    generalAsset.setUseDepartment(companyDep
                            .get(0).getCostCenterName()); //部门名称

                }
                String s = String.valueOf(generalAssetTwo.getId());
                generalAsset.setId(Long.parseLong(s));
                generalAsset.setUpdateDate(new Date());
                generalAsset.setUpdateBy("定时任务");
                updateListGeneralAsset.add(generalAsset);
            }
            if (null != updateListGeneralAsset && updateListGeneralAsset.size() != 0) {
                try {
                    generalAssetDAO.updateAssetFromSapByList(updateListGeneralAsset);
                } catch (Exception e) {
                    purchaseCommLogger.setDocument(generalAsset.getAssetCode() + ":" + generalAsset.getCompanyCode());
                    purchaseCommLogger.setLogger("卡片:" + e);
                    purchaseCommLogger.setUserCode("定时任务");
                    purchaseCommonLoggerDAO.savePurchaseCommLogger(purchaseCommLogger);
                }
            }


        }
        purchaseCommLogger.setDocument("定时任务");
        purchaseCommLogger.setLogger("卡片更新sap结束！");
        purchaseCommLogger.setUserCode("定时任务");
        purchaseCommonLoggerDAO.savePurchaseCommLogger(purchaseCommLogger);

        return result;
    }

}
