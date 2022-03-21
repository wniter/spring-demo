package com.example.springboot.ehcache.mapper;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

// 开启ehcache缓存模式
//@EnableCaching
//@CacheConfig(cacheNames = "userCache")
//public interface UserMapper {
//    @Select("SELECT ID ,NAME,AGE FROM users where id=#{id}")
//    @Cacheable
//    List<Users> getUser(@Param("id") Long id);
//}
//@Cacheable  加了该注解的方法表示可以缓存
//@CacheConfig 表示创建缓存配置，Key为userCache


/*Ehcache配置说明
1、diskStore ：指定数据(.data and .index)存储位置，可指定磁盘中的文件夹位置期 The diskStore element is optional. It must be configured if you have overflowToDisk or diskPersistent enabled for any cache. If it is not configured, a warning will be issues and java.io.tmpdir will be used.
２、defaultCache ： 默认的管理策略
Ehcache 使用Map集合实现的 element 其实就是 key 和value
一)、以下属性是必须的：
　　1、name： Cache的名称，必须是唯一的(ehcache会把这个cache放到HashMap里)。
　　2、maxElementsInMemory：在内存中缓存的element的最大数目。
　　3、maxElementsOnDisk：在磁盘上缓存的element的最大数目，默认值为0，表示不限制。
　　４、eternal：设定缓存的elements是否永远不过期。如果为true，则缓存的数据始终有效，如果为false那么还要根据timeToIdleSeconds，timeToLiveSeconds判断。
　　５、overflowToDisk： 如果内存中数据超过内存限制，是否要缓存到磁盘上。
二)、以下属性是可选的：
　　１、timeToIdleSeconds： 对象空闲时间，指对象在多长时间没有被访问就会失效。只对eternal为false的有效。默认值0，表示一直可以访问。
　　２、timeToLiveSeconds： 对象存活时间，指对象从创建到失效所需要的时间。只对eternal为false的有效。默认值0，表示一直可以访问。
　　３、diskPersistent： 是否在磁盘上持久化。指重启jvm后，数据是否有效。默认为false。
　　４、diskExpiryThreadIntervalSeconds： 对象检测线程运行时间间隔。标识对象状态的线程多长时间运行一次。
　　５、diskSpoolBufferSizeMB： DiskStore使用的磁盘大小，默认值30MB。每个cache使用各自的DiskStore。
　　６、memoryStoreEvictionPolicy： 如果内存中数据超过内存限制，向磁盘缓存时的策略。默认值LRU，可选FIFO、LFU。*/
