import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

abstract class Player{
    private String name;
    private String initNumber;
    private List<String> fightList = new LinkedList<String>();
    private Boolean isEnd;
    public Player(String name, String initNumber) {
        this.name = name;
        this.initNumber = initNumber;
        this.isEnd = false;
    }
    public abstract void play(String number) throws Exception;
    public void addFightList(String number) { fightList.add(number); }
    public Boolean getIsEnd() { return isEnd; }
    public void setIsEnd(Boolean isEnd) { this.isEnd = isEnd; }
    public String getInitNumber() { return initNumber; }
    public String getName() { return name; }
    public void printFightList() {
        // TODO
    }
}
class PlayerATeam extends Player
{
    public PlayerATeam(String name, String number) {
        super(name, number);
    }

    @Override
    public void play(String number) throws Exception {
        int strike = 0;
        int ball = 0;
        int out = 4 - strike - ball;
        String play ="";
        String number ="";

        //상대가 제시하는 숫자 4자릿수 분해
        char firstNum = play.charAt(0);
        char secondNum = play.charAt(1);
        char thirdNum = play.charAt(2);
        char fourthNum = play.charAt(3);

        //정답 4자릿수, 변수 이름을 잘못 가져온 거 같긴함..
        char firstName = number.charAt(0);
        char secondName = number.charAt(1);
        char thirdName = number.charAt(2);
        char fourthName = number.charAt(3);

        // 숫자와 자리가 일치하면 스트라이크 ++
        if(firstNum==firstName) strike++;
        if(secondName==secondNum) strike++;
        if(thirdName==thirdNum) strike++;
        if(fourthName==fourthNum) strike++;

        if(firstName == secondNum || firstName == thirdNum || firstName == fourthNum) ball++;
        if(secondName == firstNum || secondName == thirdNum || secondName == fourthNum) ball++;
        if(thirdName == secondNum || thirdName == firstNum || thirdName == fourthNum) ball++;
        if(fourthName == firstNum || fourthName == thirdNum || fourthName == secondNum) ball++;

        // TODO

        if(strike == 4) {
            System.out.println("Congratulation!");
            super.setIsEnd(true);
        } else {
            System.out.println("Strike : "+strike+", Ball :"+ball+", Out: "+out);
        }
    }
}

class PlayerBTeam extends Player
{
    public PlayerBTeam(String name, String number) {
        super(name, number);
    }
    @Override
    public void play(String number) throws Exception {
        int strike = 0;
        int ball = 0;
        int out = 0;

        //좀 더 간단한 방법으로 숫자 및 숫자 자리 체크하기
        int num = number.charAt();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (num[i] == num[j]) {
                    // 숫자랑 순서가 일치
                    if (i == j) {
                        strike++;
                    }
                    else { // 숫자만 일치
                        ball++;
                    }
                }
            }
        }

        if(ball == 4) {
            System.out.println("Congratulation!");
            super.setIsEnd(true);
        } else {
            System.out.println("Strike : "+strike+", Ball :"+ball+", Out: "+out);
        }
    }
}

public class Main {

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Player playerArray[] = {new PlayerATeam("A Team","1234"), new PlayerBTeam("B Team","5678")};
        int checkPlayer = 0;

        while(true) {
            checkPlayer = 0;
            for(Player player : playerArray) {
                if (player.getIsEnd() == false) {
                    System.out.print("Please enter a 4 digit number (" + player.getName() + ") defence : ");
                    String number = scanner.nextLine();
                    try {
                        player.play(number);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    checkPlayer++;
                }
            }
            if(checkPlayer == playerArray.length) break;
        }

        for(Player player : playerArray) {
            player.printFightList();
        }
    }
}