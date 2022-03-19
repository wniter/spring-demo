package com.example.springboot.mybatis;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;
import java.util.HashMap;
import java.util.Map;

/**
 * Mapper生成器
 *
 * @create 2022-03-05 19:48
 */
public class MapperNameGenerator implements BeanNameGenerator {

    Map<String, Integer> mapper = new HashMap<>();
    private final static int index01 = 1;

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {

        //获取类的名字
        String shortName = ClassUtils.getShortName(definition.getBeanClassName());
        //将类名转化成规范变量
        String beanName = Introspector.decapitalize(shortName);
        //判断是否存在，存在则在后面加序号
        if (mapper.containsKey(beanName)) {
            int index = mapper.get(beanName) + 1;
            mapper.put(beanName, index);
        } else {
            mapper.put(beanName,index01);
        }
        return beanName;
    }
}
