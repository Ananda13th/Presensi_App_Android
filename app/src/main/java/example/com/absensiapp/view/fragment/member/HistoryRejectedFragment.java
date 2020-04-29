package example.com.absensiapp.view.fragment.member;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import example.com.absensiapp.R;
import example.com.absensiapp.model.OverrideHistoryRespListModel;
import example.com.absensiapp.view.adapter.OverrideHistoryAdapter;
import example.com.absensiapp.viewmodel.OverrideViewModel;

public class HistoryRejectedFragment extends Fragment {
    private OverrideViewModel overrideViewModel = new OverrideViewModel();
    private String userid;
    private OverrideHistoryAdapter overrideHistoryAdapter = new OverrideHistoryAdapter("Ditolak");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_override_rejected, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences = this.requireActivity().getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        userid = sharedPreferences.getString("UserId", "");
        Log.d("USERID", userid);
        setOverrideHistory();
        setRecycleView();
    }

    private void setOverrideHistory() {
        overrideViewModel.getOverrideHistory(userid).observe(this, new Observer<OverrideHistoryRespListModel>() {
            @Override
            public void onChanged(OverrideHistoryRespListModel overrideHistoryRespListModel) {
                overrideHistoryAdapter.setOverrideHistory(overrideHistoryRespListModel.getResetPassList());
            }
        });
    }

    private void setRecycleView() {
        RecyclerView recyclerView = getView().findViewById(R.id.override_history_recycleview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(overrideHistoryAdapter);
    }

}
