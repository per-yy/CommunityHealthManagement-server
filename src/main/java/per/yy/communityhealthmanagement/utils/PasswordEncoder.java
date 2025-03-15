package per.yy.communityhealthmanagement.utils;


import org.springframework.util.DigestUtils;

public class PasswordEncoder {

    public static String encode(String rawPassword) {
        return DigestUtils.md5DigestAsHex(rawPassword.getBytes());
    }
    public static boolean match(String rawPassword,String encodedPassword) {
        return DigestUtils.md5DigestAsHex(rawPassword.getBytes()).equals(encodedPassword);
    }
}