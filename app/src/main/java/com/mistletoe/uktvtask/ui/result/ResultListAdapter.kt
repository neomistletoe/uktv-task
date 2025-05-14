package com.mistletoe.uktvtask.ui.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mistletoe.uktvtask.R
import com.mistletoe.uktvtask.data.model.Film
import com.mistletoe.uktvtask.data.model.Transportation
import com.mistletoe.uktvtask.databinding.ItemFilmBinding
import com.mistletoe.uktvtask.databinding.ItemTransportationBinding

class InfoListAdapter(private val resultList: List<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val FILM_VIEW_TYPE = 1
        private const val TRANSPORTATION_VIEW_TYPE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            FILM_VIEW_TYPE -> FilmViewHolder(ItemFilmBinding.inflate(inflater, parent, false))
            TRANSPORTATION_VIEW_TYPE -> TransportationViewHolder(
                ItemTransportationBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )

            else -> error("Unsupported view type: $viewType")
        }
    }

    override fun getItemCount(): Int = resultList.size

    override fun getItemViewType(position: Int): Int = when (resultList[position]) {
        is Film -> FILM_VIEW_TYPE
        is Transportation -> TRANSPORTATION_VIEW_TYPE
        else -> error("Unknown item type at position $position")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FilmViewHolder -> holder.bind(resultList[position] as Film)
            is TransportationViewHolder -> holder.bind(resultList[position] as Transportation)
            else -> error("Unknown ViewHolder type")
        }
    }

    class FilmViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film) = with(binding) {
            textTitle.text = itemView.context.getString(R.string.film_title, film.title)
            textEpisodeId.text =
                itemView.context.getString(R.string.film_episode_id, film.episodeId)
            textDirector.text = itemView.context.getString(R.string.film_director, film.director)
            textProducer.text = itemView.context.getString(R.string.film_producer, film.producer)
            textReleaseDate.text =
                itemView.context.getString(R.string.film_release_date, film.releaseDate)
            textOpeningCrawl.text =
                itemView.context.getString(R.string.film_opening_crawl, film.openingCrawl)
        }
    }

    class TransportationViewHolder(private val binding: ItemTransportationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transportation: Transportation) = with(binding) {
            textName.text = itemView.context.getString(R.string.trans_name, transportation.name)
            textModel.text = itemView.context.getString(R.string.trans_model, transportation.model)
            textManufacturer.text =
                itemView.context.getString(R.string.trans_manufacturer, transportation.manufacturer)
            textCredits.text =
                itemView.context.getString(R.string.trans_credits, transportation.costInCredits)
            textLength.text =
                itemView.context.getString(R.string.trans_length, transportation.length)
            textCrew.text = itemView.context.getString(R.string.trans_crew, transportation.crew)
            textPassengers.text =
                itemView.context.getString(R.string.trans_passengers, transportation.passengers)
            textCargoCapacity.text =
                itemView.context.getString(R.string.trans_cargo, transportation.cargoCapacity)
        }
    }
}