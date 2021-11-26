package cn.xavier.util;

import cn.xavier.system.domain.DictionaryDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Zheng-Wei Shui
 * @date 11/23/2021
 */
public class JsonTest {
    public static void main(String[] args) throws JsonProcessingException {
        DictionaryDetail dictionaryDetail = new DictionaryDetail();
        dictionaryDetail.setId(1L);
        dictionaryDetail.setName("test");
        dictionaryDetail.setTypes_id(2L);
        System.out.println(new ObjectMapper().writeValueAsString(dictionaryDetail));
    }
}
