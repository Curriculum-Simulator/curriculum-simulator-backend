package alahyaoui.curriculum.util;

import java.util.ArrayList;
import java.util.HashMap;

import alahyaoui.curriculum.dto.CourseDto;

public class HashMapUtil {
    
    public static HashMap<String, CourseDto> convertArrayListToHashMap(ArrayList<CourseDto> arrayList) {
        HashMap<String, CourseDto> hashMap = new HashMap<>();
        for (var item : arrayList) {
            hashMap.put(item.getId(), item);
        }
        return hashMap;
    }
}
