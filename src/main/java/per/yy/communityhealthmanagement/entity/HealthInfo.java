package per.yy.communityhealthmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthInfo {
    private int id;
    private int residentId;
    private int height;
    private int weight;
    private float bmi;
    private float heartRate;
    private String bloodType;
    private int bloodPressure;
    private float bloodFat;
    private float bloodGlucose;
    private String medicalHistory;
}
