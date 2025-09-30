package com.example.parcialmoviles.ui.cargar;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;

public class CargarViewModel extends AndroidViewModel {

    public CargarViewModel(@NonNull Application application) {
        super(application);
    }

    private final MutableLiveData<String> codigo = new MutableLiveData<>("");
    private final MutableLiveData<String> descripcion = new MutableLiveData<>("");
    private final MutableLiveData<String> precio = new MutableLiveData<>("");
    private final MutableLiveData<String> mensaje = new MutableLiveData<>();


    public LiveData<String> getCodigo() { return codigo; }
    public LiveData<String> getDescripcion() { return descripcion; }
    public LiveData<String> getPrecio() { return precio; }
    public LiveData<String> getMensaje() { return mensaje; }

    public void setCodigo(String c) { codigo.setValue(c); }
    public void setDescripcion(String d) { descripcion.setValue(d); }
    public void setPrecio(String p) { precio.setValue(p); }

    public boolean validarFormulario(String codigoStr, String descripcionStr, String precioStr) {
        if (codigoStr == null || codigoStr.trim().isEmpty() ||
                descripcionStr == null || descripcionStr.trim().isEmpty() ||
                precioStr == null || precioStr.trim().isEmpty()) {
            mensaje.setValue("Complete todos los campos");
            return false;
        }
        try {
            Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            mensaje.setValue("Precio inválido");
            return false;
        }
        mensaje.setValue("");
        return true;
    }
}