package br.com.zeno.appope

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso

class TattooAdapter (
        val tattoos: List<Tattoo>,
        val onClick: (Tattoo) -> Unit): RecyclerView.Adapter<TattooAdapter.TattooViewHolder>() {

    class TattooViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg : ImageView
        var cardProgress: ProgressBar
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.findViewById<ImageView>(R.id.cardImg)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.findViewById<CardView>(R.id.card_tattoos)
        }
    }


    override fun getItemCount() = this.tattoos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TattooViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_tattoo, parent, false)
        val holder = TattooViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: TattooViewHolder, position: Int) {
        val context = holder.itemView.context
        val tattoo = tattoos[position]

        holder.cardNome.text = tattoo.titulo
        holder.cardProgress.visibility = View.VISIBLE

        Picasso.with(context).load(tattoo.img).fit().into(holder.cardImg,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        holder.cardProgress.visibility = View.GONE
                    }

                    override fun onError() {
                        holder.cardProgress.visibility = View.GONE
                    }
                })
        holder.itemView.setOnClickListener {onClick(tattoo)}
    }
}