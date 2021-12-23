package cn.authing.springsecurityoidc.controller;

import cn.hutool.http.HttpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CallBackController {


    @GetMapping("/callback")
    public String getTokenByCode(String code){
        //'https://<你的应用域名>.authing.cn/oidc/token
//        --data-urlencode 'code=61yhuOVrgyhKlFTU~bnEKA_fnnz' \
//        --data-urlencode 'client_id=5e37979f7b757ead14c534af' \
//        --data-urlencode 'client_secret=64b517f8de3648091654eb4ee9b479d3' \
//        --data-urlencode 'grant_type=authorization_code' \
//        --data-urlencode 'redirect_uri=https://baidu.com'

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("code",code);
        paramMap.put("client_id","{替换为你的 client_id 如：61319680ea8b30c9ca9ca071}");
        paramMap.put("client_secret","{替换为你的 client-secret 如：cc8a53d7e22ce6b845330ced6cc5d9f2}");
        paramMap.put("grant_type","authorization_code");
        paramMap.put("redirect_uri","{替换为你的 redirect-uri 如http://localhost:8080/callback}");
        String result = HttpUtil.post("https://cjtjls-demo.authing.cn/oidc/token", paramMap);
        return result;
    }
}
