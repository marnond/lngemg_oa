package org.jeecg.modules.tree.mapper;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.tree.entity.FosSysArea;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 行政区划
 * @Author: jeecg-boot
 * @Date:   2021-06-14
 * @Version: V1.0
 */
public interface FosSysAreaMapper extends BaseMapper<FosSysArea> {

	/**
	 * 编辑节点状态
	 * @param id
	 * @param status
	 */
	void updateTreeNodeStatus(@Param("id") String id,@Param("status") String status);

}
