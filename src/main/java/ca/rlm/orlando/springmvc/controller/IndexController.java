package ca.rlm.orlando.springmvc.controller;


import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class IndexController {

  @GetMapping("/")
  public Object json(final HttpServletResponse response) {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    return "Welcome to Orlando.";
  }
}
