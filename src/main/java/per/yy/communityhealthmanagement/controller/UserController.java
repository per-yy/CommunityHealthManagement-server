package per.yy.communityhealthmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import per.yy.communityhealthmanagement.entity.User;
import per.yy.communityhealthmanagement.result.Result;
import per.yy.communityhealthmanagement.service.UserService;
import per.yy.communityhealthmanagement.utils.ThreadLocalUtil;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return Result.success(userService.login(user));
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        userService.register(user);
        return Result.success();
    }

    @PostMapping("/changePassword")
    public Result changePassword(@RequestBody User user) {
        userService.changePassword(user);
        return Result.success();
    }

    @GetMapping("/userInfo/query")
    public Result getUserInfo() {
        //从thread local中取出邮箱
        Map<String, Object> map = ThreadLocalUtil.get();
        String email = (String) map.get("email");
        return Result.success(userService.getUserInfo(email));
    }

    @PostMapping("/userInfo/update")
    public Result updateUserInfo(@RequestBody User user) {
        userService.updateUserInfo(user);
        return Result.success();
    }
}
