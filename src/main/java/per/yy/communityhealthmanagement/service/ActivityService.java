package per.yy.communityhealthmanagement.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.yy.communityhealthmanagement.dto.PageQueryDto;
import per.yy.communityhealthmanagement.entity.Activity;
import per.yy.communityhealthmanagement.entity.PageBean;
import per.yy.communityhealthmanagement.mapper.ActivityMapper;
import per.yy.communityhealthmanagement.mapper.UserMapper;
import per.yy.communityhealthmanagement.vo.ActivityVo;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private UserMapper userMapper;
    public PageBean<ActivityVo> getActivity(String email,PageQueryDto pageQueryDto) {
        //用邮箱查出用户id
        int userId=userMapper.selectIdByEmail(email);

        PageBean<ActivityVo> pageBean = new PageBean<>();
        // 开启分页（必须紧邻分页查询的SQL）
        PageHelper.startPage(pageQueryDto.getPageNum(), pageQueryDto.getPageSize());
        // 执行分页查询（此处返回的List实际是Page类型）
        List<ActivityVo> activities = activityMapper.selectAll(userId);
        // 安全转换：通过PageInfo获取分页数据
        PageInfo<ActivityVo> pageInfo = new PageInfo<>(activities);
        // 填充PageBean
        pageBean.setItems(pageInfo.getList());
        pageBean.setTotal(pageInfo.getTotal());
        return pageBean;
    }

    @Transactional
    public void join(String email, int activityId) {
        //用邮箱查出用户id
        int userId=userMapper.selectIdByEmail(email);
        //插入报名记录
        activityMapper.insert(userId,activityId);
        //活动报名人数加1
        activityMapper.increaseRegistered(activityId);
    }
}
