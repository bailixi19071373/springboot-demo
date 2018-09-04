package com.sc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class RedisCacheConfig {
    @Value("${redis.expire.seconds}")
    private long expireSeconds;
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        CompositeCacheManager ccm = new CompositeCacheManager();
        List<CacheManager> list = new ArrayList<>();
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(factory);
//		Set<String> cacheNames = new HashSet<String>() {{
//	        add("student");//缓存名称在 redis里相当于一个命名空间student::key
//	    }};
//		builder.initialCacheNames(cacheNames);
        builder.cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().
                //设置缓存过期时间为n秒后
                        entryTtl(Duration.ofSeconds(expireSeconds)));
        RedisCacheManager rcm = builder.build();
        list.add(rcm);
        ccm.setCacheManagers(list);
        return ccm;
    }

//    @Bean//字符串序列化器
//    public StringRedisSerializer stringRedisSerializer() {
//        return new StringRedisSerializer();
//    }
//
//    @Bean//JSON序列化器
//    public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
//        return new GenericJackson2JsonRedisSerializer();
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory,
//                                                       StringRedisSerializer stringRedisSerializer,
//                                                       GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory);
//        // key序列化方式;（不然会出现乱码;）,但是如果方法上有Long等非String类型的话，会报类型转换错误；
//        // 所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现ObjectRedisSerializer
//        // 或者JdkSerializationRedisSerializer序列化方式;
//        //使用JSON序列化器
//        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
//        redisTemplate.setHashKeySerializer(genericJackson2JsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
//        return redisTemplate;
//    }

}