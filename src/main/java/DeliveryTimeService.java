import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeliveryTimeService {
        public List<List<Double>> groupPackages(List<DetailedPackage> packages, VehicleConfig config) {
             List<Double> originalLst = packages.stream()
                .map(DetailedPackage::getWeight).sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        List<Double> weightLst = new ArrayList<>(originalLst);
        Map<Double, List<List<Double>>> map = new HashMap<>();

        for (int i = 0; i < weightLst.size(); i++) {
            Double sum = weightLst.get(i);
            int j = 1;
            callBack(sum, i, j, "new", weightLst, map, originalLst, config.getMaxCarriableWeight());
            removeWeightList(weightLst, map, weightLst.get(i));
            i--;
        }

        List<List<Double>> result = new ArrayList<>();
        for (Map.Entry<Double, List<List<Double>>> entry : map.entrySet()) {
            List<Double> combined = new ArrayList<>();
            combined.add(entry.getKey());
            entry.getValue().forEach(combined::addAll);

            if (combined.size() > 1) {
                List<Double> valuesOnly = combined.subList(1, combined.size());
                valuesOnly.sort(Comparator.reverseOrder());
            }
            result.add(combined);
        }

        result.sort((list1, list2) -> {
            double sum1 = list1.stream().mapToDouble(Double::doubleValue).sum();
            double sum2 = list2.stream().mapToDouble(Double::doubleValue).sum();
            return Double.compare(sum2, sum1);
        });

        return result;
        }

            // this method is to remove weights from list after got max weight of Package from the list
    public static void removeWeightList(List < Double > weightLst, Map < Double, List < List < Double >>> map, Double currWeight) {
        // System.out.println("remove" + ":" + weightLst + ":" + map + ":" + currWeight);

        for (Map.Entry < Double, List < List < Double >>> entry: map.entrySet()) {
            List < List < Double >> listOfLists = new ArrayList < > ();
            for (List < Double > inner: entry.getValue()) {
                listOfLists.add(new ArrayList < > (inner));
            }

            int maxIndex = -1;
            double largestVal = 0.0;

            for (int idx = 0; idx < listOfLists.size(); idx++) {
                double total = 0.0;
                for (Double val: listOfLists.get(idx)) {
                    total += val;
                }
                if (total >= largestVal) {
                    largestVal = total;
                    maxIndex = idx;
                }
            }

            if (maxIndex == -1) {
                continue;
            }

            List < Double > innerList = listOfLists.get(maxIndex);

            weightLst.remove(currWeight);
            for (Double val: new ArrayList < > (innerList)) {
                weightLst.remove(val);
            }

            List < Double > keep = listOfLists.get(maxIndex);
            listOfLists.clear();
            listOfLists.add(keep);
            entry.setValue(listOfLists);

            // System.out.println("removelast" + ":" + weightLst + ":" + map + ":" + currWeight);
        }
    }
    // to map the packages to send together based on criteria
    public static void callBack(Double sum, int i, int j, String status, List < Double > weightLst, Map < Double, List < List < Double >>> map, List < Double > originalLst,double maxCarriableWeight) {
        List < Double > result = new ArrayList < > ();

        for (Map.Entry < Double, List < List < Double >>> entry: map.entrySet()) {

            result.add(entry.getKey());

            for (List < Double > innerList: entry.getValue()) {
                result.addAll(innerList);
            }
        }
        List < Double > sorted1 = new ArrayList < > (originalLst);
        List < Double > sorted2 = new ArrayList < > (result);
        Collections.sort(sorted1);
        Collections.sort(sorted2);
        
        // to check all the packages are mapped
        boolean same = sorted1.equals(sorted2);
        if (same) {
            return;
        }
        
        if (sum < maxCarriableWeight) {
            // System.out.println("start"+sum +" "+j+" "+status+" "+weightLst+" "+map);

            if (status.equals("new")) {
                List < Double > newList = new ArrayList < > ();
                // System.out.println(sum + " " +weightLst.get(weightLst.size() - j));
                if (sum + weightLst.get(weightLst.size() - j) < maxCarriableWeight && sum != weightLst.get(weightLst.size() - j)) {
                    newList.add(weightLst.get(weightLst.size() - j));
                }
                // System.out.println(sum);

                map.put(weightLst.get(i), Arrays.asList(newList));

                sum += weightLst.get(weightLst.size() - j);
                j++;
                // System.out.println("end"+sum +" "+j+" "+status+" "+weightLst+" "+map);
                callBack(sum, i, j, "old", weightLst, map, originalLst,maxCarriableWeight);
                // System.out.println(sum +" "+j+" "+status+" "+weightLst+" "+map);
            } else {
                List < Double > newList = new ArrayList < > ();
                List < Double > newList1 = new ArrayList < > ();
                for (List < Double > ss: map.get(weightLst.get(i))) {
                    try {
                        // Double s1 = ss.get(j - 2);
                        // System.out.println(weightLst.get(i)+":"+ss+""+s1);
                        newList = ss;
                    } catch (Exception e) {

                    }

                }
                // System.out.println(map.get(j));
                
                // to check while traversing it doesnt go above the index
                if (weightLst.size() >= j) {
                    // System.out.println("weight"+j+ ";"+weightLst+"sum"+sum+":"+(sum +weightLst.get(weightLst.size() - j)));
                    
                    //check sum + next index weight is less than maxCarriableWeight else adding in to new array by this getting possible list of packages
                    if (sum + weightLst.get(weightLst.size() - j) <= maxCarriableWeight) {
                        newList.add(weightLst.get(weightLst.size() - j));
                    } else if (weightLst.get(i) + weightLst.get(weightLst.size() - j) <= maxCarriableWeight && weightLst.get(i) != weightLst.get(weightLst.size() - j)) {
                        // System.out.println(weightLst.get(i) +weightLst.get(weightLst.size() - j));
                        newList1 = new ArrayList < > ();
                        newList1.add(weightLst.get(weightLst.size() - j));
                    }
                    if (newList1.size() != 0) {
                        map.put(weightLst.get(i), Arrays.asList(newList, newList1));
                    } else {
                        map.put(weightLst.get(i), Arrays.asList(newList));
                    }

                    if (sum + weightLst.get(weightLst.size() - j) < maxCarriableWeight) {
                        sum += weightLst.get(weightLst.size() - j);
                        j++;
                        // System.out.println("end"+sum +" "+j+" "+status+" "+weightLst+" "+map);

                        callBack(sum, i, j, "old", weightLst, map, originalLst,maxCarriableWeight);
                    }
                }

            }
            // System.out.println("bott"+sum + " " +weightLst.get(weightLst.size() - j));

        }
    }


}
