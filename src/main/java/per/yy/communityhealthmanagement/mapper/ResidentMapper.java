package per.yy.communityhealthmanagement.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import per.yy.communityhealthmanagement.entity.ResidentInfo;

@Mapper
public interface ResidentMapper {

    @Select("select * from resident_info where user_id=#{userId}")
    ResidentInfo selectResidentInfoByUserId(int userId);

    @Select("select id from resident_info where user_id=#{userId}")
    int selectResidentIdByUserId(int userId);

    void updateResidentInfo(int userId, ResidentInfo residentInfo);

    @Insert("insert into resident_info(user_id) values (#{userId})")
    void insert(int userId);


}
