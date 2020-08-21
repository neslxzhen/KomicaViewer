package self.nesl.komicaviewer.model.komica._2cat;

import org.jsoup.select.Elements;

import self.nesl.komicaviewer.dto.PostDTO;
import self.nesl.komicaviewer.model.komica.sora.SoraBoard;
import self.nesl.komicaviewer.util.UrlUtils;

import static self.nesl.komicaviewer.util.Utils.print;

public class _2catBoard extends SoraBoard {

    @Override
    public String getDownloadUrl(int page, String boardUrl,String postId){
        String host=new UrlUtils(boardUrl).getHost();
        boardUrl=boardUrl.replace(host+"/~",host+"/");
        return page!=0?boardUrl+"/?page="+page:boardUrl;
    }

    public _2catBoard() {
        this.setReplyModel(new _2catPost());
    }

    public _2catBoard(PostDTO dto) {
        this.setPostId(getBoardId(dto.boardUrl));
        this.setUrl(dto.boardUrl);
        this.setPostElement(dto.postElement);
    }

    public static String getBoardId(String url){
        String boardCode= new UrlUtils(url).getPath();
        return boardCode.replace("/~","");
    }

    @Override
    public Elements getThreads(){
        return getPostElement().select("div.threadStructure");
    }
}