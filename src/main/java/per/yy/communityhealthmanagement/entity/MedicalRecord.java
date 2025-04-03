package per.yy.communityhealthmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {
    private int id;
    private int residentId;
    private LocalDate visitTime;
    private String visitType;
    private String hospital;
    private String department;
    private String doctor;
    private String diagnosticResult;
}
