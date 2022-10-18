  public static int oneTwoThree() {
    int ot = 2*oneTwo() + oneTwo() - 2;
    if(ot < 4)
      return ot%3 + 1;
    return oneTwoThree();
  }