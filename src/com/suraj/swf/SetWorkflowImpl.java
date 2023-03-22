
package com.suraj.swf;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.tarana.tcc.deviceconfig.service.entity.SetConfigParams;
import com.tarana.tcc.deviceconfig.service.swf.activity.SetDeviceConfigActivitiesClient;
import javax.annotation.Resource;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * Workflow implementation of {@link com.tarana.tcc.deviceconfig.service.swf.workflow.SetDeviceConfigWorkflow}.
 *
 * @author suraj kumar
 */
@Log4j2
public class SetWorkflowImpl implements SetWorkflow {

  /**
   * {@link SetDeviceConfigActivitiesClient} instance.
   */
  @Setter
  @Resource
  private SetDeviceConfigActivitiesClient activitiesClient;

  @Override
  public void start(String opId, SetConfigParams setConfigParams) {
    log.info("SetDeviceConfigWorkflow - Deciding next task");
    final Promise<Boolean> isSetConfigSuccess =
        activitiesClient.setConfiguration(opId, setConfigParams);

    activitiesClient.finalizeOperation(Promise.asPromise(opId),
        isSetConfigSuccess);
  }
}
