package main.com.gstsolution;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 01/02/2018.
 */

public class MaterialAdapter extends ArrayAdapter<RecommendData> implements View.OnClickListener{
    List<RecommendData> list = new ArrayList<>();
    Context mContext;

    public MaterialAdapter(@NonNull Context context, int resource, List<RecommendData> list) {
        super(context, resource);
        mContext = context;
        this.list = list;
    }

    public static class ViewHolder{
        TextView title;
        ImageView img;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        RecommendData rd = list.get(position);
        UserData.mid = rd.id;
        UserData.rd = rd;
        Intent i = new Intent(mContext, MaterialDetail.class);
        mContext.startActivity(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final RecommendData dm = list.get(position);
        MaterialAdapter.ViewHolder viewHolder;

        View result;
        if(convertView==null){
            viewHolder = new MaterialAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.rowitem2,parent,false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.rowTitle);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.rownextbtn);

            result = convertView;

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (MaterialAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.title.setText(dm.title+" ("+dm.type+")");
        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserData.mid = dm.id;
                UserData.rd = dm;
                Intent i = new Intent(mContext, MaterialDetail.class);
                mContext.startActivity(i);
            }
        });

        return convertView;
    }
}
