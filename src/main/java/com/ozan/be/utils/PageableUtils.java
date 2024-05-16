package com.ozan.be.utils;

import java.util.Map;
import java.util.TreeMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Slf4j
public class PageableUtils {

  /** Sorting for entities that do not inherit from the Auditable class. */
  public static Pageable prepareDefaultSorting(Pageable pageable) {
    Map<String, String> defaultSorting = new TreeMap<>();
    defaultSorting.put("createdAt", "desc");
    return applySorting(pageable, defaultSorting);
  }

  /** Sorting for entities that inherit from the Auditable class. */
  public static Pageable prepareAuditSorting(Pageable pageable) {
    Map<String, String> defaultSorting = new TreeMap<>();
    defaultSorting.put("updatedAt", "desc");
    defaultSorting.put("createdAt", "desc");
    return applySorting(pageable, defaultSorting);
  }

  /**
   * Applies the default sorting to the given pageable object based on the provided sort map.
   *
   * @param pageable: a pageable object
   * @param sortMap: a map containing sorting fields and orders
   * @return a sorted pageable object
   */
  private static PageRequest applySorting(Pageable pageable, Map<String, String> sortMap) {
    Sort[] finalSort = {pageable.getSort()};

    sortMap.forEach(
        (sortBy, sortOrder) -> {
          Sort sort =
              "desc".equalsIgnoreCase(sortOrder)
                  ? Sort.by(Sort.Order.desc(sortBy))
                  : Sort.by(Sort.Order.asc(sortBy));
          finalSort[0] = finalSort[0].and(sort);
        });

    return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), finalSort[0]);
  }
}
