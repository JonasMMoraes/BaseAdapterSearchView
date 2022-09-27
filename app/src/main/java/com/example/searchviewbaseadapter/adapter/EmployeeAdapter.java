package com.example.searchviewbaseadapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.example.searchviewbaseadapter.R;
import com.example.searchviewbaseadapter.model.Employee;

import java.util.ArrayList;

public class EmployeeAdapter extends BaseAdapter implements Filterable {

    public Context context;
    public ArrayList<Employee> employeesFiltrados;
    public ArrayList<Employee> employeesOriginal;

    public EmployeeAdapter(Context context, ArrayList<Employee> employees) {
        super();
        this.context = context;
        this.employeesFiltrados = employees;
    }

    class EmployeeSuporte {
        TextView nome;
        TextView idade;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return employeesFiltrados.size();
    }

    @Override
    public Object getItem(int i) {
        return employeesFiltrados.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        EmployeeSuporte employeeSuporte;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_listview_main, viewGroup, false);
            employeeSuporte = new EmployeeSuporte();
            employeeSuporte.nome = (TextView) view.findViewById(R.id.txtNome);
            employeeSuporte.idade = (TextView) view.findViewById(R.id.txtIdade);
            view.setTag(employeeSuporte);
        } else {
            employeeSuporte = (EmployeeSuporte) view.getTag();
        }

        employeeSuporte.nome.setText(employeesFiltrados.get(i).getNome());
        employeeSuporte.idade.setText(String.valueOf(employeesFiltrados.get(i).getIdade()));

        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults retorno = new FilterResults();
                final ArrayList<Employee> resultado = new ArrayList<Employee>();

                if (employeesOriginal == null) {
                    employeesOriginal = employeesFiltrados;
                }

                if (charSequence != null) {
                    if (employeesOriginal != null && employeesOriginal.size() > 0) {
                        for (final Employee employee : employeesOriginal) {
                            if (employee.getNome().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                                resultado.add(employee);
                            }
                        }
                    }
                    retorno.values = resultado;
                }
                return retorno;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                employeesFiltrados = (ArrayList<Employee>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
