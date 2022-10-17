class Nba {
    
    public static String nbaCup(String resultSheet, String toFind) {
        String team = toFind;
        boolean found = false;
        int win = 0, lose = 0, draw = 0, points = 0, score = 0, concede = 0;
        String[] results = resultSheet.split(",");
        Pattern digitPattern = Pattern.compile("\\d+(?![a-z0-9])");

        if(toFind.length() == 0) {
            return "";
        }
      
        for(String result : results) {
            int possibleTeamPosition = result.lastIndexOf(team);
            if(possibleTeamPosition >= 0
                    && ((possibleTeamPosition + team.length()) < result.length())
                    && result.charAt(possibleTeamPosition+team.length()) != ' ') {
                continue;
            }
            else if(possibleTeamPosition >= 0) {
                found = true;
                Matcher matcher = digitPattern.matcher(result);
                int result1 = -1, result2 = -1, result3 = -1;
                while(matcher.find()) {
                    if(result1 < 0) {
                        result1 = Integer.valueOf(matcher.group());
                        System.out.println("r1: " + result1);
                        continue;
                    }
                    if(result2 < 0) {
                        result2 = Integer.valueOf(matcher.group());
                        System.out.println("r2: " + result2);
                        continue;
                    }
                    if(result3 < 0) {
                        result3 = Integer.valueOf(matcher.group());
                        System.out.println("r3: " + result3);
                        break;
                    }
                }
              
                if(result3 > 0) {
                    return String.format("Error(float number):%s", result);
                }

                if(result.startsWith(team)) {
                    if(result1 > result2) {
                        win++;
                        points += 3;
                        score += result1;
                        concede += result2;
                    }
                    if(result1 < result2) {
                        lose++;
                        score += result1;
                        concede += result2;
                    }
                    if(result1 == result2) {
                        draw++;
                        points += 1;
                        score += result1;
                        concede += result2;
                    }
                }
                else {
                    if(result1 > result2) {
                        lose++;
                        score += result2;
                        concede += result1;
                    }
                    if(result1 < result2) {
                        win++;
                        points += 3;
                        score += result2;
                        concede += result1;
                    }
                    if(result1 == result2) {
                        draw++;
                        points += 1;
                        score += result2;
                        concede += result1;
                    }
                }
            }
        }

        return !found ? String.format("%s:This team didn't play!", team)
                    : String.format("%s:W=%d;D=%d;L=%d;Scored=%d;Conceded=%d;Points=%d",
                                team, win, draw, lose, score, concede, points);
    }
}