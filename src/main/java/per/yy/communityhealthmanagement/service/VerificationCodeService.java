package per.yy.communityhealthmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import per.yy.communityhealthmanagement.entity.EmailProperties;
import per.yy.communityhealthmanagement.entity.User;
import per.yy.communityhealthmanagement.exception.BusinessException;
import per.yy.communityhealthmanagement.utils.MailUtil;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {
    private static final int CODE_LENGTH = 6;
    private static final Random RANDOM = new Random();

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    EmailProperties emailProperties;

    //生成验证码 静态方法
    public static String generateCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(RANDOM.nextInt(10)); // 添加一位随机数字
        }
        return code.toString();
    }

    //发送验证码
    public void send(String email) {
        String code = VerificationCodeService.generateCode();
        try {
            MailUtil.sendMail(emailProperties, email, "社区健康管理系统", "你的验证码是：" + code + "<br/>验证码在3分钟内有效 请勿泄露给他人");
            //验证码存入redis 持续3分钟 与前端保持一致
            redisTemplate.opsForValue().set(email, code, 3, TimeUnit.MINUTES);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //验证验证码
    public void verify(User user) {
        String verificationCode = redisTemplate.opsForValue().get(user.getEmail());
        //判断验证码是否失效
        if (verificationCode == null) {
            throw new BusinessException("验证码已过期");
        }
        //判断验证码是否错误
        if (!verificationCode.equals(user.getVerificationCode())) {
            throw new BusinessException("验证码错误");
        }
    }
}
