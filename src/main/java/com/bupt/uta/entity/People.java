package com.bupt.uta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p></p>
 * <p>
 * <PRE>
 *
 * @author jingxp
 * @version 1.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class People {

    private String peopleName;
    private String peopleState;
    private String salary;
    private String room;
    private String picture;
    private String keywords;
    private int grade;
    private int status;
}
