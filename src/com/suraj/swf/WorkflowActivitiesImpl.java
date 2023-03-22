
package com.suraj.swf;

import javax.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;

/**
 * Activities Implementation of {@link SetDeviceConfigActivities}.
 *
 * @author suraj kumar
 */
@Log4j2
public class WorkflowActivitiesImpl implements WorkflowActivities {

  /**
   * {@link DeviceConfigWorkflowComponent} instance.
   */
  @Resource(name = DeviceConfigWorkflowComponent.BEAN_NAME)
  private DeviceConfigWorkflowComponent deviceConfigWorkflowComponent;
  /**
   * {@link OperationDbRepository} instance.
   */
  @Resource(name = OperationDbRepository.BEAN_NAME)
  private OperationDbRepository operationDbRepository;
  /**
   * {@link AdvancedOperationDbRepository} instance.
   */
  @Resource(name = AdvancedOperationDbRepository.BEAN_NAME)
  private AdvancedOperationDbRepository advancedOperationDbRepository;

  @Override
  public boolean setConfiguration(String opId, SetConfigParams setConfigParams)
      throws SystemException {
    try {
      ThreadContext.put(OPERATION_ID, opId);
      log.info("task : setConfiguration. (opId={}, deviceId={}, version={})", opId,
          setConfigParams.getEntityId(), setConfigParams.getEntityVersion());
      return deviceConfigWorkflowComponent.setDeviceConfiguration(opId, setConfigParams);
    } catch (final SystemException e) {
      log.error("Failed to execute Step: setConfiguration, will retry", e);
      OperationDbEntity operationDbEntity = operationDbRepository.findByOpId(opId);
      operationDbEntity.setNoOfTries(operationDbEntity.getNoOfTries() + 1);
      advancedOperationDbRepository.saveOperation(operationDbEntity);
      if (operationDbEntity.getNoOfTries() >= TOTAL_RETRIES) {
        return false;
      }
      throw new SystemException(e);
    } catch (ResourceNotFoundException e) {
      //no retry on resource not found.
      log.error("device or operation (opId={}, deviceId={}) with details not found.",
          opId, setConfigParams.getEntityId());
      return false;
    } catch (ForbiddenAccessException e) {
      //no retry on forbidden access
      log.error("No access to deviceId={}.", setConfigParams.getEntityId());
      return false;
    } catch (Exception e) {
      //uncaught exception
      log.error("unknown exception occurred in setConfiguration.", e);
      return false;
    } finally {
      ThreadContext.remove(OPERATION_ID);
    }
  }

  @Override
  public void finalizeOperation(String opId, boolean isSetConfigurationSuccess)
      throws SystemException {
    try {
      ThreadContext.put(OPERATION_ID, opId);
      deviceConfigWorkflowComponent.finalizeDeviceSetConfigOperation(opId,
          isSetConfigurationSuccess);
    } catch (Exception e) {
      log.error("Exception finalizing operation" + opId);
      throw e;
    } finally {
      ThreadContext.remove(OPERATION_ID);
    }
  }
}
