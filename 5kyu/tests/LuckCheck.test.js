const kata = require("../LuckyNumber");

test("GivenTicketHasDifferentFormatThanDecimal_WhenLuckyNumberCheck_ThenThrowError", () => { 
    expect(() => kata.luckyNumber("DDD334")).toThrow(Error);
});

test("GivenTicketIsBlank_WhenLuckyNumberCheck_ThenThrowError", () => {
    expect(() => kata.luckyNumber("     ")).toThrow(Error);
    expect(() => kata.luckyNumber("")).toThrow(Error);
});

test("GivenWinningEvenNumbersLengthTicket_WhenLuckyNumberCheck_ThenReturnTrue", () => {
    expect(kata.luckyNumber("003111")).toBe(true);
})

test("GivenLosingEvenNumbersLengthTicket_WhenLuckyNumberCheck_ThenReturnFalse", () => {
    expect(kata.luckyNumber("683000")).toBe(false);
})

test("GivenWinningOddNumbersLengthTicket_WhenLuckyNumberCheck_ThenReturnTrue", () => {
    expect(kata.luckyNumber("02211")).toBe(true);
})