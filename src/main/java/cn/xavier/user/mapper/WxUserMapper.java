package cn.xavier.user.mapper;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.user.domain.WxUser;

public interface WxUserMapper extends BaseMapper<WxUser> {
    WxUser loadByOpenId(String openid);
}
