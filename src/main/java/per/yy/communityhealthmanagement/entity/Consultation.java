package per.yy.communityhealthmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultation {
    private int id;
    private int residentId;
    private String residentName;
    private String title;
    private String content;
    private short isReply;
    private String doctorId;
    private String doctorName;
    private String replyContent;
    private LocalDateTime createTime;
    private LocalDateTime replyTime;
}
