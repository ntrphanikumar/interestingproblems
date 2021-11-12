package interesting.leetcode.msi;

import java.util.List;

public class MeetingTime {

    static class Slot implements Comparable<Slot> {
        int start;
        int end;

        Slot(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Slot slot) {
            return Integer.compare(start, slot.start);
        }

        @Override
        public String toString() {
            return "Slot{" +
                    "start=" + (start / 60) + ":" + (start % 60) +
                    ", end=" + (end / 60) + ":" + (end % 60) +
                    '}';
        }

    }

    private static Slot toSlot(List<Integer> busySchedule) {
        return new Slot((busySchedule.get(0) * 60) + busySchedule.get(1), (busySchedule.get(2) * 60) + busySchedule.get(3));
    }

    public static void meetingSlots(List<List<Integer>> busySchedules, int meetingTime) {
        List<Slot> busySlots = busySchedules.stream().map(MeetingTime::toSlot).sorted().collect(java.util.stream.Collectors.toList());
        List<Slot> freeOnes = new java.util.ArrayList<>();
        Slot currentBusy = busySlots.get(0);
        freeOnes.add(new Slot(0, currentBusy.start));
        for (int i = 1; i < busySlots.size(); i++) {
            Slot newBusy = busySlots.get(i);
            if (newBusy.start <= currentBusy.end) {
                currentBusy.end = Math.max(newBusy.end, currentBusy.end);
            } else {
                if (newBusy.start - currentBusy.end >= meetingTime) {
                    freeOnes.add(new Slot(currentBusy.end, newBusy.start));
                }
                currentBusy = newBusy;
            }
        }
        if (24 * 60 - currentBusy.end >= meetingTime) {
            freeOnes.add(new Slot(currentBusy.end, 24 * 60));
        }

        System.out.println(freeOnes);
    }

    public static void meetingSlotsBitSet(List<List<Integer>> busySchedules, int meetingTime) {
        java.util.BitSet bs = new java.util.BitSet();
        for (List<Integer> schedule : busySchedules) {
            bs.set(schedule.get(0) * 60 + schedule.get(1), schedule.get(2) * 60 + schedule.get(3));
        }
        System.out.println(bs);

        int start = 0, end = bs.nextSetBit(start);
        while (end > start) {
            if (end - start >= meetingTime) {
                System.out.println(start / 60 + " " + start % 60 + "  " + end / 60 + " " + end % 60);
            }
            start = bs.nextClearBit(end);
            end = bs.nextSetBit(start);
        }
        if (end == -1 && meetingTime <= 1440 - start) {
            System.out.println(start / 60 + " " + start % 60 + "  24 00");
        }
    }

    public static void main(String[] args) {
        meetingSlotsBitSet(java.util.Arrays.asList(java.util.Arrays.asList(16, 00, 17, 00),
                java.util.Arrays.asList(10, 30, 14, 30),
                java.util.Arrays.asList(20, 45, 22, 15),
                java.util.Arrays.asList(10, 00, 13, 15),
                java.util.Arrays.asList(9, 00, 11, 00)), 90);

//
//        java.util.BitSet bs = new java.util.BitSet();
//        System.out.println(bs);
//        bs.set(0, 10);
//        bs.set(100, 500);
//        System.out.println(bs);
//        System.out.println(bs.size());
//        System.out.println(bs.nextSetBit(600));
//        System.out.println(bs.previousSetBit(600));
//        System.out.println(bs);
//        System.out.println(bs.size());
    }
}
