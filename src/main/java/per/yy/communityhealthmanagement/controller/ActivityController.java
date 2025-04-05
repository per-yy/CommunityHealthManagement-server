package per.yy.communityhealthmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.yy.communityhealthmanagement.dto.PageQueryDto;
import per.yy.communityhealthmanagement.result.Result;
import per.yy.communityhealthmanagement.service.ActivityService;
import per.yy.communityhealthmanagement.utils.ThreadLocalUtil;

import java.util.Map;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    //分页查询活动
    @PostMapping("/query")
    public Result getActivity(@RequestBody PageQueryDto pageQueryDto){
        //从thread local中取出邮箱
        Map<String, Object> map = ThreadLocalUtil.get();
        String email = (String) map.get("email");
        return Result.success(activityService.getActivity(email,pageQueryDto));
    }
    //报名活动
    @GetMapping("/join/{activityId}")
    public Result joinActivity(@PathVariable int activityId){
        //从thread local中取出邮箱
        Map<String, Object> map = ThreadLocalUtil.get();
        String email = (String) map.get("email");
        activityService.join(email,activityId);
        return Result.success();
    }

}
