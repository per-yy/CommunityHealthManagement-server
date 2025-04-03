package per.yy.communityhealthmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import per.yy.communityhealthmanagement.entity.HealthInfo;
import per.yy.communityhealthmanagement.result.Result;
import per.yy.communityhealthmanagement.service.HeathInfoService;

@RestController
public class HealthInfoController {
    @Autowired
    private HeathInfoService heathInfoService;

    //居民端修改居民健康信息
    @PostMapping("/health/update")
    public Result updateHealthInfo(@RequestBody HealthInfo healthInfo){
        heathInfoService.updateHealthInfo(healthInfo);
        return Result.success();
    }

    //居民端查询居民健康信息
    @GetMapping("/health/query")
    public Result getHealthInfo(){
        return Result.success(heathInfoService.getHealthInfo());
    }
}
