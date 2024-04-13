package com.studies.chatwiseassignment.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.studies.chatwiseassignment.R


class imageAdapter(val context: Context, val list:List<String>): RecyclerView.Adapter<imageAdapter.myviewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val view= LayoutInflater.from(context).inflate(R.layout.imageitemview,parent,false);
        return myviewholder(view);
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val  currentitem=list.get(position)
     //   Glide.with(context).load(currentitem).into(holder.image);

        if(currentitem==null){
            // Toast.makeText(context,"null",Toast.LENGTH_LONG).show()
        }else{
           Picasso.get().load(currentitem).into(holder.image);


        }
//        if(holder.image==null){
//            Toast.makeText(context,"null view",Toast.LENGTH_LONG).show()
//        }else{
//            Toast.makeText(context,"not null view",Toast.LENGTH_LONG).show()
//        }
    }

    class myviewholder(view: View):RecyclerView.ViewHolder(view){


        val image= view.findViewById<ImageView>(R.id.RVimage)

    }



}