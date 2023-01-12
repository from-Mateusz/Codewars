public class NextLexicographicalSmallerNumber {

    @Test
    public void given_any_units_number_then_find_no_next_smaller_value() {
        long number = 9;
        long expected = -1;
        assertThat(nextSmaller(number), equalTo(expected));
    }

    @Test
    public void given_any_number_that_is_already_the_smallest_possible_then_find_no_next_smaller_value() {
        long number = 111;
        long expected = -1;
        assertThat(nextSmaller(number), equalTo(expected));
    }

    @Test
    public void given_any_next_number_that_is_already_the_smallest_possible_then_find_no_next_smaller_value() {
        long number = 135;
        long expected = -1;
        assertThat(nextSmaller(number), equalTo(expected));
    }

    @Test
    public void given_any_next_number_that_is_already_the_smallest_possible_and_smaller_could_have_leading_zero_then_find_no_next_smaller_value() {
        long number = 1027;
        long expected = -1;
        assertThat(nextSmaller(number), equalTo(expected));
    }

    @Test
    public void given_any_next_number_that_is_not_the_lexicographically_smallest_possible_then_find_next_smaller_value() {
        long number = 21;
        long expected = 12;
        assertThat(nextSmaller(number), equalTo(expected));
    }

    @Test
    public void given_any_other_next_number_that_is_not_the_lexicographically_smallest_possible_then_find_next_smaller_value() {
        long number = 907;
        long expected = 790;
        assertThat(nextSmaller(number), equalTo(expected));
    }

    @Test
    public void given_any_other_huge_number_that_is_not_the_lexicographically_smallest_possible_then_find_next_smaller_value() {
        long number = 59884848483559L;
        long expected = 59884848459853L;
        assertThat(nextSmaller(number), equalTo(expected));
    }

    @Test
    public void given_any_other_next__huge_number_that_is_not_the_lexicographically_smallest_possible_then_find_next_smaller_value() {
        long number = 125611003;
        long expected = 125610310;
        assertThat(nextSmaller(number), equalTo(expected));
    }

    public static long nextSmaller(long n) {
        String number = String.valueOf(n);
        
        int keyDigitIndex = keyDigitIndex(number);

        if(keyDigitIndex < 0) return -1;

        char[] digits = number.toCharArray();

        int shiftedDigitIndex = -1;
        
        // Let's put every digit that is after the "key digit" in a separate set as to order its values in a DESC fashion
        Character[] remaining = CharBuffer.wrap(number.substring(keyDigitIndex + 1).toCharArray())
                                            .chars()
                                            .mapToObj(v -> Character.valueOf((char)v))
                                            .toArray(Character[]::new);
        Arrays.sort(remaining, Collections.reverseOrder());
        
        // Take into account that there the situation like below can happen:
        // The next digit to the right of the "key digit" which by default should be swapped with the "key digit",
        // is not the last digit in the set and there might be n greater numbers that are still smaller than the key digit
        // In this situation we have to find the very first number that is greater than "default swap" and mark it
        // as our correct swap
        OptionalInt greaterSmallerNumberIndex = IntStream.range(0, remaining.length)
                                                            .filter(i -> remaining[i] < digits[keyDigitIndex])
                                                            .findFirst();
        shiftedDigitIndex = keyDigitIndex + (greaterSmallerNumberIndex.getAsInt() + 1);

        for(int i = 0; i < remaining.length; i++) {
            digits[(keyDigitIndex + 1 + i)] = remaining[i];
        }
        
        char swappedDigit = digits[keyDigitIndex];
        digits[keyDigitIndex] = digits[shiftedDigitIndex];
        digits[shiftedDigitIndex] = swappedDigit;

        String result = String.valueOf(digits);

        if(result.charAt(0) == '0') return -1;

        if(number.equals(result)) return  -1;

        if(Long.valueOf(number) < Long.valueOf(result)) return -1;

        return Long.valueOf(result);
    }

    /**
     * This method finds the very first number that is greater than the number to the right,
     * seeking number in question in RTL manner
     * @param number
     * @return
     */
    private static int keyDigitIndex(String number) {
        for(int i = number.length() - 2; i >= 0; i--) {
            if(number.charAt(i) > number.charAt(i + 1)) return i;
        }
        return -1;
    }
}
