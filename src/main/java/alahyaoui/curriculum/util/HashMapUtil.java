package alahyaoui.curriculum.util;

import java.util.HashMap;
import java.util.List;

import alahyaoui.curriculum.dto.CourseDto;

public class HashMapUtil {
    
    public static HashMap<String, CourseDto> convertListToHashMap(List<CourseDto> arrayList) {
        HashMap<String, CourseDto> hashMap = new HashMap<>();
        for (var item : arrayList) {
            hashMap.put(item.getId(), item);
        }
        return hashMap;
    }
}
