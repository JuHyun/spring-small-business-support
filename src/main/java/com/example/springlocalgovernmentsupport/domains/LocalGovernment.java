package com.example.springlocalgovernmentsupport.domains;

import com.example.springlocalgovernmentsupport.dtos.LocalGovernmentSupportedItemCsvDto;
import com.example.springlocalgovernmentsupport.generators.StringPrefixedSequenceIdGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

// TODO: UNIQUE KEY Custom Name으로 생성되지 않는 현상 Fix.
@Entity
@Table(name = "local_government",
        uniqueConstraints = @UniqueConstraint(name = "uk_local_government_name", columnNames = "name"))
@NoArgsConstructor
@Getter
public class LocalGovernment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "local_government_seq")
    @GenericGenerator(
            name = "local_government_seq",
            strategy = "com.example.springlocalgovernmentsupport.generators.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "reg"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")})
    private String id;

    @Column(name = "name", length = 100, unique = true, nullable = false)
    @Setter
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supported_item_id", foreignKey = @ForeignKey(name = "fk_supported_item_id"))
    private SupportedItem supportedItem;

    public LocalGovernment(String name) {
        this.name = name;
    }

    public LocalGovernment(String name, SupportedItem supportedItem) {
        this.name = name;
        this.setSupportedItem(supportedItem);
    }

    public void setSupportedItem(SupportedItem supportedItem) {
        if (supportedItem == null) {
            if (this.supportedItem != null) {
                this.supportedItem.setLocalGovernment(null);
            }
        } else {
            if (supportedItem.getLocalGovernment() != this) {
                this.supportedItem = supportedItem;
                supportedItem.setLocalGovernment(this);
            }
        }
        this.supportedItem = supportedItem;
    }

    public static LocalGovernment create(LocalGovernmentSupportedItemCsvDto supportedItemDto) {
        LocalGovernment localGovernment = new LocalGovernment(supportedItemDto.getLocalGovernmentName());
        return localGovernment;
    }
}
