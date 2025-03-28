package per.yy.communityhealthmanagement.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import per.yy.communityhealthmanagement.entity.User;

@Mapper
public interface UserMapper {

    @Insert("insert into user(username,password,phone,email,create_time) values (#{username},#{password},#{phone},#{email},#{createTime})")
    void insert(User user);

    @Select("select id from role where role=#{role}")
    int selectIdByRole(String role);

    @Select("select id from user where email=#{email}")
    int selectIdByEmail(String email);

    @Insert("insert into user_role(user_id,role_id) values (#{userId},#{roleId})")
    void insertRole(int userId, int roleId);

    @Select("select * from user where email=#{email}")
    User selectByEmail(String email);

    @Update("update user set password=#{password} where email=#{email}")
    void updatePassword(User user);

    User selectByEmailAndRole(User user);

    @Update("update user set username=#{username},phone=#{phone} where id=#{userId}")
    void updateUserInfo(int userId, User user);

    @Update("update user set username=#{username},phone=#{phone} where email=#{email}")
    void update(User user);
}
