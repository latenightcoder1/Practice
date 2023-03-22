
package com.suraj.mongo;

import io.micrometer.core.annotation.Timed;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.mapping.BasicMongoPersistentEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.stereotype.Repository;

/**
 * Operation repository for advanced operations.
 *
 * @author suraj kumar
 */
@Repository(AdvancedOperationDbRepository.BEAN_NAME)
@Log4j2
public class AdvancedOperationDbRepository
    extends SimpleMongoRepository<OperationDbEntity, String> {

  /**
   * Bean name.
   */
  public static final String BEAN_NAME = "advancedOperationDbRepository";

  private MongoOperations mongoOperations;

  private MongoEntityInformation<OperationDbEntity, String> entityInformation;

  @Value("${mongodb.ttl.days:90}")
  private long ttlInDays;

  /**
   * Creates a new {@link SimpleMongoRepository} for the given {@link MongoEntityInformation} and
   * {@link MongoOperations}.
   *
   * @param mongoOperations must not be {@literal null}.
   */
  public AdvancedOperationDbRepository(
      @Autowired
          MongoOperations mongoOperations) {
    super(MongoEntityInformationFactory.entityInformationFor(new BasicMongoPersistentEntity(
        ClassTypeInformation.from(OperationDbEntity.class)), String.class), mongoOperations);

    this.mongoOperations = mongoOperations;

    this.entityInformation =
        MongoEntityInformationFactory.entityInformationFor(new BasicMongoPersistentEntity(
            ClassTypeInformation.from(OperationDbEntity.class)), String.class);
  }

  /**
   * Find all operations for operatorId and retailerId.
   *
   * @param pageable {@link Pageable}
   * @param filter   {@link DeviceConfigOperationSearchFilter}
   * @param range    {@link DeviceConfigOperationSearchRange}
   * @return {@link Page}
   */
  @Timed(histogram = true, value = "database.calls",
      description = "deviceconfig service db calls",
      extraTags = {"activity", "find-paginated-operations"})
  public Page<OperationDbEntity> findAll(Pageable pageable,
      DeviceConfigOperationSearchFilter filter, DeviceConfigOperationSearchRange range,
      Integer retailerId) {
    Query query = new Query();

    if (filter != null) {
      if (StringUtils.isNotBlank(filter.getOpId())) {
        query.addCriteria(Criteria.where(OperationEntityFields.OP_ID).is(filter.getOpId()));
      }
      if (StringUtils.isNotBlank(filter.getOperationStatus())) {
        query.addCriteria(
            Criteria.where(OperationEntityFields.OPERATION_STATUS).is(filter.getOperationStatus()));
      }
      if (StringUtils.isNotBlank(filter.getOperationType())) {
        query.addCriteria(
            Criteria.where(OperationEntityFields.OPERATION_TYPE).is(filter.getOperationType()));
      }
      if (StringUtils.isNotBlank(filter.getEntityVersion())) {
        query.addCriteria(
            Criteria.where(OperationEntityFields.ENTITY_VERSION).is(filter.getEntityVersion()));
      }

      //entity type
      if (StringUtils.isNotBlank(filter.getEntityId())) {
        query.addCriteria(Criteria.where(OperationEntityFields.ENTITY_ID).is(filter.getEntityId()));
      }
      if (StringUtils.isNotBlank(filter.getEntityType())) {
        query.addCriteria(
            Criteria.where(OperationEntityFields.ENTITY_TYPE).is(filter.getEntityType()));
      }

      //hierarchy
      if (filter.getSectorId() != null) {
        query.addCriteria(Criteria.where(OperationEntityFields.SECTOR_ID).is(filter.getSectorId()));
      }
      if (filter.getCellId() != null) {
        query.addCriteria(Criteria.where(OperationEntityFields.CELL_ID).is(filter.getCellId()));
      }
      if (filter.getSiteId() != null) {
        query.addCriteria(Criteria.where(OperationEntityFields.SITE_ID).is(filter.getSiteId()));
      }
      if (filter.getMarketId() != null) {
        query.addCriteria(Criteria.where(OperationEntityFields.MARKET_ID).is(filter.getMarketId()));
      }
      if (filter.getRegionId() != null) {
        query.addCriteria(Criteria.where(OperationEntityFields.REGION_ID).is(filter.getRegionId()));
      }
      if (filter.getOperatorId() != null) {
        query.addCriteria(Criteria.where(OperationEntityFields.OPERATOR_ID)
            .is(String.valueOf(filter.getOperatorId())));
      }
      if (retailerId != null) {
        query.addCriteria(
            Criteria.where(OperationEntityFields.RETAILER_ID).is(String.valueOf(retailerId)));
      }
    }

    if (range != null) {
      if (range.getStartTimeMillisRange() != null
          && range.getStartTimeMillisRange().getGte() != null
          && range.getStartTimeMillisRange().getLte() != null) {
        query.addCriteria(
            Criteria.where(OperationEntityFields.OPERATION_START_MILLIS).exists(true)
                .andOperator(
                    Criteria.where(OperationEntityFields.OPERATION_START_MILLIS)
                        .gte(range.getStartTimeMillisRange().getGte()),
                    Criteria.where(OperationEntityFields.OPERATION_START_MILLIS)
                        .lte(range.getStartTimeMillisRange().getLte())));
      }

      if (range.getCompletedTimeMillisRange() != null &&
          range.getCompletedTimeMillisRange().getGte() != null
          && range.getCompletedTimeMillisRange().getLte() != null) {
        query.addCriteria(
            Criteria.where(OperationEntityFields.OPERATION_COMPLETED_MILLIS).exists(true)
                .andOperator(
                    Criteria.where(OperationEntityFields.OPERATION_COMPLETED_MILLIS)
                        .gte(range.getCompletedTimeMillisRange().getGte()),
                    Criteria.where(OperationEntityFields.OPERATION_COMPLETED_MILLIS)
                        .lte(range.getCompletedTimeMillisRange().getLte())));
      }
    }
    return findAll(query, pageable);
  }
  public Page<OperationDbEntity> findAll(final Query query, final Pageable pageable) {
    long total = mongoOperations
        .count(query, entityInformation.getJavaType(), entityInformation.getCollectionName());

    List<OperationDbEntity> content =
        mongoOperations.find(query.with(pageable), entityInformation.getJavaType(),
            entityInformation.getCollectionName());

    return new PageImpl<OperationDbEntity>(content, pageable, total);
  }

  /**
   * save operations.
   *
   * @param operationDbEntity {@link OperationDbEntity}
   * @return {@link OperationDbEntity}
   */
  @Timed(histogram = true, value = "database.calls",
      description = "deviceconfig service db calls",
      extraTags = {"activity", "save-operation"})
  public OperationDbEntity saveOperation(OperationDbEntity operationDbEntity) {
    operationDbEntity.setWriteTime(new Date());
    try {
      ensureWriteTimeIndex(mongoOperations);
      return mongoOperations.save(operationDbEntity);
    } catch (Exception e) {
      log.info("Operation save caught an error : ", e);
      mongoOperations.indexOps(OperationDbEntity.class).dropIndex(OperationEntityFields.WRITE_TIME);
      log.info("Indexes with operations after dropping available index writeTime : {}",
          mongoOperations.indexOps(OperationDbEntity.class).getIndexInfo());
      ensureWriteTimeIndex(mongoOperations);
      log.info("Indexes with operations after creating new index writeTime : {}",
          mongoOperations.indexOps(OperationDbEntity.class).getIndexInfo());
      return mongoOperations.save(operationDbEntity);
    }
  }

  /**
   * Ensure ttl index writeTime available, if not it will be created.
   *
   * @param mongoOperations {@link MongoOperations}
   */
  private void ensureWriteTimeIndex(MongoOperations mongoOperations) {
    mongoOperations
        .indexOps(OperationDbEntity.class)
        .ensureIndex(new Index().on(OperationEntityFields.WRITE_TIME, Sort.Direction.ASC)
            .named(OperationEntityFields.WRITE_TIME).expire(ttlInDays, TimeUnit.DAYS));
  }

}
