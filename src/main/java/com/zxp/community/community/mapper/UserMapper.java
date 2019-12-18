package com.zxp.community.community.mapper;

import com.zxp.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO User (name,account_id,token,gmt_Create,gmt_Modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void Insert(User user);

    @Select("SELECT * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
