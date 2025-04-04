package per.yy.communityhealthmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.yy.communityhealthmanagement.entity.ResidentInfo;
import per.yy.communityhealthmanagement.result.Result;
import per.yy.communityhealthmanagement.service.ResidentService;
import per.yy.communityhealthmanagement.utils.ThreadLocalUtil;

import java.util.Map;

@RestController
@RequestMapping("/resident")
public class ResidentController {
    @Autowired
    private ResidentService residentService;

    //居民端查询居民基本信息
    @GetMapping("/query")
    public Result getResidentInfo(){
        //从thread local中取出邮箱
        Map<String, Object> map = ThreadLocalUtil.get();
        String email = (String) map.get("email");
        return Result.success(residentService.getResidentInfo(email));
    }

    //居民端修改居民基本信息
    @PostMapping("/update")
    public Result updateBasicInfo(@RequestBody ResidentInfo residentInfo){
        //从thread local中取出邮箱
        Map<String, Object> map = ThreadLocalUtil.get();
        String email = (String) map.get("email");
        residentService.updateBasicInfo(email,residentInfo);
        return Result.success();
    }
}


