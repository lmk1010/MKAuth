package com.mk.auth.core.entity;

import lombok.*;

/**
 * @Author liumingkang
 * @Date 2019-10-19 22:15
 * @Destcription url和method实体类
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthResources
{
    /** 资源ID **/
    private int id;

    /** 资源名称 **/
    private String resourceName;

    /** 资源URl **/
    private String resourceUrl;

    /** 资源方法 **/
    private String resourceMethod;

    /** 资源类型 **/
    private String resourceType;

    /** 资源属客户端 **/
    private String resourceClient;

    /** 资源描述 **/
    private String resourceDescription;

}
