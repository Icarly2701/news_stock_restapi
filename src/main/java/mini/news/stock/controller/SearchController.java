package mini.news.stock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini.news.stock.dto.SearchKeywordNewsListDto;
import mini.news.stock.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class SearchController {

    private final NewsService newsService;

    @GetMapping("/news/search")
    public ResponseEntity getNewsDetail(@RequestParam("search_keyword_title") String searchTitle,
                                        @RequestParam("sort_index") String sortIndex){
        return new ResponseEntity<>(newsService.getSearchKeywordNewsDetail(searchTitle.replace("\"", ""), sortIndex), HttpStatus.OK);
    }
}
