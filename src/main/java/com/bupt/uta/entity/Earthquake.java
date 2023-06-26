package com.bupt.uta.entity;

import cn.hutool.core.date.DateTime;
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
 * @date Created in 2023年06月26日 16:15
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Earthquake {
    private String id;
    private String time;
    private String latitude;
    private String longitude;
    private String depth;
    private String mag;
    private String magType;
    private int nst;
    private double gap;
    private double dmin;
    private double rms;
    private String net;
    private String updated;
    private String place;
    private String type;
    private double horizontalError;
    private double magError;
    private int magNst;
    private String status;
    private String locationSource;
    private String magSource;
    private int quakeStatus;
}
