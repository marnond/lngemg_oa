package com.lngemg.modules.fossil.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import com.lngemg.modules.fossil.entity.FosSpecimenRegisterInfo;
import com.lngemg.modules.fossil.service.IFosSpecimenRegisterInfoService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 古生物化石标本登记表
 * @Author: jeecg-boot
 * @Date:   2021-06-08
 * @Version: V1.0
 */
@Api(tags="古生物化石标本登记表")
@RestController
@RequestMapping("/fossil/fosSpecimenRegisterInfo")
@Slf4j
public class FosSpecimenRegisterInfoController extends JeecgController<FosSpecimenRegisterInfo, IFosSpecimenRegisterInfoService> {
	@Autowired
	private IFosSpecimenRegisterInfoService fosSpecimenRegisterInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param fosSpecimenRegisterInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "古生物化石标本登记表-分页列表查询")
	@ApiOperation(value="古生物化石标本登记表-分页列表查询", notes="古生物化石标本登记表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(FosSpecimenRegisterInfo fosSpecimenRegisterInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<FosSpecimenRegisterInfo> queryWrapper = QueryGenerator.initQueryWrapper(fosSpecimenRegisterInfo, req.getParameterMap());
		Page<FosSpecimenRegisterInfo> page = new Page<FosSpecimenRegisterInfo>(pageNo, pageSize);
		IPage<FosSpecimenRegisterInfo> pageList = fosSpecimenRegisterInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param fosSpecimenRegisterInfo
	 * @return
	 */
	@AutoLog(value = "古生物化石标本登记表-添加")
	@ApiOperation(value="古生物化石标本登记表-添加", notes="古生物化石标本登记表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody FosSpecimenRegisterInfo fosSpecimenRegisterInfo) {
		fosSpecimenRegisterInfoService.save(fosSpecimenRegisterInfo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param fosSpecimenRegisterInfo
	 * @return
	 */
	@AutoLog(value = "古生物化石标本登记表-编辑")
	@ApiOperation(value="古生物化石标本登记表-编辑", notes="古生物化石标本登记表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody FosSpecimenRegisterInfo fosSpecimenRegisterInfo) {
		fosSpecimenRegisterInfoService.updateById(fosSpecimenRegisterInfo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "古生物化石标本登记表-通过id删除")
	@ApiOperation(value="古生物化石标本登记表-通过id删除", notes="古生物化石标本登记表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		fosSpecimenRegisterInfoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "古生物化石标本登记表-批量删除")
	@ApiOperation(value="古生物化石标本登记表-批量删除", notes="古生物化石标本登记表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.fosSpecimenRegisterInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "古生物化石标本登记表-通过id查询")
	@ApiOperation(value="古生物化石标本登记表-通过id查询", notes="古生物化石标本登记表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		FosSpecimenRegisterInfo fosSpecimenRegisterInfo = fosSpecimenRegisterInfoService.getById(id);
		if(fosSpecimenRegisterInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(fosSpecimenRegisterInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param fosSpecimenRegisterInfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, FosSpecimenRegisterInfo fosSpecimenRegisterInfo) {
        return super.exportXls(request, fosSpecimenRegisterInfo, FosSpecimenRegisterInfo.class, "古生物化石标本登记表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, FosSpecimenRegisterInfo.class);
    }

}
