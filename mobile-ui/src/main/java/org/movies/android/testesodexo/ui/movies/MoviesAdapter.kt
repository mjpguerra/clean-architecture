package org.movies.android.testesodexo.ui.movies

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.movies.android.testesodexo.data.movies.Movie
import org.movies.android.testesodexo.ui.R

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var Movies: List<Movie> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Movie = Movies[position]
        holder.nameText.text = Movie.nomeFilme
        holder.titleText.text = Movie.ano

       // Glide.with(holder.itemView.context)
       //         .load(Movie.avatar)
        //        .apply(RequestOptions.circleCropTransform())
        //        .into(holder.avatarImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return Movies.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       // var avatarImage: ImageView
        var nameText: TextView
        var titleText: TextView

        init {
          //  avatarImage = view.findViewById(R.id.image_avatar)
            nameText = view.findViewById(R.id.text_name)
            titleText = view.findViewById(R.id.text_title)
        }
    }

}