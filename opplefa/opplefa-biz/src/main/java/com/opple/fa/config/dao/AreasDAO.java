package com.opple.fa.config.dao;

import com.opple.fa.config.entity.Areas;
import com.opple.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Wangkaixuan on 2017/4/6.
 */
public interface AreasDAO {
    /**
     * 模糊查询
     * @param pager
     * @param areas
     * @return
     */
    List<Areas> searchAreasList(@Param("pager") Pager<Areas> pager, @Param("areas") Areas areas);
    Long searchAreasListCount(@Param("areas")Areas areas);

    /**
     * 根据区域编码查询
     * @param areaCode
     * @return
     */
    Areas getAreaByAreaCode(String areaCode);

    /**
     * 导入时验证导入的编码是否存在
     * @param areaCode
     * @return
     */
    Integer getAreasNumByAreaCode(String areaCode);
    /**
     * 添加区域
     * @param area
     */
    void addArea(Areas area);

    /**
     * 修改区域
     * @param area
     */
    void updateArea(Areas area);

    void updateStatusOfArea(Areas area);

    /**
     * 导出
     * @param areas
     * @return
     */
    List<Areas> exportAreasList(@Param("areas")Areas areas);

    Integer getAreasNumByAreaName(@Param("areaCode") String areaCode, @Param("areaName") String areaName);
}
