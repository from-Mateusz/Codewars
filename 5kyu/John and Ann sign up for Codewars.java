public class Johnann {
  
    private static final long JOHN_KATAS_AT_FIRST_DAY = 0;
    private static final long ANN_KATAS_AT_FIRST_DAY = 1;
    private static List<Long> dailyJohnKata = new ArrayList<>();
    private static List<Long> dailyAnnKata = new ArrayList<>();

    public static List<Long> john(long n) {
        List<Long> katas = new ArrayList<>();
        if(dailyJohnKata.size() > 0) {
            katas.addAll(dailyJohnKata);
        }
        if(katas.size() > n) {
            return katas.subList(0, (int)n);
        }
        else {
            for(int day = katas.size(); day < n; day++) {
                katas.add(countJohnKataAtDayIteratively(day));
            }
        }
        return katas;
    }

    //Efficient solution (prefer loops over recursion)
    private static long countJohnKataAtDayIteratively(long n) {
        if(dailyJohnKata.size() == 0 && dailyAnnKata.size() == 0) {
            dailyJohnKata.add(JOHN_KATAS_AT_FIRST_DAY);
            dailyAnnKata.add(ANN_KATAS_AT_FIRST_DAY);
        }

        long result = dailyJohnKata.get(0);
        int day = dailyJohnKata.size();
        int lastDay = (int)n;
        if(day > n) {
            int startDay = 1;
            while(startDay <= n) {
                result = startDay - dailyAnnKata.get((int)(long)dailyJohnKata.get((int)startDay-1));
                startDay++;
            }
        }
        else if(day <= n) {
            int startDay = 1;
            while(startDay < day) {
                result = startDay - dailyAnnKata.get((int)(long)dailyJohnKata.get((int)startDay-1));
                startDay++;
            }
            while(startDay <= n) {
                result = startDay - dailyAnnKata.get((int)(long)dailyJohnKata.get((int)startDay-1));
                dailyJohnKata.add(result);
                dailyAnnKata.add(startDay - dailyJohnKata.get((int)(long)dailyAnnKata.get((int)startDay-1)));
                startDay++;
            }
        }
        return result;
    }

    public static List<Long> ann(long n) {
        List<Long> katas = new ArrayList<>();
        if(dailyAnnKata.size() > 0) {
            katas.addAll(dailyAnnKata);
        }
        if(katas.size() > n) {
            return katas.subList(0, (int)n);
        }
        else {
            for(int day = katas.size(); day < n; day++) {
                katas.add(countAnnKataAtDayIteratively(day));
            }
        }
        return katas;
    }

    //Efficient solution (prefer loops over recursion)
    private static long countAnnKataAtDayIteratively(long n) {

        if(dailyJohnKata.size() == 0 && dailyAnnKata.size() == 0) {
            dailyJohnKata.add(JOHN_KATAS_AT_FIRST_DAY);
            dailyAnnKata.add(ANN_KATAS_AT_FIRST_DAY);
        }

        long result = dailyAnnKata.get(0);
        int day = dailyAnnKata.size();
        int lastDay = (int)n;
        if(day > n) {
            int startDay = 1;
            while(startDay <= n) {
                result = startDay - dailyJohnKata.get((int)(long)dailyAnnKata.get((int)startDay-1));
                startDay++;
            }
        }
        else if(day <= n) {
            int startDay = 1;
            while(startDay < day) {
                result = startDay - dailyJohnKata.get((int)(long)dailyAnnKata.get((int)startDay-1));
                startDay++;
            }
            while(startDay <= n) {
                dailyJohnKata.add(startDay - dailyAnnKata.get((int)(long)dailyJohnKata.get(startDay-1)));
                result = startDay - dailyJohnKata.get((int)(long)dailyAnnKata.get(startDay-1));
                dailyAnnKata.add(result);
                startDay++;
            }
        }
        return result;
    }

    public static long sumJohn(long n) {
        return john(n).stream().reduce(0L, (nn, m) -> nn + m);
    }

    public static long sumAnn(long n) {
        return ann(n).stream().reduce(0L, (nn, m) -> nn + m);
    }
}