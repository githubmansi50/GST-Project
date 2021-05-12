package main.com.gstsolution;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 10/01/2018.
 */

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.LinkHolder> {

    private List<LinkData> list = new ArrayList<LinkData>();

    public LinkAdapter(List<LinkData> list){
        this.list= list;
    }

    @Override
    public LinkAdapter.LinkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.linkscard, parent, false);
        return new LinkAdapter.LinkHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LinkAdapter.LinkHolder holder, int position) {
        LinkData ld = list.get(position);
        holder.name.setText(ld.name);
        String link = ld.link;
        holder.link.setText(Html.fromHtml("<a href='"+link+"'>"+link+"</a>"));
        holder.link.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LinkHolder extends RecyclerView.ViewHolder{
        protected TextView name;
        protected TextView link;
        public LinkHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.cardLinksName);
            link = (TextView) itemView.findViewById(R.id.cardLinksBodyLink);
        }
    }
}
