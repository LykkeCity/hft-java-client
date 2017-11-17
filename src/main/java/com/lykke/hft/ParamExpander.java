package com.lykke.hft;

import feign.Param;

import java.text.DateFormat;
import java.util.Date;

/**
 * Param Expander to convert {@link java.util.Date} to RFC3339
 *
 * @author niau
 * @version $Id: $Id
 */
public class ParamExpander implements Param.Expander {

  private static final DateFormat dateformat = new RFC3339DateFormat();

  /** {@inheritDoc} */
  @Override
  public String expand(Object value) {
    if (value instanceof Date) {
      return dateformat.format(value);
    }
    return value.toString();
  }
}
