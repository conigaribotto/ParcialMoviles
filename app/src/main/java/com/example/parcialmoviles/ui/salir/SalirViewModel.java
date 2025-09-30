package com.example.parcialmoviles.ui.salir;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SalirViewModel extends ViewModel {

    private final MutableLiveData<Boolean> salir = new MutableLiveData<>(false);

    public LiveData<Boolean> getSalir() {
        return salir;
    }
    public void confirmarSalir() {
        salir.setValue(true);
    }
    public void cancelarSalir() {
        salir.setValue(false);
    }
}
