package per.yy.communityhealthmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import per.yy.communityhealthmanagement.entity.HealthInfo;
import per.yy.communityhealthmanagement.entity.ResidentInfo;

@Mapper
public interface ResidentMapper {

    @Select("select * from resident_info where user_id=#{userId}")
    ResidentInfo selectResidentInfoByUserId(int userId);

    @Select("select id from resident_info where user_id=#{userId}")
    int selectResidentIdByUserId(int userId);

    @Select("select * from health_info where resident_id=#{residentId}")
    HealthInfo selectHealthInfoByResidentId(int residentId);

    void updateHealthInfo(int residentId, HealthInfo healthInfo);

    void updateResidentInfo(int userId, ResidentInfo residentInfo);
}
