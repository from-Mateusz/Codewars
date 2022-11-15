// NEEDS REFACTORING TOWARDS OPTIMIZATION

function spiralColumn (n, col) {
    let sum = 0;

    let top = 1, bottom = n; left = 1; right = n;
    
    let leftRight = false, topBottom = false; rightLeft = false; bottomTop = false;

    let recentVal = 1;

    let recentCol = 1;

    leftRight = true; // we start traversing our matrix from its top left to right from

    while( (top <= bottom && left <= right) || !(left > col || right < col)) {
        if(leftRight) {
            for(let i = left; i <= right; i++) {
                recentCol = i;
                if(col === recentCol) {
                    sum += recentVal;
                }
                recentVal++;
            }

            ++top;
            leftRight = false;
            topBottom = true;
        }
        if(topBottom) {
            for(let i = top; i <= bottom; i++) {
                if(recentCol == col) {
                    sum += recentVal;
                }
                recentVal++; 
            }

            --right;
            --recentCol;
            topBottom = false;
            rightLeft = true;
        }

        if(rightLeft) {
            for(let i = right; i >= left; i--) {
                recentCol = i;
                if(col == recentCol) {
                    sum += recentVal;
                }
                recentVal++;
            }

            --bottom;
            rightLeft = false;
            bottomTop = true;
        }

        if(bottomTop) {
            for(let i = bottom; i >= top; i--) {
                if(recentCol == col) {
                    sum += recentVal;
                }
                recentVal++; 
            }

            ++left;
            ++recentCol;
            bottomTop = false;
            leftRight = true;
        }
    }

    return sum;
}