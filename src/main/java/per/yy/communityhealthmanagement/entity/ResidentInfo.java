package per.yy.communityhealthmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResidentInfo {
    private int id;
    private int userId;
    private String name;
    private short gender;
    private short age;
    private String address;
}
