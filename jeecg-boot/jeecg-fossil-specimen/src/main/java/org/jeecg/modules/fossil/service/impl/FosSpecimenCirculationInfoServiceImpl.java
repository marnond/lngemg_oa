package org.jeecg.modules.fossil.service.impl;

import org.jeecg.modules.fossil.entity.FosSpecimenCirculationInfo;
import org.jeecg.modules.fossil.mapper.FosSpecimenCirculationInfoMapper;
import org.jeecg.modules.fossil.service.IFosSpecimenCirculationInfoService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 古生物化石标本流转记录表
 * @Author: jeecg-boot
 * @Date:   2021-06-25
 * @Version: V1.0
 */
@Service
public class FosSpecimenCirculationInfoServiceImpl extends ServiceImpl<FosSpecimenCirculationInfoMapper, FosSpecimenCirculationInfo> implements IFosSpecimenCirculationInfoService {
	
	@Autowired
	private FosSpecimenCirculationInfoMapper fosSpecimenCirculationInfoMapper;
	
	@Override
	public List<FosSpecimenCirculationInfo> selectByMainId(String mainId) {
		return fosSpecimenCirculationInfoMapper.selectByMainId(mainId);
	}
}
