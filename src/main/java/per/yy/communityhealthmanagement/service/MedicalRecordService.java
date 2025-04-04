package per.yy.communityhealthmanagement.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.yy.communityhealthmanagement.dto.PageQueryDto;
import per.yy.communityhealthmanagement.entity.MedicalRecord;
import per.yy.communityhealthmanagement.entity.PageBean;
import per.yy.communityhealthmanagement.mapper.MedicalRecordMapper;
import per.yy.communityhealthmanagement.mapper.ResidentMapper;
import per.yy.communityhealthmanagement.mapper.UserMapper;

import java.util.List;

@Service
public class MedicalRecordService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ResidentMapper residentMapper;
    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    public PageBean<MedicalRecord> getMedicalRecord(String email, PageQueryDto pageQueryDto) {
        //根据邮箱查出用户id
        int userId = userMapper.selectIdByEmail(email);
        //根据用户id查出居民id
        int residentId = residentMapper.selectResidentIdByUserId(userId);

        PageBean<MedicalRecord> pageBean = new PageBean<>();
        // 开启分页（必须紧邻分页查询的SQL）
        PageHelper.startPage(pageQueryDto.getPageNum(), pageQueryDto.getPageSize());
        // 执行分页查询（此处返回的List实际是Page类型）
        List<MedicalRecord> medicalRecords = medicalRecordMapper.selectMedicalRecordByResidentId(residentId);
        // 安全转换：通过PageInfo获取分页数据
        PageInfo<MedicalRecord> pageInfo = new PageInfo<>(medicalRecords);
        // 填充PageBean
        pageBean.setItems(pageInfo.getList());
        pageBean.setTotal(pageInfo.getTotal());
        return pageBean;
    }

    public void addMedicalRecord(String email, MedicalRecord medicalRecord) {
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
