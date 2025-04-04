package per.yy.communityhealthmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.yy.communityhealthmanagement.dto.PageQueryDto;
import per.yy.communityhealthmanagement.entity.MedicalRecord;
import per.yy.communityhealthmanagement.entity.PageBean;
import per.yy.communityhealthmanagement.result.Result;
import per.yy.communityhealthmanagement.service.MedicalRecordService;
import per.yy.communityhealthmanagement.utils.ThreadLocalUtil;

import java.util.Map;

@RestController
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;

    //查询居民就诊记录
    @PostMapping("/medicalRecord/query")
    public Result getMedicalRecord(@RequestBody PageQueryDto pageQueryDto){
        //从thread local中取出邮箱
        Map<String, Object> map = ThreadLocalUtil.get();
        String email = (String) map.get("email");
        return Result.success(medicalRecordService.getMedicalRecord(email,pageQueryDto));
    }

    //添加居民就诊记录
    @PostMapping("/medicalRecord/add")
    public Result addMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        //从thread local中取出邮箱
        Map<String, Object> map = ThreadLocalUtil.get();
        String email = (String) map.get("email");
        medicalRecordService.addMedicalRecord(email,medicalRecord);
        return Result.success();
    }

    //删除居民就诊记录
    @DeleteMapping("/medicalRecord/delete/{id}")
    public Result deleteMedicalRecord(@PathVariable("id") int id){
        medicalRecordService.deleteMedicalRecordById(id);
        return Result.success();
    }
}
