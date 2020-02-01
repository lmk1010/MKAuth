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
    private String resource_name;

    /** 资源URl **/
    private String resource_url;

    /** 资源方法 **/
    private String resource_method;

    /** 资源类型 **/
    private String resource_type;

    /** 资源描述 **/
    private String resource_description;

}
