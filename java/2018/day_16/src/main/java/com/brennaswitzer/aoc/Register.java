package com.brennaswitzer.aoc;

import java.util.*;

public class Register {
    
    List<Opcode> instructions = new ArrayList<>();
    Map<Integer, Operation> codes = new HashMap<>();
    Set<Operation> operations = EnumSet.allOf(Operation.class);
    int count = 0;
    
    /**
     * create an array of potential opcodes to run through, List<Opcodes> codes
     * create Map of opcodes that each have a set of potentials
     * count the number of keys in your map that have at least three items
     */
    
    void addOpcode(int id, int A, int B, int C, int[] before, int[] after) {
        Opcode o = new Opcode(id, A, B, C, before, after);
        instructions.add(o);
    }
    
    Histogram buildPotentials(Set<Operation> operations) {
        Histogram h = new Histogram();
        
        for (Opcode i : instructions) {
            operations.forEach(operation -> {
                int[] result = getOperation(operation, i);
                if (Arrays.equals(result, i.after)) {
                    if (h.containsKey(i.id)) {
                        Set<Operation> o = h.get(i.id);
                        o.add(operation);
                    } else {
                        Set<Operation> o = new HashSet<>();
                        o.add(operation);
                        h.put(i.id, o);
                    }
                }
            });
        }
        return h;
    }
    
    
    Map<Integer, Operation> codeMapper() {
        int remaining = Integer.MAX_VALUE;
        while(remaining > 0) {
            Histogram h = buildPotentials(operations);
            remaining = h.size();
            
            Set<Integer> keys = h.keySet();
            for (int k : keys) {
                if (h.get(k).size() == 1) {
                    Set<Operation> o = h.get(k);
                    List<Operation> list = new ArrayList<>(o);
                    codes.put(k, list.get(0));
                    operations.remove(list.get(0));
                }
            }
        }
        
        return codes;
    }
    
    public int countInstructions() {
        EnumSet<Operation> op = EnumSet.allOf(Operation.class);
        int total = 0;
        
        for (Opcode i : instructions) {
            this.count = 0;
            op.forEach(operation -> {
                int[] result = getOperation(operation, i);
                if (Arrays.equals(result, i.after)) {
                    this.count++;
                }
            });
            if (count >= 3) {
                total++;
            }
        }
        return total;
    }
    
    public int runProgram(List<Opcode> instructions) {
    
        Map<Integer, Operation> codes = codeMapper();
        int[] registers = {0,0,0,0};
        
        for (Opcode instruction : instructions) {
            Operation o = codes.get(instruction.id);
            instruction.setBefore(registers);
            registers = getOperation(o, instruction);
        }
        return registers[1];
    }
    
    int[] getOperation(Operation operation, Opcode i) {
        switch (operation) {
            case ADDI:
                return addi(i);
            case ADDR:
                return addr(i);
            case MULR:
                return mulr(i);
            case MULI:
                return muli(i);
            case BANR:
                return banr(i);
            case BANI:
                return bani(i);
            case BORR:
                return borr(i);
            case BORI:
                return bori(i);
            case SETR:
                return setr(i);
            case SETI:
                return seti(i);
            case GTIR:
                return gtir(i);
            case GTRI:
                return gtri(i);
            case GTRR:
                return gtrr(i);
            case EQIR:
                return eqir(i);
            case EQRI:
                return eqri(i);
            case EQRR:
                return eqrr(i);
            default:
                return null;
        }
    }
    
    Opcode getInstruction(int index) {
        return instructions.get(index);
    }
    
