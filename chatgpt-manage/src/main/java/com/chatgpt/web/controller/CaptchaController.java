package com.chatgpt.web.controller;

import com.chatgpt.constant.Constant;
import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码控制器
 */
@RestController
@Api(tags = {"验证生成"})
@RequestMapping("/captcha")
public class CaptchaController extends BaseController {
    @Value("${captcha.len:4}")
    private int CAPTCHA_LEN;
    @Value("${captcha.width:150}")
    private int CAPTCHA_WIDTH;
    @Value("${captcha.height:48}")
    private int CAPTCHA_HEIGHT;
    @Value("${captcha.expiration:1}")
    private int CAPTCHA_EXPIRATION;

    /**
     * 生成验证
     *
     * @param token
     * @param response
     */
    @GetMapping("/{token}/generate")
    @ApiOperation(value = "生成验证")
    public void generate(@PathVariable("token") String token, HttpServletResponse response) throws IOException {
        SpecCaptcha specCaptcha = new SpecCaptcha(CAPTCHA_WIDTH, CAPTCHA_HEIGHT, CAPTCHA_LEN);
        stringRedisTemplate.opsForValue().set(Constant.MANAGE_CAPTCHA_PREFIX + token, specCaptcha.text().toLowerCase(), CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        specCaptcha.out(response.getOutputStream());
    }

}
