function mostFrequentDays(year, daysOrder = [
    "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
  ]) {
    let daysCounts = [[daysOrder[0], 0], [daysOrder[1], 0], [daysOrder[2], 0], [daysOrder[3], 0],
    [daysOrder[4], 0], [daysOrder[5], 0], [daysOrder[6], 0]];
    for(let month = 0; month <= 11; month++) {
      let endMonthDate = month < 11 ? new Date(year, month + 1, 0) : new Date(year + 1, 0, 0);
      let totalDaysInMonth = endMonthDate.getDate();
      let dayOfTheWeek = endMonthDate.getDay();
      for(let day = totalDaysInMonth; day > 0; day--) {
        daysCounts[dayOfTheWeek][1] = daysCounts[dayOfTheWeek][1] + 1;
        if(--dayOfTheWeek < 0) {
          dayOfTheWeek = 6
        }
      }
    }
    return findMostFrequentDaysAndSort(daysCounts);
  }
  
  function findMostFrequentDaysAndSort(days, daysOrder = {
    'Monday': 1,
    'Tuesday': 2,
    'Wednesday': 3,
    'Thursday': 4,
    'Friday': 5,
    'Saturday': 6,
    'Sunday': 7
  }) {
    let maxFrequentDays = findMaxFrequentDays(days);
    let daysOrderObj = {...daysOrder}
    maxFrequentDays.sort((dayA, dayB) => {
      if(daysOrder[dayA[0]] < daysOrder[dayB[0]]) {
        return -1;
      }
      if(daysOrder[dayA[0]] > daysOrder[dayB[0]]) {
        return 1;
      }
      return 0;
    });
    return maxFrequentDays.map(day => day[0]);
  }
  
  function findMaxFrequentDays(days) {
    sortDaysByFrequency(days);
    let mostFrequentDays = [days[0]];
    let maxFrequency = mostFrequentDays[0][1];
    
    for(i = 1; i < days.length; i++) {
      if(days[i][1] == maxFrequency) {
        mostFrequentDays.push(days[i]);
      }
    }
    return mostFrequentDays;
  }
  
  // Insertion Sort
  function sortDaysByFrequency(days) {
    for(let d = 1; d < days.length; d++) {
      let day = days[d];
      let startPos = d - 1;
      while(startPos >= 0 && days[startPos][1] < day[1]) {
        days[startPos + 1] = days[startPos];
        startPos = startPos - 1;
      }
      days[startPos + 1] = day
    }
  }