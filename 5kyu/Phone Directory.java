class PhoneDir {
    
    private static final List<String> phoneBookEntries = new ArrayList<>();

    private static final Map<String, Integer> phoneNumberOccurrences = new HashMap<>();
  
    public static  String phone(String strng, String num) {
        String pNum = "+" + num;

        if(null == getPhoneNumberOccurrences(strng).get(pNum)) {
            return String.format("Error => Not found: %s", num);
        }

        if(getPhoneNumberOccurrences(strng).get(pNum) > 1) {
            return String.format("Error => Too many people: %s", num);
        }

        String phoneBookEntry = getPhoneBookEntry(num);
        Pattern phoneNumberPattern = Pattern.compile("^.*("+ num +").*$");
        Pattern contactNamePattern = Pattern.compile("^.*(<\\w+\\s?\\w+'?\\w+?>).*$");
        Pattern poBoxPattern = Pattern.compile("^.*(\\s{1}\\w+-[0-9]{5,}).*$");
        Matcher phoneNumberMatcher = phoneNumberPattern.matcher(phoneBookEntry);
        Matcher contactNameMatcher = contactNamePattern.matcher(phoneBookEntry);
        Matcher poBoxMatcher = poBoxPattern.matcher(phoneBookEntry);
            if(phoneNumberMatcher.matches() && contactNameMatcher.matches() && poBoxMatcher.matches()) {
            String street = phoneBookEntry.replace(phoneNumberMatcher.group(1), "")
                    .replace(contactNameMatcher.group(1), "")
                    .replace(poBoxMatcher.group(1), "")
                    .replaceAll("[^a-zA-Z0-9\\s_.]", "")
                    .replaceAll("_", " ")
                    .replaceAll("(\\s){2,}", " ") 
                    .trim();
            String formattedPhoneBookEntry = String.format("Phone => %s, Name => %s, Address => %s %s",
                    phoneNumberMatcher.group(1),
                    contactNameMatcher.group(1).replaceAll("[<>]", ""),
                    street,
                    poBoxMatcher.group(1).trim());
            return formattedPhoneBookEntry;
        }
        if(phoneNumberMatcher.matches() && contactNameMatcher.matches()) {
                  String street = phoneBookEntry.replace(phoneNumberMatcher.group(1), "")
                          .replace(contactNameMatcher.group(1), "")
                          .replaceAll("[^a-zA-Z0-9.\\s_.]", "")
                          .replaceAll("_", " ")
                          .replaceAll("(\\s){2,}", " ") 
                          .trim();
                  String formattedPhoneBookEntry = String.format("Phone => %s, Name => %s, Address => %s",
                          phoneNumberMatcher.group(1),
                          contactNameMatcher.group(1).replaceAll("[<>]", ""),
                          street);
                  return formattedPhoneBookEntry;
           }
      
         return "";
    }
  
  private static Map<String, Integer> getPhoneNumberOccurrences(String directory) {
        List<String> entries = getAllPhoneBookEntries(directory);
        if(phoneNumberOccurrences.isEmpty()) {
            Pattern phoneNumberPattern = Pattern.compile("^.*(\\+\\d{1,2}-\\d{3}-\\d{3}-\\d{4}).*$");
            List<String> phoneNumbers = entries.stream()
                    .map(entry -> {
                        Matcher phoneNumberMatcher = phoneNumberPattern.matcher(entry);
                        if(phoneNumberMatcher.matches()) {
                            return phoneNumberMatcher.group(1);
                        }
                        return "";
                    })
                    .collect(Collectors.toList());
            Set<String> uniquePhoneNumbers = new HashSet<>(phoneNumbers);
            uniquePhoneNumbers.forEach(num -> phoneNumberOccurrences.put(num, 0));
            phoneNumbers.forEach(num -> {
                int occurrence = phoneNumberOccurrences.get(num);
                phoneNumberOccurrences.put(num, ++occurrence);
            });
        }
        return phoneNumberOccurrences;
    }

    private static List<String> getAllPhoneBookEntries(String directory) {
        if(phoneBookEntries.isEmpty()) {
            phoneBookEntries.addAll(List.of(directory.split("\n")));
        }
        phoneBookEntries.forEach(entry -> System.out.println(entry));
        return phoneBookEntries;
    }

    private static String getPhoneBookEntry(String num) {
        Pattern phoneNumberPattern = Pattern.compile("^.*(\\+"+ num +").*$");
        Optional<String> possiblePhoneBookEntry = phoneBookEntries.stream()
                .filter(entry -> phoneNumberPattern.matcher(entry).matches())
                .findFirst();
        return possiblePhoneBookEntry.isPresent() ? possiblePhoneBookEntry.get() : "";
    }
}