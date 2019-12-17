package com.zxp.community.community.mapper;

import com.zxp.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO User (name,account_id,token,gmt_Create,gmt_Modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void Insert(User user);
}
