package com.example.parcialmoviles.ui.modelo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductosViewModel extends ViewModel {

    private final MutableLiveData<List<Producto>> productosLive = new MutableLiveData<>(new ArrayList<>());

    private final MutableLiveData<String> mensajeLive = new MutableLiveData<>();

    public LiveData<List<Producto>> getProductos() {
        return productosLive;
    }


    public LiveData<String> getMensaje() {
        return mensajeLive;
    }


    public void agregarProducto(String codigo, String descripcion, String precioStr) {

        if (codigo == null || codigo.trim().isEmpty() ||
                descripcion == null || descripcion.trim().isEmpty() ||
                precioStr == null || precioStr.trim().isEmpty()) {
            mensajeLive.setValue("Complete todos los campos");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            mensajeLive.setValue("Precio inválido");
            return;
        }


        List<Producto> currentList = productosLive.getValue();
        for (Producto p : currentList) {
            if (p.getCodigo().equalsIgnoreCase(codigo.trim())) {
                mensajeLive.setValue("Código ya existe");
                return;
            }
        }


        List<Producto> nuevaLista = new ArrayList<>(currentList);
        nuevaLista.add(new Producto(codigo.trim(), descripcion.trim(), precio));


        Collections.sort(nuevaLista, Comparator.comparing(Producto::getDescripcion, String.CASE_INSENSITIVE_ORDER));

        productosLive.setValue(nuevaLista);
        mensajeLive.setValue("OK");
    }


    public Producto buscarProductoPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) return null;

        List<Producto> lista = productosLive.getValue();
        if (lista == null) return null;

        for (Producto p : lista) {
            if (p.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return p;
            }
        }
        return null;
    }

    public void modificarProducto(String codigoOriginal, String codigoNuevo, String descripcion, String precioStr) {

        if (codigoNuevo == null || codigoNuevo.trim().isEmpty() ||
                descripcion == null || descripcion.trim().isEmpty() ||
                precioStr == null || precioStr.trim().isEmpty()) {
            mensajeLive.setValue("Complete todos los campos");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            mensajeLive.setValue("Precio inválido");
            return;
        }


        List<Producto> lista = new ArrayList<>(productosLive.getValue());
        Producto productoEncontrado = null;


        for (Producto p : lista) {
            if (p.getCodigo().equalsIgnoreCase(codigoOriginal)) {
                productoEncontrado = p;
                break;
            }
        }

        if (productoEncontrado == null) {
            mensajeLive.setValue("Producto no encontrado");
            return;
        }


        if (!codigoOriginal.equalsIgnoreCase(codigoNuevo)) {
            for (Producto p : lista) {
                if (p.getCodigo().equalsIgnoreCase(codigoNuevo.trim())) {
                    mensajeLive.setValue("Código ya existe");
                    return;
                }
            }
        }


        productoEncontrado.setCodigo(codigoNuevo.trim());
        productoEncontrado.setDescripcion(descripcion.trim());
        productoEncontrado.setPrecio(precio);


        Collections.sort(lista, Comparator.comparing(Producto::getDescripcion, String.CASE_INSENSITIVE_ORDER));

        productosLive.setValue(lista);
        mensajeLive.setValue("OK");
    }
}
