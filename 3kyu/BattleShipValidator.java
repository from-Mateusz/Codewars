public class BattleShipValidator {

    private static final int SHIP_FLAG = 1;

    private static final int BATTLESHIPS_QTY = 1, CRUISERS_QTY = 2, DESTROYERS_QTY = 3, SUBMARINES_QTY = 4;

    private static int[][] battleField = {{1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    @Test
    public void when_have_placed_ships_on_the_board_then_validate_their_setup() {
        final boolean expected = true;
        assertThat(validate(battleField), is(true));
    }

    public static boolean validate(int[][] field) {
        int battleships = 0;
        int cruisers = 0;
        int destroyers = 0;
        int submarines = 0;

        for(int n = 0; n < field.length; n++) {
            for(int m = 0; m < field.length; m++) {
                if(field[n][m] == SHIP_FLAG) {
                    if(n > 0 && field[n - 1][m] == SHIP_FLAG) continue;
                    if(m > 0 && field[n][m - 1] == SHIP_FLAG) continue;

                    int shipSize = examine(n, m, field);

                    if(shipSize == 4) battleships++;
                    else if(shipSize == 3) cruisers++;
                    else if(shipSize == 2) destroyers++;
                    else if(shipSize == 1) submarines++;
                }
            }
        }

        if(BATTLESHIPS_QTY != battleships) return false;
        if(CRUISERS_QTY != cruisers) return false;
        if(DESTROYERS_QTY != destroyers) return false;
        if(SUBMARINES_QTY != submarines) return false;

        return true;
    }

    private static int examine(int n, int m, int[][] field) {

        if(n > 0 && m < 9) {
            if(field[n - 1][m + 1] == SHIP_FLAG) return 5;
        }
        if(n < 9 && m < 9) {
            if(field[n + 1][m + 1] == SHIP_FLAG) return 5;
            if(field[n + 1][m] == SHIP_FLAG && field[n][m + 1] == SHIP_FLAG) return 5;
        }

        if(n < 9) {
            if(field[n + 1][m] == SHIP_FLAG) return 1 + examine(n + 1, m, field);
        }

        if(m < 9) {
            if(field[n][m + 1] == SHIP_FLAG) return 1 + examine(n, m + 1, field);
        }

        return 1;
    }
}
