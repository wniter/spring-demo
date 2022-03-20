package com.example.springboot.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Data
@Component
@TableName("user")
@ToString
public class User implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    private String name;
    private Integer age;
    private String email;


/**
 *     public enum IdType {
 *         AUTO(0),//数据库ID自增
 *         NONE(1),//未设置主键
 *         INPUT(2),//手动输入
 *         ASSIGN_ID(3),//默认的全局唯一主键
 *         ASSIGN_UUID(4);//全局唯一id uuid
 *         private final int key; //
 *         IdType(int key) {
 *             this.key = key;
 *         }
 *     }
 */


//    @Bean
//    public IKeyGenerator keyGenerator() {
//        H2KeyGenerator h2KeyGenerator = new H2KeyGenerator();
//        h2KeyGenerator.executeSql(id.toString());
//        return h2KeyGenerator;
//    }
//    @Bean
//    public MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
//        return plusProperties -> plusProperties.getGlobalConfig().getDbConfig().setKeyGenerator(new H2KeyGenerator());
//    }
}