function solve(s){
    let numbers = s.split(/[a-z]{1}/)
                    .map(val => parseInt(val, 10))
                    .filter(val => !Number.isNaN(val));
    return numbers.length == 1 ? numbers[0] :
            numbers.length > 1 ? numbers.reduce((prev, next) => {
              return prev < next ? next : prev;
            }) : 0;
};