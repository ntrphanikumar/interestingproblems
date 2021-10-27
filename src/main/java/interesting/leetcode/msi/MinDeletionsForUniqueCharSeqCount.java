package interesting.leetcode.msi;

import java.util.HashSet;
import java.util.Set;

public class MinDeletionsForUniqueCharSeqCount {
    public int minDeletions(String s) {
        int[] occurrences = new int[26];
        for (char c : s.toCharArray()) {
            occurrences[c - 'a']++;
        }
        Set<Integer> alreadyOccurred = new HashSet<>();
        int deletions = 0;
        for (int occ : occurrences) {
            if (occ <= 0) {
                continue;
            }
            if (!alreadyOccurred.contains(occ)) {
                alreadyOccurred.add(occ);
            } else {
                int num = occ;
                while (alreadyOccurred.contains(num) && num > 0) {
                    num--;
                }
                if (num > 0) {
                    alreadyOccurred.add(num);
                }
                deletions += occ - num;
            }
        }
        return deletions;
    }
}
