package self.nesl.komicaviewer.model.komica.sora;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;


import java.text.MessageFormat;

import self.nesl.komicaviewer.dto.PostDTO;
import self.nesl.komicaviewer.model.Post;
import self.nesl.komicaviewer.util.UrlUtils;


import static self.nesl.komicaviewer.util.ProjectUtils.installThreadTag;
import static self.nesl.komicaviewer.util.Utils.parseChiToEngWeek;
import static self.nesl.komicaviewer.util.Utils.parseJpnToEngWeek;
import static self.nesl.komicaviewer.util.ProjectUtils.parseTime;
import static self.nesl.komicaviewer.util.Utils.print;

public class SoraPost extends Post{
    private String fsub;
    private String fcom;

//    komica.org (
//            [綜合,男性角色,短片2,寫真],
//            [新番捏他,新番實況,漫畫,動畫,萌,車],
//            [四格],
//            [女性角色,歡樂惡搞,GIF,Vtuber],
//            [蘿蔔,鋼普拉,影視,特攝,軍武,中性角色,遊戲速報,飲食,小說,遊戲王,奇幻/科幻,電腦/消費電子,塗鴉王國,新聞,布袋戲,紙牌,網路遊戲]
//            )

    public SoraPost() {
    }

    public SoraPost newInstance(PostDTO dto) {
        return (SoraPost)new SoraPost(dto).parse();
    }

    public SoraPost(PostDTO dto) {
        super(dto);
    }

    public Object parse() {
        setPicture();
        setDetail();
        setQuote();
        installPicture();
        setTitle();
        return this;
    }

    public void setDetail() {
        try {
            installDefaultDetail();
        } catch (NullPointerException e) {
            try {
                install2catDetail();
            } catch (NullPointerException | StringIndexOutOfBoundsException e2) {
                installAnimeDetail();
            }
        }
    }

    public void setPicture() {
        try {
            Element thumbImg = getPostElement().selectFirst("img");
            String originalUrl = thumbImg.parent().attr("href");
            String url=new UrlUtils(originalUrl, this.getBoardUrl()).getUrl();
            this.setPictureUrl(url);
        } catch (NullPointerException ignored) {
        }
    }

    public void installDefaultDetail() { // 綜合: https://sora.komica.org
        this.setTitle(getPostElement().select("span.title").text());
        String[] post_detail = getPostElement().selectFirst("div.post-head span.now").text().split(" ID:");
        this.setTime(parseTime(parseChiToEngWeek(post_detail[0].trim())));
        this.setPoster(post_detail[1]);
    }

    public void install2catDetail() { // 新番捏他: https://2cat.komica.org/~tedc21thc/new
        Element detailEle = getPostElement().selectFirst(String.format("label[for='%s']", getPostId()));
        Element titleEle = detailEle.selectFirst("span.title");
        if (titleEle != null) {
            this.setTitle(titleEle.text().trim());
            titleEle.remove();
        }

        String s = detailEle.text().trim();
        String[] post_detail = s.substring(1, s.length() - 1).split(" ID:");
        this.setTime(parseTime(parseJpnToEngWeek(post_detail[0].trim())));
        this.setPoster(post_detail[1]);
    }

    public void installAnimeDetail() { // 動畫: https://2cat.komica.org/~tedc21thc/anime/ 比起 2cat.komica 沒有label[for="3273507"]
        String detailStr = getPostElement().ownText();
        detailStr = detailStr.length() == 0 ? getPostElement().text() : detailStr;
        String[] post_detail = detailStr.split(" ID:");
        this.setTime(parseTime(parseChiToEngWeek(post_detail[0].substring(post_detail[0].indexOf("[") + 1).trim())));
        this.setPoster(post_detail[1].substring(0, post_detail[1].indexOf("]")));
    }

    public void addPost(Element reply_ele) {
        String reply_id = reply_ele.selectFirst(".qlink").text().replace("No.", "");
        SoraPost reply = newInstance(new PostDTO(getBoardUrl(),reply_id, reply_ele));

        Elements eles = reply_ele.select("span.resquote a.qlink");
        if (eles.size() <= 0) {
            // is main
            reply.installPreview(this, this.getPostId());
            this.addPost(this.getPostId(), reply);

        } else {
            for (Element resquote : eles) {
                String resquote_id = resquote.attr("href").replace("#r", "");
                SoraPost target = (SoraPost) this.getPost(resquote_id);
                reply.installPreview(this, resquote_id);
                target.addPost(target.getPostId(), reply);
            }
        }
    }

    void installPreview(Post parent, String target_id) {
        Post target = target_id.equals(this.getPostId()) ? this : parent.getPost(target_id);
        String context = String.format(">>%s(%s)<br>", target_id, target.getIntroduction(10, null));
        this.getQuoteElement().prepend("<font color=#2bb1ff>" + context);
        Elements resquote = this.getQuoteElement().select("span.resquote");
        resquote.next("br").remove();
        resquote.remove();
    }

    public void setTitle() {
        String title = this.getTitle(0);
        if (title!=null && title.length() > 0) {
            this.getQuoteElement().prepend(String.format("[%s]<br>", title));
        }
    }

    public void setQuote() {
        this.setQuoteElement(getPostElement().selectFirst("div.quote"));
    }

    public void installPicture() {
        String url = this.getPictureUrl();
        if (url != null) {
            if(url.endsWith(".webm")){
                getQuoteElement().append(MessageFormat.format("<br><a href=\"{0}\">{0}</a><br><video src=\"{0}\" type=\"video/webm\">",url));
            }else{
                getQuoteElement().append(MessageFormat.format("<br><a href=\"{0}\">{0}</a><br><img src=\"{0}\">",url));
            }

        }
    }

    @Override
    public String getDownloadUrl(int page, String boardUrl,String postId) {
        return boardUrl+"/pixmicat.php?res="+postId;
    }

    @Override
    public void download(OnResponse onResponse, int page, String boardUrl, String postId) {
        String url = getDownloadUrl(page,boardUrl,postId);
        print(this, "AndroidNetworking", url);
        AndroidNetworking.get(url).build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                Element thread = installThreadTag(Jsoup.parse(response).body().getElementById("threads")).selectFirst("div.thread");
                Element threadpost = thread.selectFirst("div.threadpost");

                SoraPost subPost = newInstance(new PostDTO(
                        boardUrl,
                        threadpost.attr("id").substring(1),
                        threadpost
                ));
                for (Element reply_ele : thread.select("div.reply")) {
                    subPost.addPost(reply_ele);
                }
                onResponse.onResponse(subPost);
            }

            @Override
            public void onError(ANError anError) {
                anError.printStackTrace();
            }
        });
    }

    @Override
    public String getIntroduction(int words, String[] rank) {
        String ind = getQuote().replaceAll(">>(No\\.)*[0-9]{6,} *(\\(.*\\))*", "");
        ind = ind.replaceAll(">+.+\n", "");
        if (words != 0 && ind.length() > words) {
            ind = ind.substring(0, words + 1) + "...";
        }
        return ind.trim();
    }
}
