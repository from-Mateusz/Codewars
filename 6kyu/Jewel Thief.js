var crack = function crack(safe) {
    let combinationParts = [];
    let workingCombinationParts = [];
    for(let i = 0; i < 100; i++) {
      combinationParts.push(`L${i < 10 ? '0' + i : i}`);
      combinationParts.push(`R${i < 10 ? '0' + i : i}`);
    }
    if(!workingCombinationParts[0]) {
      let counter = 0;
      let combination = combinationParts[counter] + '-RL-RL';
      while(safe.unlock(combination) !== 'click' && counter < combinationParts.length) {
        combination = combination.replace(combinationParts[counter], combinationParts[++counter]);
      }
      workingCombinationParts.push(combination.split('-')[0]);
    }
    if(!workingCombinationParts[1]) {
      let counter = 0;
      let combination = '-' + combinationParts[counter] + '-RL';
      while(safe.unlock(workingCombinationParts[0] + combination) !== 'click-click' && counter < combinationParts.length) {
        combination = combination.replace(combinationParts[counter], combinationParts[++counter]);
      }
      workingCombinationParts.push(combination.split('-')[1]);
    }
    if(!workingCombinationParts[2]) {
      let counter = 0;
      let combination = '-' + combinationParts[counter];
      while(safe.unlock(workingCombinationParts.join('-') + combination) !== 'click-click-click' && counter < combinationParts.length) {
        combination = combination.replace(combinationParts[counter], combinationParts[++counter]);
      }
      workingCombinationParts.push(combination.split('-')[1]);
    }
    return safe.open();
  }