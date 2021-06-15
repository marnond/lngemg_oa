package org.jeecg.modules.tree.entity;

import java.io.Serializable;
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
import java.io.UnsupportedEncodingException;

/**
 * @Description: 行政区划
 * @Author: jeecg-boot
 * @Date:   2021-06-14
 * @Version: V1.0
 */
@Data
@TableName("fos_sys_area")
@ApiModel(value="fos_sys_area对象", description="行政区划")
public class FosSysArea implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**父级节点*/
	@Excel(name = "父级节点", width = 15)
    @ApiModelProperty(value = "父级节点")
    private java.lang.String pid;
	/**所有父级节点*/
	@Excel(name = "所有父级节点", width = 15)
    @ApiModelProperty(value = "所有父级节点")
    private java.lang.String pids;
	/**是否有子节点*/
	@Excel(name = "是否有子节点", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @ApiModelProperty(value = "是否有子节点")
    private java.lang.String hasChild;
	/**是否最末级*/
	@Excel(name = "是否最末级", width = 15)
    @ApiModelProperty(value = "是否最末级")
    private java.lang.String treeLeaf;
	/**层次级别*/
	@Excel(name = "层次级别", width = 15)
    @ApiModelProperty(value = "层次级别")
    private java.lang.String treeLevel;
	/**全节点名*/
	@Excel(name = "全节点名", width = 15)
    @ApiModelProperty(value = "全节点名")
    private java.lang.String treeNames;
	/**区域名称*/
	@Excel(name = "区域名称", width = 15)
    @ApiModelProperty(value = "区域名称")
    private java.lang.String areaName;
	/**区域类型*/
	@Excel(name = "区域类型", width = 15)
    @ApiModelProperty(value = "区域类型")
    private java.lang.String areaType;
	/**状态（0正常 1删除 2停用）*/
	@Excel(name = "状态（0正常 1删除 2停用）", width = 15)
    @ApiModelProperty(value = "状态（0正常 1删除 2停用）")
    private java.lang.String status;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
}
