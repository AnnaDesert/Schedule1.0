package com.example.schedule;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schedule.utils.RecyclerTable;

public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    RecyclerView recyclerView;
    static String str="";

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPage = getArguments().getInt(ARG_PAGE);
        }
    }

    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.tablelist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        switch (mPage){
            case 1: Table.Week = Table.Mnd; break;
            case 2: Table.Week = Table.Tue; break;
            case 3: Table.Week = Table.Wed; break;
            case 4: Table.Week = Table.Th; break;
            case 5: Table.Week = Table.Fri; break;
            case 6: Table.Week = Table.Sat; break;
        }
        RecyclerTable tableAdapter = new RecyclerTable(Table.Week, Table.Week.size());
        //Log.i("muTag","Size Week: " + Table.Week.size());
        //RecyclerTable tableAdapter = new RecyclerTable(Table.Week, Table.Week.get(Table.Week.size()-1).getNumber());
        recyclerView.setAdapter(tableAdapter);
        return view;
    }
}
