package com.bupt.uta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author jingxp
 * @version 1.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz1Vo {
    private String peopleName;
    private int grade;
    private int id;
    private String pic;
    private String notes;
    private int status;
    private Integer startId;
    private Integer endId;
    private Integer startGrade;
    private Integer endGrade;
}
