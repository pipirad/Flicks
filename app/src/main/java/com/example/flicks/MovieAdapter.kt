package com.example.flicks

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flicks.databinding.MovieItem2Binding
import com.example.flicks.databinding.MovieItemBinding

class MovieAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mMovie = ArrayList<Movie>()

    fun setDataList(data: ArrayList<Movie>){
        this.mMovie.addAll(data)
    }

    inner class ViewHolder (private val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (item: Movie){
            binding.movie = item
        }
    }

    inner class ViewHolder2 (private val binding: MovieItem2Binding): RecyclerView.ViewHolder(binding.root){
        fun bind (item: Movie){
            binding.movie = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType==1) ViewHolder(MovieItemBinding.inflate(inflater))
            else ViewHolder2(MovieItem2Binding.inflate(inflater))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)){
            1 -> (holder as ViewHolder).bind(mMovie[position])
            -1 -> (holder as ViewHolder2).bind(mMovie[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovie.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (mMovie[position].vote_average>=7.5) -1 else 1 // -1 --> backdrop_path
    }

    companion object{
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(poster_image: ImageView, url: String){
            Glide.with(poster_image)
                .load("https://image.tmdb.org/t/p/w500${url}")
                .override(600,800)
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(poster_image)
        }

        @JvmStatic
        @BindingAdapter("loadBackdrop")
        fun loadBackdrop(backdropImage: ImageView, url: String){
            Glide.with(backdropImage)
                .load("https://image.tmdb.org/t/p/w500${url}")
                .override(1400,800)
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(backdropImage)
        }
    }

}