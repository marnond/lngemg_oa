package org.jeecg.modules.fossil.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 古生物化石标本登记表
 * @Author: jeecg-boot
 * @Date:   2021-06-18
 * @Version: V1.0
 */
@Data
@TableName("fos_specimen_register_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="fos_specimen_register_info对象", description="古生物化石标本登记表")
public class FosSpecimenRegisterInfo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**编号*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编号")
    private java.lang.String id;
	/**行政区*/
	@Excel(name = "行政区", width = 15)
    @ApiModelProperty(value = "行政区")
    private java.lang.String district;
	/**收藏人*/
	@Excel(name = "收藏人", width = 15)
    @ApiModelProperty(value = "收藏人")
    private java.lang.String collector;
	/**收藏人性质*/
	@Excel(name = "收藏人性质", width = 15)
    @ApiModelProperty(value = "收藏人性质")
    private java.lang.String collectorNature;
	/**联系人及联系电话*/
	@Excel(name = "联系人及联系电话", width = 15)
    @ApiModelProperty(value = "联系人及联系电话")
    private java.lang.String contactAndPhone;
	/**标本名称*/
	@Excel(name = "标本名称", width = 15)
    @ApiModelProperty(value = "标本名称")
    private java.lang.String specimenName;
	/**拉丁文名称*/
	@Excel(name = "拉丁文名称", width = 15)
    @ApiModelProperty(value = "拉丁文名称")
    private java.lang.String latinName;
	/**编号*/
	@Excel(name = "编号", width = 15)
    @ApiModelProperty(value = "编号")
    private java.lang.String specimenId;
	/**收藏日期*/
	@Excel(name = "收藏日期", width = 15)
    @ApiModelProperty(value = "收藏日期")
    private java.lang.String collectDate;
	/**标本来源*/
	@Excel(name = "标本来源", width = 15, dicCode = "fos_specimen_origin")
	@Dict(dicCode = "fos_specimen_origin")
    @ApiModelProperty(value = "标本来源")
    private java.lang.String specimenOrigin;
	/**标本位置*/
	@Excel(name = "标本位置", width = 15)
    @ApiModelProperty(value = "标本位置")
    private java.lang.String specimenPosition;
	/**模式标本*/
	@Excel(name = "模式标本", width = 15, dicCode = "fos_model")
	@Dict(dicCode = "fos_model")
    @ApiModelProperty(value = "模式标本")
    private java.lang.String model;
	/**模式类型*/
	@Excel(name = "模式类型", width = 15, dicCode = "fos_model_type")
	@Dict(dicCode = "fos_model_type")
    @ApiModelProperty(value = "模式类型")
    private java.lang.String modelType;
	/**标本类别*/
	@Excel(name = "标本类别", width = 15)
    @ApiModelProperty(value = "标本类别")
    private java.lang.String specimenFamily;
	/**标本去向*/
	@Excel(name = "标本去向", width = 15, dicCode = "fos_specimen_whereabouts")
	@Dict(dicCode = "fos_specimen_whereabouts")
    @ApiModelProperty(value = "标本去向")
    private java.lang.String specimenWhereabouts;
	/**保护级别*/
	@Excel(name = "保护级别", width = 15, dicCode = "fos_protect_level")
	@Dict(dicCode = "fos_protect_level")
    @ApiModelProperty(value = "保护级别")
    private java.lang.String protectLevel;
	/**标本状态*/
	@Excel(name = "标本状态", width = 15, dicCode = "fos_protect_state")
	@Dict(dicCode = "fos_protect_state")
    @ApiModelProperty(value = "标本状态")
    private java.lang.String protectState;
	/**产出地点*/
	@Excel(name = "产出地点", width = 15)
    @ApiModelProperty(value = "产出地点")
    private java.lang.String specimenSite;
	/**时代*/
	@Excel(name = "时代", width = 15)
    @ApiModelProperty(value = "时代")
    private java.lang.String age;
	/**标本描述*/
	@Excel(name = "标本描述", width = 15)
    @ApiModelProperty(value = "标本描述")
    private java.lang.String discription;
	/**鉴定人*/
	@Excel(name = "鉴定人", width = 15)
    @ApiModelProperty(value = "鉴定人")
    private java.lang.String appraiser;
	/**鉴定日期*/
	@Excel(name = "鉴定日期", width = 15)
    @ApiModelProperty(value = "鉴定日期")
    private java.lang.String appraisalDate;
	/**产地经.*/
	@Excel(name = "产地经.", width = 15)
    @ApiModelProperty(value = "产地经.")
    private java.lang.String siteLongitude;
	/**产地纬.*/
	@Excel(name = "产地纬.", width = 15)
    @ApiModelProperty(value = "产地纬.")
    private java.lang.String siteLatitude;
	/**数量*/
	@Excel(name = "数量", width = 15)
    @ApiModelProperty(value = "数量")
    private java.lang.Integer quantity;
	/**自采号*/
	@Excel(name = "自采号", width = 15)
    @ApiModelProperty(value = "自采号")
    private java.lang.String serialNumber;
	/**尺寸*/
	@Excel(name = "尺寸", width = 15)
    @ApiModelProperty(value = "尺寸")
    private java.lang.String specimenSize;
	/**岩性*/
	@Excel(name = "岩性", width = 15)
    @ApiModelProperty(value = "岩性")
    private java.lang.String rockCharacter;
	/**地质层位*/
	@Excel(name = "地质层位", width = 15)
    @ApiModelProperty(value = "地质层位")
    private java.lang.String rockUnit;
	/**门*/
	@Excel(name = "门", width = 15)
    @ApiModelProperty(value = "门")
    private java.lang.String phylum;
	/**纲*/
	@Excel(name = "纲", width = 15)
    @ApiModelProperty(value = "纲")
    private java.lang.String cclass;
	/**目*/
	@Excel(name = "目", width = 15)
    @ApiModelProperty(value = "目")
    private java.lang.String oorder;
	/**科*/
	@Excel(name = "科", width = 15)
    @ApiModelProperty(value = "科")
    private java.lang.String family;
	/**属*/
	@Excel(name = "属", width = 15)
    @ApiModelProperty(value = "属")
    private java.lang.String genus;
	/**种*/
	@Excel(name = "种", width = 15)
    @ApiModelProperty(value = "种")
    private java.lang.String species;
	/**发掘单位*/
	@Excel(name = "发掘单位", width = 15)
    @ApiModelProperty(value = "发掘单位")
    private java.lang.String excavationUnit;
	/**发掘日期*/
	@Excel(name = "发掘日期", width = 15)
    @ApiModelProperty(value = "发掘日期")
    private java.lang.String excavationDate;
	/**发掘原因*/
	@Excel(name = "发掘原因", width = 15)
    @ApiModelProperty(value = "发掘原因")
    private java.lang.String excavationReason;
	/**发掘批复函*/
	@Excel(name = "发掘批复函", width = 15)
    @ApiModelProperty(value = "发掘批复函")
    private java.lang.String excavationReply;
	/**发掘评审*/
	@Excel(name = "发掘评审", width = 15)
    @ApiModelProperty(value = "发掘评审")
    private java.lang.String excavationReview;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remarks;
	/**自设编号*/
	@Excel(name = "自设编号", width = 15)
    @ApiModelProperty(value = "自设编号")
    private java.lang.String serialId;
	/**文献记录*/
	@Excel(name = "文献记录", width = 15)
    @ApiModelProperty(value = "文献记录")
    private java.lang.String documentation;
	/**修复记录*/
	@Excel(name = "修复记录", width = 15)
    @ApiModelProperty(value = "修复记录")
    private java.lang.String repairRecord;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
}
