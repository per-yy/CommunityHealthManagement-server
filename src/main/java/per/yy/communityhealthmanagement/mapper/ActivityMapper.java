package per.yy.communityhealthmanagement.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import per.yy.communityhealthmanagement.vo.ActivityVo;

import java.util.List;

@Mapper
public interface ActivityMapper {
    List<ActivityVo> selectAll(int userId);

    @Insert("insert into activity_registration (user_id,activity_id) values (#{userId},#{activityId})")
    void insert(int userId, int activityId);

    @Update("update activity set registered=registered+1 where id=#{activityId}")
    void increaseRegistered(int activityId);
}
