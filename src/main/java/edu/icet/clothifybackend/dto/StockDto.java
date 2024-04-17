package edu.icet.clothifybackend.dto;

import lombok.*;

import java.util.Date;
@Data
@ToString
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
