/**
 * I will use The Sieve of Eratosthenes's algorithm.
 * It is and efficient algorithm to find whether the positive integer n > 1
 * is a prime or composite number, provided that n < 10^6.
 * So it is a good fit for this kata.
 */

 function primeBefAft(num) {
    const primeFactors = findPrimeFactors(num);
    return [findPreviousNearestPrime(num, primeFactors), findNextNearestPrime(num, primeFactors)];
}

function findPrimeFactors(num) {
    const primeFactors = [];

    for(let k = 2; k <= Math.ceil(Math.sqrt(num)); k++) {
        let composite = false;
        for(let l = 2; l < k; l++) {
            if(k % l == 0) {
                composite = true;
                break;
            }
        }

        if(!composite) primeFactors.push(k); 
    }

    return primeFactors;
} 

function findPreviousNearestPrime(num, primeFactors) {
    let possiblePrime = num - 1;
    while(isComposite(possiblePrime, primeFactors)) {
        --possiblePrime;
    }

    return possiblePrime;
}

function findNextNearestPrime(num, primeFactors) {
    let possiblePrime = num + 1;
    while(isComposite(possiblePrime, primeFactors)) {
        ++possiblePrime;
    }
    
    return possiblePrime;
}

function isComposite(num, primeFactors) {
    let composite = false;
    for(let k = 0; k < primeFactors.length; k++) {
        if(composite) break;
        if(num === primeFactors[k] || primeFactors[k] > num) break;
        if(num % primeFactors[k] === 0) composite = true;
    }
    return composite;
}

console.log(primeBefAft(120));