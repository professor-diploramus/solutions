package org.pdiploramus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FrogJump {

  public boolean canCross(int[] stones) {
    boolean[] canJump = new boolean[stones.length];
    Arrays.fill(canJump, false);
    List<List<Integer>> jumpLenghts = new ArrayList<>();
    for (int i=0; i<stones.length; i++) {
      jumpLenghts.add(new ArrayList<>());
    }
    canJump[0] = true;
    jumpLenghts.get(0).add(0);
    for (int i=1; i<stones.length; i++) {
      for (int j=i-1; j>=0; j--) {
        if (!canJump[j]) continue;
        int distToCover = stones[i] - stones[j];
        List<Integer> prevJumps = jumpLenghts.get(j);
        for (int k : prevJumps) {
          if (k == distToCover || (k-1) == distToCover || (k+1) == distToCover) {
            canJump[i] = true;
            jumpLenghts.get(i).add(distToCover);
            break;
          }
        }
      }
    }
    return canJump[stones.length-1];
  }

  public static void main(String[] args) {
    int[] arr1 = {0,1,3,5,6,8,12,17};
    int[] arr2 = {0,1,2,3,4,8,9,11};
    System.out.println(new FrogJump().canCross(arr2));
  }
}
