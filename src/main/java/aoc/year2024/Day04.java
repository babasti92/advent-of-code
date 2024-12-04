package aoc.year2024;

import java.util.List;

import aoc.Day;

public class Day04 implements Day {
  @Override
  public String part1(List<String> input) {
    var numOfXmas = 0;
    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).length(); j++) {
        var character = input.get(i).charAt(j);
        if (character == 'X' && isXMas(i, j, input)) {
          numOfXmas++;
        }
      }
    }
    return String.valueOf(numOfXmas);
  }

  @Override
  public String part2(List<String> input) {
    return null;
  }

  private boolean isXMas(int xLineNumber, int xCharPosition, List<String> input) {
    // horizontally
    for(int i=1;i<=3;i++){
      if(i<3 && ){
      
        continue;
      }
      if(){
        return true;
      }

    }
    if (input.get(xLineNumber - 1).charAt(xCharPosition) == 'M') {
      if (input.get(xLineNumber - 2).charAt(xCharPosition) == 'A') {
        if (input.get(xLineNumber - 3).charAt(xCharPosition) == 'S') {
          return true;
        }
      }
    }
  }
}
