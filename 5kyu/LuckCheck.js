const CORRECT_TICKET_FORMAT = /^\d+$/;
const LOSE = false;
const WIN = true;
const EMPTY_ERROR_MSG = "";

module.exports.luckyNumber = (ticket) => {    
    if(!ticket || !CORRECT_TICKET_FORMAT.test(ticket)) {
        throw new Error(EMPTY_ERROR_MSG);
    }
    
    let sums = getLeftRightSums(ticket);
        
    if(sums.left === sums.right) {
        return WIN;
    }
    
    return LOSE;
}

function getLeftRightSums(ticket) {
    let leftSum = 0, rightSum = 0;
    let ticketNumbers = [...ticket];
    if(!hasTicketEvenNumberOfDigits(ticket)) {
        ticketNumbers.splice( Math.ceil(ticket.length / 2) - 1, 1 );
    }

    return { left: sumNumbers(ticketNumbers, 0, ticketNumbers.length / 2), 
             right: sumNumbers(ticketNumbers, ticketNumbers.length / 2) 
        };
}

function hasTicketEvenNumberOfDigits(ticket) {
    return ticket.length % 2 === 0;
}

function sumNumbers(ticketNumbers, start, end) {
    let sum = 0;
    let _end = !end ? ticketNumbers.length : end;
    for(let i = start; i < _end; i++) {
        sum += parseInt(ticketNumbers[i]);
    }
    return sum;
}
