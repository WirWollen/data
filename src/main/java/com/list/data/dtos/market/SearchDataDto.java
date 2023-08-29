package com.list.data.dtos.market;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDataDto {
    private String query;
    private Boolean search_descriptions;
    private Integer total_count;
    private Integer pagesize;
    private String prefix;
    private String class_prefix;
}
