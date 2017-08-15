package com.opple.fa.config.service;

import com.opple.fa.config.entity.Position;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;

import java.util.List;


public interface PositionService {
	/**单条保存
	 * @param position
	 * @return
	 */
	ExecuteResult<Position> save(Position position);
	
	/**批量保存
	 * @param positions
	 * @return
	 */
	ExecuteResult<List<Position>> saveBatch(List<Position> positions);
	
	/**删除，根据cpositionCodes的值删除  ---（private List<String> cpositionCodes;// 资产编码--删除使用）
	 * @param position
	 * @return
	 */
	ExecuteResult<Position> delete(Position position);
	
	/**更新
	 * @param position
	 * @return
	 */
	ExecuteResult<Position> update(Position position);
	
	/**单条查询
	 * @param cpositionCode
	 * @return
	 */
	Position get(String cpositionCode);
	
	/**分页查询
	 * @param pager
	 * @param position
	 * @return
	 */
	Pager<Position> searchPagerList(Pager<Position> pager, Position position);
	
	/**分页查询
	 * @param position
	 * @return
	 */
	List<Position> getList(Position position);
	
	
	/**查询数据库中资产编码重复项
	 * @param cpositionCodes
	 * @return
	 */
	List<String> selectExistCodes(List<String> cpositionCodes);

	/**
	 * 根据位置编码统计数量
	 *
	 * @param positionCode
	 * @return
	 */
	Integer checkPositionCode(String positionCode);

	/**
	 * 批量更新或添加
	 *
	 * @param addList
	 * @param upateList
	 */
	void addOrUpdatePosition(List<Position> addList, List<Position> upateList);

    Integer checkPositionName(Position position);
}