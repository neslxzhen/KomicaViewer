package self.nesl.komicaviewer.model.komica.twocat;
import android.os.Bundle;
import org.jsoup.nodes.Element;
import self.nesl.komicaviewer.model.komica.sora.SoraPost;

public class TwocatPost extends SoraPost {

    @Override
    public TwocatPost newInstance(Bundle bundle) {
        return (TwocatPost) new TwocatPost(
                bundle.getString(COLUMN_POST_URL),
                bundle.getString(COLUMN_POST_ID),
                new Element("<html>").html(bundle.getString(COLUMN_THREAD))
        ).parse();
    }

    public TwocatPost() {
    }

    public TwocatPost(String postUrl,String postId, Element thread) {
        super(postUrl,postId,thread);
    }

    @Override
    public SoraPost parse(){
        super.setPictures();

        try {
            super.install2catDetail();
        }catch (NullPointerException | StringIndexOutOfBoundsException e){
            super.installAnimeDetail();
        }

        super.setQuote();
        super.setTitle();
        return this;
    }
}