function solve(chips) {
    chips = chips.sort((a, b) => b - a);
    let days = 0;
    let sum = 0;
    while (sum != chips[0]) {
      let [first, second, third] = chips;
      first--;
  
      if (third) {
        third--;
      } else {
        second--;
      }
      
      days++;
  
      if (second > first) {
        chips = [second, first, third];
      } else {
        chips = [first, second, third];
      }
      sum = first + second + third;
    }
    return days;
  }