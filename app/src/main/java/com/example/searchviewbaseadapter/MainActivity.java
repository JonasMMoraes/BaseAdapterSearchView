package com.example.searchviewbaseadapter;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SearchView;
import com.example.searchviewbaseadapter.adapter.EmployeeAdapter;
import com.example.searchviewbaseadapter.model.Employee;

import java.util.ArrayList;

public class MainActivity extends Activity implements SearchView.OnQueryTextListener {


    private SearchView mSearchView; //SerchView do layout
    private ListView mListView; // ListView do layout
    private ArrayList<Employee> employees; // arrayList do modelo que vou filtrar
    private EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchView = (SearchView) findViewById(R.id.SerchView);
        mListView = (ListView) findViewById(R.id.ListviewMain);

        employees = new ArrayList<Employee>();
        employees.add(new Employee("ABC", 24));
        employees.add(new Employee("ACF", 25));
        employees.add(new Employee("AOD", 26));
        employees.add(new Employee("EFG", 28));

        employeeAdapter = new EmployeeAdapter(MainActivity.this, employees);
        mListView.setAdapter(employeeAdapter);
        mListView.setTextFilterEnabled(true);
        configuraSerchView();
    }

    private void configuraSerchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Pesquisa...");
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (TextUtils.isEmpty(s)) {
            mListView.clearTextFilter();
        } else {
            mListView.setFilterText(s);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }
}