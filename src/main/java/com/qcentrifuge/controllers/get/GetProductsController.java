package com.qcentrifuge.controllers.get;

import com.qcentrifuge.dbproducts.ProductsRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class GetProductsController {


    @Autowired
    private ProductsRep productsRep;


    @GetMapping
    public Map<String, List<Map<String, Object>>> getProducts(){

        Map<String, List<Map<String, Object>>> prods = new HashMap<>();

        List<Map<String, Object>> maps = new ArrayList<>();

        productsRep.findAll().forEach(products -> {
            maps.add(new HashMap<String, Object>(){{
                put("id", products.getId());
                put("name", products.getName());
                put("price", products.getPrice());
                put("description", products.getDescription());
                put("imageName", products.getImageName());
            }});
        });


        prods.put("prods", maps);

        return prods;
    }

    @GetMapping("/top")
    public Map<String, List<Map<String, Object>>> topProducts(){


        Map<String, List<Map<String, Object>>> prods = new HashMap<>();

        List<Map<String, Object>> maps = new ArrayList<>();

        productsRep.findAll().forEach(products -> {
            maps.add(new HashMap<String, Object>(){{
                put("id", products.getId());
                put("name", products.getName());
                put("price", products.getPrice());
                put("description", products.getDescription());
                put("imageName", products.getImageName());
            }});
        });


        prods.put("prods", maps);
        return prods;
    }

//
//    @MessageMapping("/change")
//    @SendTo("/topic/activity")
//    public String changeProdData(){
//        try {
//            Thread.sleep(1000);//Имитация задержки на обработку
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return "changed";
//    }


}
