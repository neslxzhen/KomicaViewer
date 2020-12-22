package self.nesl.komicaviewer.models.fb;

import java.util.HashMap;
import java.util.Map;

import self.nesl.komicaviewer.R;
import self.nesl.komicaviewer.models.Host;
import self.nesl.komicaviewer.models.komica.sora.SoraBoardRequest;
import self.nesl.komicaviewer.models.komica.sora.SoraThreadRequest;

public class FBHost extends Host {
    public FBHost(){
        setIcon( R.drawable.ic_menu_slideshow);
    }

    @Override
    public String getHost() {
        return "facebook.com";
    }

    @Override
    public void downloadBoardlist(OnResponse onResponse) {

    }

    @Override
    public Map[] getRequests() {
        return new Map[]{
                new HashMap<String, Object>(){{
                    put(Host.MAP_HOST_COLUMN, "facebook.com");
                    put(Host.MAP_BOARD_MODEL_COLUMN, FBBoardRequest.class);
                    put(Host.MAP_POST_MODEL_COLUMN, FBThreadRequest.class);
                }},
        };
    }
}
