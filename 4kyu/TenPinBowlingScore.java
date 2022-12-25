public class TenPinBowling {

    @Test
    public void read_frame_scores_without_spares_and_strikes_then_sum_up_them() {
        final String frames = "11 11 11 11 11 11 11 11 11 11";
        final int expectedScore = 20;
        assertThat(game_record(frames), equalTo(expectedScore));
    }

    @Test
    public void read_frame_scores_with_perfect_game_then_sum_up_them() {
        final String frames = "X X X X X X X X X XXX";
        final int expectedScore = 300;
        assertThat(game_record(frames), equalTo(expectedScore));
    }

    @Test
    public void read_frame_scores_with_some_strikes_then_sum_up_them() {
        final String frames = "X X 81 80 X X 90 81 71 44";
        final int expectedScore = 146;
        assertThat(game_record(frames), equalTo(expectedScore));
    }

    @Test
    public void read_frame_scores_with_some_strikes_and_spares_then_sum_up_them() {
        final BowlingGame game1 = new BowlingGame("5/ 4/ 3/ 2/ 1/ 0/ X 9/ 4/ 8/8", 150);
        final BowlingGame game2 = new BowlingGame("00 00 00 00 00 00 00 00 00 0/X", 20);
        final BowlingGame game3 = new BowlingGame( "7/ X 8/ 5/ 15 42 X 22 X 53", 122);
        final BowlingGame game4 = new BowlingGame("51 23 8/ 2/ 06 4/ 62 60 6/ XX1", 110);
        assertThat(game_record(game1.frames), equalTo(game1.expectedScore));
        assertThat(game_record(game2.frames), equalTo(game2.expectedScore));
        assertThat(game_record(game3.frames), equalTo(game3.expectedScore));
        assertThat(game_record(game4.frames), equalTo(game4.expectedScore));
    }

    private static class BowlingGame {

        final String frames;

        final int expectedScore;

        public BowlingGame(String frames, int expectedScore) {
            this.frames = frames;
            this.expectedScore = expectedScore;
        }
    }

    private int game_record(String frames) {
        final String[] rounds = frames.split("\\s");
        final String SPARE = "/";
        final String STRIKE = "X";
        final int MAX_SCORE = 10;

        int total = 0;

        final int total_rounds = rounds.length - 1;

        for(int ri = 0; ri <= total_rounds; ri++) {
            final String round = rounds[ri];
            boolean is_not_before_last_round = ri < total_rounds - 1;

            if(is_not_before_last_round) {
                if(round.equals(STRIKE)) {
                    total += MAX_SCORE;
                    String next = rounds[ri + 1];
                    if(next.contains(STRIKE)) {
                        total += MAX_SCORE;
                        next = rounds[ri + 2];
                        if(next.contains(STRIKE)) {
                            total += MAX_SCORE;
                        }
                        else {
                            total += Character.digit(next.charAt(0), 10);
                        }
                    }
                    else if(next.contains(SPARE)) {
                        total += MAX_SCORE;
                    }
                    else {
                        total += Character.digit(next.charAt(0), 10) + Character.digit(next.charAt(1), 10);
                    }
                }
                else if(round.contains(SPARE)) {
                    final String next = rounds[ri + 1];
                    total += MAX_SCORE + (next.equals(STRIKE) ? MAX_SCORE : Character.digit(next.charAt(0), 10));
                }
                else {
                    total += Character.digit(round.charAt(0), 10) + Character.digit(round.charAt(1), 10);
                }
            }

            else {
                boolean is_before_last_round = ri < total_rounds;
                if(is_before_last_round) {
                    if(round.equals(STRIKE)) {
                        total += MAX_SCORE;
                        final String last_round = rounds[total_rounds];
                        if(last_round.contains("XX")) total += MAX_SCORE * 2;
                        else if(last_round.startsWith("X")) total += MAX_SCORE + Character.digit(last_round.charAt(1), 10);
                        else if(last_round.contains(SPARE)) total += MAX_SCORE;
                        else total += Character.digit(last_round.charAt(0), 10) + Character.digit(last_round.charAt(1), 10);
                    }
                    else if(round.contains(SPARE)) {
                        final String last_round = rounds[total_rounds];
                        total += MAX_SCORE + (last_round.startsWith(STRIKE) ? MAX_SCORE : Character.digit(last_round.charAt(0), 10));
                    }
                    else {
                        total += Character.digit(round.charAt(0), 10) + Character.digit(round.charAt(1), 10);
                    }
                }
                else {
                    if(round.equals("XXX")) total += MAX_SCORE * 3;
                    else if(round.contains("XX")) total += MAX_SCORE * 2 + Character.digit(round.charAt(2), 10);
                    else if(round.contains(STRIKE)) total += MAX_SCORE + (round.contains(SPARE) ?
                            MAX_SCORE : Character.digit(round.charAt(1), 10) + Character.digit(round.charAt(2), 10));
                    else if(round.contains(SPARE)) total += MAX_SCORE + Character.digit(round.charAt(2), 10);
                    else total += Character.digit(round.charAt(0), 10) + Character.digit(round.charAt(1), 10);
                }
            }
        }

        return total;
    }
}