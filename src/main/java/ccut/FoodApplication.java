package ccut;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



 @SpringBootApplication
 @MapperScan({"ccut.mapper"})
 public class FoodApplication
 {
   public static void main(String[] args) {
     SpringApplication.run(FoodApplication.class, args);
   }
 }
