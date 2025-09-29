package com.example.parcialmoviles.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcialmoviles.ProductosAdapter;
import com.example.parcialmoviles.R;
import com.example.parcialmoviles.ui.modelo.ProductosViewModel;

public class ListarFragment extends Fragment {

    private RecyclerView rvProductos;
    private ProductosAdapter adapter;
    private ProductosViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_listar, container, false);

        rvProductos = root.findViewById(R.id.rvProductos);
        rvProductos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProductosAdapter();
        rvProductos.setAdapter(adapter);

        viewModel = new ViewModelProvider(requireActivity()).get(ProductosViewModel.class);
        viewModel.getProductos().observe(getViewLifecycleOwner(), adapter::setItems);

        return root;
    }
}
