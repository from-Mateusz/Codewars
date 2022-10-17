function manhattanDistance(pointA, pointB){
    let [x1, y1] = pointA;
    let [x2, y2] = pointB
    return Math.abs(x1-x2) + Math.abs(y1-y2);
  }