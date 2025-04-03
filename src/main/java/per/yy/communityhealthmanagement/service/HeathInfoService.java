package per.yy.communityhealthmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.yy.communityhealthmanagement.entity.HealthInfo;
import per.yy.communityhealthmanagement.mapper.HealthInfoMapper;
import per.yy.communityhealthmanagement.mapper.ResidentMapper;
import per.yy.communityhealthmanagement.mapper.UserMapper;
import per.yy.communityhealthmanagement.utils.ThreadLocalUtil;

import java.util.Map;

@Service
public class HeathInfoService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ResidentMapper residentMapper;
    @Autowired
    private HealthInfoMapper healthInfoMapper;

    public HealthInfo getHealthInfo() {
        //从thread local中取出邮箱
        Map<String, Object> map = ThreadLocalUtil.get();
        String email = (String) map.get("email");
        //根据邮箱查出用户id
        int userId=userMapper.selectIdByEmail(email);
        //根据用户id查出居民id
        int residentId=residentMapper.selectResidentIdByUserId(userId);
        //根据居民id查出居民健康信息
        return healthInfoMapper.selectHealthInfoByResidentId(residentId);
    }

    public void updateHealthInfo(HealthInfo healthInfo) {
        //从thread local中取出邮箱
        Map<String, Object> map = ThreadLocalUtil.get();
        String email = (String) map.get("email");
        //根据邮箱查出用户id
        int userId=userMapper.selectIdByEmail(email);
        //根据用户id查出居民id
        int residentId=residentMapper.selectResidentIdByUserId(userId);
        healthInfoMapper.updateHealthInfo(residentId,healthInfo);
    }
}
