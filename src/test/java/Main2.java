import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String costs = in.next();
            String prices = in.next();
            int money = in.nextInt();
            String[] costSplit = costs.split(",");
            String[] priceSplit = prices.split(",");
            ArrayList<Integer> costList = new ArrayList<>();
            ArrayList<Integer> priceList = new ArrayList<>();
            HashMap<Integer, Integer> map = new HashMap<>();
            if (money < Integer.valueOf(costSplit[0])) {
                System.out.println(0);
            }
            for (int i = 0; i < costs.length(); i++) {
                Integer cost = Integer.valueOf(costSplit[i]);
                Integer price = Integer.valueOf(priceSplit[i]);
                if (cost < price) {
                    costList.add(cost);
                    priceList.add(price);
                    map.put(i, price - cost);
                }

            }
            Collections.sort(priceList);
            for (int i = 0; i < priceList.size(); i++) {
                costList.set(i, priceList.get(i) - map.get(i));
                
            }

            int costAll = 0;
            while (money >= costAll) {


            }

        }

    }

}
