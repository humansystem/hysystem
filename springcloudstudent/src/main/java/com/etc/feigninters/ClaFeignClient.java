package com.etc.feigninters;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name="springcloudcla")
public interface ClaFeignClient {
    @RequestMapping(value = "/claController/getOneCla/{claNum}",method = RequestMethod.GET)
     Map<String,Object> getClaByClaNum(@PathVariable String claNum );

    @RequestMapping(value = "/claController/getClaByName/{claName}",method = RequestMethod.GET)
     Map<String,Object> getClaByClaName(@PathVariable String claName);

}
