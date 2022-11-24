const hexs = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F'];

module.exports.rgb = (r, g, b) => {
    return hex(Math.max(0, Math.min(255, r)))
            + hex(Math.max(0, Math.min(255, g)))
            + hex(Math.max(0, Math.min(255, b)))
}

function hex(n) {
    if(n === 0) return '00';
    let h = hexs[Math.floor(n % 16)]
    let result = hex(Math.floor(n / 16)) + h;
    return result.length > 2 ? result.substring(result.length - 2) : result;
}

module.exports.rgb2 = (...argv) => {
    return argv.map(color => Math.max(0, Math.min(255, color))
                            .toString(16)
                            .padStart(2, '0')
                            .toUpperCase())
                .join('');
}
