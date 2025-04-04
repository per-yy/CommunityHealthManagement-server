package per.yy.communityhealthmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.yy.communityhealthmanagement.dto.PageQueryDto;
import per.yy.communityhealthmanagement.entity.Consultation;
import per.yy.communityhealthmanagement.result.Result;
import per.yy.communityhealthmanagement.service.ConsultationService;
import per.yy.communityhealthmanagement.utils.ThreadLocalUtil;

import java.util.Map;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {
    @Autowired
    private ConsultationService consultationService;

    //居民端查询咨询
    @PostMapping("/query")
    public Result getConsultation(@RequestBody PageQueryDto pageQueryDto) {
        //从thread local中取出邮箱
        Map<String, Object> map = ThreadLocalUtil.get();
        String email = (String) map.get("email");
        return Result.success(consultationService.getConsultation(email, pageQueryDto));
    }

    //删除咨询
    @DeleteMapping("/delete/{id}")
    public Result deleteConsultation(@PathVariable("id") int id) {
        consultationService.deleteConsultation(id);
        return Result.success();
    }

    //添加咨询
    @PostMapping("/add")
    public Result addConsultation(@RequestBody Consultation consultation) {
        //从thread local中取出邮箱
        Map<String, Object> map = ThreadLocalUtil.get();
        String email = (String) map.get("email");
        consultationService.addConsultation(email, consultation);
        return Result.success();
    }
}
