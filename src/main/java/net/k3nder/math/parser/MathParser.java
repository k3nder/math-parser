package net.k3nder.math.parser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MathParser {
    private static final MathParser INSTANCE = new MathParser();
    public static MathParser getInstance() {
        return INSTANCE;
    }
    public Long parse(String input) {
        var probOp = getProbs(input);
        var probs = probOp.probs();
        var ops = probOp.operations();

        var results = Arrays.stream(probs).map(Prob::operate).toList();
        if (results.size() == 1) return results.get(0);
        var innerProbs = new ArrayList<Prob>();
        var innerOps = new ArrayList<>(Arrays.stream(Arrays.copyOf(ops, ops.length)).toList());
        for (var i = 0; i < ops.length+1; i += 2) {
            var op = ( i >= ops.length ? Operation.NUM : ops[i] );
            var a = results.get(i);
            var b = ( i+1 >= results.size() ? 0 : results.get(i + 1) );
            innerProbs.add(Prob.builder()
                    .a(a)
                    .b(b)
                    .operation(op)
                    .parented(false)
                    .build());
            if (!(i >= innerOps.size())) innerOps.remove(i);
        }
        if (innerProbs.size() == 1) return innerProbs.get(0).operate();
        for (var i = 0; i < innerOps.size(); i+=2) {
            var op = innerOps.get(i);
            var proba = innerProbs.get(i).operate();
            var probb = innerProbs.get(i+1).operate();
            var str = "" + proba + op.getCharacter() + probb;
            System.out.println(str);
            return parse(str);
        }
        return null;
    }
    public ProbOp getProbs(String input) {
        var numbers = getNumbers(input);
        var operators = getOperators(input);
        var probs = new ArrayList<Prob>();
        var interOp = new ArrayList<Operation>();
        for (var i = 0; i < operators.length+1; i += 2) {
            var operator = ( operators.length <= i ? Operation.NUM : operators[i] );
            var a = numbers[i];
            var b = ( operators.length <= i ? 0 : numbers[i + 1] );
            if (i%2 == 0 && i != 0) interOp.add(operators[i-1]);
            probs.add(Prob.builder()
                    .a(a)
                    .b(b)
                    .operation(operator)
                    .parented(false)
                    .build());
        }

        return new ProbOp(probs.toArray(new Prob[0]), interOp.toArray(new Operation[0]));
    }
    public Long[] getNumbers(String input) {
        List<Long> numbers = new ArrayList<>();
        var chars = input.toCharArray();
        while (true) {
            var idx = 0;
            var string = new StringBuilder();
            for (char aChar : chars) {
                if (aChar == '(') {
                    continue;
                }
                if (!Character.isDigit(aChar)) {
                    break;
                }
                string.append(aChar);
                idx++;
            }
            numbers.add(Long.parseLong(string.toString()));
            if (idx == chars.length) {
                break;
            }
            chars = Arrays.copyOfRange(chars, idx+1, chars.length);
            if (chars.length == 0) break;
        }
        return numbers.toArray(new Long[0]);
    }
    public Operation[] getOperators(String input) {
        var chars = input.toCharArray();
        var operators = new ArrayList<Operation>();
        for (char aChar : chars) {
            if (Character.isDigit(aChar)) {continue;}
            operators.add(Operation.getOperationOf(aChar));
        }
        return operators.toArray(new Operation[0]);
    }
}
