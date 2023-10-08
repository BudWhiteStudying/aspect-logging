package com.budwhite.studying.aspect.logging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakeService {
  private final Logger logger = LoggerFactory.getLogger(FakeService.class);

  public String greet() {
    logger.info("Doing some important shit in here");
    return "Hello";
  }
}
