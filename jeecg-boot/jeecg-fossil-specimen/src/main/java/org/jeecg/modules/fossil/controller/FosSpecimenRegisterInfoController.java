package org.jeecg.modules.fossil.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.fossil.entity.FosSpecimenCirculationInfo;
import org.jeecg.modules.fossil.entity.FosSpecimenRegisterInfo;
import org.jeecg.modules.fossil.vo.FosSpecimenRegisterInfoPage;
import org.jeecg.modules.fossil.service.IFosSpecimenRegisterInfoService;
import org.jeecg.modules.fossil.service.IFosSpecimenCirculationInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 古生物化石标本登记表
 * @Author: jeecg-boot
 * @Date:   2021-06-25
 * @Version: V1.0
 */
@Api(tags="古生物化石标本登记表")
@RestController
@RequestMapping("/fossil/fosSpecimenRegisterInfo")
@Slf4j
public class FosSpecimenRegisterInfoController {
	@Autowired
	private IFosSpecimenRegisterInfoService fosSpecimenRegisterInfoService;
	@Autowired
	private IFosSpecimenCirculationInfoService fosSpecimenCirculationInfoService;
	
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
	 * @param fosSpecimenRegisterInfoPage
	 * @return
	 */
	@AutoLog(value = "古生物化石标本登记表-添加")
	@ApiOperation(value="古生物化石标本登记表-添加", notes="古生物化石标本登记表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody FosSpecimenRegisterInfoPage fosSpecimenRegisterInfoPage) {
		FosSpecimenRegisterInfo fosSpecimenRegisterInfo = new FosSpecimenRegisterInfo();
		BeanUtils.copyProperties(fosSpecimenRegisterInfoPage, fosSpecimenRegisterInfo);
		fosSpecimenRegisterInfoService.saveMain(fosSpecimenRegisterInfo, fosSpecimenRegisterInfoPage.getFosSpecimenCirculationInfoList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param fosSpecimenRegisterInfoPage
	 * @return
	 */
	@AutoLog(value = "古生物化石标本登记表-编辑")
	@ApiOperation(value="古生物化石标本登记表-编辑", notes="古生物化石标本登记表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody FosSpecimenRegisterInfoPage fosSpecimenRegisterInfoPage) {
		FosSpecimenRegisterInfo fosSpecimenRegisterInfo = new FosSpecimenRegisterInfo();
		BeanUtils.copyProperties(fosSpecimenRegisterInfoPage, fosSpecimenRegisterInfo);
		FosSpecimenRegisterInfo fosSpecimenRegisterInfoEntity = fosSpecimenRegisterInfoService.getById(fosSpecimenRegisterInfo.getId());
		if(fosSpecimenRegisterInfoEntity==null) {
			return Result.error("未找到对应数据");
		}
		fosSpecimenRegisterInfoService.updateMain(fosSpecimenRegisterInfo, fosSpecimenRegisterInfoPage.getFosSpecimenCirculationInfoList());
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
		fosSpecimenRegisterInfoService.delMain(id);
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
		this.fosSpecimenRegisterInfoService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
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
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "古生物化石标本流转记录表通过主表ID查询")
	@ApiOperation(value="古生物化石标本流转记录表主表ID查询", notes="古生物化石标本流转记录表-通主表ID查询")
	@GetMapping(value = "/queryFosSpecimenCirculationInfoByMainId")
	public Result<?> queryFosSpecimenCirculationInfoListByMainId(@RequestParam(name="id",required=true) String id) {
		List<FosSpecimenCirculationInfo> fosSpecimenCirculationInfoList = fosSpecimenCirculationInfoService.selectByMainId(id);
		return Result.OK(fosSpecimenCirculationInfoList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param fosSpecimenRegisterInfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, FosSpecimenRegisterInfo fosSpecimenRegisterInfo) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<FosSpecimenRegisterInfo> queryWrapper = QueryGenerator.initQueryWrapper(fosSpecimenRegisterInfo, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<FosSpecimenRegisterInfo> queryList = fosSpecimenRegisterInfoService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<FosSpecimenRegisterInfo> fosSpecimenRegisterInfoList = new ArrayList<FosSpecimenRegisterInfo>();
      if(oConvertUtils.isEmpty(selections)) {
          fosSpecimenRegisterInfoList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          fosSpecimenRegisterInfoList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<FosSpecimenRegisterInfoPage> pageList = new ArrayList<FosSpecimenRegisterInfoPage>();
      for (FosSpecimenRegisterInfo main : fosSpecimenRegisterInfoList) {
          FosSpecimenRegisterInfoPage vo = new FosSpecimenRegisterInfoPage();
          BeanUtils.copyProperties(main, vo);
          List<FosSpecimenCirculationInfo> fosSpecimenCirculationInfoList = fosSpecimenCirculationInfoService.selectByMainId(main.getId());
          vo.setFosSpecimenCirculationInfoList(fosSpecimenCirculationInfoList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "古生物化石标本登记表列表");
      mv.addObject(NormalExcelConstants.CLASS, FosSpecimenRegisterInfoPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("古生物化石标本登记表数据", "导出人:"+sysUser.getRealname(), "古生物化石标本登记表"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<FosSpecimenRegisterInfoPage> list = ExcelImportUtil.importExcel(file.getInputStream(), FosSpecimenRegisterInfoPage.class, params);
              for (FosSpecimenRegisterInfoPage page : list) {
                  FosSpecimenRegisterInfo po = new FosSpecimenRegisterInfo();
                  BeanUtils.copyProperties(page, po);
                  fosSpecimenRegisterInfoService.saveMain(po, page.getFosSpecimenCirculationInfoList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
