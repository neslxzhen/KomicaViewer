package self.nesl.komicaviewer.ui.adapter;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import self.nesl.komicaviewer.R;
import self.nesl.komicaviewer.model.Post;
import static self.nesl.komicaviewer.util.Utils.print;

public class PostlistAdapter extends RecyclerView.Adapter<PostlistAdapter.PostlistViewHolder> {
    private ArrayList<Post> postlist;
    private ItemOnClickListener callBack;
    private Fragment fragment;

    public PostlistAdapter( Fragment fragment,ItemOnClickListener callBack) {
        this.postlist =  new ArrayList<Post>();
        this.callBack = callBack;
        this.fragment=fragment;
    }

    public class PostlistViewHolder extends RecyclerView.ViewHolder{
        private TextView txtPostId;
        private TextView txtPostInd;
        private TextView txtReplyCount;
        private TextView txtPoster;
        private TextView txtTime;
        private WebView webView;

        PostlistViewHolder(View v) {
            super(v);
            txtTime = v.findViewById(R.id.txtTime);
            txtReplyCount = v.findViewById(R.id.txtReplyCount);
            txtPostId = v.findViewById(R.id.txtId);
            txtPoster = v.findViewById(R.id.txtPoster);
            webView=v.findViewById(R.id.webView);
//            itemView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        }
    }

    @NonNull
    @Override
    public PostlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new PostlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostlistViewHolder holder, final int i) {
        final Post post = postlist.get(i);

        // other
        holder.txtPostId.setText("No." + post.getPostId());
        holder.txtReplyCount.setText("回應:" + post.getReplyCount());
        holder.txtPoster.setText(post.getPoster());
        holder.txtTime.setText(post.getTimeStr());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.itemOnClick(post);
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        fragment.getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        // todo
//        int width=holder.itemView.getWidth();
//        print(this,"width",width+"");

        Element quoteEle=post.getQuoteElement();
        for(Element imgEle: quoteEle.select("img")){
            imgEle.attr("width", (width/3)+"");
        }
        holder.webView.setInitialScale(250);
        holder.webView.loadDataWithBaseURL("",quoteEle.html(),"text/html","UTF-8","");
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return postlist.size();
    }

    public void addAllPost(ArrayList<Post> postlist) {
        LinkedHashSet set=  new LinkedHashSet<>();
        set.addAll(this.postlist);
        set.addAll(postlist);
        this.postlist.addAll(set);
    }

    public void setPostlist(ArrayList<Post> postlist) {
        this.postlist=postlist;
    }

    public void addThreadpost(Post post) {
        if(!postlist.contains(post))postlist.add(0, post);
    }

    public void clear() {
        postlist.clear();
    }

    public interface ItemOnClickListener {
        public void itemOnClick(Post post);
    }
}