package com.example.demo.repository.specification;

import com.example.demo.entity.ProductEntity;
import com.example.demo.enumeration.BrandEnumeration;
import com.example.demo.enumeration.ColorEnumeration;
import com.example.demo.exception.InvalidCriteriaException;
import com.example.demo.repository.specification.vo.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Slf4j
public class ProductSpecification implements Specification<ProductEntity> {

    private static final String PERCENT = "%";

    private final List<SearchCriteria> listSearchCriteria;

    @Override
    public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : listSearchCriteria) {

            final String key = criteria.getKey();
            final String value = criteria.getValue();
            final String fromValue = criteria.getFromValue();
            final String toValue = criteria.getToValue();

            Field field = null;

            try {
                field = ProductEntity.class.getDeclaredField(String.valueOf(key));
            } catch (NoSuchFieldException e) {
                log.error("NoSuchFieldException::" + e.getMessage());
            }

            switch (criteria.getOperation()) {
                case GREATER_THAN:
                    predicates.add(builder.greaterThan(root.get(key), String.valueOf(value)));
                    break;
                case GREATER_THAN_EQUAL:
                    predicates.add(builder.greaterThanOrEqualTo(root.get(key), value));
                    break;
                case LESS_THAN:
                    predicates.add(builder.lessThan(root.get(key), value));
                    break;
                case LESS_THAN_EQUAL:
                    predicates.add(builder.lessThanOrEqualTo(root.get(key), value));
                    break;
                case EQUAL:
                    if (Objects.nonNull(field) && field.getType() == ColorEnumeration.class) {
                        predicates.add(builder.equal(root.get(key), ColorEnumeration.valueOf(value)));
                    } else if (Objects.nonNull(field) && field.getType() == BrandEnumeration.class) {
                        predicates.add(builder.equal(root.get(key), BrandEnumeration.valueOf(value)));
                    } else {
                        predicates.add(builder.equal(root.get(key), value));
                    }
                    break;
                case NOT_EQUAL:
                    if (Objects.nonNull(field) && field.getType() == ColorEnumeration.class) {
                        predicates.add(builder.notEqual(root.get(key), ColorEnumeration.valueOf(value)));
                    } else if (Objects.nonNull(field) && field.getType() == BrandEnumeration.class) {
                        predicates.add(builder.notEqual(root.get(key), BrandEnumeration.valueOf(value)));
                    } else {
                        predicates.add(builder.notEqual(root.get(key), value));
                    }
                    break;
                case MATCH:
                    predicates.add(builder.like(builder.lower(root.get(key)), PERCENT + value.toLowerCase() + PERCENT));
                    break;
                case MATCH_END:
                    predicates.add(builder.like(builder.lower(root.get(key)), value.toLowerCase() + PERCENT));
                    break;
                case MATCH_START:
                    predicates.add(builder.like(builder.lower(root.get(key)), PERCENT + value.toLowerCase()));
                    break;
                case IN:
                    predicates.add(builder.in(root.get(key)).value(value));
                    break;
                case NOT_IN:
                    predicates.add(builder.not(root.get(key)).in(value));
                    break;
                case BETWEEN:
                    if (Objects.nonNull(field) && field.getType() == LocalDate.class) {
                        LocalDate fromDate = LocalDate.parse(fromValue);
                        LocalDate toDate = LocalDate.parse(toValue);
                        predicates.add(builder.between(root.get(key), fromDate, toDate));

                    } else if (Objects.nonNull(field) && field.getType() == Double.class) {
                        predicates.add(builder.between(root.get(key), Double.valueOf(fromValue), Double.valueOf(toValue)));
                    }
                    break;
                default:
                    throw new InvalidCriteriaException("Invalid Search Criteria.");
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }

}
