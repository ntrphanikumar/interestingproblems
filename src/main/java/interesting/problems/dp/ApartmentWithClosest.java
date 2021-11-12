package interesting.problems.dp;

public class ApartmentWithClosest {
    public static int blockWithClosestFacilities(boolean[][] blocks, int[] necessaryFacilities) {
        int[][] blockFacilities = new int[blocks.length][blocks[0].length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                blockFacilities[i][j] = blocks[i][j] ? 0 : blocks.length;
            }
        }

        for (int facilityId : necessaryFacilities) {
            for (int blockIdx = 1; blockIdx < blocks.length; blockIdx++) {
                if (!blocks[blockIdx][facilityId]) {
                    blockFacilities[blockIdx][facilityId] = Math.min(blockFacilities[blockIdx][facilityId], blockFacilities[blockIdx - 1][facilityId] + 1);
                }
            }

            for (int blockIdx = blocks.length - 2; blockIdx >= 0; blockIdx--) {
                if (!blocks[blockIdx][facilityId]) {
                    blockFacilities[blockIdx][facilityId] = Math.min(blockFacilities[blockIdx][facilityId], blockFacilities[blockIdx + 1][facilityId] + 1);
                }
            }
        }

        int[] maxDistance = new int[blocks.length];
        for (int blockIdx = 0; blockIdx < blocks.length; blockIdx++) {
            for (int facilityId : necessaryFacilities) {
                maxDistance[blockIdx] = Math.max(maxDistance[blockIdx], blockFacilities[blockIdx][facilityId]);
            }
        }

        int bestBlock = 0, minDistance = Integer.MAX_VALUE;
        for (int block = 0; block < maxDistance.length; block++) {
            if (minDistance > maxDistance[block]) {
                minDistance = maxDistance[block];
                bestBlock = block;
            }
        }
        return bestBlock;
    }

    public static void main(String[] args) {
        boolean[][] blocks = {{false, true, false}, {true, false, false}, {true, true, false}, {false, true, false}, {false, true, true}};
        System.out.println(blockWithClosestFacilities(blocks, new int[]{0, 1, 2}));
        System.out.println(blockWithClosestFacilities(blocks, new int[]{0}));
        System.out.println(blockWithClosestFacilities(blocks, new int[]{1}));
        System.out.println(blockWithClosestFacilities(blocks, new int[]{2}));
        System.out.println(blockWithClosestFacilities(blocks, new int[]{0, 1}));
        System.out.println(blockWithClosestFacilities(blocks, new int[]{1, 2}));
        System.out.println(blockWithClosestFacilities(blocks, new int[]{0, 2}));
    }
}
