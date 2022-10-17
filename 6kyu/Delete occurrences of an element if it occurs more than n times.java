public class EnoughIsEnough {

  public static int[] deleteNth(int[] elements, int maxOccurrences) {
       if(maxOccurrences < 1) return new int[0];

        int retentionArr[] = new int[elements.length];
        int ultimateArrSize = 0;
        for(int i = 0; i < elements.length; i++) {
            int occurrences = 1;
            if(retentionArr[i] != 0) continue;
            else {
                retentionArr[i] = 1;
                ultimateArrSize++;
            }

            for(int j = i + 1; j < elements.length; j++) {
                if(elements[i] == elements[j]) {
                    occurrences++;
                    ultimateArrSize++;
                    if(occurrences <= maxOccurrences) {
                        retentionArr[j] = 1;
                    }
                    else {
                        retentionArr[j] = -1;
                        ultimateArrSize--;
                    }
                }
            }
        }

        int ultimateArr[] = new int[ultimateArrSize];
        int effectiveIndex = 0;
        for(int i = 0; i < retentionArr.length; i++) {
            if(effectiveIndex > 0 && retentionArr[i] != 1) continue;
            else {
                ultimateArr[effectiveIndex] = elements[i];
                effectiveIndex++;
            }
        }

        return ultimateArr;
  }

}