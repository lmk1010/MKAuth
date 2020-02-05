package com.mk.auth.core.util;

import com.mk.auth.common.exception.MKRuntimeException;
import com.mk.auth.core.constant.CommonConstant;
import com.mk.auth.core.entity.AuthUser;
import com.mk.auth.core.model.MKToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.UUID;

/**
 * @Author liumingkang
 * @Date 2020-02-02 17:47
 * @Destcription 处理Token系列的工具类
 * @Version 1.0
 **/
public class TokenUtils
{

    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

    /** 默认过期时间 60min*/
    private static final long DEFAULT_EXPIRE = 60 * 60L;

    /** APP Token */
    public static final String APP_TOKEN = "TOKEN_APP";

    /** WEB Token */
    public static final String WEB_TOKEN = "TOKEN_WEB";

    /** Assistant Token */
    public static final String ASSISTANT_TOKEN = "TOKEM_NAVIOUS";

    /** access_token */
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN_";

    /** refresh_token */
    public static final String REFRESH_TOKEN = "REFRESH_TOKEN_";

    /** Token的前缀 */
    public static final String SPERATE = "_";

    /** 默认的token type*/
    private static final String[] tokenArray = new String[]{APP_TOKEN, WEB_TOKEN, ASSISTANT_TOKEN};

    /** 初始化Token信息 */
    public static void initToken(MKToken mkToken, String tokenType)
    {
        if (null == mkToken || StringUtils.isBlank(tokenType))
        {
            log.error(CommonConstant.LOG_PREFIX + "Illega argument!");
            throw new MKRuntimeException("Illega argument!");
        }
        if (Arrays.stream(tokenArray).noneMatch(type -> StringUtils.equals(tokenType,type)))
        {
            log.error(CommonConstant.LOG_PREFIX + "Token type is error!");
            throw new MKRuntimeException("Token type is error! your type is:" + tokenType);
        }
        mkToken.setAccessToken(createAccessToken(tokenType));
        mkToken.setAccessType(tokenType);
        mkToken.setExpire(DEFAULT_EXPIRE);
        mkToken.setRefreshToken(createRefreshToken());
    }

    /** AccessToken的生成规则 */
    /** token的组成: 前缀 + UUID + 用户名 + ip 实际用户名和ip不会返回给用户 每个由_分割*/
    private static String createAccessToken(String tokenType)
    {
        // TODO: 2020-02-02 后期要加规则
        StringBuffer sb = new StringBuffer();
        /** 前缀 */
        sb.append(tokenType).append(SPERATE);
        /** UUID */
        sb.append(UUID.randomUUID());
        return sb.toString();
    }

    /** RefreshToken的生成规则 */
    private static String createRefreshToken()
    {
        return UUID.randomUUID().toString();
    }

    /**
     *
     * @Author liumingkang
     * @Description 判断为MKToken的格式
     * @Date 17:34 2020-02-05
     * @Param [accessToken]
     * @return boolean
     **/
    public static boolean isMKToken(String accessToken)
    {
        if (Arrays.stream(tokenArray).anyMatch(t -> StringUtils.startsWith(accessToken, t)))
        {
            return true;
        }
        return false;
    }

}
