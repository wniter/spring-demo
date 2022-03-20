package com.example.springboot.fastjson.controller;
import com.alibaba.fastjson.annotation.JSONField;

import com.example.springboot.fastjson.vo.ModelResult;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;


@RestController
public class TestController {

    @GetMapping("/json")
    public ModelResult<JsonBean> testJson() {
        ModelResult<JsonBean> result = new ModelResult<>();
        JsonBean jsonBean = new JsonBean();
        jsonBean.setBirthDay(new Date());
        jsonBean.setName("测试");
        result.setData(jsonBean);
        // 效果
        /*{
            "code": "200",
                "data": {
            "birthDay": "2019年05月12日",
                    "name": "测试",
                    "qqList": []
        },
            "msg": ""
        }*/
        return result;
    }


    @Data
    class JsonBean {
        private String name;
        @JSONField(format = "yyyy年MM月dd日")
        private Date birthDay;
        private List<String> qqList;
    }
}