    /**
     * addr (add register) stores into register C the result of adding register A and register B.
     *
     * @param i Opcode
     * @return int[] register
     */
    public int[] addr(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A] + i.before[i.B];
        return result;
    }
    
    /**
     * addi (add immediate) stores into register C the result of adding register A and value B.
     *
     * @param i Opcode
     * @return int[] register
     */
    public int[] addi(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A] + i.B;
        return result;
    }
    
    /**
     * mulr (multiply register) stores into register C the result of multiplying register A and register B.
     *
     * @param i Opcode
     * @return int[] register
     */
    public int[] mulr(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A] * i.before[i.B];
        return result;
    }
    
    /**
     * muli (multiply register) stores into register C the result of multiplying register A and value B.
     *
     * @param i Opcode
     * @return int[] register
     */
    public int[] muli(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A] * i.B;
        return result;
    }
    
    /**
     * banr (bitwise AND register) stores into register C the result of the bitwise AND of register A and register B.
     *
     * @param i Opcode
     * @return int[] register
     */
    public int[] banr(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A] & i.before[i.B];
        return result;
    }
    
    /**
     * bani (bitwise AND immediate) stores into register C the result of the bitwise AND of register A and value B.
     *
     * @param i Opcode
     * @return int[] register
     */
    public int[] bani(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A] & i.B;
        return result;
    }
    
    /**
     * borr (bitwise OR register) stores into register C the result of the bitwise OR of register A and register B.
     *
     * @param i Opcode
     * @return int[] register
     */
    public int[] borr(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A] | i.before[i.B];
        return result;
    }
    
    /**
     * bori (bitwise OR immediate) stores into register C the result of the bitwise OR of register A and value B.
     *
     * @param i Opcode
     * @return int[] register
     */
    public int[] bori(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A] | i.B;
        return result;
    }
    
    /**
     * setr (set register) copies the contents of register A into register C. (Input B is ignored.)
     *
     * @param i Opcode
     * @return int[] register
     */
    public int[] setr(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A];
        return result;
    }
    
    /**
     * seti (set immediate) stores value A into register C. (Input B is ignored.)
     *
     * @param i Opcode
     * @return int[] register
     */
    public int[] seti(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.A;
        return result;
    }
    
    /**
     * gtir (greater-than immediate/register) sets register C to 1 if value A is greater than register B. Otherwise, register C is set to 0.
     *
     * @param i Opcode
     * @return int[] register
     */
    public int[] gtir(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        if (i.A > i.before[i.B]) {
            result[i.C] = 1;
        } else {
            result[i.C] = 0;
        }
        return result;
    }
    
    /**
     * gtri (greater-than register/immediate) sets register C to 1 if register A is greater than value B. Otherwise, register C is set to 0.
     *
     * @param i Opcode
     * @return int[] register
     */
    public int[] gtri(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        if (i.before[i.A] > i.B) {
            result[i.C] = 1;
        } else {
            result[i.C] = 0;
        }
        return result;
    }
    
    /**
     * gtrr (greater-than register/register) sets register C to 1 if register A is greater than register B. Otherwise, register C is set to 0.
     *
     * @param i Opcode
     * @return int[] register
     */
    public int[] gtrr(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        if (i.before[i.A] > i.before[i.B]) {
            result[i.C] = 1;
        } else {
            result[i.C] = 0;
        }
        return result;
    }
    
    /**
     * eqir (equal immediate/register) sets register C to 1 if value A is equal to register B. Otherwise, register C is set to 0.
     *
     * @param i Opcode
     * @return int[] register
     */
    public int[] eqir(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        if (i.A == i.before[i.B]) {
            result[i.C] = 1;
        } else {
            result[i.C] = 0;
        }
        return result;
    }
    
    /**
     * eqri (equal register/immediate) sets register C to 1 if register A is equal to value B. Otherwise, register C is set to 0.
     *
     * @param i Opcode
     * @return int[] register
     */
    public int[] eqri(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        if (i.before[i.A] == i.B) {
            result[i.C] = 1;
        } else {
            result[i.C] = 0;
        }
        return result;
    }
    
    /**
     * eqrr (equal register/register) sets register C to 1 if register A is equal to register B. Otherwise, register C is set to 0.
     *
     * @param i Opcode
     * @return int[] register
     */
    public int[] eqrr(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        if (i.before[i.A] == i.before[i.B]) {
            result[i.C] = 1;
        } else {
            result[i.C] = 0;
        }
        return result;
    }
    
    
    public static class Opcode {
        int id;
        int A;
        int B;
        int C;
        int[] before;
        int[] after;
        
        Opcode(int id, int A, int B, int C) {
            this.id = id;
            this.A = A;
            this.B = B;
            this.C = C;
        }
        
        Opcode(int id, int A, int B, int C, int[] before, int[] after) {
            this.id = id;
            this.A = A;
            this.B = B;
            this.C = C;
            this.before = before;
            this.after = after;
        }
        
        void setBefore(int[] register) {
            this.before = Arrays.copyOf(register, register.length);
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Before: " + before);
            sb.append("[" + id + "," + A + "," + B + "," + C + "]");
            sb.append("After: " + after);
            return sb.toString();
        }
    }
    
    public enum Operation {
        ADDR, ADDI, MULR, MULI, BANR, BANI, BORR, BORI, SETR, SETI, GTIR, GTRI, GTRR, EQIR, EQRI, EQRR
    }
}
