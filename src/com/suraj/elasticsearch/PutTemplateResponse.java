
package com.suraj.elasticsearch;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author suraj kumar
 */
@Builder
@Getter
@ToString
public class PutTemplateResponse {

  private String name;
  private boolean acknowledged;
}
