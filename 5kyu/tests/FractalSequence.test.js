const fractalSequences = require("../FractalSequence");

test("GivenNthIndexOfFractalSeqThenReturnMappedValue", () => {
    expect(fractalSequences.a112382(0)).toBe(1);
    expect(fractalSequences.a112382(1)).toBe(1);
    expect(fractalSequences.a112382(2)).toBe(2);
    expect(fractalSequences.a112382(3)).toBe(1);
    expect(fractalSequences.a112382(4)).toBe(3);
    expect(fractalSequences.a112382(5)).toBe(4);
    expect(fractalSequences.a112382(6)).toBe(2);
    expect(fractalSequences.a112382(7)).toBe(5);
    expect(fractalSequences.a112382(8)).toBe(1);
    expect(fractalSequences.a112382(9)).toBe(6);
    expect(fractalSequences.a112382(10)).toBe(7);
    expect(fractalSequences.a112382(1818)).toBe(1748);
});