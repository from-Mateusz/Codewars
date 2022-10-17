function reverse(str){
    if(str.length == 0) return str;
    let wordsArray = str.trim().split(/\s+/);
    for(let i = 1; i < wordsArray.length; i+=2) {
        wordsArray[i] = wordsArray[i].split('').reverse().join('');
    }
    return wordsArray.join(' ');
}