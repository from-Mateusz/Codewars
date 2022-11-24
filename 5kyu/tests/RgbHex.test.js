const { rgb, rgb2 } = require("../RgbHex");

test("givenDecimalRGBThenConvertToHex", () => {
    expect(rgb(0, 0, 0)).toEqual('000000');
    expect(rgb(0, 0, -20)).toEqual('000000');
    expect(rgb(300,255,255)).toEqual('FFFFFF');
    expect(rgb(173,255,47)).toEqual('ADFF2F');

    expect(rgb2(0, 0, 0)).toEqual('000000');
    expect(rgb2(0, 0, -20)).toEqual('000000');
    expect(rgb2(300,255,255)).toEqual('FFFFFF');
    expect(rgb2(173,255,47)).toEqual('ADFF2F');
});