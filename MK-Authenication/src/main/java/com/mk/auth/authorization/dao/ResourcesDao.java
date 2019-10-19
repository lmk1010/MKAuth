package com.mk.auth.authorization.dao;

import com.mk.auth.authorization.entity.Resources;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author liumingkang
 * @Date 2019-10-19 22:31
 * @Destcription 资源Dao层 todo 需要把select * 改掉
 * @Version 1.0
 **/
@Mapper
@Repository("resourcesDao")
public interface ResourcesDao
{

    @Select({"select r.id,r.resource_name,r.resource_url,r.resource_method,r.resource_type,r.resource_description from mk_auth_resources r right join mk_auth_role_resource rr on r.id = rr.resource_id where rr.role_id = #{role_id}"})
    List<Resources> findAllResourcesByRole(@Param("role_id") String role_id);


}
