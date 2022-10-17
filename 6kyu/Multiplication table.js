multiplicationTable = function(size) {
    let result = [];
    for(let i = 0; i < size; i++) { result.push([]); }
    for(let i = 0; i < result.length; i++) {
      for(let l = 1; l <= size; l++) {
        result[i].push(l*(i+1));
      }
    }
    return result; 
  }