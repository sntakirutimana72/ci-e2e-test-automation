package com.util;

import com.ibm.icu.text.RuleBasedNumberFormat;
import org.slf4j.helpers.MessageFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FormatUtil {

  public static String numberToWord(long number) {
    return new RuleBasedNumberFormat(Locale.US, RuleBasedNumberFormat.SPELLOUT)
      .format(number);
  }

  public static String interpolate(String template, Object...args) {
    return MessageFormatter.format(template, args).getMessage();
  }

  public static double extractPrice(String price) {
    String amount = price.replaceAll(".*?(\\d+\\.\\d+).*", "$1");
    return Double.parseDouble(amount);
  }
}
