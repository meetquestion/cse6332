package com.bupt.uta.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.uta.Service.EarthquakeService;
import com.bupt.uta.entity.Earthquake;
import com.bupt.uta.mapper.EarthquakeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
 * @date Created in 2023年06月26日 21:27
 * @since 1.0
 */
@Service
public class EarthquakeServiceImpl extends ServiceImpl<EarthquakeMapper, Earthquake> implements EarthquakeService {
    @Autowired
    private EarthquakeMapper earthquakeMapper;
    @Override
    public List<Earthquake> randomNum(Integer num) {
        List<Earthquake> earthquakes = new ArrayList<>();
        try {
            earthquakes = earthquakeMapper.randomNum(num);
        } catch (Exception e) {
            log.error("e:",e);
        }
        return earthquakes;
    }
}
