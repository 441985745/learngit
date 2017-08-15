package com.opple.fa.config.service;

import com.opple.fa.config.entity.Areas;
import com.opple.util.Pager;

import java.util.List;

/**
 * Created by Wangkaixuan on 2017/4/6.
 */
public interface AreasService {

    /**
     * 模糊查询
     * @param pager
     * @param areas
     * @return
     */
    Pager<Areas> searchAreasList(Pager<Areas> pager, Areas areas);

    /**
     * 根据区域编码查询
     * @param areaCode
     * @return
     */
    Areas getAreaByAreaCode(String areaCode);

    /**
     * 根据区域编码查询数量
     * @param areaCode
     * @return
     */
    Integer getAreasNumByAreaCode(String areaCode);

    /**
     * 添加区域
     * @param areas
     */
    void addArea(Areas areas);
    void addAreas(List<Areas> areas);

    /**
     * 修改区域
     * @param areas
     */
    void updateArea(Areas areas);
    void updateAreas(List<Areas> areasList);

    void updateStatusOfAreas(List<Areas> areasList);

    String addOrUpdateAreas(List<Areas> addAreasList, List<Areas> updateAreasList);

    List<Areas> exportAreasList(Areas areas);

    Integer getAreasNumByAreaName(String areaCode, String areaName);
}
