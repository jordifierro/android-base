package com.jordifierro.androidbase.presentation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.presentation.R;

import java.util.Collections;
import java.util.List;

public class NotesAdapter extends BaseAdapter {

    public interface OnItemClickListener {
        void onNoteItemClicked(NoteEntity note);
    }

    private Context context;
    private OnItemClickListener listener;
    private List<NoteEntity> notes;

    public NotesAdapter(Context context) {
        this.context = context;
        this.notes = Collections.emptyList();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setNotes(List<NoteEntity> notes) {
        this.notes = notes;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int arg0) {
        return notes.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return notes.get(arg0).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.view_cell_note, parent, false);
        }

        ((TextView)view.findViewById(R.id.title)).setText(notes.get(position).getTitle());
        view.setTag(position);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                listener.onNoteItemClicked(notes.get((int)arg0.getTag()));
            }
        });

        return view;
    }

}
