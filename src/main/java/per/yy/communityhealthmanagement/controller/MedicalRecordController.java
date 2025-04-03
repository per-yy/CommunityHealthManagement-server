package per.yy.communityhealthmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.yy.communityhealthmanagement.entity.MedicalRecord;
import per.yy.communityhealthmanagement.result.Result;
import per.yy.communityhealthmanagement.service.MedicalRecordService;

@RestController
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;

    //查询居民就诊记录
    @GetMapping("/medicalRecord/query")
    public Result getMedicalRecord(){
        return Result.success(medicalRecordService.getMedicalRecord());
    }

    //添加居民就诊记录
    @PostMapping("/medicalRecord/add")
    public Result addMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        medicalRecordService.addMedicalRecord(medicalRecord);
        return Result.success();
    }

    //删除居民就诊记录
    @DeleteMapping("/medicalRecord/delete/{id}")
    public Result deleteMedicalRecord(@PathVariable("id") int id){
        medicalRecordService.deleteMedicalRecordById(id);
        return Result.success();
    }
}
