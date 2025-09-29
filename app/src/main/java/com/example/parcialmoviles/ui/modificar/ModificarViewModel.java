package com.example.parcialmoviles.ui.modificar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parcialmoviles.ui.modelo.Producto;
import com.example.parcialmoviles.ui.modelo.ProductosViewModel;

public class ModificarViewModel extends ViewModel {

    private final ProductosViewModel productosViewModel;
    private final MutableLiveData<Producto> productoSeleccionado = new MutableLiveData<>();
    private final MutableLiveData<String> mensaje = new MutableLiveData<>();

    public ModificarViewModel(ProductosViewModel sharedViewModel) {
        this.productosViewModel = sharedViewModel;
    }

    public LiveData<Producto> getProductoSeleccionado() { return productoSeleccionado; }
    public LiveData<String> getMensaje() { return mensaje; }

    public void buscarProducto(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            mensaje.setValue("Ingrese un código");
            return;
        }

        Producto p = productosViewModel.buscarProductoPorCodigo(codigo.trim());
        if (p != null) {
            productoSeleccionado.setValue(p);
        } else {
            mensaje.setValue("Producto no encontrado");
        }
    }
}
