package per.yy.communityhealthmanagement.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import per.yy.communityhealthmanagement.entity.User;

@Mapper
public interface UserMapper {

    @Insert("insert into user(role,username,password,phone,email,create_time) values (#{role},#{username},#{password},#{phone},#{email},#{createTime})")
    void insert(User user);

    @Select("select id from user where email=#{email}")
    int selectIdByEmail(String email);

    @Select("select * from user where email=#{email}")
    User selectByEmail(String email);

    @Update("update user set password=#{password} where email=#{email}")
    void updatePassword(User user);

    User selectByEmailAndRole(User user);

    @Update("update user set username=#{username},phone=#{phone} where email=#{email}")
    void update(User user);
}
