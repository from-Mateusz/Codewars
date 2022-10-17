public class Solution {
    public static int[] sortByFrequency(int[] array) {
        Arrays.sort(array);
        return buildSortedArrByValFrequency(array);
    }
  
    private static int[] buildSortedArrByValFrequency(int[] source) {
      if(source.length == 0) {
        return new int[]{};
      }
      List<Integer> results = new ArrayList<>();
      LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap();
        
      sortByOccurances(source).entrySet()
                              .stream()
                              .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                              .forEachOrdered(elem -> sortedMap.put(elem.getKey(), elem.getValue()));
                                
      for(Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
          int counter = entry.getValue();
          while(counter > 0) {
              results.add(entry.getKey());
              counter--;
          }
        }
      return results.stream().mapToInt(v -> v).toArray();
    }
    
    public static Map<Integer, Integer> sortByOccurances(int[] arr) {
      LinkedHashMap<Integer, Integer> occurances = new LinkedHashMap();
      for(int i = 0; i < arr.length; i++) {
          occurances.put(arr[i], countOccurences(arr, arr[i]));
      }
      return occurances;
    }
    
    private static int countOccurences(int[] arr, int val) {
        int occurances = 0;
        for(int i = 0; i < arr.length; i++) {
            if(val == arr[i]) { occurances++; }
        }
        return occurances;
    }
}