function decrypt(encryption) {
    let letters = new Array(26);
    letters = letters.fill(0);
    const aCode = 97;
    const zCode = 122;
    const encryptionArr = encryption.split('')
                                    .map(ch => ch.charCodeAt(0));
    let p = 0;
    do {
        const letterIndex = encryptionArr[p] - aCode;
        if(letterIndex >= 0 && letterIndex < 26) letters[letterIndex] = letters[letterIndex] + 1;
        p++;  
    } while(p < encryptionArr.length);
    return letters.join('');
}