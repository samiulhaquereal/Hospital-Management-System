package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hospital_management.Details_ac;
import com.example.hospital_management.R;

import java.util.List;

import model.allcountries.Countryinfo;

public class Countryadapter extends RecyclerView.Adapter<Countryadapter.Holder>{

    Context context;
    List<Countryinfo> countryinfoList;

    public Countryadapter(Context context, List<Countryinfo> countryinfoList) {
        this.context = context;
        this.countryinfoList = countryinfoList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.semplelistview, parent , false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        String flag = countryinfoList.get(position).getFlag();
        String name = countryinfoList.get(position).getName();

        holder.countryname.setText(name);
        Glide.with(context).load(flag).into(holder.countryflag);

    }

    @Override
    public int getItemCount() {
        return countryinfoList.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView countryflag;
        TextView countryname;

        public Holder(@NonNull View itemView) {
            super(itemView);

            countryflag = itemView.findViewById(R.id.imgid);
            countryname = itemView.findViewById(R.id.countryid);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Intent intent = new Intent(context, Details_ac.class);
            intent.putExtra("countryName",countryinfoList.get(getAdapterPosition()).getName());
            context.startActivity(intent);

        }
    }
}
