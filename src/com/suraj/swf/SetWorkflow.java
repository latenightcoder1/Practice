package com.suraj.swf;

import com.amazonaws.services.simpleworkflow.flow.annotations.Execute;
import com.amazonaws.services.simpleworkflow.flow.annotations.Workflow;
import com.amazonaws.services.simpleworkflow.flow.annotations.WorkflowRegistrationOptions;
import com.tarana.tcc.deviceconfig.service.entity.SetConfigParams;

/**
 * SetDeviceConfigWorkflow interface definition.
 *
 * @author suraj kumar
 */
@Workflow
@WorkflowRegistrationOptions(defaultExecutionStartToCloseTimeoutSeconds = 10 * 60)
public interface SetWorkflow {

  /**
   * Starts the workflow.
   * @param opId - operation Id.
   * @param setConfigParams - parameters for set configuration.
   */
  @Execute(version = "1.0")
  void start(String opId, SetConfigParams setConfigParams);
}
