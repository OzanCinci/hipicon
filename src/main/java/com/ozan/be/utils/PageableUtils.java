package com.ozan.be.utils;

import java.util.Map;
import java.util.TreeMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Slf4j
public class PageableUtils {

  public static Pageable prepareDefaultSorting(Pageable pageable, Map<String, String> sortMap) {
    // If any sorting is applied then keep it as is
    if (pageable.getSort().isSorted()) {
      return pageable;
    }

    // if not sorting applied set default sorts
    return returnDefaultSorting(pageable, sortMap);
  }

  public static Pageable prepareDefaultAuditSorting(Pageable pageable) {
    Map<String, String> defaultSorting = new TreeMap<>();
    defaultSorting.put("lastUpdate", "asc");
    defaultSorting.put("createdAt", "asc");
    return prepareDefaultSorting(pageable, defaultSorting);
  }

  public static Pageable prepareUserAuditSorting(Pageable pageable) {
    Map<String, String> defaultSorting = new TreeMap<>();
    defaultSorting.put("createdAt", "asc");
    return prepareDefaultSorting(pageable, defaultSorting);
  }

  private static PageRequest returnDefaultSorting(Pageable pageable, Map<String, String> sortMap) {

    Sort finalSort = pageable.getSort();

    for (String sortBy : sortMap.keySet()) {

      String sortOrder = sortMap.get(sortBy);
      Sort sort;

      if ("desc".equalsIgnoreCase(sortOrder)) {
        sort = Sort.by(Sort.Order.desc(sortBy));
      } else {
        sort = Sort.by(Sort.Order.asc(sortBy));
      }
      finalSort = sort.and(finalSort);
    }

    return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), finalSort);
  }
}
