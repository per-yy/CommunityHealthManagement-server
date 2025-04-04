package per.yy.communityhealthmanagement.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.yy.communityhealthmanagement.dto.PageQueryDto;
import per.yy.communityhealthmanagement.entity.Consultation;
import per.yy.communityhealthmanagement.entity.PageBean;
import per.yy.communityhealthmanagement.mapper.ConsultationMapper;
import per.yy.communityhealthmanagement.mapper.ResidentMapper;
import per.yy.communityhealthmanagement.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultationService {
    @Autowired
    private ConsultationMapper consultationMapper;
    @Autowired
    private ResidentMapper residentMapper;
    @Autowired
    private UserMapper userMapper;

    public PageBean<Consultation> getConsultation(String email, PageQueryDto pageQueryDto) {
        // 先查询关联的居民ID（分页前完成其他数据库操作）
        int userId = userMapper.selectIdByEmail(email);
        int residentId = residentMapper.selectResidentIdByUserId(userId);

        PageBean<Consultation> pageBean = new PageBean<>();
        // 开启分页（必须紧邻分页查询的SQL）
        PageHelper.startPage(pageQueryDto.getPageNum(), pageQueryDto.getPageSize());
        // 执行分页查询（此处返回的List实际是Page类型）
        List<Consultation> consultations = consultationMapper.selectByResidentId(residentId);
        // 安全转换：通过PageInfo获取分页数据
        PageInfo<Consultation> pageInfo = new PageInfo<>(consultations);
        // 填充PageBean
        pageBean.setItems(pageInfo.getList());
        pageBean.setTotal(pageInfo.getTotal());

        return pageBean;
    }

    public void deleteConsultation(int id) {
        consultationMapper.deleteById(id);
    }

    public void addConsultation(String email,Consultation consultation) {
        //根据邮箱查出用户id
        int userId = userMapper.selectIdByEmail(email);
        //根据用户id查出居民id
        int residentId = residentMapper.selectResidentIdByUserId(userId);
        //设置居民id
        consultation.setResidentId(residentId);
        //设置发起时间
        consultation.setCreateTime(LocalDateTime.now());
        consultationMapper.insert(consultation);
    }
}
