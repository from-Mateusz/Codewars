private double solution(int[] arr1, int[] arr2) {
    if(arr1 == null || arr2 == null) return 0;

    if(arr1.length != arr2.length) return 0;

    int itemsQty = arr1.length;

    double diffSqrSum = 0;

    for(int i = 0; i < itemsQty; i++) {
        diffSqrSum += Math.pow(arr1[i] - arr2[i], 2);
    }

    return diffSqrSum / itemsQty;
}