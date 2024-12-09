package com.sheikhiahmed.productmanagerapi;

import com.sheikhiahmed.productmanagerapi.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
@JsonTest
public class ProductJsonTest {
    @Autowired
    private JacksonTester<ProductDto> json;
    @Test
    void productSerializationTest() throws IOException {
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
    }



    }

