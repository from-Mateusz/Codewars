const seqA = [1, 3, 7, 12, 18, 26, 35, 45, 56, 69, 83, 98];

function hof(n) {
    if(n < seqA.length - 1) return getValueForSeqA(n); 
    while(n > seqA.length - 1) {
        let nextValueSeqA = getLastValueForSeqA() + getLastValueForSeqB();
        seqA.push(nextValueSeqA);
    }
    return getLastValueForSeqA();
}

function getLastValueForSeqB() {
    let index = 1; 
    let valuesCount = 0;
    while(true) {
        const range = [seqA[index-1] + 1, seqA[index]];
        
        valuesCount += range[1] - range[0];
        
        if(valuesCount >= seqA.length ) {
            valuesCount -= (range[1] - range[0]);
            for(let v = range[0];  ; v++) {
                ++valuesCount;
                if(valuesCount === seqA.length) {
                    return v; 
                }  
            }
        }
        
        ++index;
    }
}

function getValueForSeqA(n) {
    return seqA[n];
}

function getLastValueForSeqA() {
    return seqA[seqA.length - 1]; 
}