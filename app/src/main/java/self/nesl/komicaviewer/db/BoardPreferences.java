package self.nesl.komicaviewer.db;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Map;
import self.nesl.komicaviewer.models.Host;
import self.nesl.komicaviewer.models.po.Post;
import static android.content.Context.MODE_PRIVATE;
import static self.nesl.komicaviewer.util.ProjectUtils.arrayMapToArrayPost;
import static self.nesl.komicaviewer.util.ProjectUtils.arrayPostToArrayMap;
public final class BoardPreferences {
    public static final String PREF_NAME = "board_urls";

    private static Context context;

    public static void initialize(Context context) {
        BoardPreferences.context = context;
    }

    public static void update(final Host host, Host.OnResponse onResponse) {
        host.downloadBoardlist(new Host.OnResponse() {
            @Override
            public void onResponse(ArrayList<Post> arrayList) {
                context.getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit().putString(host.getClass().getName(), new Gson().toJson(arrayPostToArrayMap(arrayList))).apply();
                onResponse.onResponse(arrayList);
            }
        });
    }

    public static ArrayList<Post> getBoards(Host host) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        if (prefs.getString(host.getClass().getName(), "").equals("")) {
            return null;
        }

        /*
        Google Gson - deserialize list<class> object? (generic type)?
        https://stackoverflow.com/questions/5554217/google-gson-deserialize-listclass-object-generic-type
         */
        // todo: toJson(Post)
        ArrayList<Map<String, String>> maps= new Gson().fromJson(prefs.getString(host.getClass().getName(), ""), new TypeToken<ArrayList<Map<String, String>>>() {}.getType());
        return arrayMapToArrayPost(maps);
    }
}