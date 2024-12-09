package com.sheikhiahmed.productmanagerapi;

import com.sheikhiahmed.productmanagerapi.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;


import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
@JsonTest
public class ProductJsonTest {
    @Autowired
    private JacksonTester<ProductDto> json;

    //Serialization object to json
    @Test
    void productSerializationTest() throws Exception {
        ProductDto product = ProductDto.builder()
                .id(99L)
                .name("Bucket Bag")
                .description("A bucket bag got into a trend just some time ago")
                .price(12.30)
                .category("Bag")
                .build();

        // Check if the entire JSON matches the expected file
        assertThat(json.write(product)).isStrictlyEqualToJson("expected.json");


        // Verify the presence and value of individual JSON fields
        assertThat(json.write(product)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(product)).extractingJsonPathNumberValue("@.id").isEqualTo(99);

        assertThat(json.write(product)).hasJsonPathStringValue("@.name");
        assertThat(json.write(product)).extractingJsonPathStringValue("@.name").isEqualTo("Bucket Bag");

        assertThat(json.write(product)).hasJsonPathStringValue("@.description");
        assertThat(json.write(product)).extractingJsonPathStringValue("@.description")
                .isEqualTo("A bucket bag got into a trend just some time ago");

        assertThat(json.write(product)).hasJsonPathNumberValue("@.price");
        assertThat(json.write(product)).extractingJsonPathNumberValue("@.price").isEqualTo(12.30);

        assertThat(json.write(product)).hasJsonPathStringValue("@.category");
        assertThat(json.write(product)).extractingJsonPathStringValue("@.category").isEqualTo("Bag");

        // check for constraints
        assertThat(json.write(product)).extractingJsonPathStringValue("@.name").isNotNull();
        assertThat(json.write(product)).extractingJsonPathNumberValue("@.price").isNotNull();
        assertThat(json.write(product)).extractingJsonPathStringValue("@.category").isNotNull();
    }

    // Deserialization to transform a file or byte stream back into an object
    @Test
    void productDeserializationTest() throws Exception{
        String expected = """
                {  "id": 99,
                   "name": "Bucket Bag",
                   "description": "A bucket bag got into a trend just some time ago",
                   "price": 12.30,
                   "category": "Bag"}
                """;
        ProductDto productDto = ProductDto.builder()
                .id(99L)
                .name("Bucket Bag")
                .description("A bucket bag got into a trend just some time ago")
                .price(12.30)
                .category("Bag")
                .build();
        assertThat(json.parseObject(expected).getId()).isEqualTo(99);
        assertThat(json.parseObject(expected).getName()).isEqualTo("Bucket Bag");
        assertThat(json.parseObject(expected).getDescription()).isEqualTo("A bucket bag got into a trend just some time ago");
        assertThat(json.parseObject(expected).getPrice()).isEqualTo(12.30);
        assertThat(json.parseObject(expected).getCategory()).isEqualTo("Bag");
        //
        assertThat(json.parseObject(expected).getName()).isNotNull(); // Name is required
        assertThat(json.parseObject(expected).getPrice()).isGreaterThanOrEqualTo(0.0); // Price must be greater than 0
        assertThat(json.parseObject(expected).getCategory()).isNotNull(); // Category is required
    }


    }





