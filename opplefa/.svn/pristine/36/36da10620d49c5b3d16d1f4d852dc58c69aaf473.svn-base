package com.opple.fa.config.service.impl;

import com.opple.fa.config.dao.AreasDAO;
import com.opple.fa.config.entity.Areas;
import com.opple.fa.config.service.AreasService;
import com.opple.util.Pager;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Wangkaixuan on 2017/4/6.
 */
public class AreasServiceImpl implements AreasService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AreasServiceImpl.class);

    @Getter @Setter
    private AreasDAO areasDAO;

    @Override
    public Pager<Areas> searchAreasList(Pager<Areas> pager, Areas areas) {
        List<Areas> areasList = areasDAO.searchAreasList(pager, areas);
        Long areasListCount = areasDAO.searchAreasListCount(areas);
        return Pager.cloneFromPager(pager, areasListCount, areasList);
    }

    @Override
    public Areas getAreaByAreaCode(String areaCode) {
        return  areasDAO.getAreaByAreaCode(areaCode);
    }
    @Override
    public Integer getAreasNumByAreaCode(String areaCode) {
        return  areasDAO.getAreasNumByAreaCode(areaCode);
    }

    @Override
    public void addArea(Areas areas) {
        areasDAO.addArea(areas);
    }

    @Override
    public void addAreas(List<Areas> areas) {
        for (Areas area : areas) {
            areasDAO.addArea(area);
        }
    }

    @Override
    public void updateArea(Areas areas) {
        areasDAO.updateArea(areas);
    }

    @Override
    public void updateAreas(List<Areas> areasList) {
        for (Areas areas : areasList) {
            areasDAO.updateArea(areas);
        }
    }

    @Override
    public void updateStatusOfAreas(List<Areas> areasList) {
        for (Areas areas : areasList) {
            areasDAO.updateStatusOfArea(areas);
        }
    }

    @Override
    public String addOrUpdateAreas(List<Areas> addAreasList, List<Areas> updateAreasList) {
        String result = "";
        if (addAreasList.size() > 0) {
            try {
                addAreas(addAreasList);
            } catch (Exception e) {
                LOGGER.error("Exception", e);
                result += "新增区域添加失败 !\n";
            }
        }
        if (updateAreasList.size() > 0) {
            try {
                updateAreas(updateAreasList);
            } catch (Exception e) {
                LOGGER.error("Exception", e);
                result += "更新区域维护失败 !\n";
            }
        }
        return result;
    }

    @Override
    public List<Areas> exportAreasList(Areas areas) {
        return areasDAO.exportAreasList(areas);
    }

    @Override
    public Integer getAreasNumByAreaName(String areaCode, String areaName) {
        return areasDAO.getAreasNumByAreaName(areaCode, areaName);
    }
}
