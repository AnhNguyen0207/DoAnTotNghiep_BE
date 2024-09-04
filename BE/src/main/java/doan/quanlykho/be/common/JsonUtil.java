package doan.quanlykho.be.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Phương thức để chuyển đổi đối tượng thành chuỗi JSON với định dạng đẹp
    public static String toPrettyJson(Object object) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Phương thức để chuyển đổi chuỗi thành JSON với định dạng đẹp
    public static String stringToPrettyJson(String string) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(string, Object.class);
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

            return writer.writeValueAsString(json);
        } catch (Exception e) {
            e.printStackTrace();
            return string;
        }
    }
}
