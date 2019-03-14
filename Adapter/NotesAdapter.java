package com.apkglobal.mynotes.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apkglobal.mynotes.Database.model.Note;
import com.apkglobal.mynotes.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private Context context;
    private List<Note> list;

    public NotesAdapter(Context context, List<Note> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(context).inflate(R.layout.note_item,viewGroup,false);
        MyViewHolder holder=new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.note.setText(list.get(i).getNote());
        myViewHolder.dot.setText(Html.fromHtml("&#8226;"));
        myViewHolder.timestamp.setText(formatDate(list.get(i).getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView note;
        public TextView dot;
        public TextView timestamp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            note=itemView.findViewById(R.id.note);
            dot = itemView.findViewById(R.id.dot);
            timestamp = itemView.findViewById(R.id.timestamp);

        }
    }


    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("d MMM  hh:mm a");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }
}
