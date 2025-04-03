package per.yy.communityhealthmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.yy.communityhealthmanagement.entity.ResidentInfo;
import per.yy.communityhealthmanagement.result.Result;
import per.yy.communityhealthmanagement.service.ResidentService;

@RestController
@RequestMapping("/resident")
public class ResidentController {
    @Autowired
    private ResidentService residentService;

    //居民端查询居民基本信息
    @GetMapping("/query")
    public Result getResidentInfo(){
        return Result.success(residentService.getResidentInfo());
    }

    //居民端修改居民基本信息
    @PostMapping("/update")
    public Result updateBasicInfo(@RequestBody ResidentInfo residentInfo){
        residentService.updateBasicInfo(residentInfo);
        return Result.success();
    }
}


