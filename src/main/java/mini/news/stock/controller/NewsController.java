package mini.news.stock.controller;

import lombok.RequiredArgsConstructor;
import mini.news.stock.dto.NewsDto;
import mini.news.stock.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NewsController {

    private final NewsService newsService;

//    @GetMapping("/news")
//    public ResponseEntity<NewsDto> getNews(@RequestParam String sort_index,
//                                           @RequestParam String search_page){
//
//        return new ResponseEntity<NewsDto>;
//    }


    @GetMapping("/news/{newsId}")
    public ResponseEntity<NewsDto> getNewsDetail(@PathVariable("newsId") Long newsId){
        return new ResponseEntity<>(newsService.getNewsDetail(newsId), HttpStatus.OK);
    }
}
