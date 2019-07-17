
import android.R
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.model.RetroPhoto

import android.R
import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.TextView

import com.example.retrofit.model.RetroPhoto
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class CustomAdapter(private val context: Context, private val dataList: List<RetroPhoto>) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        var txtTitle: TextView = mView.findViewById(R.id.title)
        private val coverImage: ImageView = mView.findViewById(R.id.coverImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.custom_row, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.txtTitle.text = dataList[position].title

        val builder = Picasso.Builder(context)
        builder.downloader(OkHttp3Downloader(context))
        builder.build().load(dataList[position].thumbnailUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}