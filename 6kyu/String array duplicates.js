/** Short one */

function dup(s) {
    return s.map(str => str.replace(/(.)\1+/g, "$1"));
};

/** Long one (probably time complexiy s^2) */

function dup(s) {
    let sResult = [];
    for(let str of s) {
      let partResult = [str[0]];
      for(let i = 1; i < str.length; i++) {
        let recentLetter = partResult.pop();
        let currentLetter = str[i];
        if(recentLetter !== currentLetter) {
          partResult.push(recentLetter);
          partResult.push(currentLetter);
        }
        else {
          partResult.push(recentLetter);
        }
      }
      sResult.push(partResult.join(""));
    }
    return sResult;
};