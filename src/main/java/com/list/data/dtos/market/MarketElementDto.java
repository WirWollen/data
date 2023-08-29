package com.list.data.dtos.market;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarketElementDto {
    private String name;
    private String hash_name;
    private Integer sell_listings;
    private Integer sell_price;
    private String sell_price_text;
    private String app_icon;
    private String app_name;
    private AssetDescriptionDto asset_description;
    private String sale_price_text;
}
