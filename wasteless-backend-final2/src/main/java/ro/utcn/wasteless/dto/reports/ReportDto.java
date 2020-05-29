package ro.utcn.wasteless.dto.reports;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportDto {
    private int weekCalories;
    private int weekQuantity;
    private int monthCalories;
    private int monthQuantity;
}
