package org.jeecg.modules.tree.service;

import org.jeecg.modules.tree.entity.FosSysArea;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.exception.JeecgBootException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;

/**
 * @Description: 行政区划
 * @Author: jeecg-boot
 * @Date:   2021-06-14
 * @Version: V1.0
 */
public interface IFosSysAreaService extends IService<FosSysArea> {

	/**根节点父ID的值*/
	public static final String ROOT_PID_VALUE = "0";
	
	/**树节点有子节点状态值*/
	public static final String HASCHILD = "1";
	
	/**树节点无子节点状态值*/
	public static final String NOCHILD = "0";

	/**新增节点*/
	void addFosSysArea(FosSysArea fosSysArea);
	
	/**修改节点*/
	void updateFosSysArea(FosSysArea fosSysArea) throws JeecgBootException;
	
	/**删除节点*/
	void deleteFosSysArea(String id) throws JeecgBootException;

	/**查询所有数据，无分页*/
    List<FosSysArea> queryTreeListNoPage(QueryWrapper<FosSysArea> queryWrapper);

}
