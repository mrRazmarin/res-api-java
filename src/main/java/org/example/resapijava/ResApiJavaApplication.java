package org.example.resapijava;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ResApiJavaApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(
                ResApiJavaApplication.class
        )
                .headless(false)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false);

        builder.run(args);
    }
}
