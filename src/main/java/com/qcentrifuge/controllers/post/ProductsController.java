package com.qcentrifuge.controllers.post;

import com.qcentrifuge.dbproducts.Products;
import com.qcentrifuge.dbproducts.ProductsRep;
import com.qcentrifuge.service.SendTelegramInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsRep products;

    @Autowired
    private SendTelegramInfo tgSender;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @PostMapping("/add")
    public String addProducts(@Valid Products products, @RequestParam("file") MultipartFile file){
        if (file != null){
            File file1 = new File("upload");
            if (file1.exists()){
                String name = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
                try {
                    file.transferTo(Paths.get(file1 + "/" + name));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.products.save(new Products(products.getName(), products.getPrice(), products.getDescription(), name));
            }else {
                System.out.println("Сделан файл");
                file1.mkdir();
            }
        }else{
            System.out.println("Файла нет");
        }
        return "redirect:/";
    }

    @PostMapping("/del/{id}")
    public String delProducts(@PathVariable String id){
        File file1 = new File("upload/" + products.findById(Long.valueOf(id)).get().getImageName());
        file1.delete();
        products.deleteById(Long.valueOf(id));
        tgSender.sendToAdmin("Удален продукт под id " + Long.valueOf(id));
        simpMessagingTemplate.convertAndSend("/topic/activity", "changed"); //Отправка инфы клиентам через websocket
        return "redirect:/";
    }

}
