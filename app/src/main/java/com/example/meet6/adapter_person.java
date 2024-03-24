package com.example.meet6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class adapter_person extends ArrayAdapter<person> {
    Context context;
    ArrayList<person> personArrayList;



    public adapter_person(@NonNull Context context, ArrayList<person> personArrayList) {
        super(context,R.layout.list_row,personArrayList );
        this.context = context;
        this.personArrayList = personArrayList;


    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null){
            LayoutInflater Inflater= LayoutInflater.from(context);
            convertView=Inflater.inflate(R.layout.list_row,null);
        }

        ImageView img =convertView.findViewById(R.id.img);
        TextView txt_name=convertView.findViewById(R.id.txt_name);
        TextView txt_dis=convertView.findViewById(R.id.txt_dis);
        Button btn_delet=convertView.findViewById(R.id.btn_delet);

        img.setImageResource(personArrayList.get(position).getImg());
        txt_name.setText(personArrayList.get(position).getName());
        txt_dis.setText(personArrayList.get(position).getAge());


        txt_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(adapter_person.this.getContext(), txt_name.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        btn_delet.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               personArrayList.remove(position);
               notifyDataSetChanged();

           }
       });

        return convertView;
    }
}
