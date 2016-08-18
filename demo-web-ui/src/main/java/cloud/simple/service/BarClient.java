package cloud.simple.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("bar") //bind to bar (which is demo-micorservice server name)
public interface BarClient {

    @RequestMapping(method = RequestMethod.GET, value = "/go")
    public String go(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
    

}
