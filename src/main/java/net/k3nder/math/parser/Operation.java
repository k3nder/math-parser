package net.k3nder.math.parser;

public enum Operation {
    MUL,
    SUM,
    RES,
    DIV,
    OTH,
    NUM;

    public long operate(Long a, Long b) {
        if (this == Operation.SUM) {
            return a + b;
        } else if (this == Operation.RES) {
            return a - b;
        } else if (this == Operation.MUL) {
            return a * b;
        } else if (this == Operation.DIV) {
            return a / b;
        } else if (this == Operation.NUM) {
            return a;
        } else if (this == Operation.OTH) {
            return  ( (a%2 != 0) ? 1 : 0);
        }
        return 0;
    }
    public char getCharacter() {
        if (this == Operation.SUM) {
            return '+';
        } else if (this == Operation.RES) {
            return '-';
        } else if (this == Operation.MUL) {
            return '*';
        } else if (this == Operation.DIV) {
            return '/';
        } else if (this == Operation.NUM) {
            return '\0';
        } else if (this == Operation.OTH) {
            return '=';
        }
        return '\0';
    }
    public static Operation getOperationOf(char op) {
        if ('+' == op) return Operation.SUM;
        else if ('-' == op) return Operation.RES;
        else if ('*' == op) return Operation.MUL;
        else if ('/' == op) return Operation.DIV;
        else if ('=' == op) return Operation.OTH;
        return Operation.SUM;
    }
}
