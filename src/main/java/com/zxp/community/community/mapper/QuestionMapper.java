package com.zxp.community.community.mapper;

import com.zxp.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
<<<<<<< HEAD
import org.apache.ibatis.annotations.Select;

import java.util.List;
=======
>>>>>>> bb7014c18066c4945d1a1495d0950e2e53c349f3

@Mapper
public interface QuestionMapper {

<<<<<<< HEAD
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    public void create(Question question);

    @Select("SELECT * FROM QUESTION")
    List<Question> list();
=======
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    public void create(Question question);

>>>>>>> bb7014c18066c4945d1a1495d0950e2e53c349f3
}

