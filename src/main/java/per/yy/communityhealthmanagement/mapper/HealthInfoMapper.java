package per.yy.communityhealthmanagement.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import per.yy.communityhealthmanagement.entity.HealthInfo;

@Mapper
public interface HealthInfoMapper {
    @Select("select * from health_info where resident_id=#{residentId}")
    HealthInfo selectHealthInfoByResidentId(int residentId);

    void updateHealthInfo(int residentId, HealthInfo healthInfo);

    @Insert("insert into health_info(resident_id) values (#{residentId})")
    void insertHealthInfo(int residentId);
}
