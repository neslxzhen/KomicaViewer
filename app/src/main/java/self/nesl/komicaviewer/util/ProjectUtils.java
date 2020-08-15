package self.nesl.komicaviewer.util;

import android.os.Bundle;

import org.jsoup.nodes.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.Nullable;
import self.nesl.komicaviewer.dto.PostDTO;
import self.nesl.komicaviewer.model.Host;
import self.nesl.komicaviewer.model.Post;
import self.nesl.komicaviewer.model.komica.host.Komica2Host;
import self.nesl.komicaviewer.model.komica.host.KomicaHost;
import self.nesl.komicaviewer.model.komica.host.KomicaTop50Host;

import static self.nesl.komicaviewer.db.BoardPreferences.getBoards;
import static self.nesl.komicaviewer.model.Host.MAP_BOARD_MODEL_COLUMN;
import static self.nesl.komicaviewer.model.Host.MAP_HOST_COLUMN;
import static self.nesl.komicaviewer.util.Utils.print;

public class ProjectUtils {
    private static final String MAP_TITLE_COLUMN="title";
    private static final String MAP_LINK_COLUMN="link";
    public static Host host;

    public static Host[] getHosts(){
        return new Host[]{
                new KomicaTop50Host(),
                new Komica2Host(),
//                new KomicaHost()
        };
    }

    public static Element installThreadTag(Element threads){
        //如果找不到thread標籤，就是2cat.komica.org，要用addThreadTag()改成標準綜合版樣式
        if (threads.selectFirst("div.thread") == null) {

            //將thread加入threads中，變成標準綜合版樣式
            Element thread = threads.appendElement("div").addClass("thread");
            for (Element div : threads.children()) {
                thread.appendChild(div);
                if (div.tagName().equals("hr")) {
                    threads.appendChild(thread);
                    thread = threads.appendElement("div").addClass("thread");
                }
            }
        }
        return threads;
    }

    public static Date parseTime(String time){
        for(String s : Arrays.asList(
                "yyyy/MM/dd(EEE) HH:mm:ss.SSS",
                "yy/MM/dd(EEE) HH:mm:ss",
                "yy/MM/dd(EEE)HH:mm:ss",
                "yy/MM/dd(EEE)HH:mm",
                "yy/MM/dd HH:mm:ss" // mymoe
        )){
            try {
                return new SimpleDateFormat(s, Locale.ENGLISH).parse(time);
            }catch (ParseException ignored) {}
        }
        return null;
    }

    public static ArrayList<Post> arrayMapToArrayPost(ArrayList<Map<String, String>> maps){
        if(maps==null)return null;
        ArrayList<Post> arrayList=new ArrayList<Post>();
        for(Map<String, String> map : maps){
            String url=map.get(MAP_LINK_COLUMN);
            Post p=new Post() {
                @Override
                public String getIntroduction(int words, String[] rank) {
                    return null;
                }

                @Override
                public String getDownloadUrl(int page, String boardUrl, String postId) {
                    return null;
                }

                @Override
                public void download(OnResponse onResponse, int page, String boardUrl, @Nullable String postId) {

                }

                @Override
                public Post newInstance(PostDTO dto) {
                    return null;
                }
            };
            p.setUrl(url);
            p.setTitle(map.get(MAP_TITLE_COLUMN));
            arrayList.add(p);
        }
        return arrayList;
    }

    public static ArrayList<Map<String, String>> arrayPostToArrayMap(ArrayList<Post> arrayList){
        if(arrayList==null || arrayList.size()==0)return null;
        ArrayList<Map<String, String>> maps=new ArrayList<>();
        for(Post p : arrayList){
            Map<String,String> myMap = new HashMap<String,String>();
            myMap.put(MAP_TITLE_COLUMN,p.getTitle(0));
            myMap.put(MAP_LINK_COLUMN,p.getUrl());
            maps.add(myMap);
        }
        return maps;
    }

    public static Post getPostModel(String boardUrl, boolean isBoard){
        return getPostModel(boardUrl,isBoard,false);
    }

    public static Post getPostModel(String boardUrl, boolean isBoard,boolean newInstanceWhenNull) {
        Post postModel = null;
        for(Host host : getHosts()){
            ArrayList<Post> boards=getBoards(host);
            if(boards==null || boards.size()==0)continue;
            for(Post board:boards){
                if(boardUrl.contains(board.getUrl())){
                    for (Map map : host.getSubHosts()) {
                        if (boardUrl.contains(map.get(MAP_HOST_COLUMN).toString())) {
                            postModel=(Post)map.get(MAP_BOARD_MODEL_COLUMN);
                            if(!isBoard && postModel!=null)postModel=postModel.getReplyModel();
                            break;
                        }
                    }
                }
            }
        }
        if(postModel==null && newInstanceWhenNull){
            postModel=new Post() {
                @Override
                public String getIntroduction(int words, String[] rank) {
                    return null;
                }

                @Override
                public String getDownloadUrl(int page, String boardUrl, String postId) {
                    return null;
                }

                @Override
                public void download(OnResponse onResponse, int page, String boardUrl, String postId) {}

                @Override
                public Post newInstance(PostDTO dto) {
                    return null;
                }
            };
        }
        return postModel;
    }
}
