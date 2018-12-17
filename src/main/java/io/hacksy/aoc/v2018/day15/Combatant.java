package io.hacksy.aoc.v2018.day15;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Combatant {
    int hitPoints;
    int attackPoints;
    Type type;

    enum Type {
        Goblin, Elf
    }
}
