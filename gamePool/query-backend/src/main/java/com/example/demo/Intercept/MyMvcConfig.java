package com.example.demo.Intercept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new ProofInterceptor()).addPathPatterns("/coinProof","/cytProof","/roleProof","/gameProof",
                "/coinProofWihtChianId","/cytProofWihtChianId","/roleProofWihtChianId","/gameProofWihtChianId",
        "/erc20ProofWihtChianId","/erc721ProofWihtChianId","/erc1155ProofWihtChianId","/gameUpgradeProofWihtChianId","/roleUpgradeProofWithChainId");

    }
}