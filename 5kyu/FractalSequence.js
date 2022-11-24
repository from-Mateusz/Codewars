const FRACTAL_SEQ = [ 1, 1, 2, 1, 3, 4, 2, 5, 1, 6, 7, 8, 3, 9, 10,
                    11, 12, 4, 13, 14, 2, 15, 16, 17, 18, 19, 5, 20, 1, 21, 22, 23,
                    24, 25, 26, 6, 27, 28, 29, 30, 31, 32, 33, 7, 34, 35, 36, 37, 38,
                    39, 40, 41, 8, 42, 43, 44, 3, 45, 46, 47, 48, 49, 50, 51, 52, 53,
                        9, 54, 55, 56, 57, 58, 59, 60 ];

// module.exports.a112382 = function a112382(n) {
//     return findX(n);
// };

function a112382(n) {
    return findX(n);
};

function findX(n) {
    if(n < FRACTAL_SEQ.length) {
        return FRACTAL_SEQ[n];
    }
    else {
        augmentSequenceTo(n);
        return findX(n);
    }
}

function augmentSequenceTo(n) {
    let moves = (n - FRACTAL_SEQ.length) - 1;
    let startAt = FRACTAL_SEQ.lastIndexOf(9);
    let examinedNumber = { n: 10, pos: FRACTAL_SEQ.indexOf(10) };
    let countNumbersToAdd = examinedNumber.n - (FRACTAL_SEQ.length - startAt);
    let m = 0;
    
    while(m <= moves && FRACTAL_SEQ.length < n) {
        
        if(m === 0) {
            let numberToStartFrom = FRACTAL_SEQ[FRACTAL_SEQ.length - 1]
            for(let l = 1; l <= countNumbersToAdd; l++) {
                FRACTAL_SEQ.push(numberToStartFrom + l);
            }
            FRACTAL_SEQ.push(examinedNumber.n);
            examinedNumber = { n: FRACTAL_SEQ[examinedNumber.pos + 1], pos: examinedNumber.pos + 1 };
        }

        else {
            let numberToStartFrom = FRACTAL_SEQ[FRACTAL_SEQ.length - 2]
            for(let l = 1; l <= examinedNumber.n; l++) {
                FRACTAL_SEQ.push(numberToStartFrom + l);
            }
    
            FRACTAL_SEQ.push(examinedNumber.n);
            examinedNumber = { n: FRACTAL_SEQ[examinedNumber.pos + 1], pos: examinedNumber.pos + 1};
        }

        ++m;
    }
}

console.log(a112382(1479));

 