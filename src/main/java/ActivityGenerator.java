import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ActivityGenerator {
    private String requestBody;
//    public static final MediaType JSON
//            = MediaType.parse("application/json; charset=utf-8");

    public ActivityGenerator(){
        requestBody="";
    }
    public ActivityGenerator(String type, Float accessibility, Integer participants, Float price){
        requestBody= "?type="+ type;
        if(accessibility!=-1) requestBody+="&&accessibility="+accessibility;
        if(participants!=-1) requestBody+="&participants="+participants;
        if(price!=-1) requestBody+="&price="+price;
    }
    public Map<String, Object> generateActivity() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
//        RequestBody body = RequestBody.create(requestBody.toString(), JSON);
        Request request = new Request.Builder()
                .url("http://www.boredapi.com/api/activity"+requestBody)
//                .method("GET",body)
                .build();
        Response response = client.newCall(request).execute();
        Map<String, Object> resultMap = new Gson().fromJson(
                response.body().string(), new TypeToken<HashMap<String, Object>>() {}.getType()
        );
        return resultMap;
//        for(Object r: returnMap.keySet()){
//            System.out.println(r+" "+returnMap.get(r));
//        }
    }



    public static void main(String[] args){
        ActivityGenerator ag= new ActivityGenerator("",-1f,-1,-1f);
        try {
            Map<String, Object> activity= ag.generateActivity();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
};

