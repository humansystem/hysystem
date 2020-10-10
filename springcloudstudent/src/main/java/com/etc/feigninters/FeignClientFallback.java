package com.etc.feigninters;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FeignClientFallback implements ClaFeignClient {

    @Override
    public Map<String, Object> getClaByClaNum(String claNum) {
        Map<String,Object> map = new HashMap<>();
        map.put("message","未根据班级编号查询到哦");
        return map;
    }

    @Override
    public Map<String, Object> getClaByClaName(String claName) {
        Map<String,Object> map = new HashMap<>();
        map.put("message","未根据班级名查询到哦");
        return map;
    }
}
