package mini.news.stock.service.renewaltopic;

import mini.news.stock.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "renewalNews", url = "${api.base.url}" ,configuration = FeignConfig.class)
public interface NewsClient {

    @GetMapping()
    Map<String, Object> getNews(@RequestParam("query") String topic,
                                @RequestParam("display") int display,
                                @RequestParam("sort") String sort);
}
