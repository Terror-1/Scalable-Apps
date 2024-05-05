package com.productservice.productservice;

import com.productservice.productservice.entity.Product;
import com.productservice.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;
import java.util.Optional;

@SpringBootApplication
public class ProductServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(ProductRepository productRepository) {
		return args -> {
			//shoes
			Product product1 = new Product();
			product1.setCategory("shoes");
			product1.setSku("Running_Shoes_blue");
			product1.setPrice(1200);
			product1.setSmallMidLargeOneSize("one Size");
			product1.setSizeNumber(13);
			product1.setQuantity(50);
			product1.setGender("female");
			product1.setDescription("blue running shoes");
			productRepository.save(product1);
			Optional<Product> products1 = productRepository.findBySku(product1.getSku());
			int product1Id = product1.getId();



			Product product2 = new Product();
			product2.setCategory("shoes");
			product2.setSku("Running_Shoes_Black");
			product2.setPrice(1200);
			product2.setSmallMidLargeOneSize("one Size");
			product2.setSizeNumber(12);
			product2.setQuantity(50);
			product2.setGender("male");
			product2.setDescription("black unning shoes");
			productRepository.save(product2);
			Optional<Product> products2 = productRepository.findBySku(product2.getSku());
			int product2Id = product2.getId();

			Product product3 = new Product();
			product3.setCategory("shoes");
			product3.setSku("Running_Shoes_White");
			product3.setPrice(1200);
			product3.setSmallMidLargeOneSize("one Size");
			product3.setSizeNumber(1);
			product3.setQuantity(50);
			product3.setGender("female");
			product3.setDescription("white running shoes");
			productRepository.save(product3);
			Optional<Product> products3 = productRepository.findBySku(product3.getSku());
			int product3Id = product3.getId();


			Product product4 = new Product();
			product4.setCategory("shoes");
			product4.setSku("Running_Shoes_Grey");
			product4.setPrice(1200);
			product4.setSmallMidLargeOneSize("one Size");
			product4.setSizeNumber(10);
			product4.setQuantity(50);
			product4.setGender("male");
			product4.setDescription("grey running shoes");
			productRepository.save(product4);
			Optional<Product> products4 = productRepository.findBySku(product4.getSku());
			int product4Id = product4.getId();

			Product product5 = new Product();
			product5.setCategory("shoes");
			product5.setSku("Running_Shoes_Red");
			product5.setPrice(1200);
			product5.setSmallMidLargeOneSize("one Size");
			product5.setSizeNumber(10);
			product5.setQuantity(50);
			product5.setGender("female");
			product5.setDescription(" red running shoes");
			productRepository.save(product5);
			Optional<Product> products5 = productRepository.findBySku(product5.getSku());
			int product5Id = product5.getId();

			Product product6 = new Product();
			product6.setCategory("shoes");
			product6.setSku("Running_Shoes_Orange");
			product6.setPrice(1200);
			product6.setSmallMidLargeOneSize("one Size");
			product6.setSizeNumber(10);
			product6.setQuantity(50);
			product6.setGender("male");
			product6.setDescription("orange running shoes");
			productRepository.save(product6);
			Optional<Product> products6 = productRepository.findBySku(product6.getSku());
			int product6Id = product6.getId();

			Product product7 = new Product();
			product7.setCategory("shoes");
			product7.setSku("Running_Shoes_Pink");
			product7.setPrice(1200);
			product7.setSmallMidLargeOneSize("one Size");
			product7.setSizeNumber(12);
			product7.setQuantity(50);
			product7.setGender("female");
			product7.setDescription("pink running shoes");
			productRepository.save(product7);
			Optional<Product> products7 = productRepository.findBySku(product7.getSku());
			int product7Id = product7.getId();

			Product product8 = new Product();
			product8.setCategory("shoes");
			product8.setSku("Running_Shoes_Yellow");
			product8.setPrice(1200);
			product8.setSmallMidLargeOneSize("one Size");
			product8.setSizeNumber(8);
			product8.setQuantity(50);
			product8.setGender("male");
			product8.setDescription("yellow running shoes");
			productRepository.save(product8);
			Optional<Product> products8 = productRepository.findBySku(product8.getSku());
			int product8Id = product8.getId();

			Product product9 = new Product();
			product9.setCategory("shoes");
			product9.setSku("Running_Shoes_Green");
			product9.setPrice(1200);
			product9.setSmallMidLargeOneSize("one Size");
			product9.setSizeNumber(9);
			product9.setQuantity(50);
			product9.setGender("female");
			product9.setDescription("green running shoes");
			productRepository.save(product9);
			Optional<Product> products9 = productRepository.findBySku(product9.getSku());
			int product9Id = product9.getId();

			Product product10 = new Product();
			product10.setCategory("shoes");
			product10.setSku("Running_Shoes_Brown");
			product10.setPrice(1200);
			product10.setSmallMidLargeOneSize("one Size");
			product10.setSizeNumber(9);
			product10.setQuantity(50);
			product10.setGender("male");
			product10.setDescription("brown running shoes");
			productRepository.save(product10);
			Optional<Product> products10 = productRepository.findBySku(product10.getSku());
			int product10Id = product10.getId();


			//bags

			Product product11 = new Product();
			product11.setCategory("bags");
			product11.setSku("black_female_bag");
			product11.setPrice(1200);
			product11.setSmallMidLargeOneSize("one Size");
			product11.setSizeNumber(-1);
			product11.setQuantity(50);
			product11.setGender("female");
			product11.setDescription("black bag");
			productRepository.save(product11);
			Optional<Product> products11 = productRepository.findBySku(product11.getSku());
			int product11Id = product11.getId();


			Product product12 = new Product();
			product12.setCategory("bags");
			product12.setSku("black_male_bag");
			product12.setPrice(1200);
			product12.setSmallMidLargeOneSize("one Size");
			product12.setSizeNumber(-1);
			product12.setQuantity(50);
			product12.setGender("male");
			product12.setDescription("black bag");
			productRepository.save(product12);
			Optional<Product> products12 = productRepository.findBySku(product12.getSku());
			int product12Id = product12.getId();


			Product product13 = new Product();
			product13.setCategory("bags");
			product13.setSku("white_female_bag");
			product13.setPrice(1200);
			product13.setSmallMidLargeOneSize("one Size");
			product13.setSizeNumber(-1);
			product13.setQuantity(50);
			product13.setGender("female");
			product13.setDescription("white bag");
			productRepository.save(product13);
			Optional<Product> products13 = productRepository.findBySku(product13.getSku());
			int product13Id = product13.getId();


			Product product14 = new Product();
			product14.setCategory("bags");
			product14.setSku("white_male_bag");
			product14.setPrice(1200);
			product14.setSmallMidLargeOneSize("one Size");
			product14.setSizeNumber(-1);
			product14.setQuantity(50);
			product14.setGender("male");
			product14.setDescription("white bag");
			productRepository.save(product14);
			Optional<Product> products14 = productRepository.findBySku(product14.getSku());
			int product14Id = product14.getId();


			Product product15 = new Product();
			product15.setCategory("bags");
			product15.setSku("pink_female_bag");
			product15.setPrice(1200);
			product15.setSmallMidLargeOneSize("one Size");
			product15.setSizeNumber(-1);
			product15.setQuantity(50);
			product15.setGender("female");
			product15.setDescription("pink bag");
			productRepository.save(product15);
			Optional<Product> products15 = productRepository.findBySku(product15.getSku());
			int product15Id = product15.getId();


			Product product16 = new Product();
			product16.setCategory("bags");
			product16.setSku("pink_male_bag");
			product16.setPrice(1200);
			product16.setSmallMidLargeOneSize("one Size");
			product16.setSizeNumber(-1);
			product16.setQuantity(50);
			product16.setGender("male");
			product16.setDescription("pink bag");
			productRepository.save(product16);
			Optional<Product> products16 = productRepository.findBySku(product16.getSku());
			int product16Id = product16.getId();

			Product product17 = new Product();
			product17.setCategory("bags");
			product17.setSku("yellow_female_bag");
			product17.setPrice(1200);
			product17.setSmallMidLargeOneSize("one Size");
			product17.setSizeNumber(-1);
			product17.setQuantity(50);
			product17.setGender("female");
			product17.setDescription("yellow bag");
			productRepository.save(product17);
			Optional<Product> products17 = productRepository.findBySku(product17.getSku());
			int product17Id = product17.getId();


			Product product18 = new Product();
			product18.setCategory("bags");
			product18.setSku("yellow_male_bag");
			product18.setPrice(1200);
			product18.setSmallMidLargeOneSize("one Size");
			product18.setSizeNumber(-1);
			product18.setQuantity(50);
			product18.setGender("male");
			product18.setDescription("yellow bag");
			productRepository.save(product18);
			Optional<Product> products18 = productRepository.findBySku(product18.getSku());
			int product18Id = product18.getId();


			Product product19 = new Product();
			product19.setCategory("bags");
			product19.setSku("green_female_bag");
			product19.setPrice(1200);
			product19.setSmallMidLargeOneSize("one Size");
			product19.setSizeNumber(-1);
			product19.setQuantity(50);
			product19.setGender("female");
			product19.setDescription("green bag");
			productRepository.save(product19);
			Optional<Product> products19 = productRepository.findBySku(product19.getSku());
			int product19Id = product19.getId();


			Product product20 = new Product();
			product20.setCategory("bags");
			product20.setSku("green_male_bag");
			product20.setPrice(1200);
			product20.setSmallMidLargeOneSize("one Size");
			product20.setSizeNumber(-1);
			product20.setQuantity(50);
			product20.setGender("male");
			product20.setDescription("green bag");
			productRepository.save(product20);
			Optional<Product> products20 = productRepository.findBySku(product20.getSku());
			int product20Id = product20.getId();

			//watches
			Product product21 = new Product();
			product21.setCategory("watches");
			product21.setSku("Black_male_watch");
			product21.setPrice(1200);
			product21.setSmallMidLargeOneSize("one Size");
			product21.setSizeNumber(-1);
			product21.setQuantity(50);
			product21.setGender("male");
			product21.setDescription("black male watch");
			productRepository.save(product21);
			Optional<Product> products21 = productRepository.findBySku(product21.getSku());
			int product21Id = product21.getId();


			Product product22 = new Product();
			product22.setCategory("watches");
			product22.setSku("Black_female_watch");
			product22.setPrice(1200);
			product22.setSmallMidLargeOneSize("one Size");
			product22.setSizeNumber(-1);
			product22.setQuantity(50);
			product22.setGender("female");
			product22.setDescription("black female watch");
			productRepository.save(product22);
			Optional<Product> products22 = productRepository.findBySku(product22.getSku());
			int product22Id = product22.getId();


			Product product23 = new Product();
			product23.setCategory("watches");
			product23.setSku("White_male_watch");
			product23.setPrice(1200);
			product23.setSmallMidLargeOneSize("one Size");
			product23.setSizeNumber(-1);
			product23.setQuantity(50);
			product23.setGender("male");
			product23.setDescription("white male watch");
			productRepository.save(product23);
			Optional<Product> products23 = productRepository.findBySku(product23.getSku());
			int product23Id = product23.getId();


			Product product24 = new Product();
			product24.setCategory("watches");
			product24.setSku("White_female_watch");
			product24.setPrice(1200);
			product24.setSmallMidLargeOneSize("one Size");
			product24.setSizeNumber(-1);
			product24.setQuantity(50);
			product24.setGender("female");
			product24.setDescription("white female watch");
			productRepository.save(product24);
			Optional<Product> products24 = productRepository.findBySku(product24.getSku());
			int product24Id = product24.getId();

			Product product25 = new Product();
			product25.setCategory("watches");
			product25.setSku("Red_male_watch");
			product25.setPrice(1200);
			product25.setSmallMidLargeOneSize("one Size");
			product25.setSizeNumber(-1);
			product25.setQuantity(50);
			product25.setGender("male");
			product25.setDescription("red male watch");
			productRepository.save(product25);
			Optional<Product> products25 = productRepository.findBySku(product25.getSku());
			int product25Id = product25.getId();


			Product product26 = new Product();
			product26.setCategory("watches");
			product26.setSku("Red_female_watch");
			product26.setPrice(1200);
			product26.setSmallMidLargeOneSize("one Size");
			product26.setSizeNumber(-1);
			product26.setQuantity(50);
			product26.setGender("female");
			product26.setDescription("red female watch");
			productRepository.save(product26);
			Optional<Product> products26 = productRepository.findBySku(product26.getSku());
			int product26Id = product26.getId();

			Product product27 = new Product();
			product27.setCategory("watches");
			product27.setSku("Blue_male_watch");
			product27.setPrice(1200);
			product27.setSmallMidLargeOneSize("one Size");
			product27.setSizeNumber(-1);
			product27.setQuantity(50);
			product27.setGender("male");
			product27.setDescription("blue male watch");
			productRepository.save(product27);
			Optional<Product> products27 = productRepository.findBySku(product27.getSku());
			int product27Id = product27.getId();

			Product product28 = new Product();
			product28.setCategory("watches");
			product28.setSku("Blue_female_watch");
			product28.setPrice(1200);
			product28.setSmallMidLargeOneSize("one Size");
			product28.setSizeNumber(-1);
			product28.setQuantity(50);
			product28.setGender("female");
			product28.setDescription("blue female watch");
			productRepository.save(product28);
			Optional<Product> products28 = productRepository.findBySku(product28.getSku());
			int product28Id = product28.getId();

			Product product29 = new Product();
			product29.setCategory("watches");
			product29.setSku("Pink_male_watch");
			product29.setPrice(1200);
			product29.setSmallMidLargeOneSize("one Size");
			product29.setSizeNumber(-1);
			product29.setQuantity(50);
			product29.setGender("male");
			product29.setDescription("pink male watch");
			productRepository.save(product29);
			Optional<Product> products29 = productRepository.findBySku(product29.getSku());
			int product29Id = product29.getId();

			Product product30 = new Product();
			product30.setCategory("watches");
			product30.setSku("Pink_female_watch");
			product30.setPrice(1200);
			product30.setSmallMidLargeOneSize("one Size");
			product30.setSizeNumber(-1);
			product30.setQuantity(50);
			product30.setGender("female");
			product30.setDescription("pink female watch");
			productRepository.save(product30);
			Optional<Product> products30 = productRepository.findBySku(product30.getSku());
			int product30Id = product30.getId();

			//clothing

			Product product31 = new Product();
			product31.setCategory("clothes");
			product31.setSku("Black_male_shirt1");
			product31.setPrice(1200);
			product31.setSmallMidLargeOneSize("small");
			product31.setSizeNumber(4);
			product31.setQuantity(50);
			product31.setGender("male");
			product31.setDescription("small black shirt");
			productRepository.save(product31);
			Optional<Product> products31 = productRepository.findBySku(product31.getSku());
			int product31Id = product31.getId();


			Product product32 = new Product();
			product32.setCategory("clothes");
			product32.setSku("Black_female_shirt2");
			product32.setPrice(1200);
			product32.setSmallMidLargeOneSize("small");
			product32.setSizeNumber(3);
			product32.setQuantity(50);
			product32.setGender("female");
			product32.setDescription("small black female shirt");
			productRepository.save(product32);
			Optional<Product> products32 = productRepository.findBySku(product32.getSku());
			int product32Id = product32.getId();


			Product product33 = new Product();
			product33.setCategory("clothes");
			product33.setSku("Red_male_shirt");
			product33.setPrice(1200);
			product33.setSmallMidLargeOneSize("medium");
			product33.setSizeNumber(7);
			product33.setQuantity(50);
			product33.setGender("male");
			product33.setDescription("medium red shirt");
			productRepository.save(product33);
			Optional<Product> products33 = productRepository.findBySku(product33.getSku());
			int product33Id = product33.getId();


			Product product34 = new Product();
			product34.setCategory("clothes");
			product34.setSku("Red_female_shirt");
			product34.setPrice(1200);
			product34.setSmallMidLargeOneSize("large");
			product34.setSizeNumber(10);
			product34.setQuantity(50);
			product34.setGender("female");
			product34.setDescription("large red female shirt");
			productRepository.save(product34);
			Optional<Product> products34 = productRepository.findBySku(product34.getSku());
			int product34Id = product34.getId();


			Product product35 = new Product();
			product35.setCategory("clothes");
			product35.setSku("Blue_male_shirt");
			product35.setPrice(800);
			product35.setSmallMidLargeOneSize("small");
			product35.setSizeNumber(3);
			product35.setQuantity(50);
			product35.setGender("male");
			product35.setDescription("small blue shirt");
			productRepository.save(product35);
			Optional<Product> products35 = productRepository.findBySku(product35.getSku());
			int product35Id = product35.getId();


			Product product36 = new Product();
			product36.setCategory("clothes");
			product36.setSku("Blue_female_shirt");
			product36.setPrice(800);
			product36.setSmallMidLargeOneSize("small");
			product36.setSizeNumber(4);
			product36.setQuantity(50);
			product36.setGender("female");
			product36.setDescription("small blue female shirt");
			productRepository.save(product36);
			Optional<Product> products36 = productRepository.findBySku(product36.getSku());
			int product36Id = product36.getId();


			Product product37 = new Product();
			product37.setCategory("clothes");
			product37.setSku("Pink_male_shirt");
			product37.setPrice(800);
			product37.setSmallMidLargeOneSize("small");
			product37.setSizeNumber(4);
			product37.setQuantity(50);
			product37.setGender("male");
			product37.setDescription("small pink shirt");
			productRepository.save(product37);
			Optional<Product> products37 = productRepository.findBySku(product37.getSku());
			int product37Id = product37.getId();


			Product product38 = new Product();
			product38.setCategory("clothes");
			product38.setSku("Pink_female_shirt");
			product38.setPrice(800);
			product38.setSmallMidLargeOneSize("small");
			product38.setSizeNumber(4);
			product38.setQuantity(50);
			product38.setGender("female");
			product38.setDescription("small pink female shirt");
			productRepository.save(product38);
			Optional<Product> products38 = productRepository.findBySku(product38.getSku());
			int product38Id = product38.getId();


			Product product39 = new Product();
			product39.setCategory("clothes");
			product39.setSku("Green_male_shirt");
			product39.setPrice(800);
			product39.setSmallMidLargeOneSize("small");
			product39.setSizeNumber(4);
			product39.setQuantity(50);
			product39.setGender("male");
			product39.setDescription("small green shirt");
			productRepository.save(product39);
			Optional<Product> products39 = productRepository.findBySku(product39.getSku());
			int product39Id = product39.getId();


			Product product40 = new Product();
			product40.setCategory("clothes");
			product40.setSku("Green_female_shirt");
			product40.setPrice(800);
			product40.setSmallMidLargeOneSize("small");
			product40.setSizeNumber(4);
			product40.setQuantity(50);
			product40.setGender("female");
			product40.setDescription("small green female shirt");
			productRepository.save(product40);
			Optional<Product> products40 = productRepository.findBySku(product40.getSku());
			int product40Id = product40.getId();


			Product product41 = new Product();
			product41.setCategory("clothes");
			product41.setSku("Yellow_male_shirt");
			product41.setPrice(800);
			product41.setSmallMidLargeOneSize("small");
			product41.setSizeNumber(4);
			product41.setQuantity(50);
			product41.setGender("male");
			product41.setDescription("small yellow shirt");
			productRepository.save(product41);
			Optional<Product> products41 = productRepository.findBySku(product41.getSku());
			int product41Id = product41.getId();


			Product product42 = new Product();
			product42.setCategory("clothes");
			product42.setSku("Yellow_female_shirt");
			product42.setPrice(800);
			product42.setSmallMidLargeOneSize("small");
			product42.setSizeNumber(5);
			product42.setQuantity(50);
			product42.setGender("female");
			product42.setDescription("small yellow female shirt");
			productRepository.save(product42);
			Optional<Product> products42 = productRepository.findBySku(product42.getSku());
			int product42Id = product42.getId();


			Product product43 = new Product();
			product43.setCategory("clothes");
			product43.setSku("Purple_male_shirt");
			product43.setPrice(800);
			product43.setSmallMidLargeOneSize("small");
			product43.setSizeNumber(3);
			product43.setQuantity(50);
			product43.setGender("male");
			product43.setDescription("small purple shirt");
			productRepository.save(product43);
			Optional<Product> products43 = productRepository.findBySku(product43.getSku());
			int product43Id = product43.getId();


			Product product44 = new Product();
			product44.setCategory("clothes");
			product44.setSku("Purple_female_shirt");
			product44.setPrice(800);
			product44.setSmallMidLargeOneSize("small");
			product44.setSizeNumber(4);
			product44.setQuantity(50);
			product44.setGender("female");
			product44.setDescription("small purple female shirt");
			productRepository.save(product44);
			Optional<Product> products44 = productRepository.findBySku(product44.getSku());
			int product44Id = product44.getId();

			Product product45 = new Product();
			product45.setCategory("clothes");
			product45.setSku("Orange_male_shirt");
			product45.setPrice(800);
			product45.setSmallMidLargeOneSize("small");
			product45.setSizeNumber(4);
			product45.setQuantity(50);
			product45.setGender("male");
			product45.setDescription("small orange shirt");
			productRepository.save(product45);
			Optional<Product> products45 = productRepository.findBySku(product45.getSku());
			int product45Id = product45.getId();


			Product product46 = new Product();
			product46.setCategory("clothes");
			product46.setSku("Orange_female_shirt");
			product46.setPrice(800);
			product46.setSmallMidLargeOneSize("small");
			product46.setSizeNumber(4);
			product46.setQuantity(50);
			product46.setGender("female");
			product46.setDescription("small orange female shirt");
			productRepository.save(product46);
			Optional<Product> products46 = productRepository.findBySku(product46.getSku());
			int product46Id = product46.getId();


			Product product47 = new Product();
			product47.setCategory("clothes");
			product47.setSku("White_male_shirt");
			product47.setPrice(800);
			product47.setSmallMidLargeOneSize("small");
			product47.setSizeNumber(4);
			product47.setQuantity(50);
			product47.setGender("male");
			product47.setDescription("small white shirt");
			productRepository.save(product47);
			Optional<Product> products47 = productRepository.findBySku(product47.getSku());
			int product47Id = product47.getId();

			Product product48 = new Product();
			product48.setCategory("clothes");
			product48.setSku("White_female_shirt");
			product48.setPrice(800);
			product48.setSmallMidLargeOneSize("small");
			product48.setSizeNumber(4);
			product48.setQuantity(50);
			product48.setGender("female");
			product48.setDescription("small white female shirt");
			productRepository.save(product48);
			Optional<Product> products48 = productRepository.findBySku(product48.getSku());
			int product48Id = product48.getId();

			Product product49 = new Product();
			product49.setCategory("clothes");
			product49.setSku("Black_male_shirt2");
			product49.setPrice(800);
			product49.setSmallMidLargeOneSize("small");
			product49.setSizeNumber(4);
			product49.setQuantity(50);
			product49.setGender("male");
			product49.setDescription("small black shirt");
			productRepository.save(product49);
			Optional<Product> products49 = productRepository.findBySku(product49.getSku());
			int product49Id = product49.getId();


			Product product50 = new Product();
			product50.setCategory("clothes");
			product50.setSku("Black_female_shirt1");
			product50.setPrice(800);
			product50.setSmallMidLargeOneSize("small");
			product50.setSizeNumber(4);
			product50.setQuantity(50);
			product50.setGender("female");
			product50.setDescription("small black female shirt");
			productRepository.save(product50);
			Optional<Product> products50 = productRepository.findBySku(product50.getSku());
			int product50Id = product50.getId();

			//watches

			Product product51 = new Product();
			product51.setCategory("watches");
			product51.setSku("male_perfume1");
			product51.setPrice(800);
			product51.setSmallMidLargeOneSize("small");
			product51.setSizeNumber(-1);
			product51.setQuantity(50);
			product51.setGender("male");
			product51.setDescription("small male perfume");
			productRepository.save(product51);
			Optional<Product> products51 = productRepository.findBySku(product51.getSku());
			int product51Id = product51.getId();

			Product product52 = new Product();
			product52.setCategory("watches");
			product52.setSku("female_perfume1");
			product52.setPrice(800);
			product52.setSmallMidLargeOneSize("small");
			product52.setSizeNumber(-1);
			product52.setQuantity(50);
			product52.setGender("female");
			product52.setDescription("small female perfume");
			productRepository.save(product52);
			Optional<Product> products52 = productRepository.findBySku(product52.getSku());
			int product52Id = product52.getId();

			Product product53 = new Product();
			product53.setCategory("watches");
			product53.setSku("male_perfume2");
			product53.setPrice(800);
			product53.setSmallMidLargeOneSize("medium");
			product53.setSizeNumber(-1);
			product53.setQuantity(50);
			product53.setGender("male");
			product53.setDescription("medium male perfume");
			productRepository.save(product53);
			Optional<Product> products53 = productRepository.findBySku(product53.getSku());
			int product53Id = product53.getId();

			Product product54 = new Product();
			product54.setCategory("watches");
			product54.setSku("female_perfume2");
			product54.setPrice(800);
			product54.setSmallMidLargeOneSize("medium");
			product54.setSizeNumber(-1);
			product54.setQuantity(50);
			product54.setGender("female");
			product54.setDescription("medium female perfume");
			productRepository.save(product54);
			Optional<Product> products54 = productRepository.findBySku(product54.getSku());
			int product54Id = product54.getId();

			Product product55 = new Product();
			product55.setCategory("watches");
			product55.setSku("male_perfume3");
			product55.setPrice(800);
			product55.setSmallMidLargeOneSize("large");
			product55.setSizeNumber(-1);
			product55.setQuantity(50);
			product55.setGender("male");
			product55.setDescription("large male perfume");
			productRepository.save(product55);
			Optional<Product> products55 = productRepository.findBySku(product55.getSku());
			int product55Id = product55.getId();

			Product product56 = new Product();
			product56.setCategory("watches");
			product56.setSku("female_perfume3");
			product56.setPrice(800);
			product56.setSmallMidLargeOneSize("large");
			product56.setSizeNumber(-1);
			product56.setQuantity(50);
			product56.setGender("female");
			product56.setDescription("large female perfume");
			productRepository.save(product56);
			Optional<Product> products56 = productRepository.findBySku(product56.getSku());
			int product56Id = product56.getId();

			//sunglasses

			Product product57 = new Product();
			product57.setCategory("sunglasses");
			product57.setSku("male_sunglasses1");
			product57.setPrice(800);
			product57.setSmallMidLargeOneSize("oneSize");
			product57.setSizeNumber(-1);
			product57.setQuantity(50);
			product57.setGender("male");
			product57.setDescription("male sunglasses 1");
			productRepository.save(product57);
			Optional<Product> products57 = productRepository.findBySku(product57.getSku());
			int product57Id = product57.getId();

			Product product58 = new Product();
			product58.setCategory("sunglasses");
			product58.setSku("female_sunglasses1");
			product58.setPrice(800);
			product58.setSmallMidLargeOneSize("oneSize");
			product58.setSizeNumber(-1);
			product58.setQuantity(50);
			product58.setGender("female");
			product58.setDescription("female sunglasses 1");
			productRepository.save(product58);
			Optional<Product> products58 = productRepository.findBySku(product58.getSku());
			int product58Id = product58.getId();

			Product product59 = new Product();
			product59.setCategory("sunglasses");
			product59.setSku("male_sunglasses2");
			product59.setPrice(800);
			product59.setSmallMidLargeOneSize("oneSize");
			product59.setSizeNumber(-1);
			product59.setQuantity(50);
			product59.setGender("male");
			product59.setDescription("male sunglasses 2");
			productRepository.save(product59);
			Optional<Product> products59 = productRepository.findBySku(product59.getSku());
			int product59Id = product59.getId();

			Product product60 = new Product();
			product60.setCategory("sunglasses");
			product60.setSku("female_sunglasses2");
			product60.setPrice(800);
			product60.setSmallMidLargeOneSize("oneSize");
			product60.setSizeNumber(-1);
			product60.setQuantity(50);
			product60.setGender("female");
			product60.setDescription("female sunglasses 2");
			productRepository.save(product60);
			Optional<Product> products60 = productRepository.findBySku(product60.getSku());
			int product60Id = product60.getId();

			Product product61 = new Product();
			product61.setCategory("sunglasses");
			product61.setSku("male_sunglasses3");
			product61.setPrice(800);
			product61.setSmallMidLargeOneSize("oneSize");
			product61.setSizeNumber(-1);
			product61.setQuantity(50);
			product61.setGender("male");
			product61.setDescription("male sunglasses 3");
			productRepository.save(product61);
			Optional<Product> products61 = productRepository.findBySku(product61.getSku());
			int product61Id = product61.getId();

			Product product62 = new Product();
			product62.setCategory("sunglasses");
			product62.setSku("female_sunglasses3");
			product62.setPrice(800);
			product62.setSmallMidLargeOneSize("oneSize");
			product62.setSizeNumber(-1);
			product62.setQuantity(50);
			product62.setGender("female");
			product62.setDescription("female sunglasses 3");
			productRepository.save(product62);
			Optional<Product> products62 = productRepository.findBySku(product62.getSku());
			int product62Id = product62.getId();

			Product product63 = new Product();
			product63.setCategory("sunglasses");
			product63.setSku("male_sunglasses4");
			product63.setPrice(800);
			product63.setSmallMidLargeOneSize("oneSize");
			product63.setSizeNumber(-1);
			product63.setQuantity(50);
			product63.setGender("male");
			product63.setDescription("male sunglasses 4");
			productRepository.save(product63);
			Optional<Product> products63 = productRepository.findBySku(product63.getSku());
			int product63Id = product63.getId();

			Product product64 = new Product();
			product64.setCategory("sunglasses");
			product64.setSku("female_sunglasses4");
			product64.setPrice(800);
			product64.setSmallMidLargeOneSize("oneSize");
			product64.setSizeNumber(-1);
			product64.setQuantity(50);
			product64.setGender("female");
			product64.setDescription("female sunglasses 4");
			productRepository.save(product64);
			Optional<Product> products64 = productRepository.findBySku(product64.getSku());
			int product64Id = product64.getId();

			//accessories

			Product product65 = new Product();
			product65.setCategory("accessories");
			product65.setSku("male_accessories1");
			product65.setPrice(800);
			product65.setSmallMidLargeOneSize("oneSize");
			product65.setSizeNumber(-1);
			product65.setQuantity(50);
			product65.setGender("male");
			product65.setDescription("male accessories 1");
			productRepository.save(product65);
			Optional<Product> products65 = productRepository.findBySku(product65.getSku());
			int product65Id = product65.getId();

			Product product66 = new Product();
			product66.setCategory("accessories");
			product66.setSku("female_accessories1");
			product66.setPrice(800);
			product66.setSmallMidLargeOneSize("oneSize");
			product66.setSizeNumber(-1);
			product66.setQuantity(50);
			product66.setGender("female");
			product66.setDescription("female accessories 1");
			productRepository.save(product66);
			Optional<Product> products66 = productRepository.findBySku(product66.getSku());
			int product66Id = product66.getId();

			Product product67 = new Product();
			product67.setCategory("accessories");
			product67.setSku("male_accessories2");
			product67.setPrice(800);
			product67.setSmallMidLargeOneSize("oneSize");
			product67.setSizeNumber(-1);
			product67.setQuantity(50);
			product67.setGender("male");
			product67.setDescription("male accessories 2");
			productRepository.save(product67);
			Optional<Product> products67 = productRepository.findBySku(product67.getSku());
			int product67Id = product67.getId();

			Product product68 = new Product();
			product68.setCategory("accessories");
			product68.setSku("female_accessories2");
			product68.setPrice(800);
			product68.setSmallMidLargeOneSize("oneSize");
			product68.setSizeNumber(-1);
			product68.setQuantity(50);
			product68.setGender("female");
			product68.setDescription("female accessories 2");
			productRepository.save(product68);
			Optional<Product> products68 = productRepository.findBySku(product68.getSku());
			int product68Id = product68.getId();

		};
	}
}
