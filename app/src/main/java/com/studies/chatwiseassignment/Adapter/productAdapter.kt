package com.studies.chatwiseassignment.Adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.studies.chatwiseassignment.Models.Product
import com.studies.chatwiseassignment.R

class productAdapter(val context:Context,val list:List<Product>):RecyclerView.Adapter<productAdapter.myviewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val view=LayoutInflater.from(context).inflate(R.layout.productitemview,parent,false);
        return myviewholder(view);
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val  currentitem=list.get(position)
        holder.disountTextView.text=currentitem.discountPercentage.toString()
        holder.similarBrandTypeText.text=currentitem.brand.toString()

        holder.title.text=currentitem.title.toString()
        holder.category.text=currentitem.category.toString()
        holder.description.text=currentitem.description.toString()
        holder.actualPriceTextView.text="Rs. "+currentitem.price.toString()
        if(currentitem.stock>0){
            holder.availabilityTextView.text="Available : In Stock"
            holder.availabilityTextView.setTextColor(Color.parseColor("#81C784"))
        }else{
            holder.availabilityTextView.text="Out Of Stock"
            holder.availabilityTextView.setTextColor(ContextCompat.getColor(context, R.color.red))
        }

        holder.unitTvOrg.text=currentitem.stock.toString()
        holder.ratingBar.rating=currentitem.rating.toFloat()
        holder.disountTextView.text=currentitem.discountPercentage.toString()+" % Off"
    //    Glide.with(context).load(currentitem.thumbnail).into(holder.productImageView)
        Picasso.get().load(currentitem.thumbnail).into(holder.productImageView);
        holder.productImageView.setOnClickListener {
          //  showMultipleImageLayout(currentitem.images)
            if(currentitem.images==null){
              //  Toast.makeText(context,"null",Toast.LENGTH_LONG).show()
            }else{
                showMultipleImageLayout(context,currentitem.images)
              //  Toast.makeText(context,"not null",Toast.LENGTH_LONG).show()
            }
        }
    }

    class myviewholder(view:View):RecyclerView.ViewHolder(view){

        val disountTextView= view.findViewById<TextView>(R.id.disountTextView)
        val similarBrandTypeText= view.findViewById<TextView>(R.id.similarBrandTypeText)
        val title= view.findViewById<TextView>(R.id.title)
        val category= view.findViewById<TextView>(R.id.category)
        val description= view.findViewById<TextView>(R.id.description)
        val actualPriceTextView= view.findViewById<TextView>(R.id.actualPriceTextView)
        val availabilityTextView= view.findViewById<TextView>(R.id.availabilityTextView)
        val unitTvOrg= view.findViewById<TextView>(R.id.unitTvOrg)
        val ratingBar= view.findViewById<RatingBar>(R.id.ratingBar)
        val productImageView= view.findViewById<ImageView>(R.id.productImageView)

    }

    fun showMultipleImageLayout( context2:Context,list: List<String>) {
//        val factory = LayoutInflater.from(context)
//        val addressLayout = factory.inflate(R.layout.product_image_slider_dialog_box, null)
//
//        val imageDialog = AlertDialog.Builder(context).create()
//        val layoutParams = WindowManager.LayoutParams()
//        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
////    imageDialog.window?.decorView?.setBackgroundColor(Color.TRANSPARENT)
//        imageDialog.window?.attributes = layoutParams
      //  imageDialog.setView(addressLayout)
        val imageDialog = Dialog(context2)
        imageDialog.setContentView(R.layout.product_image_slider_dialog_box)


        val smallImageRecycler: RecyclerView = imageDialog.findViewById(R.id.smallImageRecycler)
        val cancelImage: ImageView = imageDialog.findViewById(R.id.cancelImage)


        val adapter = imageAdapter(context2,list)
        smallImageRecycler.adapter=adapter

        imageDialog.show()



        cancelImage.setOnClickListener {
            imageDialog.dismiss()
        }



    }




}