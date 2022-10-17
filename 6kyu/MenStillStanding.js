function menStillStanding(cards) {
    // numbers in arrays stands for yellow cards quantity
    const teamA = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
    const teamB = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
    const remainingPlayers = [11, 11];
    if(cards.length == 0) return remainingPlayers;

    let remainingPlayersTeamA = 11;
    let remainingPlayersTeamB = 11;

    for(let i = 0; i < cards.length; i++) {
        const card = cards[i];
        const playerTeam = card[0];
        const playerNumber = card.substring(1, card.length - 1);
        const cardColor = card[card.length - 1];
    
        if(playerTeam == 'A') {
            if(teamA[playerNumber-1] < 2) {
                if(cardColor == 'R') {
                    teamA[playerNumber-1] = 2;
                    remainingPlayersTeamA--;
                }
                else if(cardColor == 'Y' && teamA[playerNumber-1] == 1) {
                    teamA[playerNumber-1] = 2;
                    remainingPlayersTeamA--;
                }
                else teamA[playerNumber-1] += 1;
            }
        }

        if(playerTeam == 'B') {
            if(teamB[playerNumber-1] < 2) {
                if(cardColor == 'R') {
                    teamB[playerNumber-1] = 2;
                    remainingPlayersTeamB--;
                }
                else if(cardColor == 'Y' && teamB[playerNumber-1] == 1) {
                    teamB[playerNumber-1] = 2;
                    remainingPlayersTeamB--;
                }
                else teamB[playerNumber-1] += 1;
            }
        }
        if(remainingPlayersTeamA == 6 || remainingPlayersTeamB == 6) break; 
    }

    remainingPlayers[0] = teamA.reduce((i, val) => i + (val < 2), 0);
    remainingPlayers[1] = teamB.reduce((i, val) => i + (val < 2), 0);

    return remainingPlayers;
}