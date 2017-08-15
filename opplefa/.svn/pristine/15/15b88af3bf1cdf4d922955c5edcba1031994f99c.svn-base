package com.opple.fa.config.dao;

import com.opple.fa.config.entity.Position;
import com.opple.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionDAO {
	
	int save(@Param("position") Position position);
	Long saveBatch(List<Position> positions);
	int delete(@Param("position") Position position);
	int update(@Param("position") Position position);
	Position get(String cpositionCode);
	List<Position> searchList(@Param("pager") Pager<?> pager, @Param("position") Position position);
	Long searchListCount(@Param("position") Position position);
	/**不带分页的查询
	 * @param position
	 * @return
	 */
	List<Position> getList(@Param("position") Position position);
	List<String> selectExistCodes(@Param("cpositionCodes") List<String> cpositionCodes);

    Integer checkPositionCode(String positionCode);

    Integer checkPositionName(@Param("position") Position position);
}