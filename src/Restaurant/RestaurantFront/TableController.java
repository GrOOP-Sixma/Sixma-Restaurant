package Restaurant.RestaurantFront;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TableController {
    private final Map<Integer, ArrayList<Table>> tableMap;
    private final Map<Integer, Integer> vacancyMap;

    // constructor
    public TableController() {
        tableMap = new HashMap<>();
        vacancyMap = new HashMap<>();
    }

    // methods
    public void addTable(int numSeats) {
        Table table = new Table(numSeats);

        ArrayList<Table> tableList;
        if (!tableMap.containsKey(numSeats)) {
            tableList = new ArrayList<>();
            vacancyMap.put(numSeats, 1);
        }
        else {
            tableList = tableMap.get(numSeats);
            vacancyMap.put(numSeats, vacancyMap.get(numSeats) + 1);
        }
        tableList.add(table);
        tableMap.put(numSeats, tableList);
    }

    public void removeTable(int numSeats) {
        if (tableMap.containsKey(numSeats)) {
            if (vacancyMap.get(numSeats) == 1) {
                tableMap.remove(numSeats);
                vacancyMap.remove(numSeats);
            }
            else {
                ArrayList<Table> tableList = tableMap.get(numSeats);
                tableList.remove(0);
                tableMap.put(numSeats, tableList);
                vacancyMap.put(numSeats, vacancyMap.get(numSeats) - 1);
            }
        }
    }
}