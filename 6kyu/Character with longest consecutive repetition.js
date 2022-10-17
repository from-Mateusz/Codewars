function longestRepetition(s) {
    let longestRepetition = ["", 0];
    if(!s || s.length == 0) {
      return longestRepetition;
    }
    else {
      let repetitions = s.match(/(.)\1+/g);
      if(!repetitions) {
        return longestRepetition = [s[0], 1];
      }
      longestOne = repetitions.reduce((prev, next) => prev.length < next.length ? next : prev);
      return longestRepetition = [longestOne[0], longestOne.length];
    }
  }