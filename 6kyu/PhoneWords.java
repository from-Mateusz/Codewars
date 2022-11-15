public static String phoneWords(String sequence) {
    if(sequence == null || sequence.isBlank()) return "";

    final Map<Character, char[]> keyboard = getKeyboard();

    StringBuilder group = new StringBuilder();
    List<String> groups = new ArrayList<>();

    for(int n = 0; n < sequence.length(); n++) {
        final char key = sequence.charAt(n);

        if(key == '1') {
            if(!group.isEmpty()) {
                groups.add(group.toString());
                group.setLength(0);
            }
            continue;
        }

        if(group.isEmpty()) {
            group.append(key);

            if(n + 1 == sequence.length()) {
                groups.add(group.toString());
            }
        }
        else {
            if(group.charAt(0) == key) {
                if(group.length() == keyboard.get(key).length) {
                    groups.add(group.toString());
                    group.setLength(0);
                    group.append(key);
                }
                else {
                    group.append(key);
                }

                if(n + 1 == sequence.length()) {
                    groups.add(group.toString());
                }
            }
            else {
                groups.add(group.toString());
                group.setLength(0);
                group.append(key);

                if(n + 1 == sequence.length()) {
                    groups.add(group.toString());
                }
            }
        }
    }

    final StringBuilder message = new StringBuilder();
    groups.stream().forEach(g -> message.append(keyboard.get(g.charAt(0))[g.length() - 1]));

    return message.toString();
}

static Map<Character, char[]> getKeyboard() {
    final Map<Character, char[]> keyboard = new HashMap<>();
    keyboard.put('0', new char[]{' '});
    keyboard.put('2', new char[]{'a', 'b', 'c'});
    keyboard.put('3', new char[] {'d', 'e', 'f'});
    keyboard.put('4', new char[] {'g', 'h', 'i'});
    keyboard.put('5', new char[] {'j', 'k', 'l'});
    keyboard.put('6', new char[] {'m', 'n', 'o'});
    keyboard.put('7', new char[] {'p', 'q', 'r', 's'});
    keyboard.put('8', new char[] {'t', 'u', 'v'});
    keyboard.put('9', new char[] {'w', 'x', 'y', 'z'});
    keyboard.put('*', new char[] {'*'});
    keyboard.put('*', new char[] {'#'});
    return keyboard;
}