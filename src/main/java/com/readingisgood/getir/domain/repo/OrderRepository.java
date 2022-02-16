package com.readingisgood.getir.domain.repo;

import com.readingisgood.getir.domain.model.OrderEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends BaseJpaRepository<OrderEntity, Long> {

    List<OrderEntity> findOrderEntityByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(Date startDate, Date endDate);

    List<OrderEntity> findAllByCustomerId(Long customerId);

    @Query(
        value = "SELECT " +
                "to_char(oe.created_date, 'MM') month, " +
                "oe.amount amount, " +
                "count(od.id) productCount, " +
                "count(DISTINCT oe.id) orderCount " +
                "FROM ORDER_ENTITY oe " +
                "INNER JOIN ORDER_ENTITY_ORDER_DETAIL_ENTITIES oeod ON oeod.order_entity_id = oe.id " +
                "INNER JOIN ORDER_DETAIL od ON od.id = oeod.order_detail_entities_id " +
                "AND oe.customer_id = :customerId " +
                "GROUP BY to_char(oe.created_date, 'MM')",
        nativeQuery = true)
    List<StatisticMonthConvert> getAllMontlyOrderByCustomer(Long customerId);
}