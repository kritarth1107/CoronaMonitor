package com.kritarthagrawal.coronamonitor.ui.list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.kritarthagrawal.coronamonitor.R;
import com.kritarthagrawal.coronamonitor.ui.report.ReportActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private Context mContext ;
    private List<CountriesStat> mData ;
    RequestOptions option;
    int[] flags = {R.drawable.ic_china,R.drawable.ic_south_korea,R.drawable.ic_italy,R.drawable.ic_iran,R.drawable.defaultflag,R.drawable.germany,R.drawable.france,R.drawable.japan,R.drawable.spain,R.drawable.ic_usa,R.drawable.singapore,R.drawable.uk,R.drawable.switzerland,R.drawable.hong_kong,R.drawable.sweden,R.drawable.norway,R.drawable.netherlands,R.drawable.kuwait,R.drawable.bahrain,R.drawable.malaysia,R.drawable.australia,R.drawable.belgium,R.drawable.thailand,R.drawable.taiwan,R.drawable.austria,R.drawable.canada,R.drawable.iraq,R.drawable.iceland,R.drawable.greece,R.drawable.ic_india,R.drawable.united_arab_emirates,R.drawable.san_marino,R.drawable.denmark,R.drawable.algeria,R.drawable.israel,R.drawable.lebanon,R.drawable.oman,R.drawable.vietnam,R.drawable.ecuador,R.drawable.czech_republic,R.drawable.finland,R.drawable.macao,R.drawable.croatia,R.drawable.portugal,R.drawable.qatar,R.drawable.palestine,R.drawable.azerbaijan,R.drawable.belarus,R.drawable.ireland,R.drawable.mexico,R.drawable.romania,R.drawable.pakistan,R.drawable.saudi_arabia,R.drawable.brazil,R.drawable.georgia,R.drawable.russia,R.drawable.senegal,R.drawable.philippines,R.drawable.egypt,R.drawable.estonia,R.drawable.new_zealand,R.drawable.chile,R.drawable.slovenia,R.drawable.indonesia,R.drawable.morocco,R.drawable.bosnia_and_herzegovina,R.drawable.hungary,R.drawable.defaultflag,R.drawable.andorra,R.drawable.armenia,R.drawable.cambodia,R.drawable.dominican_republic,R.drawable.jordan,R.drawable.latvia,R.drawable.lithuania,R.drawable.luxembourg,R.drawable.republic_of_macedonia,R.drawable.monaco,R.drawable.nepal,R.drawable.nigeria,R.drawable.sri_lanka,R.drawable.tunisia,R.drawable.ukraine,R.drawable.argentina,R.drawable.liechtenstein,R.drawable.poland,R.drawable.south_africa};
    String[] country = {"China","S. Korea","Italy","Iran","Diamond Princess","Germany","France","Japan","Spain","USA","Singapore","UK","Switzerland","Hong Kong","Sweden","Norway","Netherlands","Kuwait","Bahrain","Malaysia","Australia","Belgium","Thailand","Taiwan","Austria","Canada","Iraq","Iceland","Greece","India","UAE","San Marino","Denmark","Algeria","Israel","Lebanon","Oman","Vietnam","Ecuador","Czechia","Finland","Macao","Croatia","Portugal","Qatar","Palestine","Azerbaijan","Belarus","Ireland","Mexico","Romania","Pakistan","Saudi Arabia","Brazil","Georgia","Russia","Senegal","Philippines","Egypt","Estonia","New Zealand","Chile","Slovenia","Indonesia","Morocco","Bosnia and Herzegovina","Hungary","Afghanistan","Andorra","Armenia","Cambodia","Dominican Republic","Jordan","Latvia","Lithuania","Luxembourg","North Macedonia","Monaco","Nepal","Nigeria","Sri Lanka","Tunisia","Ukraine","Argentina","Liechtenstein","Poland","South Africa"};

    public ListAdapter(Context mContext, List<CountriesStat> mData) {
        this.mContext = mContext;
        this.mData = mData;
        //option = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.ic_broken_image);

    }

    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.listview,parent,false) ;
        final ListAdapter.MyViewHolder viewHolder = new ListAdapter.MyViewHolder(view) ;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListAdapter.MyViewHolder holder,final int position) {
        holder.countryName.setText(mData.get(position).getCountryName());
        holder.totalDeaths.setText(mData.get(position).getDeaths());
        holder.totalRegistered.setText(mData.get(position).getCases());
        for(int i=0;i<flags.length;i++){
            if(mData.get(position).getCountryName().equals(country[i])){
                holder.flagImage.setImageResource(flags[i]);
            }

        }
        holder.countryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mData.get(position).getCountryName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.fullReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ReportActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("country",mData.get(position).getCountryName());
                intent.putExtra("cases",mData.get(position).getCases());
                intent.putExtra("deaths",mData.get(position).getDeaths());
                intent.putExtra("recovered",mData.get(position).getTotalRecovered());
                intent.putExtra("newcase",mData.get(position).getNewCases());
                intent.putExtra("critical",mData.get(position).getSeriousCritical());
                intent.putExtra("newdeaths",mData.get(position).getNewDeaths());
                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.countryName)
        TextView countryName;
        @BindView(R.id.flagImage)
        ImageView flagImage;
        @BindView(R.id.totalRegistered)
        TextView totalRegistered;
        @BindView(R.id.totalDeaths)
        TextView totalDeaths;
        @BindView(R.id.countryCard)
        CardView countryCard;
        @BindView(R.id.fullReport)
        LinearLayout fullReport;




        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);




        }
    }

}
