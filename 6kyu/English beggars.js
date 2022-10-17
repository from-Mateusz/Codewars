function beggars(values, n){
    let beggars = [];
    for(let i = 0; i < n; i++) {
      beggars.push(0)
    }
    for(let i = 0; i < beggars.length; i++) {
      for(b = i; b < values.length; b = b+n) {
         beggars[i] += values[b] ? values[b] : 0;
      }
    }
    return beggars;
  }