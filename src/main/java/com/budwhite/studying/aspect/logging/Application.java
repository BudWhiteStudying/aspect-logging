package com.budwhite.studying.aspect.logging;

import com.budwhite.studying.aspect.logging.service.AnotherService;
import com.budwhite.studying.aspect.logging.service.FakeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
  private static final Logger logger = LoggerFactory.getLogger(Application.class);
  private static final String GREETING_TARGET = "Pippo";

  private static final FakeService fakeService = new FakeService();
  private static final AnotherService anotherService = new AnotherService();

  public static void main(String[] args) {
    logger.info("Application started");
    logger.info("Greeting result is '{}'", fakeService.greet());
    logger.info("Insulting result is '{}'", anotherService.insult(GREETING_TARGET));
  }
}
