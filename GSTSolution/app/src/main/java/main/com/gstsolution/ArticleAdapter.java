package main.com.gstsolution;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by user on 05/01/2018.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder>{

    private static List<ArticleData> list = new ArrayList<>();
    private static List<ArticleHolder> hlist;
    private Bitmap bmp;
    String [] urls;
    String htmlText = " %s ";
    String addbookmarkURL = IPaddress.ip+"addbookmark.php";

    public ArticleAdapter(List<ArticleData> list){
        this.list = list;
        urls = new String[list.size()];
        for(int i=0;i<list.size();i++){
            urls[i] = "0";
        }
        hlist = new ArrayList<ArticleHolder>();
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.articlecard, parent, false);
        return new ArticleAdapter.ArticleHolder(itemview);
    }

    @Override
    public void onBindViewHolder(final ArticleHolder holder, int position) {
        final ArticleData ad = list.get(position);
        System.out.print(ad.image);
        holder.head.setText(ad.heading);
        holder.body.setText(ad.body);
        if(!ad.image.equals("no image")) {
            hlist.add(holder);
            if(UserData.type.equals("3")) {
                urls[position] = IPaddress.ip + "articles/" + ad.image;
            }else if(UserData.type.equals("1")){
                urls[position] = IPaddress.ip + "news/" + ad.image;
            }
            //new GetImage().execute(IPaddress.ip + "articles/"+ad.image+"@"+position);
        }
        if(position==list.size()-1){
            new GetImage().execute(urls);
        }
        holder.bmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(v.getContext());
                ab.setTitle("Add bookmark");
                ab.setMessage("Enter name to add bookmark");
                final EditText edit = new EditText(v.getContext());
                edit.setInputType(InputType.TYPE_CLASS_TEXT);
                edit.setText(ad.heading);
                ab.setView(edit);

                ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = edit.getText().toString();
                        if(!name.isEmpty()) {
                            addBookmark(name,ad.id,v,holder.bmark);
                        }else{
                            Toast.makeText(v.getContext(), "Please enter a name to add bookmark!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog a = ab.create();
                a.show();
            }
        });
        holder.exapnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.exapnd.setText(holder.body.isExpanded() ? "Read more" : "Collapse");
                holder.body.toggle();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ArticleHolder extends RecyclerView.ViewHolder{

        protected ImageView img;
        protected TextView head;
        protected ExpandableTextView body;
        protected ImageView bmark;
        protected TextView exapnd;

        public ArticleHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgGSTarticlesImage);
            head = (TextView) itemView.findViewById(R.id.cardGSTarticlesHeading);
            body = (ExpandableTextView) itemView.findViewById(R.id.cardGSTarticlesBody);
            bmark = (ImageView) itemView.findViewById(R.id.imgBookmark);
            exapnd = (TextView) itemView.findViewById(R.id.cardGSTarticlesBodyExapnd);
        }
    }

    class GetImage extends AsyncTask<String, Void, List<Bitmap>> {

        int p= 0;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List b) {
            super.onPostExecute(b);
            for(int i=0;i<hlist.size();i++){
                ArticleHolder al = hlist.get(i);
                al.img.setImageBitmap((Bitmap) b.get(i));
            }

        }

        @Override
        protected List<Bitmap> doInBackground(String... params) {
            List<Bitmap> images = new ArrayList<>();
            for(int i=0;i<params.length && !params[i].equals("0"); i++) {
                //String[] temp = params[0].split("@");
                String id = params[i];
                //p = Integer.parseInt(temp[1]);
//                String add = "http://10.0.3.2/Smart_Institute/images/image1.jpg";
                URL url = null;
                Bitmap image1 = null;
                try {
                    url = new URL(id);
                    image1 = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    images.add(image1);
                    Log.d("Result", String.valueOf(image1));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return images;
        }

    }

    public void addBookmark(String name, String id, final View v, final ImageView im){
        RequestParams params = new RequestParams();
        params.put("bid",id);
        params.put("type",UserData.type);
        params.put("uid",UserData.id);
        params.put("name",name);

        final ProgressDialog pDialog = new ProgressDialog(v.getContext());
        pDialog.setMessage("Adding bookmark");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(addbookmarkURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.dismiss();
                String res = new String(responseBody);
                if(res.equals("200")){
                    Toast.makeText(v.getContext(), "Bookmark added!", Toast.LENGTH_SHORT).show();
                    im.setBackgroundColor(Color.BLUE);
                }else if(res.equals("202")){
                    Toast.makeText(v.getContext(), "Bookmark already added!", Toast.LENGTH_SHORT).show();
                    im.setBackgroundColor(Color.BLUE);
                }else{
                    Toast.makeText(v.getContext(), "Bookmark failed! Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                Toast.makeText(v.getContext(), "Bookmark failed! Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
