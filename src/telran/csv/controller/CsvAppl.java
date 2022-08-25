package telran.csv.controller;

import telran.csv.controller.Telran.csv.model.Passanger;

import java.io.*;

public class CsvAppl {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("train.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String value = "";
        double totalFares = 0;
        int[] classCounters = {0,0,0,0};
        int[] classTotalFares = {0,0,0,0};
        int survQuantity = 0;
        int unSuQuantity = 0;
        int[] survivedGenders = {0,0,0}; //men,women ,children
        int[] unsurvivedGenders = {0,0,0};
        reader.readLine();
        while (value != null){
            value = reader.readLine();
            if (value == null)
                break;;
            Passanger pas = parseLine(value);
            totalFares+= pas.getFare();
            classCounters[pas.getpClass()]++;
            classTotalFares[pas.getpClass()]+=pas.getFare();
            if(pas.isSurvived()) {
                survQuantity++;
                if (pas.getSex().equals("male"))
                    survivedGenders[0]++;
                else
                    survivedGenders[1]++;
                if (pas.getAge() < 18)
                    survivedGenders[2]++;
            } else {
                unSuQuantity++;
                if (pas.getSex().equals("male"))
                    unsurvivedGenders[0]++;
                else
                    unsurvivedGenders[1]++;
                if (pas.getAge() < 18)
                    unsurvivedGenders[2]++;
            }
        }
        System.out.println("totalFares = "  + totalFares);
        System.out.println("average fare for 1 class = "  + (classTotalFares[1] * 1.0 / classCounters[1])  );
        System.out.println("average fare for 2 class = "  + (classTotalFares[2] * 1.0 / classCounters[2])  );
        System.out.println("average fare for 3 class = "  + (classTotalFares[3] * 1.0 / classCounters[3])  );
        System.out.println("total survived " + survQuantity);
        System.out.println("total unsurvived " + unSuQuantity);
        System.out.println("total unsurvived " + unSuQuantity);
        System.out.println("total men survived " + survivedGenders[0] + " unsurvuved " + unsurvivedGenders[0] );
        System.out.println("total women survived " + survivedGenders[1] + " unsurvuved " + unsurvivedGenders[1] );
        System.out.println("total childs survived " + survivedGenders[2] + " unsurvuved " + unsurvivedGenders[2] );
    }
    private static Passanger parseLine(String line){

        String[] lines = line.split(",");
        if (lines.length == 12)
            System.out.println("");
        Passanger pas = new Passanger();
        pas.setPassangerId(Integer.parseInt(lines[0]));
        pas.setSurvived(lines[1].equals("1")?true:false);
        pas.setpClass(Integer.parseInt(lines[2]));
        pas.setName(lines[3] + ", " + lines[4]);
        pas.setSex(lines[5]);
        pas.setAge(lines[6].equals("")? -1.0:Double.parseDouble(lines[6]));
        pas.setSibSp(Integer.parseInt(lines[7]));
        pas.setParch(Integer.parseInt(lines[8]));
        pas.setTicket(lines[9]);
        pas.setFare(Double.parseDouble(lines[10]));
        pas.setCabin(lines[11]);
        pas.setEmbarked(lines.length == 12 ?"":lines[12]);
        return pas;
    }
}
