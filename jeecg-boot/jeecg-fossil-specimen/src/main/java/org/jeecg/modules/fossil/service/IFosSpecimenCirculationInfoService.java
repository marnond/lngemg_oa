package org.jeecg.modules.fossil.service;

import org.jeecg.modules.fossil.entity.FosSpecimenCirculationInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 古生物化石标本流转记录表
 * @Author: jeecg-boot
 * @Date:   2021-06-25
 * @Version: V1.0
 */
public interface IFosSpecimenCirculationInfoService extends IService<FosSpecimenCirculationInfo> {

	public List<FosSpecimenCirculationInfo> selectByMainId(String mainId);
}
