function sakuraFall(v) {
    const totalRoad = 5 * 80;
    if(v <= 0) {
      return 0;
    }
    return totalRoad / v;
  }