package com.example.springboot.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 为了开发方便，一般使用 <String, Object>
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 为了开发方便，一般使用 <String, Object>
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        // 序列化配置
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // String 的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key 采用 String 的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash 的 key 也采用 String 的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value 序列化方式采用 Jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash 的 value 序列化方式采用 Jackson
        template.setHashKeySerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }

//    @Bean(name = "ooxx")
//    public StringRedisTemplate ooxx(RedisConnectionFactory fc){
//
//        StringRedisTemplate tp = new StringRedisTemplate(fc);
//
//        tp.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
//        return  tp ;
//    }


//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory factory) {
//         RedisTemplate<String, Object> template = new RedisTemplate();
//         template.setConnectionFactory(factory);
// 
//         // ****** 改2 ******
//         
//        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//         // Jackson 格式
//         Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//         ObjectMapper om = new ObjectMapper();
//         om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//         // 方法过期，改 1 时注释掉这里，正常 或 改 2 时使用
// //        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//         // ****** 改1 ******，其他情况下注释掉
//         om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance);
//         jackson2JsonRedisSerializer.setObjectMapper(om);
//
//         // String 类型格式
//         StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//         // 在使用注解@Bean返回RedisTemplate的时候，同时配置hashKey与hashValue的序列化方式
//         // key 采用String的序列化方式
//         template.setKeySerializer(stringRedisSerializer);
//         // value 采用jackson的序列化方式，使用 ****** 改 2 ****** 对象
//         template.setValueSerializer(genericJackson2JsonRedisSerializer);
// 
//         // hash 的key 也采用String的序列化方式
//         template.setHashKeySerializer(stringRedisSerializer);
//         // hash 的value采用jackson的序列化方式，使用 ****** 改 2 ****** 对象
//         template.setHashValueSerializer(genericJackson2JsonRedisSerializer);
// 
//         template.afterPropertiesSet();
// 
//         return template;
//   }


}