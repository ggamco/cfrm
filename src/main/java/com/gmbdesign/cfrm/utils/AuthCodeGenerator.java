package com.gmbdesign.cfrm.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public class AuthCodeGenerator {

  private SecureRandom random = new SecureRandom();

  public String generateAuthCode() {
    return new BigInteger(130, random).toString(32);
  } 
}
