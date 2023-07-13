import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import jdk.jfr.DataAmount;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class PageEntry implements Comparable<PageEntry> {
    private final String pdfName;
    private final int page;
    private final int count;

    public PageEntry(String pdfName, int page, int count) {
        this.pdfName = pdfName;
        this.page = page;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compareTo(PageEntry o) {
        return Integer.compare(o.getCount(), this.getCount());
    }



    @Override
    public String toString() {
        Map map = new LinkedHashMap();
        map.put("pdfName", pdfName);
        map.put("page", page);
        map.put("count", count);
        JSONObject result = new JSONObject(map);
        return result.toString();
    }
}
