package net.k3nder.math.parser;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class Prob {
    private Operation operation;
    private Long a;
    private Long b;
    private Boolean parented = false;
    public long operate() {
        return operation.operate(a, b);
    }
    public String toWrited() {
        if (operation.equals(Operation.NUM)) return a.toString();
        return a.toString() + operation.getCharacter() + b.toString();
    }
}
