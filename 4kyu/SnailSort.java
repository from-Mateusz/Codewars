public class SnailSort {

    private static final int MOVE_RIGHT = 0;
    private static final int MOVE_DOWN = 1;
    private static final int MOVE_UP = 2;
    private static final int MOVE_LEFT = 3;

    private static final boolean VISITED = true;

    @Test
    public void snail_square_matrix_then_get_numbers_set_equal_in_size_with_matrix_size() {
        int[][] array
                = {{1, 2, 3},
                {4, 5, 6},



                {7, 8, 9}};
        final int EXPECTED_SIZE = 9;
        assertThat(snail(array).length, is(EXPECTED_SIZE));
    }

    @Test
    public void snail_square_3_matrix_then_get_numbers_set_ordered_in_clockwise_fashion() {
        int[][] array
                = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        final int[] EXPECTED_SET = { 1, 2, 3, 6, 9, 8, 7, 4, 5 };
        assertThat(snail(array), equalTo(EXPECTED_SET));
    }

    @Test
    public void snail_square_4_matrix_then_get_numbers_set_ordered_in_clockwise_fashion() {
        int[][] array
                = {{1, 2, 3, 1},
                {4, 5, 6, 4},
                {7, 8, 9, 7},
                {7, 8, 9, 7}};
        final int[] EXPECTED_SET = { 1, 2, 3, 1, 4, 7, 7, 9, 8, 7, 7, 4, 5, 6, 9, 8 };
        assertThat(snail(array), equalTo(EXPECTED_SET));
    }

    private static int[] snail(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }

        // assume that we are given only square matrix
        int m = matrix.length;
        int n = matrix.length;
        int row = 0;
        int col = 0;
        int direction = MOVE_RIGHT;
        int COUNT_NUMBERS = n * m;

        int[] result = new int[ COUNT_NUMBERS ];
        int resultIndex = 0;
        boolean[][] visited = new boolean[n][m];

        while(resultIndex < COUNT_NUMBERS) {
            if(direction == MOVE_RIGHT) {
                for(; col < m; col++) {
                    if(visited[row][col]) {
                       break;
                    }
                    result[resultIndex++] = matrix[row][col];
                    visited[row][col] = VISITED;
                }
                row++;
                col--;
                direction = MOVE_DOWN;
            }

            if(direction == MOVE_DOWN) {
                for(; row < n; row++) {
                    if(visited[row][col]) {
                        break;
                    }
                    result[resultIndex++] = matrix[row][col];
                    visited[row][col] = VISITED;
                }
                col--;
                row--;
                direction = MOVE_LEFT;
            }

            if(direction == MOVE_LEFT) {
                for(; col >= 0; col--) {
                    if(visited[row][col]) {
                        break;
                    }
                    result[resultIndex++] = matrix[row][col];
                    visited[row][col] = VISITED;
                }
                row--;
                col++;
                direction = MOVE_UP;
            }

            if(direction == MOVE_UP) {
                for(; row >= 0; row--) {
                    if(visited[row][col]) {
                        break;
                    }
                    result[resultIndex++] = matrix[row][col];
                    visited[row][col] = VISITED;
                }
                col++;
                row++;
                direction = MOVE_RIGHT;
            }
        }

        return result;
    }
}