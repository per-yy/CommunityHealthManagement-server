package per.yy.communityhealthmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.yy.communityhealthmanagement.entity.MedicalRecord;
import per.yy.communityhealthmanagement.mapper.MedicalRecordMapper;
import per.yy.communityhealthmanagement.mapper.ResidentMapper;
import per.yy.communityhealthmanagement.mapper.UserMapper;
import per.yy.communityhealthmanagement.utils.ThreadLocalUtil;

import java.util.List;
import java.util.Map;

@Service
public class MedicalRecordService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ResidentMapper residentMapper;
    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    public List<MedicalRecord> getMedicalRecord() {
        //从thread local中取出邮箱
        Map<String, Object> map = ThreadLocalUtil.get();
        String email = (String) map.get("email");
        //根据邮箱查出用户id
        int userId = userMapper.selectIdByEmail(email);
        //根据用户id查出居民id
        int residentId = residentMapper.selectResidentIdByUserId(userId);
        return medicalRecordMapper.selectMedicalRecordByResidentId(residentId);
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) {
        //从thread local中取出邮箱
        Map<String, Object> map = ThreadLocalUtil.get();
        String email = (String) map.get("email");
        //根据邮箱查出用户id
        int userId = userMapper.selectIdByEmail(email);
        //根据用户id查出居民id
        int residentId = residentMapper.selectResidentIdByUserId(userId);
        medicalRecordMapper.insertMedicalRecord(residentId, medicalRecord);
    }

    public void deleteMedicalRecordById(int id) {
        medicalRecordMapper.deleteMedicalRecordById(id);
    }
}
