package org.jeecg.modules.fossil.service.impl;

import org.jeecg.modules.fossil.entity.FosSpecimenRegisterInfo;
import org.jeecg.modules.fossil.entity.FosSpecimenCirculationInfo;
import org.jeecg.modules.fossil.mapper.FosSpecimenCirculationInfoMapper;
import org.jeecg.modules.fossil.mapper.FosSpecimenRegisterInfoMapper;
import org.jeecg.modules.fossil.service.IFosSpecimenRegisterInfoService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 古生物化石标本登记表
 * @Author: jeecg-boot
 * @Date:   2021-06-25
 * @Version: V1.0
 */
@Service
public class FosSpecimenRegisterInfoServiceImpl extends ServiceImpl<FosSpecimenRegisterInfoMapper, FosSpecimenRegisterInfo> implements IFosSpecimenRegisterInfoService {

	@Autowired
	private FosSpecimenRegisterInfoMapper fosSpecimenRegisterInfoMapper;
	@Autowired
	private FosSpecimenCirculationInfoMapper fosSpecimenCirculationInfoMapper;
	
	@Override
	@Transactional
	public void saveMain(FosSpecimenRegisterInfo fosSpecimenRegisterInfo, List<FosSpecimenCirculationInfo> fosSpecimenCirculationInfoList) {
		fosSpecimenRegisterInfoMapper.insert(fosSpecimenRegisterInfo);
		if(fosSpecimenCirculationInfoList!=null && fosSpecimenCirculationInfoList.size()>0) {
			for(FosSpecimenCirculationInfo entity:fosSpecimenCirculationInfoList) {
				//外键设置
				entity.setMainId(fosSpecimenRegisterInfo.getId());
				fosSpecimenCirculationInfoMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(FosSpecimenRegisterInfo fosSpecimenRegisterInfo,List<FosSpecimenCirculationInfo> fosSpecimenCirculationInfoList) {
		fosSpecimenRegisterInfoMapper.updateById(fosSpecimenRegisterInfo);
		
		//1.先删除子表数据
		fosSpecimenCirculationInfoMapper.deleteByMainId(fosSpecimenRegisterInfo.getId());
		
		//2.子表数据重新插入
		if(fosSpecimenCirculationInfoList!=null && fosSpecimenCirculationInfoList.size()>0) {
			for(FosSpecimenCirculationInfo entity:fosSpecimenCirculationInfoList) {
				//外键设置
				entity.setMainId(fosSpecimenRegisterInfo.getId());
				fosSpecimenCirculationInfoMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		fosSpecimenCirculationInfoMapper.deleteByMainId(id);
		fosSpecimenRegisterInfoMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			fosSpecimenCirculationInfoMapper.deleteByMainId(id.toString());
			fosSpecimenRegisterInfoMapper.deleteById(id);
		}
	}
	
}
