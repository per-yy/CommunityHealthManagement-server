package per.yy.communityhealthmanagement.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import per.yy.communityhealthmanagement.entity.Consultation;

import java.util.List;

@Mapper
public interface ConsultationMapper {

    List<Consultation> selectByResidentId(int residentId);

    @Delete("delete from consultation where id=#{id}")
    void deleteById(int id);

    void insert(Consultation consultation);
}
