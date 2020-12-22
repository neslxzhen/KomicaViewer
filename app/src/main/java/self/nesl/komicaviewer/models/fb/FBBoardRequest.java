package self.nesl.komicaviewer.models.fb;

import android.os.Bundle;

import self.nesl.komicaviewer.models.Parser;
import self.nesl.komicaviewer.models.Request;

public class FBBoardRequest extends Request {
    public FBBoardRequest(String url) {
        super(url);
    }

    @Override
    public Class<? extends Parser> getPostParserClass() {
        return null;
    }

    @Override
    public String getUrl(Bundle UrlParameter) {
        return null;
    }

    @Override
    public void download(OnResponse onResponse, Bundle UrlParameter) {

    }
}
