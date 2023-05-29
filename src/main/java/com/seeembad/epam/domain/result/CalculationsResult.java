package com.seeembad.epam.domain.result;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CalculationsResult {

    @Id
    private long value;

    private boolean isEven;

    private boolean isPrime;

    public CalculationsResult(long value, boolean isEven, boolean isPrime) {
        this.value = value;
        this.isEven = isEven;
        this.isPrime = isPrime;
    }

    public CalculationsResult() {

    }
}
