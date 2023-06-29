package com.example.foodpartner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodpartner.R
import com.example.foodpartner.model.QnA

class QnAAdapter(val context:Context,val itemList:ArrayList<QnA>):RecyclerView.Adapter<QnAAdapter.QnAViewHolder>() {

    class QnAViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        val question:TextView = view.findViewById(R.id.qnaQuestion)
        val answer:TextView = view.findViewById(R.id.qnaAnswer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QnAViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.qnapage_recycler_view,parent,false)
        return QnAViewHolder(view)
    }

    override fun onBindViewHolder(holder: QnAViewHolder, position: Int) {
        val qna = itemList[position]
        holder.question.text = qna.qnaQuestion
        holder.answer.text = qna.qnaAnswer

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}