package hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class FurnitureOrder implements FurnitureOrderInterface {


    HashMap<Furniture, Integer> furnitureOrderMap;
    /**
     * TODO: Create a map of Furniture items to order quantities
     */

    /**
     * Initialize a new mapping of Furniture types to order quantities.
     */
    FurnitureOrder() {
        furnitureOrderMap = new HashMap<>();
    }

    public void addToOrder(final Furniture type, final int furnitureCount) {
        Integer count = furnitureOrderMap.get(type);
        if (!Objects.isNull(count))
            furnitureOrderMap.put(type, furnitureCount + count);
        else
            furnitureOrderMap.put(type, furnitureCount);
    }

    public HashMap<Furniture, Integer> getOrderedFurniture() {
        return furnitureOrderMap;
    }

    public float getTotalOrderCost() {
        float totalCost = 0;
        for (Furniture furniture : Furniture.values()) {
            Integer count = furnitureOrderMap.get(furniture);
            if (!Objects.isNull(count)) {
                totalCost+=count*furniture.cost();
            } else
                totalCost += 0;
        }
        return totalCost;
    }

    public int getTypeCount(Furniture type) {
        Integer count = furnitureOrderMap.get(type);
        if (!Objects.isNull(count))
            return count;
        return 0;
    }

    public float getTypeCost(Furniture type) {
        Integer count = furnitureOrderMap.get(type);
        if (!Objects.isNull(count))
            return count*type.cost();
        return 0.0f;
    }

    public int getTotalOrderQuantity() {
        int sum = 0;
        Set<Map.Entry<Furniture, Integer>> countEntry = furnitureOrderMap.entrySet();
        for (Map.Entry<Furniture, Integer> entry : countEntry) {
            if (!Objects.isNull(entry.getValue()))
                sum += entry.getValue();
        }
        return sum;
    }
}