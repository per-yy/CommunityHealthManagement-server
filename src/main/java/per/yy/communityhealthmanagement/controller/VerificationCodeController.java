package per.yy.communityhealthmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import per.yy.communityhealthmanagement.result.Result;
import per.yy.communityhealthmanagement.service.VerificationCodeService;

import java.util.Map;

@RestController
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;

    @PostMapping("/send")
    public Result send(@RequestBody Map<String, String> params) {
        String email = params.get("email");
        verificationCodeService.send(email);
        return Result.success();
    }

}
