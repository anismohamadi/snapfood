package com.kurdestan.snapfood.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteria {
    private String Key;
    //برای اینکه تمام اتریبیوت ها رو ساپورت کنیم از object استفاده میکنیم
    private  Object value;
    private SearchOperation operation;
}
