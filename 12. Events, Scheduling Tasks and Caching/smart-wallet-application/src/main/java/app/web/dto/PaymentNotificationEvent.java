package app.web.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentNotificationEvent {

    private UUID userId;

    private String email;

    private BigDecimal amount;
    
    private LocalDateTime paymentTime;
}
