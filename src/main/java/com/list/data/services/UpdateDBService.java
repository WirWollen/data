package com.list.data.services;

import com.list.data.dtos.ParseNamingDto;
import com.list.data.dtos.market.MarketElementDto;
import com.list.data.dtos.market.UpperMarketDto;
import com.list.data.dtos.parsed.ConditionDto;
import com.list.data.dtos.parsed.ItemDto;
import com.list.data.dtos.parsed.NamingDto;
import com.list.data.dtos.parsed.WeaponTypeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateDBService {
    @Value("${steam.all-cs-item-url}")
    private String url;
    private final SenderService senderService;
    private final RestTemplate restTemplate = new RestTemplate();

    public boolean parseAllItems() {
        ResponseEntity<UpperMarketDto> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        int totalSize = Objects.requireNonNull(response.getBody()).getTotal_count();
        for (int i = 0; i < totalSize; i += 100) {
            ResponseEntity<UpperMarketDto> response2 = restTemplate.exchange(url + "start=" + i, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
            });
            List<MarketElementDto> items = Objects.requireNonNull(response2.getBody()).getResults();
            if (items.size() != 0) {
                items.stream().map(this::parseName).filter(Objects::nonNull).forEach(senderService::send);
            }
        }
        return true;
    }

    private ItemDto parseName(MarketElementDto dto) {
        ParseNamingDto parseNamingDto = stringToDto(dto.getName());
        if (parseNamingDto != null) {
            ItemDto item = new ItemDto();
            item.setConditionDto(new ConditionDto(null, parseNamingDto.getCondition()));
            item.setWeaponTypeDto(new WeaponTypeDto(null, parseNamingDto.getWeapon()));
            item.setNamingDto(new NamingDto(null, null, parseNamingDto.getName(), dto.getAsset_description().getIcon_url()));
            item.setSt(parseNamingDto.getSt());
            item.setSouvenir(parseNamingDto.getSouvenir());
            item.setActive(true);

            return item;
        }
        return null;
    }

    private ParseNamingDto stringToDto(String value) {
        ParseNamingDto dto = null;
        final String pattern = "^(StatTrak™)?\\s?(Souvenir)?\\s?(.+?)\\s\\|\\s(.+?)\\s\\((.+?)\\)$";
        Matcher matcher = Pattern.compile(pattern).matcher(value);
        if (matcher.find()) {
            dto = new ParseNamingDto();
            dto.setSt(matcher.group(1) != null);
            dto.setSouvenir(matcher.group(2) != null);
            dto.setWeapon(matcher.group(3));
            dto.setName(matcher.group(4));
            dto.setCondition(matcher.group(5));
        }
        return dto;
    }

}
