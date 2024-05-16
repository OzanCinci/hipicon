package com.ozan.be.common;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class AbstractSearchFilter {
  public abstract void buildPredicateList(List<Predicate> predicateList);

  public Predicate getPredicate() {
    ArrayList<Predicate> predicateList = new ArrayList();

    buildPredicateList(predicateList);

    return predicateList.isEmpty()
        ? Expressions.TRUE.isTrue()
        : ExpressionUtils.allOf(predicateList);
  }
}
