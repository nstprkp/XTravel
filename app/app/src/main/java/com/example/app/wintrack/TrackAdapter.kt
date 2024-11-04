import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.wintrack.Track

class TrackAdapter(private val trackList: List<Track>, var context: Context) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val startLocation: TextView = itemView.findViewById(R.id.startLocation)
        val endLocation: TextView = itemView.findViewById(R.id.endLocation)
        val stopsContainer: LinearLayout = itemView.findViewById(R.id.stopsContainer)
        val stopsLabel: TextView = itemView.findViewById(R.id.stopsLabel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.track_item, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = trackList[position]

        holder.startLocation.text = "Начало маршрута: ${track.startLocation}"
        holder.endLocation.text = "Конец маршрута: ${track.endLocation}"

        holder.stopsContainer.removeAllViews()

        if (track.stops.isNotEmpty()) {
            holder.stopsLabel.visibility = View.VISIBLE
            for (stop in track.stops.take(5)) {
                val stopTextView = TextView(holder.itemView.context)
                stopTextView.text = "- $stop"
                stopTextView.setTextColor(holder.itemView.context.getColor(R.color.gray))
                stopTextView.textSize = 14f
                holder.stopsContainer.addView(stopTextView)
            }
        } else {
            holder.stopsLabel.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = trackList.size
}
