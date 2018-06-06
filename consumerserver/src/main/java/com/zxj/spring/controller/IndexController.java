package com.zxj.spring.controller;

import com.google.gson.Gson;
import com.zxj.log.EsLogger;
import com.zxj.spring.common.ImageClassifyUtils;
import com.zxj.spring.service.IConsumerService;
import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

    Gson g = new Gson();

    @Autowired
    private IConsumerService consumerService;
    //这个@Value会根据配置的配置中心地址找到git仓库对应的配置和本地服务的配置文件
    @Value("${path}")
    private String path;

    @Autowired
    private JestClient jestClient;

    @GetMapping("/index")
    public String index(){
        return "zhangxj:" + path + "; result: " + consumerService.doConsum();
    }

    @GetMapping("/getConfig")
    public String getConfig(){
        return "zhangxj:" + path;
    }

    @GetMapping("/put")
    public String put(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("time", new Date());

        Index index = new Index.Builder(g.toJson(map)).index("test").type("spring").build();
        EsLogger.logger.info("put", "index", "put data into es!");
        try {
            DocumentResult result = jestClient.execute(index);
            return result.getJsonString();
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/search")
    @ResponseBody
    public String search(String name){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name", name));
        searchSourceBuilder.size(2);
        searchSourceBuilder.from(0);

        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex("test").addType("spring").build();
        try {
            SearchResult result = jestClient.execute(search);
            return result.getSourceAsStringList().toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/rangeSearch")
    @ResponseBody
    public String rangeSearch(String begin, String end){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.rangeQuery("time").gte(begin).lte(end).includeLower(true).includeUpper(true));
        searchSourceBuilder.size(2);
        searchSourceBuilder.from(0);

        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex("test").addType("spring").build();
        try {
            SearchResult result = jestClient.execute(search);
            return result.getSourceAsStringList().toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/wildcardSearch")
    @ResponseBody
    public String wildcardSearch(String filed, String value){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.wildcardQuery(filed, "*"+value+"*"));
        searchSourceBuilder.size(2);
        searchSourceBuilder.from(0);

        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex("test").addType("spring").build();
        try {
            SearchResult result = jestClient.execute(search);
            return result.getSourceAsStringList().toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/plantDetect")
    @ResponseBody
    public String plantDetect(String path){
        String result = ImageClassifyUtils.getInstance().plantDetect("C:/Users/xiaojun.zhang1/Desktop/test1.png");
//        String result = ImageClassifyUtils.getInstance().plantDetect(path);
        return result;
    }

}
