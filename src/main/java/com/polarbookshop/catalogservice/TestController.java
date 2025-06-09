package com.polarbookshop.catalogservice;


import com.polarbookshop.catalogservice.config.PolarProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

  private final PolarProperties polarProperties;

  @GetMapping("/")
  public String test() {
    return polarProperties.getGreeting();
  }

}
