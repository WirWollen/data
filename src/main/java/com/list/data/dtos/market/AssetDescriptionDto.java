package com.list.data.dtos.market;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetDescriptionDto {
    private Integer appid;
    private String classid;
    private String instanceid;
    private String background_color;
    private String icon_url;
    private String tradable;
    private String name;
    private String name_color;
    private String type;
    private String market_name;
    private String market_hash_name;
    private Integer commodity;
}
