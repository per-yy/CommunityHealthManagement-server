package per.yy.communityhealthmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.yy.communityhealthmanagement.entity.ResidentInfo;
import per.yy.communityhealthmanagement.mapper.ResidentMapper;
import per.yy.communityhealthmanagement.mapper.UserMapper;
import per.yy.communityhealthmanagement.utils.ThreadLocalUtil;

import java.util.Map;

@Service
public class ResidentService {
    @Autowired
    private ResidentMapper residentMapper;

    @Autowired
    private UserMapper userMapper;
    public ResidentInfo getResidentInfo() {
        //从thread local中取出邮箱
        Map<String, Object> map = ThreadLocalUtil.get();
        String email = (String) map.get("email");
        //根据邮箱查出用户id
        int userId=userMapper.selectIdByEmail(email);
        //根据用户id查出关联的居民信息
        return residentMapper.selectResidentInfoByUserId(userId);
    }

    @Transactional
    public void updateBasicInfo(ResidentInfo residentInfo) {
        //从thread local中取出邮箱
        Map<String, Object> map = ThreadLocalUtil.get();
        String email = (String) map.get("email");
        //根据邮箱查出用户id
        int userId=userMapper.selectIdByEmail(email);
        //更新居民信息
        residentMapper.updateResidentInfo(userId,residentInfo);
    }
}
