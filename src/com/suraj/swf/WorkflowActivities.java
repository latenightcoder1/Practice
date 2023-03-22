
package com.suraj.swf;

import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;
import com.amazonaws.services.simpleworkflow.flow.annotations.ExponentialRetry;
/**
 * Set Configuration activities interface.
 *
 * @author suraj kumar
 */
@ActivityRegistrationOptions(defaultTaskScheduleToStartTimeoutSeconds = 600,
    defaultTaskStartToCloseTimeoutSeconds = 600)
@Activities(version = "1.0")
public interface WorkflowActivities {

  /**
   * Set configuration operation.
   *
   * @param opId            - operation Id
   * @param setConfigParams - setConfig parameters.
   * @return {@link Boolean} true if setConfiguration is successful
   * @throws SystemException on system error
   */
  @ExponentialRetry(initialRetryIntervalSeconds = 5, maximumAttempts = 5,
      retryExpirationSeconds = 600)
  boolean setConfiguration(String opId, SetConfigParams setConfigParams) throws SystemException;

  /**
   * Marks the operation as success or failure in case of time out and other activities as last leg
   * of the workflow
   *
   * @param opId                      - operation Id
   * @param isSetConfigurationSuccess - isSetConfigurationSuccess.
   */
  void finalizeOperation(String opId, boolean isSetConfigurationSuccess) throws SystemException;

}
