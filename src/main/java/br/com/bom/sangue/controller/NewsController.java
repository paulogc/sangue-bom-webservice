package br.com.bom.sangue.controller;

import br.com.bom.sangue.entities.News;
import br.com.bom.sangue.service.NewsService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@RestController
@RequestMapping("/news")
public class NewsController {

    NewsService newsService = new NewsService();

    @PostMapping
    @ResponseBody
    public News create (@RequestBody News news) throws ClassNotFoundException, SQLException {
        return newsService.create(news);
    }

    @PutMapping(value = "/update")
    @ResponseBody
    public News update (@RequestBody News news) throws ClassNotFoundException, SQLException {
        return newsService.update(news);
    }

}
