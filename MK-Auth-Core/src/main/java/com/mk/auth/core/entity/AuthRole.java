package com.mk.auth.core.entity;

import lombok.*;

/**
 * @Author liumingkang
 * @Date 2019-10-13 01:57
 * @Destcription 角色
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthRole
{
    /** 角色ID **/
    private int id;
    /** 角色名称 **/
    private String role_name;
    /** 角色描述 **/
    private String role_description;
}
