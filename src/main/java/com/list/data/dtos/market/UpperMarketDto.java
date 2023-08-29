package com.list.data.dtos.market;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpperMarketDto {
    private Boolean success;
    private Integer start;
    private Integer pagesize;
    private Integer total_count;
    private SearchDataDto searchdata;
    List<MarketElementDto> results;
}
