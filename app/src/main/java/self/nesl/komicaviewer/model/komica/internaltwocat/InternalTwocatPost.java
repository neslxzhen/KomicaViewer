package self.nesl.komicaviewer.model.komica.internaltwocat;

import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import self.nesl.komicaviewer.model.komica.twocat.TwocatPost;

import static self.nesl.komicaviewer.util.Utils.print;

public class InternalTwocatPost extends TwocatPost {
    @Override
    public InternalTwocatPost newInstance(Bundle bundle) {
        return (InternalTwocatPost) new InternalTwocatPost(
                bundle.getString(COLUMN_POST_URL),
                bundle.getString(COLUMN_POST_ID),
                new Element("<html>").html(bundle.getString(COLUMN_THREAD))
        ).parse();
    }

    public InternalTwocatPost() {
    }

    public InternalTwocatPost(String postUrl, String postId, Element thread) {
        super(postUrl, postId, thread);
    }

    @Override
    public void installDefaultDetail() { // 綜合: https://sora.komica.org
        try {
            install2catDetail();
        } catch (NullPointerException e) {
        }
    }

    @Override
    public InternalTwocatPost parse() {
        super.setPicture();

        try {
            super.install2catDetail();
        } catch (NullPointerException | StringIndexOutOfBoundsException e2) {
            super.installAnimeDetail();
        }

        super.setQuote();
        super.setTitle();
        return this;
    }

    @Override
    public void download(Bundle bundle, OnResponse onResponse) {
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .cookieJar(new CookieJar() {
                    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url.host(), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();

        String[] arr= getUrl().split("/");
        String boardUrl="https://2cat.org/~"+arr[1];
        String pageUrl="https://2nyan.org/granblue/"+arr[2];

        print(this,"getUrl()",getUrl());

        print(this,"AndroidNetworking",getUrl());
        print(this,"AndroidNetworking-1",boardUrl);
        AndroidNetworking.get(boardUrl)
                .setOkHttpClient(okHttpClient)
                .addHeaders("Referer", "https://2nyan.org/")
                .build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                print(this,"AndroidNetworking-2",pageUrl);
                AndroidNetworking.get(pageUrl)
                        .setOkHttpClient(okHttpClient)
                        .build().getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Bundle bundle = new Bundle();
                        bundle.putString(COLUMN_THREAD, response);
                        bundle.putString(COLUMN_POST_URL, getUrl());

                        onResponse.onResponse(newInstance(bundle));
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                    }
                });
            }

            @Override
            public void onError(ANError anError) {
                anError.printStackTrace();
            }
        });
    }
}
