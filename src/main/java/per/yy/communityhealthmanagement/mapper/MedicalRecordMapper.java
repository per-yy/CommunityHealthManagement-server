package per.yy.communityhealthmanagement.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import per.yy.communityhealthmanagement.entity.MedicalRecord;

import java.util.List;

@Mapper
public interface MedicalRecordMapper {

    @Select("select * from medical_record where resident_id=#{residentId}")
    List<MedicalRecord> selectMedicalRecordByResidentId(int residentId);

    void insertMedicalRecord(int residentId, MedicalRecord medicalRecord);

    @Delete("delete from medical_record where id=#{id}")
    void deleteMedicalRecordById(int id);
}
