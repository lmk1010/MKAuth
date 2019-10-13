package com.mk.auth.authorization.service.impl;

import com.mk.auth.authorization.dao.AuthUserDao;
import com.mk.auth.authorization.entity.AuthUser;
import com.mk.auth.authorization.model.UserAuthorityEnum;
import com.mk.auth.authorization.service.AuthUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author liumingkang
 * @Date 2019-10-13 10:50
 * @Destcription 授权用户具体实现类
 * @Version 1.0
 **/
@Service("authUserService")
@Transactional
public class AuthUserServiceImpl implements AuthUserService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthUserServiceImpl.class);

    @Resource(name = "authUserDao")
    private AuthUserDao authUserDao;

    @Override
    public void createNewAuthUser(String authName, String authPass, String authorities)
    {
        if (null == authName && null == authPass)
        {
            LOGGER.info("auth arguments is empty!");
            return;
        }
        if (null == authorities)
        {
            /** 若权限为空 默认赋予USER权限 */
            authorities = UserAuthorityEnum.USER.toString();
        }
        if (checkAuthoritiesRule(authorities))
        {
            LOGGER.warn("authorities regex is failed");
            return;
        }
        authUserDao.insertAuthUser(authName,authPass,authorities);
    }

    @Override
    public List<AuthUser> findAuthUsers()
    {
        return null;
    }

    /**
    *
     * // todo 目前只检查enum内部的 不灵活 后期改为db读取权限字段列表
     *
     * @Author liumingkang
     * @Description 校验权限格式,不允许未经审批的权限字段
     * @Date 11:21 2019-10-13
     * @Param [authorities]
     * @return boolean
     **/
    private boolean checkAuthoritiesRule(String authorities)
    {
        /** 正则校验 只留有逗号 */
        String regex = "[ `~!@#$%^&*()+=|{}':;'\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。、？]|\n|\r|\t";
        Pattern pattern = Pattern.compile(regex);
        if (pattern.matcher(authorities).find())
        {
            LOGGER.warn("authorities regex is not correct!");
            return false;
        }

        String[] res = authorities.split(",");
        if (res.length == 0)
        {
            LOGGER.warn("authorities is empty!");
            return false;
        }

        return true;
    }
}
