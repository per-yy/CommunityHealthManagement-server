package per.yy.communityhealthmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.yy.communityhealthmanagement.entity.User;
import per.yy.communityhealthmanagement.exception.BusinessException;
import per.yy.communityhealthmanagement.mapper.HealthInfoMapper;
import per.yy.communityhealthmanagement.mapper.ResidentMapper;
import per.yy.communityhealthmanagement.mapper.UserMapper;
import per.yy.communityhealthmanagement.utils.JwtUtil;
import per.yy.communityhealthmanagement.utils.PasswordEncoder;
import per.yy.communityhealthmanagement.utils.ThreadLocalUtil;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ResidentMapper residentMapper;
    @Autowired
    private HealthInfoMapper healthInfoMapper;
    @Autowired
    private VerificationCodeService verificationCodeService;

    public String login(User user) {
        //查询用户是否存在，根据邮箱和用户角色查询
        User u = userMapper.selectByEmailAndRole(user);

        if (u == null) {
            throw new BusinessException("请先注册");
        }
        if (!PasswordEncoder.match(user.getPassword(), u.getPassword())) {
            throw new BusinessException("密码错误");
        }
        //jwt生成token
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", u.getEmail());
        return JwtUtil.genToken(claims);
    }

    @Transactional
    public void register(User user) {
        User u = userMapper.selectByEmail(user.getEmail());
        //根据邮箱判断用户是否已存在
        if (u != null) {
            throw new BusinessException("邮箱已被注册");
        }
        //验证验证码
        verificationCodeService.verify(user);
        //设置注册时间
        user.setCreateTime(LocalDateTime.now());
        //密码加密
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
        //插入新用户
        userMapper.insert(user);
        //如果是居民则再新建居民基本信息和健康信息
        if(user.getRole().equals("resident")){
            //查出刚插入的用户的id
            int userId = userMapper.selectIdByEmail(user.getEmail());
            residentMapper.insert(userId);
            int residentId=residentMapper.selectResidentIdByUserId(userId);
            healthInfoMapper.insertHealthInfo(residentId);
        }
    }

    public void changePassword(User user) {
        //判断用户是否已注册,根据邮箱和用户角色关联查询
        if (userMapper.selectByEmailAndRole(user) == null) {
            throw new BusinessException("请先注册");
        }
        //验证验证码
        verificationCodeService.verify(user);
        //密码加密
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
        //更新密码
        userMapper.updatePassword(user);
    }

    public User getUserInfo() {
        //从thread local中取出邮箱
        Map<String, Object> map = ThreadLocalUtil.get();
        String email = (String) map.get("email");
        //根据邮箱查出用户
        User u = userMapper.selectByEmail(email);
        //清空密码
        u.setPassword(null);
        return u;
    }

    public void updateUserInfo(User user) {
        //user中包含不能修改的邮箱
        userMapper.update(user);
    }
}
