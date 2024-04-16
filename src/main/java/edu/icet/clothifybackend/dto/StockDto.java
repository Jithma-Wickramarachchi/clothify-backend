package edu.icet.clothifybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    private Long stockId;
    private String companyName;
    private Integer initialItemCount;
    private Integer availableItemCount;
    private Date date;
}
