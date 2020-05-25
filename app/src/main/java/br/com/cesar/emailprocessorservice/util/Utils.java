package br.com.cesar.emailprocessorservice.util;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static void removeDuplicates(List<String> list) {
        if (list == null || list.isEmpty()) return;

        List<String> newList = new ArrayList<>();
        for (int i = 0; i < list.size() - 1; i++) {
            if (!newList.contains(list.get(i))) newList.add(list.get(i));
        }
        list.clear();
        list.addAll(newList);
    }

}
