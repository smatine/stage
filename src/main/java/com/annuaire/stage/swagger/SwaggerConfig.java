package com.annuaire.stage.swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;

import springfox.documentation.service.Parameter;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Predicates.*;
import static com.google.common.collect.Lists.*;
import static springfox.documentation.builders.PathSelectors.*;

@Configuration
@EnableSwagger2
@Component
public class SwaggerConfig {

   private static final Logger logger = LoggerFactory.getLogger( SwaggerConfig.class );
    
    /** The title for the spring boot service to be displayed on swagger UI.  */  
    @Value("${swagger.title:spring.application.name:app_title}")  
    private String title;  
    
    /** The description of the spring boot service. */  
    @Value("${swagger.description:spring.application.description:app_description}")  
    private String description;  
   
    /** The version of the service. */  
    @Value("${swagger.version:spring.application.version:versionxxx}")  
    private String version;  
   
    /** The terms of service url for the service if applicable. */  
    @Value("${swagger.termsOfServiceUrl:terms_of_service_url:}")  
    private String termsOfServiceUrl;  
   
    /** The contact name for the service. */  
    @Value("${swagger.contact.name:contact_name}")  
    private String contactName;  
   
    /** The contact url for the service. */  
    @Value("${swagger.contact.url:contact_url}")  
    private String contactURL;  
   
    /** The contact email for the service. */  
    @Value("${swagger.contact.email:email_address}")  
    private String contactEmail;  
   
    /** The license for the service if applicable. */  
    @Value("${swagger.license:license_body}")  
    private String license;  
   
    /** The license url for the service if applicable. */  
    @Value("${swagger.licenseUrl:client_licenseUrl}")  
    private String licenseURL;  
 
  public static final Contact DEFAULT_CONTACT = new Contact(
      "Soufiane Matine", "http://www.smatine.com", "soufiane.matine@gmail.com");
  
  public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
      "API Title", "API Description", "1.0",
      "urn:tos", DEFAULT_CONTACT, 
      "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

  private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = 
      new HashSet<String>(Arrays.asList("application/json"));
 
  private List<Parameter> listDocketParameters;

  
  public SwaggerConfig() {
      
      //Any parameter or header you want to require for all end_points
      Parameter oAuthHeader = new ParameterBuilder()
                                  .name("Authorization")
                                  .description("OAUTH JWT Bearer Token")
                                  .defaultValue("Bearer YourJWTTokenHere")
                                  .modelRef(new ModelRef("string"))
                                  .parameterType("header")
                                  .required(true)
                                  .build();
  
      this.listDocketParameters = new ArrayList<Parameter>();
      this.listDocketParameters.add(oAuthHeader);
  }
  
  @Bean
  public Docket api() {
	  //.apis(RequestHandlerSelectors.withClassAnnotation(classOf[RestController]))
    return new Docket(DocumentationType.SWAGGER_2)
    	.globalOperationParameters(listDocketParameters) 
        .apiInfo(DEFAULT_API_INFO)
        .produces(DEFAULT_PRODUCES_AND_CONSUMES)
        .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.annuaire.stage.controller"))
        //.paths(PathSelectors.ant("/annuaires/**"))
        .paths(stagePaths())
	    //.paths(PathSelectors.any())
	    .build()
        ;
  }
  
  private Predicate<String> stagePaths() {
      return or(
              regex("/annuaires/*.*"),
              regex("/user/*.*"),
              regex("/auth/*.*")
      );
  }
  
}