package com.wf.lifecycle.room;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wf.lifecycle.R;

import java.util.List;

/**
 * @author : wf
 * @date : 2021年11月10日 15:29
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<Student> mStudents;

    public StudentAdapter(List<Student> students) {
        mStudents = students;
    }

    public void setStudents(List<Student> students) {
        mStudents.clear();
        mStudents = students;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentAdapter.ViewHolder holder, int position) {
        Student student = mStudents.get(position);
        holder.mId.setText(String.valueOf(student.id));
        holder.mName.setText(student.name);
        holder.mAge.setText(String.valueOf(student.age));
    }

    @Override
    public int getItemCount() {
        return mStudents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mName;
        private final TextView mAge;
        private final TextView mId;

        public ViewHolder(View itemView) {
            super(itemView);
            mId = itemView.findViewById(R.id.tx_id);
            mName = itemView.findViewById(R.id.tx_name);
            mAge = itemView.findViewById(R.id.tx_age);
        }
    }
}