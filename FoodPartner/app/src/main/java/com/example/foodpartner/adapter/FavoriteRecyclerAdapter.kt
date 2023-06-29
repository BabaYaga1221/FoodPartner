package com.example.foodpartner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.foodpartner.R
import com.example.foodpartner.database.FoodEntity
import com.squareup.picasso.Picasso

class FavoriteRecyclerAdapter(val context: Context, val foodList:ArrayList<FoodEntity>):RecyclerView.Adapter<FavoriteRecyclerAdapter.FavoriteViewHolder>() {

    class FavoriteViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        val foodName:TextView = view.findViewById(R.id.txtViewFoodName)
        val foodPrice:TextView = view.findViewById(R.id.txtViewPrice)
        val foodRating:TextView = view.findViewById(R.id.txtFoodRating)
        val foodImage:ImageView = view.findViewById(R.id.imgRecycleFavourite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.activity_recycler_favorite_data,parent,false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val food = foodList[position]
        holder.foodName.text = food.foodName
        holder.foodPrice.text = food.foodPrice
        holder.foodRating.text = food.foodRating
        Picasso.get().load(food.foodImg).error(R.drawable.ic_action_food).into(holder.foodImage)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}