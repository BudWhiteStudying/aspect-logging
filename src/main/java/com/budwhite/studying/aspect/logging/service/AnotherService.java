package com.budwhite.studying.aspect.logging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnotherService {
  private final Logger logger = LoggerFactory.getLogger(AnotherService.class);

  public String insult(String target) {
    logger.info("I'm about to insult {}", target);
    return String.format("%s's ass is grass", target);
  }
}
