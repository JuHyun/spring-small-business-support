package com.example.springlocalgovernmentsupport.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocalGovernmentSupportedItemCsvDto {

    @JsonProperty("구분")
    private Long id;

    @JsonProperty("지자체명(기관명)")
    private String localGovernmentName;

    @JsonProperty("지원대상")
    private String target;

    @JsonProperty("용도")
    private String usage;

    @JsonProperty("지원한도")
    private String limitAmount;

    @JsonProperty("이차보전")
    private String secondaryPreserve;

    @JsonProperty("추천기관")
    private String institute;

    @JsonProperty("관리점")
    private String mgmt;

    @JsonProperty("취급점")
    private String reception;

    @Builder
    public LocalGovernmentSupportedItemCsvDto(Long id, String localGovernmentName, String target, String usage,
                                              String limitAmount, String secondaryPreserve, String institute,
                                              String mgmt, String reception) {
        this.id = id;
        this.localGovernmentName = localGovernmentName;
        this.target = target;
        this.usage = usage;
        this.limitAmount = limitAmount;
        this.secondaryPreserve = secondaryPreserve;
        this.institute = institute;
        this.mgmt = mgmt;
        this.reception = reception;
    }
}
