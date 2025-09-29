package com.example.parcialmoviles.ui.listar;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parcialmoviles.ui.modelo.Producto;
import com.example.parcialmoviles.ui.modelo.ProductosViewModel;

import java.util.List;

public class ListarViewModel extends ViewModel {

    private final MediatorLiveData<List<Producto>> productos = new MediatorLiveData<>();
    private boolean initialized = false;

    public LiveData<List<Producto>> getProductos() { return productos; }

    public void initWithSharedViewModel(ProductosViewModel shared) {
        if (initialized || shared == null) return;
        productos.addSource(shared.getProductos(), lista -> productos.setValue(lista));
        initialized = true;
    }
}