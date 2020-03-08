package com.kritarthagrawal.coronamonitor.ui.list;

import com.kritarthagrawal.coronamonitor.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivityPresenter implements ListActivityContract.presenter {
    ListActivityContract.view listActivityView;

    public ListActivityPresenter(ListActivityContract.view listActivityView) {
        this.listActivityView = listActivityView;
    }

    @Override
    public void getList() {
        listActivityView.loadingAnimations(true);
        try{
            RetrofitClient.retrofitAPI().getList("coronavirus-monitor.p.rapidapi.com","8d2e5da70cmsh613081e63937f15p1ad053jsnaaa530bec43e").enqueue(new Callback<ListPojo>() {
                @Override
                public void onResponse(Call<ListPojo> call, Response<ListPojo> response) {
                    listActivityView.loadingAnimations(false);
                    listActivityView.listActivityCallBack("success",response.body().getCountriesStat(),response.body().getStatisticTakenAt());
                }

                @Override
                public void onFailure(Call<ListPojo> call, Throwable t) {
                    listActivityView.loadingAnimations(false);
                    listActivityView.listActivityCallBack("Please Check your Internet Connection!",null,null);
                }
            });
        }catch (Exception e){
            listActivityView.loadingAnimations(false);
            listActivityView.listActivityCallBack(e.toString(),null,null);
        }

    }
}
