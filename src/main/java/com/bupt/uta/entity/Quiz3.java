package com.bupt.uta.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
 * @date Created in 2023年06月16日 09:20
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quiz3 {
    private String city;
    private String state;
    private String lat;
    private String lon;
    private int population;
    private int status;

}
