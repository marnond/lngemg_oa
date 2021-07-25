package org.jeecg.modules.fossil.mapper;

import java.util.List;
import org.jeecg.modules.fossil.entity.FosSpecimenCirculationInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 古生物化石标本流转记录表
 * @Author: jeecg-boot
 * @Date:   2021-06-25
 * @Version: V1.0
 */
public interface FosSpecimenCirculationInfoMapper extends BaseMapper<FosSpecimenCirculationInfo> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<FosSpecimenCirculationInfo> selectByMainId(@Param("mainId") String mainId);
}
