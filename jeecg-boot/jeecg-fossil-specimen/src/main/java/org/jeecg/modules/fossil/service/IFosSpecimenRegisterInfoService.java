package org.jeecg.modules.fossil.service;

import org.jeecg.modules.fossil.entity.FosSpecimenCirculationInfo;
import org.jeecg.modules.fossil.entity.FosSpecimenRegisterInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 古生物化石标本登记表
 * @Author: jeecg-boot
 * @Date:   2021-06-25
 * @Version: V1.0
 */
public interface IFosSpecimenRegisterInfoService extends IService<FosSpecimenRegisterInfo> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(FosSpecimenRegisterInfo fosSpecimenRegisterInfo,List<FosSpecimenCirculationInfo> fosSpecimenCirculationInfoList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(FosSpecimenRegisterInfo fosSpecimenRegisterInfo,List<FosSpecimenCirculationInfo> fosSpecimenCirculationInfoList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
