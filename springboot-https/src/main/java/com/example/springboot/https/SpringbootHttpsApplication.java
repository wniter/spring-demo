package com.example.springboot.https;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 参考
 * $ keytool --help
 * 密钥和证书管理工具
 *
 * 命令:
 *
 *  -certreq            生成证书请求
 *  -changealias        更改条目的别名
 *  -delete             删除条目
 *  -exportcert         导出证书
 *  -genkeypair         生成密钥对
 *  -genseckey          生成密钥
 *  -gencert            根据证书请求生成证书
 *  -importcert         导入证书或证书链
 *  -importpass         导入口令
 *  -importkeystore     从其他密钥库导入一个或所有条目
 *  -keypasswd          更改条目的密钥口令
 *  -list               列出密钥库中的条目
 *  -printcert          打印证书内容
 *  -printcertreq       打印证书请求的内容
 *  -printcrl           打印 CRL 文件的内容
 *  -storepasswd        更改密钥库的存储口令
 *
 * 使用 "keytool -command_name -help" 获取 command_name 的用法
 *
 *
 * Java Keytool工具简介
 *https://blog.csdn.net/liumiaocn/article/details/61921014
 */
@SpringBootApplication
public class SpringbootHttpsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootHttpsApplication.class, args);
    }

}
