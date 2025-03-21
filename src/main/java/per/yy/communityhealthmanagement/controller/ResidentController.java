package per.yy.communityhealthmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.yy.communityhealthmanagement.entity.HealthInfo;
import per.yy.communityhealthmanagement.entity.ResidentInfo;
import per.yy.communityhealthmanagement.result.Result;
import per.yy.communityhealthmanagement.service.ResidentService;

@RestController
@RequestMapping("/resident")
public class ResidentController {
    @Autowired
    private ResidentService residentService;

    //查询居民基本信息
    @GetMapping("/info/query")
    public Result getResidentInfo(){
        return Result.success(residentService.getResidentInfo());
    }

    //查询居民健康信息
    @GetMapping("/health/query")
    public Result getHealthInfo(){
        return Result.success(residentService.getHealthInfo());
    }

    //修改居民基本信息
    @PostMapping("/info/update")
    public Result updateBasicInfo(@RequestBody ResidentInfo residentInfo){
        residentService.updateBasicInfo(residentInfo);
        return Result.success();
    }
    //修改居民健康信息
    @PostMapping("/health/update")
    public Result updateHealthInfo(@RequestBody HealthInfo healthInfo){
        residentService.updateHealthInfo(healthInfo);
        return Result.success();
    }
}
