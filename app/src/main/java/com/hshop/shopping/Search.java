package com.hshop.shopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.hshop.R;
import com.hshop.adapter.MobileOS1;
import com.hshop.adapter.Phone1;
import com.hshop.adapter.RecyclerAdapter1;
import com.hshop.models.AllOrder;
import com.hshop.models.ProductName;
import com.hshop.rest.Config;
import com.hshop.rest.RestClient;
import com.hshop.utils.NetworkUtils;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Nilay on 12-Apr-17.
 */

public class Search extends Fragment {
    View view;
    String status;
    SearchView searchView;
    ProductName result;
    LinearLayout linear_no_internet,emp_empty_bucket;
    List productdata;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_search, container, false);
        linear_no_internet = (LinearLayout) view.findViewById(R.id.linear_no_internet);
        emp_empty_bucket = (LinearLayout) view.findViewById(R.id.emp_empty_bucket);
        searchView=(SearchView) view.findViewById(R.id.searchView);


        searchView.setQueryHint("Search View");
        status = NetworkUtils.getConnectivityStatus(getContext());
        if (status.equals("404")) {
            linear_no_internet.setVisibility(View.VISIBLE);
        }
        else {
           // userproductpre(Config.mem_string);
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getActivity(), query, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getActivity(), newText, Toast.LENGTH_LONG).show();
                return false;
            }
        });
        return view;
    }

    private void userproductpre(String mem_string) {
        final DilatingDotsProgressBar mDilatingDotsProgressBar = (DilatingDotsProgressBar) view.findViewById(R.id.progress);
        mDilatingDotsProgressBar.showNow();
        RestClient.GitApiInterface service = RestClient.getClient();

        Call<ProductName> call = service.getProductname(mem_string);
        call.enqueue(new Callback<ProductName>() {
            @Override
            public void onResponse(Response<ProductName> response) {
                mDilatingDotsProgressBar.hideNow();
                Log.d("fgh", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                   result = response.body();
                    if (result.getStatus().equals("success")) {
                        if (result.getData() != null) {
                            if (result.getData().size() > 0) {

                            }
                        }
                    }
                    else
                    {

                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)

                }
            }
            @Override
            public void onFailure(Throwable t) {
                mDilatingDotsProgressBar.hideNow();
            }
        });
    }

    public static Fragment newInstance() {
        Search fragment = new Search();
        return fragment;
    }
}