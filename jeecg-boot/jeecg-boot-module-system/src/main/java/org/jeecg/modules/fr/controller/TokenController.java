package org.jeecg.modules.fr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.fr.util.FrTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "帆软token")
@RestController
@RequestMapping("/fr")
@Slf4j
public class TokenController {
    @Autowired
    private FrTokenUtil frTokenUtil;

    @AutoLog(value = "获取token")
    @ApiOperation(value = "获取token", notes = "获取token")
    @GetMapping(value = "/getToken")
    public Result<?> getToken(@RequestParam(name = "path") String path) {
        return Result.OK(frTokenUtil.getToken(path));
    }
}
