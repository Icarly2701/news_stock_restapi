package mini.news.stock.controller;

import lombok.RequiredArgsConstructor;
import mini.news.stock.dto.SearchKeywordNewsDto;
import mini.news.stock.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SearchController {

    private final NewsService newsService;

    @GetMapping("/news/search")
    public ResponseEntity<SearchKeywordNewsDto> getNewsDetail(@RequestParam("search_keyword_title") String searchTitle,
                                                              @RequestParam("sort_index") String sortIndex){
        return new ResponseEntity<>(newsService.getSearchKeywordNewsDetail(searchTitle, sortIndex), HttpStatus.OK);
    }
}
