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
public class Quiz2Vo {
    private String quiz2Time;
    private String latitude;
    private String longitude;
    private double mag;
    private String net;
    private String place;
    private int status;
    private double minMag;
    private double maxMag;
    private double numMag;
}
