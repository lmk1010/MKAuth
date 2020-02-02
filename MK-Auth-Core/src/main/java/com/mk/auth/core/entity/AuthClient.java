package com.mk.auth.core.entity;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author liumingkang
 * @Date 2020-02-02 10:29
 * @Destcription 用于请求授权的对应微服务的授权认证
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthClient implements Serializable
{
    /** 客户端ID */
    private int id;

    /** 客户端名称 */
    private String clientName;

    /** 客户端密码 */
    private String clientPass;

    /** 客户端密钥 */
    private String clientKey;

    /** 客户端权限  xxx,xxx,xxx */
    private String clientAuthorities;

    /** 客户端锁 上锁不可认证 */
    private boolean locked = false;

    /** 创建时间 */
    private Timestamp createTime = new Timestamp(new Date().getTime());

    /** 更新时间 */
    private Timestamp updateTime = new Timestamp(new Date().getTime());


}
